package com.waji.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClientOption;
import com.waji.activity.R;
import com.waji.activity.R.id;
import com.waji.dao.CreatQRcode;
import com.waji.dao.StartVideo;
import com.waji.dao.TakePicture;
import com.waji.local.SaveNumber;
import com.waji.local.SaveXYZ;
import com.waji.local.SharedPrefer;
import com.waji.http.SendImage;
import com.waji.utils.LocationService;
import com.waji.interfac.MyLeanCloudApp;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.StatFs;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity implements OnPageChangeListener {

	private TextView tv_number;
	private TextView tv_location;
	private TextView tv_GyroscopeX;
	private TextView tv_GyroscopeY;
	private TextView tv_GyroscopeZ;
	private TextView tv_state;
	private EditText editText1;

	private SensorManager sensorManager;
	private Sensor sensor;
	private LocationService locService;
	private ImageView im_qrcode;
	private SurfaceView mSurfaceview;

	int number;
	TakePicture picture;
	StartVideo video1;
	Camera camera1;

	private Button bt_qiandao;
	private Button bt_qiantui;
	private Button bt_baoyang;

	private Handler locHander = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int what = msg.getData().getInt("what");
			if (what == 1) {
				try {
					BDLocation location = msg.getData().getParcelable("loc");
					final double longitude = location.getLongitude();
					final double latitude = location.getLatitude();
					String AddrStr = location.getAddrStr();

					SharedPrefer
							.SaveLatitude(getApplicationContext(), latitude);
					SharedPrefer.SaveLongitude(getApplicationContext(),
							longitude);
					SharedPrefer.SaveAddress(getApplicationContext(), AddrStr);
					// SaveLocation.save(getApplicationContext(), longitude,
					// latitude);

					String lng = "经度: " + longitude;
					String lat = "纬度 :" + latitude;

					tv_location.setText(AddrStr);

				} catch (Exception e) {
				}
			}
			if (what == 0) {
				String AddrStr = SharedPrefer
						.ReadAddress(getApplicationContext());
				tv_location.setText(AddrStr);

			}
		}
	};

	private Handler handler1 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				try {
					Toast.makeText(getApplicationContext(), "计数成功", 1).show();
					SharedPrefer.SaveService(getApplicationContext(), "stop");
					String service = SharedPrefer
							.ReadService(getApplicationContext());
				} catch (Exception e) {
					e.printStackTrace();
				}
				tv_number.setTextColor(Color.BLUE);
				break;
			case 0:
				try {
					Toast.makeText(getApplicationContext(), "计数失败，请检查网络是否连接", 1)
							.show();
					SharedPrefer.SaveService(getApplicationContext(), "stop");
					String service = SharedPrefer
							.ReadService(getApplicationContext());
				} catch (Exception e) {
					e.printStackTrace();
				}
				tv_number.setTextColor(Color.BLUE);
				break;
			}
		}
	};

	private List<ImageView> imageViewList;

	private ViewPager mViewPager;
	private boolean isLoop = true;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		registerReceiver(mbatteryReceiver, new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED));
		registerReceiver(mHomeKeyEventReceiver, new IntentFilter(
				Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

		bt_qiandao = (Button) findViewById(R.id.bt_qiandao);
		bt_qiantui = (Button) findViewById(R.id.bt_qiantui);
		bt_baoyang = (Button) findViewById(R.id.bt_baoyang);

		bt_qiandao.setOnClickListener(buttonlistener);
		bt_qiantui.setOnClickListener(buttonlistener);
		bt_baoyang.setOnClickListener(buttonlistener);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isLoop) {
					SystemClock.sleep(10 * 1000);
					handler.sendEmptyMessage(0);
				}
			}
		}).start();
		initView();
		init();
		initLocation();
		getQRcodeimage();
	}

	private BroadcastReceiver mHomeKeyEventReceiver = new BroadcastReceiver() {
		String SYSTEM_REASON = "reason";
		String SYSTEM_HOME_KEY = "homekey";
		String SYSTEM_HOME_KEY_LONG = "recentapps";

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
				String reason = intent.getStringExtra(SYSTEM_REASON);
				Toast.makeText(getApplicationContext(), "home被按下", 1).show();
				Intent newIntent = context.getPackageManager()
						.getLaunchIntentForPackage("com.waji.activity");
				context.startActivity(newIntent);
			}
		}
	};
	OnClickListener buttonlistener = new OnClickListener() {
		public void onClick(View v) {
			String service = SharedPrefer.ReadService(getApplicationContext());
			if (service.equals("stop")) {
				try {
					if (v == bt_qiandao) {

						SharedPrefer.SaveLeaveStateContext(
								getApplicationContext(), "in");

						Intent intent = new Intent(getApplicationContext(),
								QiandaoActivity.class);
						startActivity(intent);
					} else if (v == bt_qiantui) {
						SharedPrefer.SaveLeaveStateContext(
								getApplicationContext(), "in");
						Intent intent = new Intent(getApplicationContext(),
								QianTuiActivity.class);
						startActivity(intent);
					} else if (v == bt_baoyang) {
						SharedPrefer.SaveLeaveStateContext(
								getApplicationContext(), "in");
						Intent intent = new Intent(getApplicationContext(),
								BaoyangActivity.class);
						startActivity(intent);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				Toast.makeText(getApplicationContext(), "相机使用中,请稍后再试", 1)
						.show();
				return;
			}
		}

	};

	private BroadcastReceiver mbatteryReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			int battery;
			int batteryState;
			if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
				int status = intent.getIntExtra("status",
						BatteryManager.BATTERY_STATUS_UNKNOWN);
				int batteryLevel = intent.getIntExtra(
						BatteryManager.EXTRA_LEVEL, 0);
				if (status == BatteryManager.BATTERY_STATUS_CHARGING
						|| batteryLevel == 100) {
					batteryState = 1;
					battery = batteryLevel;
					getWindow().addFlags(
							WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				} else {
					batteryState = 0;
					battery = batteryLevel;
					getWindow().clearFlags(
							WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				}

				try {
					SharedPrefer.SaveBatteryState(context, batteryState);
					SharedPrefer.SaveBattery(context, battery);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		String service = SharedPrefer.ReadService(getApplicationContext());
		if (service.equals("stop")) {
			if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
				starpicture();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
				starpicture();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK) {
				starpicture();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK) {
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	public void photostart(View view) {
		String service = SharedPrefer.ReadService(getApplicationContext());
		if (service.equals("stop")) {
			starpicture();
		} else
			return;
	}

	public void starpicture() {
		try {
			SharedPrefer.SaveService(getApplicationContext(), "star");
		} catch (Exception e) {
			e.printStackTrace();
		}

		picture = new TakePicture();

		video1 = new StartVideo();

		number = SharedPrefer.ReadNumber(getApplicationContext());
		number = number + 1;

		SaveNumber.save(getApplicationContext(), number);
		String numstring = Integer.toString(number);
		if (number >= 100) {
			tv_number.setTextSize(50);
		} else if (number >= 10) {
			tv_number.setTextSize(70);
		} else {
			tv_number.setTextSize(160);
		}
		tv_number.setText(numstring);
		tv_number.setTextColor(Color.RED);
		new Thread() {
			@Override
			public void run() {
				Message msg = new Message();
				super.run();
				try {
					picture.Startpicture(getApplicationContext());
					Thread.sleep(2000);

					SharedPrefer.SaveNumber(getApplicationContext(), number);

					video1.video(mSurfaceview, getApplicationContext(), 0);
					Thread.sleep(21 * 1000);

					SendImage image = new SendImage();
					image.send(getApplicationContext(), "1");

					msg.what = 1;
					handler1.sendMessage(msg);

				} catch (Exception e) {
					e.printStackTrace();
					msg.what = 0;
					handler1.sendMessage(msg);
				}
			}
		}.start();

	}

	public void getQRcodeimage() {
		String username = SharedPrefer.ReadUsername(getApplicationContext());
		CreatQRcode code = new CreatQRcode();
		Bitmap bitmap = code.creatQRcode(getApplicationContext(), username);
		im_qrcode.setImageBitmap(bitmap);
	}

	public void init() {
		tv_number = (TextView) findViewById(R.id.tv_number);
		tv_location = (TextView) findViewById(R.id.tv_location);

		im_qrcode = (ImageView) findViewById(R.id.ib_qrcode);
		mSurfaceview = (SurfaceView) findViewById(R.id.sv_main_video);

		tv_GyroscopeX = (TextView) findViewById(R.id.tv_GyroscopeX);
		tv_GyroscopeY = (TextView) findViewById(R.id.tv_GyroscopeY);
		tv_GyroscopeZ = (TextView) findViewById(R.id.tv_GyroscopeZ);
		tv_state = (TextView) findViewById(R.id.tv_state);
		editText1 = (EditText) findViewById(R.id.editText1);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

		number = SharedPrefer.ReadNumber(getApplicationContext());

		try {
			SharedPrefer
					.SaveLeaveStateContext(getApplicationContext(), "leave");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// try {
		// SharedPrefer.SaveNumber(getApplicationContext(), 66);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		String numstring = Integer.toString(number);
		if (number >= 100) {
			tv_number.setTextSize(50);
		} else if (number >= 10) {
			tv_number.setTextSize(70);
		} else {
			tv_number.setTextSize(160);
		}
		tv_number.setText(numstring);

	}

	public void initLocation() {
		String location = SharedPrefer.ReadAddress(getApplicationContext());
		if (location != null) {
			tv_location.setText(location);
		}

		locService = ((MyLeanCloudApp) getApplication()).locationService;
		LocationClientOption mOption = locService
				.getDefaultLocationClientOption();
		mOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
		mOption.setCoorType("bd09ll");
		locService.setLocationOption(mOption);
		locService.registerListener(listener1);
		locService.start();

	}

	BDLocationListener listener1 = new BDLocationListener() {
		@Override
		public void onReceiveLocation(BDLocation location) {
			Bundle locData = new Bundle();
			Message locMsg = locHander.obtainMessage();
			if (location != null
					&& (location.getLocType() == 161 || location.getLocType() == 66)) {
				locData.putInt("what", 1);
				locData.putParcelable("loc", location);
				locMsg.setData(locData);
				locHander.sendMessage(locMsg);
			} else {
				locData.putInt("what", 0);
				locMsg.setData(locData);
				locHander.sendMessage(locMsg);
			}
		}

		public void onConnectHotSpotMessage(String s, int i) {
		}
	};

	public int iswork(float xyz, float x, float y, float z) {
		if(x>10){
			x=2;
		}
		if(y>10){
			y=2;
		}
		if(z>10){
			z=2;
		}
		int batterystate = SharedPrefer
				.ReadBatteryState(getApplicationContext());
		float avg = SharedPrefer.ReadAvgNumber(getApplicationContext());
		long n = SharedPrefer.ReadcollectNumber(getApplicationContext());
		System.out.println("得到的值n：" + n+"。。。。。。。。。。。。得到的值avg：" + avg);
		System.out.println();
		if (n >= 1000000000) {
			n = 1000000000;
		} else {
			n = n + 1;
		}

		
		
		int i = 0;
		float xyz0 = (x + y + z) / 3;
		avg = (avg * n + xyz0) / (n + 1);
		System.out.println("计算得到的值n：" + n+"。。。。。。。。。计算得到的值avg：" + avg);
		 
		try {
			SharedPrefer.SaveAvgNumber(getApplicationContext(), avg);
			SharedPrefer.SaveCollectNumber(getApplicationContext(), n);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (avg < 0.1 & avg > 2) {
			avg = (float) 0.1;
		}
		if (batterystate == 0 & (x < 0.05 || y < 0.05 || z < 0.05)) {
			i = 1;
		} else if (avg < y) {
			i = 3;
		} else if (batterystate == 1 || avg > y) {
			i = 2;
		}
		System.out.println("计算完成"+i);
		return i;
		// if (x > xyz || y > xyz || z > xyz) {
		// return true;
		// } else if (x > 0.1 & y > 0.1 & z > 0.1) {
		// return true;
		// }
		// return false;

	}

	private SensorEventListener listener = new SensorEventListener() {
		public void onSensorChanged(SensorEvent e) {
			String a = editText1.getText().toString().trim();

			String state = "暂停";
			float xyz = Float.parseFloat(a);
			
			float x = Math.abs(e.values[0]);
			float y = Math.abs(e.values[1]);
			float z = Math.abs(e.values[2]);
			SaveXYZ.save(getApplicationContext(), x, y, z);
			tv_GyroscopeX.setText("X方向 " + x);
			tv_GyroscopeY.setText("Y方向 " + y);
			tv_GyroscopeZ.setText("Z方向 " + z);
			
			
			
			int batterystate = SharedPrefer
					.ReadBatteryState(getApplicationContext());

			try {
				System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
				System.out.println("开始计算" +  "...." +y );
				int is = iswork(xyz, x, y, z);
				 
				if (is == 3) { 
					state = "工作";
					SharedPrefer.SaveState(getApplication(), "3");
				} else if (is == 2) {
					state = "怠速";
					SharedPrefer.SaveState(getApplication(), "2");
				} else {
					state = "暂停";
					SharedPrefer.SaveState(getApplication(), "1");
				}
				tv_state.setText(state);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out
			.println("\nX方向 :" + x + ".......Y方向: " + y + ".....Z方向: " + z + state);
			 
		}

		public void onAccuracyChanged(Sensor arg0, int arg1) {
		}
	};

	@SuppressWarnings("deprecation")
	public void initView() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		prepareData();
		ViewPagerAdapter adapter = new ViewPagerAdapter();
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(this);
		int n = Integer.MAX_VALUE / 2 % imageViewList.size();
		int itemPosition = Integer.MAX_VALUE / 2 - n;
		mViewPager.setCurrentItem(itemPosition);
	}

	private void prepareData() {
		imageViewList = new ArrayList<ImageView>();
		int[] imageResIDs = getImageResIDs();
		ImageView iv;
		for (int i = 0; i < imageResIDs.length; i++) {
			iv = new ImageView(this);
			iv.setBackgroundResource(imageResIDs[i]);
			imageViewList.add(iv);
		}
	}

	private int[] getImageResIDs() {
		return new int[] { R.drawable.guanggao1, R.drawable.guanggao2,
				R.drawable.guanggao1, R.drawable.guanggao2,
				R.drawable.guanggao1 };
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
			container
					.addView(imageViewList.get(position % imageViewList.size()));
			return imageViewList.get(position % imageViewList.size());
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
	}

	public void setListener() {

	}

	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(listener, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {

		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		boolean screen = pm.isScreenOn();
		String leave = SharedPrefer.ReadLeaveState(getApplicationContext());
		System.out.println("屏幕onStop" + leave);
		if (screen && leave.equals("leave")) {

			Intent i = new Intent();
			i.setAction("restart");
			sendBroadcast(i);
		}
		super.onStop();
	}

	// @Override
	// protected void onDestroy() {
	// System.out.println("屏幕onDestroy");
	// try {
	// SharedPrefer.SaveLeaveStateContext(getApplicationContext(), "leaveoff");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// isLoop = false;
	//
	// PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
	// boolean screen = pm.isScreenOn();
	// if (screen) {
	//
	// Intent i = new Intent();
	// i.setAction("restart");
	// sendBroadcast(i);
	// }
	// super.onDestroy();
	// }

}
