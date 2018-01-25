   package com.jiankong.activity;

import java.io.File;

import com.jiankong.activity.ActivitySplash.receiveVersionHandler;
import com.jiankong.dao.UpdateManager;
 
import com.jiankong.local.SharedPrefer;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class WodeSetup extends Activity {

	private ToggleButton tb;
	private LinearLayout version;
	private TextView tv_version;
	private LinearLayout update;
	private LinearLayout idea;
	private LinearLayout about;
	private TextView logout;
	
	private TextView tv_gengxing;
	private ProgressBar proBar;
	public static receiveVersionHandler handler;
	private UpdateManager manager = UpdateManager.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wode_setup);

		tb = (ToggleButton) findViewById(R.id.tb_update);
		version = (LinearLayout) findViewById(R.id.setup_version);
		tv_version = (TextView) findViewById(R.id.tv_setup_version);
		update = (LinearLayout) findViewById(R.id.setup_update);
		idea = (LinearLayout) findViewById(R.id.setup_idea);
		about = (LinearLayout) findViewById(R.id.setup_about);
		logout = (TextView) findViewById(R.id.setup_logout);
		
		tv_gengxing = (TextView) findViewById(R.id.setup_gengxing);
		proBar = (ProgressBar) findViewById(R.id.setup_progressBar);
		handler = new receiveVersionHandler();

		String versionName = getVersionName();
		tv_version.setText(versionName);

		version.setOnClickListener(listener);
		update.setOnClickListener(listener);
		idea.setOnClickListener(listener);
		about.setOnClickListener(listener);
		logout.setOnClickListener(listener);
		tb.setOnClickListener(linstener2);
		Boolean ru = SharedPrefer.ReadAutoUpdate(getApplicationContext());
		if (ru) {
			tb.setBackgroundResource(R.drawable.togglebuttonblue);
		}
		tb.setChecked(ru);
	}
	public class receiveVersionHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(getApplicationContext(), "您的软件是最新版本", 1)
				.show();
				 break;

			case 1:
				proBar.setProgress(msg.arg1);
				proBar.setVisibility(View.VISIBLE);
				tv_gengxing.setVisibility(View.VISIBLE);
				if (msg.arg1 == 100) {
					Intent intent = new Intent("android.intent.action.VIEW");
					intent.addCategory("android.intent.category.DEFAULT");
					String type = "application/vnd.android.package-archive";
					Uri data = Uri.fromFile(new File(Environment
							.getExternalStorageDirectory() + "/yizhong.apk"));
					intent.setDataAndType(data, type);
					startActivityForResult(intent, 0);
				}
				proBar.setVisibility(View.VISIBLE);
				break;
			case 2:
				Toast.makeText(getApplicationContext(), "连接网络失败，请检查网络是否连接", 0)
						.show();
				break;
			case 3:
				 break;
			}
		}
	}
	ToggleButton.OnClickListener linstener2 = new ToggleButton.OnClickListener() {
		public void onClick(View v) {
			try {
				if (tb.isChecked()) {
					tb.setBackgroundResource(R.drawable.togglebuttonblue);
					SharedPrefer.SaveAutoUpdate(getApplicationContext(), true);
					System.out.println("打开"
							+ SharedPrefer
									.ReadAutoUpdate(getApplicationContext()));
				} else {
					tb.setBackgroundResource(R.drawable.togglebutton);
					SharedPrefer.SaveAutoUpdate(getApplicationContext(), false);
					System.out.println("关闭"
							+ SharedPrefer
									.ReadAutoUpdate(getApplicationContext()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	public String getVersionName() {
		String versionName = null;
		try {
			versionName = this.getPackageManager().getPackageInfo(
					"com.jiankong.activity", 0).versionName;
		} catch (Exception e) {
			System.out.println("获取版本名异常！");
		}
		return versionName;
	}

	OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			if (v == update) {
				manager.CheckVersion(WodeSetup.this,"WodeSetup");
			 } else if (v == idea) {
				Intent intent = new Intent(WodeSetup.this, WodeSetupIdea.class);
				startActivity(intent);
			} else if (v == about) {
				// Intent intent = new Intent(WodeSetup.this, WodeUser.class);
				// startActivity(intent);
				Toast.makeText(getApplicationContext(), "此功能开发中，敬请期待", 1)
						.show();
			} else if (v == logout) {
				Intent intent = new Intent(WodeSetup.this, ActivityLogin.class);
				startActivity(intent);
				finish();
			}
		}

	};

}