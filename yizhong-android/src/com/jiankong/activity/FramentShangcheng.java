package com.jiankong.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jiankong.httpservice.GetDevices;
import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DetailsDao;
import com.jiankong.local.SQLite.DeviceDao;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;

public class FramentShangcheng extends Fragment implements
		SwipeRefreshLayout.OnRefreshListener, OnPageChangeListener {

	private List<ImageView> imageViewList;
	private LinearLayout llPoints;
	private int previousSelectPosition = 0;
	private ViewPager mViewPager;
	private boolean isLoop = true;

	private LinearLayout ll_banche;
	private LinearLayout ll_jishou;
	private LinearLayout ll_work;
	private LinearLayout ll_chuzu;
	private LinearLayout ll_chushou;
	View view;

	private ListView lv;
	private SwipeRefreshLayout mSwipeLayout;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
		}
	};

	@Override
	public View onCreateView(android.view.LayoutInflater inflater,
			android.view.ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frament_shangcheng, null);

		mViewPager = (ViewPager) view.findViewById(R.id.shangcheng_viewpager);
		llPoints = (LinearLayout) view.findViewById(R.id.ll_shangcheng_points);

		ll_banche = (LinearLayout) view.findViewById(R.id.ll_banche);
		ll_jishou = (LinearLayout) view.findViewById(R.id.ll_jishou);
		ll_work = (LinearLayout) view.findViewById(R.id.ll_work);
		ll_chuzu = (LinearLayout) view.findViewById(R.id.ll_chuzu);
		ll_chushou = (LinearLayout) view.findViewById(R.id.ll_chushou);

		ll_banche.setOnClickListener(listener);
		ll_jishou.setOnClickListener(listener);
		ll_work.setOnClickListener(listener);
		ll_chuzu.setOnClickListener(listener);
		ll_chushou.setOnClickListener(listener);

		init();
		initView();
		initEvent();
		return view;
	}

	public void init() {

		lv = (ListView) view.findViewById(R.id.lv_shangcheng_list);
		mSwipeLayout = (SwipeRefreshLayout) view
				.findViewById(R.id.swipe_shangcheng);
		mSwipeLayout.setOnRefreshListener(FramentShangcheng.this);
		mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (isLoop) {
					SystemClock.sleep(8 * 1000);
					handler.sendEmptyMessage(0);
				}
			}
		}).start();
	}

	public void onRefresh() {
		DeviceDao dao = new DeviceDao(getActivity());
		String id = SharedPrefer.Readid(getActivity());
		String deiviceId = null;
		List<Map<String, Object>> user = dao.findDevicrlist(getActivity(), id);
		for (int i = 0; i < user.size(); i++) {
			Map<String, Object> statemap = user.get(i);
			deiviceId = (String) statemap.get("deiviceId");
		}
		try {
//			DetailsDao details = new DetailsDao(getActivity());
//			details.delete(deiviceId);
			String device = GetDevices.get(getActivity());
			if (device.equals("成功！")) {
				initEvent();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getActivity(), "刷新失败，请稍候再试", 1).show();
			return;
		}

		mSwipeLayout.setRefreshing(false);
		getFragmentManager().beginTransaction().addToBackStack(null)
				.replace(R.id.fl_feament, new FramentShangcheng()).commit();

	}

	public void initEvent() {
		System.out.println("初始化");
		String id = SharedPrefer.Readid(getActivity());
		DeviceDao dao = new DeviceDao(getActivity());
		lv.setAdapter(new SimpleAdapter(getActivity(), dao.findAllData(
				getActivity(), id), R.layout.shang_item, new String[] {
				"devicename", "number", "state", "deiviceId" }, new int[] {
				R.id.tv_shang_miaoshu, R.id.tv_shang_prise, R.id.tv_shang_data,
				R.id.tv_shang_address }));

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
					String deiviceId = (String) list.get(position).get(
							"deiviceId");
					// System.out.println("frament得到的id" + id);
					String devicename = (String) list.get(position).get(
							"devicename");
					intent.putExtra("deiviceId", deiviceId);
					intent.putExtra("devicename", devicename);
					startActivity(intent);
					break;
				}

				}
			}
		});
	}

	OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			if (v == ll_banche) {
				Intent intent = new Intent(getActivity(), ShangBanche.class);
				startActivity(intent);
			} else if (v == ll_jishou) {
				Intent intent = new Intent(getActivity(), ShangJishou.class);
				startActivity(intent);
			} else if (v == ll_work) {
				Intent intent = new Intent(getActivity(), ShangWork.class);
				startActivity(intent);
			} else if (v == ll_chuzu) {
				Intent intent = new Intent(getActivity(), ShangLease.class);
				startActivity(intent);

			} else if (v == ll_chushou) {
				Intent intent = new Intent(getActivity(), ShangLease.class);
				startActivity(intent);
			}

		}

	};

	public void initView() {

		prepareData();
		ViewPagerAdapter adapter = new ViewPagerAdapter();
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(this);
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
		return new int[] { R.drawable.guanggao1, R.drawable.guanggao2,
				R.drawable.wajueji6, };
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
