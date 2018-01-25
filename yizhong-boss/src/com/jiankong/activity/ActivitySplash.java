package com.jiankong.activity;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jiankong.dao.UpdateManager;
 
import com.jiankong.httpservice.GetAdvertisement;
import com.jiankong.httpservice.GetDevices;
import com.jiankong.httpservice.Login;
import com.jiankong.interfac.PublicString;

import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DetailsDao;

public class ActivitySplash extends Activity {

	private LinearLayout ll_splash;

	private TextView tv_gengxing;
	private ProgressBar proBar;
	public static receiveVersionHandler handler;
	private UpdateManager manager = UpdateManager.getInstance();

	private Handler handler1 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String info = (String) msg.obj;
				System.out.println("输出的结果" + info);
				if(info==null){
					 Intent intent = new Intent(getApplicationContext(),
							ActivityLogin.class);
					startActivity(intent);
					finish();
				}
				if (info.equals("成功！")) {
					 ToMain();
				} else {
					 Toast.makeText(getApplicationContext(), info, 1).show();
					Intent intent = new Intent(getApplicationContext(),
							ActivityLogin.class);
					startActivity(intent);
					finish();
				}
				break;
			case 0:
				Toast.makeText(getApplicationContext(), "连接网络失败,请检查网络是否连接", 0)
						.show();
				Intent intent = new Intent(getApplicationContext(),
						ActivityLogin.class);
				startActivity(intent);
				finish();
				//ToMain();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		ll_splash = (LinearLayout) findViewById(R.id.ll_splash);
		ll_splash.setBackgroundResource(R.drawable.beijing);
		tv_gengxing = (TextView) findViewById(R.id.tv_gengxing);
		proBar = (ProgressBar) findViewById(R.id.progressBar_id);
		handler = new receiveVersionHandler();
		init();
	}

	public void init() {
		String id=SharedPrefer.Readid(getApplicationContext());
		System.out.println("id为"+id);
		if(id.equals("")){
			System.out.println("检车id为空");
			ToLogin();
		}
		Boolean ru = SharedPrefer.ReadAutoUpdate(this);
		if (ru) {
			manager.CheckVersion(this, "SplashActivity");
		} else {
			loadMain();
		 }
		 
		DetailsDao details = new DetailsDao(getApplicationContext());
		details.delete(id);

//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					GetAdvertisement.get();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
	}

	public class receiveVersionHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				loadMain();
				break;

			case 1:
				proBar.setProgress(msg.arg1);
				proBar.setVisibility(View.VISIBLE);
				tv_gengxing.setVisibility(View.VISIBLE);
				if (msg.arg1 == 100) {
					Intent intent = new Intent("android.intent.action.VIEW");
					intent.addCategory("android.intent.category.DEFAULT");
					String type = "application/vnd.android.package-archive";
					Uri data = Uri.fromFile(new File(PublicString.SavePath));
					intent.setDataAndType(data, type);
					startActivityForResult(intent, 0);
				}
				proBar.setVisibility(View.VISIBLE);
				break;
			case 2:
				Toast.makeText(getApplicationContext(), "连接网络失败，请检查网络是否连接", 1)
						.show();
				loadMain();
			}
		}
	}
	public void loadMain() {
        new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					 String device = GetDevices.get(getApplicationContext());
					Message msg = new Message();
					System.out.println("未出现异常1");
					msg.what = 1;
					msg.obj = device;
					handler1.sendMessage(msg);
				} catch (Exception e) {
					System.out.println("出现异常");
					e.printStackTrace();
					Message msg = new Message();
					msg.what = 0;
					handler1.sendMessage(msg);
				}
			}
		}).start();
	}
	private void ToMain() {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				SystemClock.sleep(3 * 1000);
				Intent intent = new Intent(getApplicationContext(),
						Frament.class);
				startActivity(intent);
				finish();

			}
		}).start();
	}
private void ToLogin() {
		 new Thread(new Runnable() {
			@Override
			public void run() {
				SystemClock.sleep(2 * 1000);
				Intent intent = new Intent(getApplicationContext(),
						ActivityLogin.class);
				startActivity(intent);
				finish();
 }
		}).start();
	}

	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		loadMain();
		super.onActivityResult(requestCode, resultCode, data);
	}

}
