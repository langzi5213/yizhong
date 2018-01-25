package com.jiankong.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jiankong.activity.WodeSetup.receiveVersionHandler;
import com.jiankong.httpservice.GetDevices;
import com.jiankong.local.GetPhone;
import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DetailsDao;
import com.jiankong.local.SQLite.DeviceDao;
import com.jiankong.local.SQLite.UserDao;
import com.jiankong.service.WarnService;

import android.R.color;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class FramentDeviceList extends Fragment implements
		SwipeRefreshLayout.OnRefreshListener, OnPageChangeListener {

	private ListView lv;
	private SwipeRefreshLayout mSwipeLayout;

	private LinearLayout ll_ditu;
	private LinearLayout ll_jiankong;
	private LinearLayout ll_qrcode;
	private LinearLayout ll_hujiao;
	private LinearLayout ll_caiwu;

	private List<ImageView> imageViewList;
	private LinearLayout llPoints;
	private int previousSelectPosition = 0;
	private ViewPager mViewPager;
	private boolean isLoop = true;
	private Button userlist_qrcode;
	View view;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
		}
	};

	private Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				lv.setAdapter(null);
				initEvent();
				mSwipeLayout.setRefreshing(false);
				Toast.makeText(getActivity(), "刷新成功", 1).show();
				// getFragmentManager().beginTransaction().addToBackStack(null)
				// .replace(R.id.fl_feament, new FramentDeviceList())
				// .commit();
				Intent i = new Intent(getActivity(), WarnService.class);
				getActivity().startService(i);
				break;
			case 0:
				Toast.makeText(getActivity(), "刷新失败，请检查网络是否连接", 1).show();
				mSwipeLayout.setRefreshing(false);
				break;
			}
		}
	};

	@Override
	public View onCreateView(android.view.LayoutInflater inflater,
			android.view.ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frament_list_device, null);

		mViewPager = (ViewPager) view.findViewById(R.id.user_viewpager);
		llPoints = (LinearLayout) view.findViewById(R.id.ll_user_points);

		ll_ditu = (LinearLayout) view.findViewById(R.id.ll_ditu);
		ll_jiankong = (LinearLayout) view.findViewById(R.id.ll_jiankong);
		ll_qrcode = (LinearLayout) view.findViewById(R.id.ll_qrcode);
		ll_hujiao = (LinearLayout) view.findViewById(R.id.ll_hujiao);
		ll_caiwu = (LinearLayout) view.findViewById(R.id.ll_caiwu);
		ll_ditu.setOnClickListener(listener);
		ll_jiankong.setOnClickListener(listener);
		ll_qrcode.setOnClickListener(listener);
		ll_hujiao.setOnClickListener(listener);
		ll_caiwu.setOnClickListener(listener);

		init();
		initEvent();
		initView();
		return view;

	}

	public void init() {
		lv = (ListView) view.findViewById(R.id.lv_user_list);
		mSwipeLayout = (SwipeRefreshLayout) view
				.findViewById(R.id.swipe_user_list);
		mSwipeLayout.setOnRefreshListener(FramentDeviceList.this);
		mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isLoop) {
					SystemClock.sleep(10 * 1000);
					handler.sendEmptyMessage(0);
				}
			}
		}).start();
	}

	public void onRefresh() {

		String id = SharedPrefer.Readid(getActivity());
		DetailsDao details = new DetailsDao(getActivity());
		details.delete(id);
		// lv.setAdapter(null);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String device = GetDevices.get(getActivity());
					System.out.println("获取设备列表activity" + device);
					if (device.equals("成功！")) {
						handler1.sendEmptyMessage(1);
					} else if (device.equals("TOKEN错误或已失效，请重新登陆.")) {
						mSwipeLayout.setRefreshing(false);
						Intent intent = new Intent(getActivity(),
								ActivityLogin.class);
						startActivity(intent);
						getActivity().finish();
					}
				} catch (Exception e) {
					handler1.sendEmptyMessage(0);
					e.printStackTrace();
				}
			}
		}).start();

	}

	public void initEvent() {
		String id = SharedPrefer.Readid(getActivity());
		DeviceDao dao = new DeviceDao(getActivity());
		lv.setAdapter(new SimpleAdapter(getActivity(), dao.findAllData(
				getActivity(), id), R.layout.device_item, new String[] {
				"devicename", "number", "state", "deviceId" }, new int[] {
				R.id.tv_user_name, R.id.tv_user_number, R.id.tv_user_state,
				R.id.tv_user_id }));

		final List<Map<String, Object>> list = dao.findAllData(getActivity(),
				id);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id1) {
				switch (position) {
				default: {
					Intent intent = new Intent(getActivity(),
							DevicelistNumberlist.class);
					String deviceId = (String) list.get(position).get(
							"deviceId");
					String devicename = (String) list.get(position).get(
							"devicename");
					intent.putExtra("deviceId", deviceId);
					intent.putExtra("devicename", devicename);
					System.out.println("传数据" + deviceId + devicename);
					startActivity(intent);
					break;
				}

				}
			}
		});
	}

	OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			if (v == ll_ditu) {
				Intent intent = new Intent(getActivity(), DevicelistMap.class);
				startActivity(intent);
			} else if (v == ll_jiankong) {
				Toast.makeText(getActivity(), "此功能模块正在开发中，敬请期待", 1).show();
			} else if (v == ll_qrcode) {
				Intent intent = new Intent(getActivity(),
						DevicelistQRcode.class);
				startActivity(intent);
			} else if (v == ll_hujiao) {
				call();
			} else if (v == ll_caiwu) {
				Intent intent = new Intent(getActivity(), DevicelistCaiwu.class);
				startActivity(intent);
			}

		}

	};

	private void InitCall(ListView listView) {
		GetPhone phonedao = new GetPhone();
		// UserDao dao=new UserDao(getActivity());
		// System.out.println("开始获取得到的手机号码InitCall");
		List<Map<String, Object>> user = phonedao
				.getPhoneContacts(getActivity());
		listView.setAdapter(new SimpleAdapter(getActivity(), user,
				R.layout.wode_item_user, new String[] { "phone", "username" },
				new int[] { R.id.delete_user_phone, R.id.delete_user_username }));

		final List<Map<String, Object>> list = phonedao
				.getPhoneContacts(getActivity());
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id1) {
				switch (position) {
				default: {
					String phone = (String) list.get(position).get("phone");
					String username = (String) list.get(position).get(
							"username");
					Intent intent = new Intent(Intent.ACTION_CALL, Uri
							.parse("tel:" + phone));
					startActivity(intent);
					break;
				}

				}
			}
		});
	}

	public void call() {
		ListView listView = new ListView(getActivity());
		listView.setBackgroundColor(color.holo_blue_light);
		GetPhone phonedao = new GetPhone();
		// System.out.println("开始获取得到的手机号码call");
		List<Map<String, Object>> user = phonedao
				.getPhoneContacts(getActivity());
		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
		dialog.setInverseBackgroundForced(true);
		if (user == null) {
			// if (user.toString() == null) {
			dialog.setTitle("您还没有添加司机信息，请添加");

		} else {
			InitCall(listView);
			dialog.setTitle("请选择下列需要拨打的电话");
		}

		dialog.setView(listView);
		dialog.setPositiveButton("返回", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		dialog.setNegativeButton("添加", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				Intent intent = new Intent(getActivity(), WodeUser.class);
				startActivity(intent);
			}
		});
		dialog.create().show();
	}

	@SuppressWarnings("deprecation")
	public void initView() {
		prepareData();
		ViewPagerAdapter adapter = new ViewPagerAdapter();
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(FramentDeviceList.this);
		llPoints.getChildAt(previousSelectPosition).setEnabled(true);
		int n = Integer.MAX_VALUE / 2 % imageViewList.size();
		int itemPosition = Integer.MAX_VALUE / 2 - n;
		mViewPager.setCurrentItem(itemPosition);
	}

	private void prepareData() {
		imageViewList = new ArrayList<ImageView>();
		int[] imageResIDs = getImageResIDs();
		ImageView iv;
		View view;
		for (int i = 0; i < imageResIDs.length; i++) {
			iv = new ImageView(getActivity());
			iv.setBackgroundResource(imageResIDs[i]);
			imageViewList.add(iv);
			view = new View(getActivity());

			view.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.point_background));
			LayoutParams lp = new LayoutParams(3, 3);
			lp.leftMargin = 20;
			lp.height = 20;
			lp.width = 20;
			view.setLayoutParams(lp);
			view.setEnabled(false);
			llPoints.addView(view);
		}
	}

	private int[] getImageResIDs() {
		return new int[] { R.drawable.guanggao1, R.drawable.wajueji5,
				R.drawable.guanggao2, };
	}

	class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(imageViewList.get(position
					% imageViewList.size()));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			try {
				container.addView(imageViewList.get(position
						% imageViewList.size()));
				return imageViewList.get(position % imageViewList.size());
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int position) {
		llPoints.getChildAt(previousSelectPosition).setEnabled(false);
		llPoints.getChildAt(position % imageViewList.size()).setEnabled(true);
		previousSelectPosition = position % imageViewList.size();
	}

	public void setListener() {

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		isLoop = false;
	}

}
