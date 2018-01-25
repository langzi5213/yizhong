package com.jiankong.activity;
 
import com.jiankong.activity.R;
import com.jiankong.httpservice.GetDevices;
import com.jiankong.httpservice.Login;
import com.jiankong.local.SharedPrefer;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends Activity {

	private EditText et_id;
	private EditText et_key;

	String id;
	String password;
	ProgressDialog progress;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String info = (String) msg.obj;

				if (info.equals("成功！")) {
					try {
						showProgress();
						SharedPrefer.Saveid(getApplicationContext(), id);
						SharedPrefer.SavePassword(getApplicationContext(),
								password);
						 
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					GetDevice();
				} else {
					Toast.makeText(getApplicationContext(), info, 0).show();
				}
				break;
			case 0:
				Toast.makeText(getApplicationContext(), "连接网络失败,请检查网络是否连接", 0).show();
				break;
			}
		}
	};
	private Handler handler1 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String info = (String) msg.obj;
				if (info == null) {
					ActivityLogin.this.finish();
					startActivity(new Intent(ActivityLogin.this, Frament.class));
				}
				if (info.equals("成功！")) {
					progress.cancel();
					ActivityLogin.this.finish();
					startActivity(new Intent(ActivityLogin.this, Frament.class));

				} else if (info.equals("账户或者密码不正确")) {
					Toast.makeText(getApplicationContext(), info, 0).show();
				} else {
					Toast.makeText(getApplicationContext(), "获取用户信息失败，请稍候再试", 0)
							.show();
					ActivityLogin.this.finish();
					startActivity(new Intent(ActivityLogin.this, Frament.class));
				}
				break;
			case 0:
				Toast.makeText(getApplicationContext(), "连接网络失败,请检查网络是否连接", 0)
						.show();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		et_id = (EditText) findViewById(R.id.et_id);
		et_key = (EditText) findViewById(R.id.et_key);

		String id = SharedPrefer.Readid(getApplicationContext());
		String key = SharedPrefer.ReadPassword(getApplicationContext());

		et_id.setText(id);
		et_key.setText(key);

	}
	
	@SuppressWarnings("deprecation")
	public void showProgress() {
		  progress = new ProgressDialog(ActivityLogin.this);
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setTitle("登录成功");
		progress.setMessage("获取数据中，请稍候！");
		progress.setIndeterminate(false);
		progress.setCancelable(true);
		progress.show();
		
	}

	public void NewRegister(View view) {
		Intent intent = new Intent(this, LoginRegister.class);
		startActivity(intent);
	}

	public void ForgetPassword(View view) {
		Intent intent = new Intent(this, LoginForgot.class);
		startActivity(intent);
	}

	public void login(View view) {

		id = et_id.getText().toString();
		password = et_key.getText().toString();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					String log = Login.login(getApplicationContext(), id,
							password);
					Message msg = new Message();
					msg.what = 1;
					msg.obj = log;
					handler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = 0;
					handler.sendMessage(msg);
				}

			}
		}).start();

	}

	public void GetDevice() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String log = GetDevices.get(getApplicationContext());
					Message msg = new Message();
					msg.what = 1;
					msg.obj = log;
					handler1.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = 0;
					handler1.sendMessage(msg);
				}

			}
		}).start();

	}

}
