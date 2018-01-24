package com.waji.activity;

import java.io.File;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.waji.dao.UpdateManager;
import com.waji.daomain.InitOther;
import com.waji.daomain.PublicString;
import com.waji.local.SharedPrefer;

public class SplashActivity extends Activity {

	private LinearLayout ll_splash;
	private String username;
	private TextView tv_gengxing;
	private ProgressBar proBar;
	public static receiveVersionHandler handler;
	private UpdateManager manager = UpdateManager.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		tv_gengxing = (TextView) findViewById(R.id.tv_gengxing);
		ll_splash = (LinearLayout) findViewById(R.id.ll_splash);
		ll_splash.setBackgroundResource(R.drawable.guanggao1);

		proBar = (ProgressBar) findViewById(R.id.progressBar_id);
		handler = new receiveVersionHandler();
		manager.CheckVersion(this);
		init();
	}
public void init(){
	TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	username = tm.getDeviceId();
	 
	try {
		SharedPrefer.SaveUsername(this, username);
		SharedPrefer.SaveService(this, "stop");
	} catch (Exception e) {
		e.printStackTrace();
	}
	InitOther other = new InitOther();
	other.init(getApplicationContext());
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
				break;
			case 2:
				Toast.makeText(getApplicationContext(), "连接网络失败，请检查网络是否连接", 0)
						.show();
				  loadMain();
				break;
			}

		}
	}

	private void loadMain() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				SystemClock.sleep(2 * 1000);

				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(intent);
				finish();

			}
		}).start();
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		loadMain();
		super.onActivityResult(requestCode, resultCode, data);
	}
 }
