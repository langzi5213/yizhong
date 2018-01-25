package com.jiankong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jiankong.decoding.Intents.Share;
import com.jiankong.httpservice.Login;
import com.jiankong.httpservice.UpdatePassword;
import com.jiankong.local.SharedPrefer;

public class LoginForgot extends Activity {

	private EditText et_phone;
	private EditText et_name;
	private EditText et_newPassword;
	private EditText et_password;

	String id;
	String name;
	String newPassword;
	String password;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String info = (String) msg.obj;
				if (info.equals("成功！")) {
					try {
						Toast.makeText(getApplicationContext(), "修改密码成功，请重新登录",
								0).show();
						SharedPrefer.SavePassword(getApplicationContext(), "");
						Intent intent = new Intent(getApplicationContext(),
								ActivityLogin.class);
						startActivity(intent);
						finish();
					} catch (Exception e2) {
						e2.printStackTrace();
					}

				} else {
					Toast.makeText(getApplicationContext(), info, 0).show();
				}
				break;
			case 0:
				Toast.makeText(getApplicationContext(), "连接网络失败...", 0).show();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_forgot);

		et_name = (EditText) findViewById(R.id.et_forgot_name);
		et_phone = (EditText) findViewById(R.id.et_forgot_phone);
		et_newPassword = (EditText) findViewById(R.id.et_forgot_password);
		et_password = (EditText) findViewById(R.id.et_forgot_password1);
	}

	public void forgot(View view) {
		id = et_phone.getText().toString();
		name = et_name.getText().toString();
		newPassword = et_newPassword.getText().toString();
		password = et_password.getText().toString();
		String oldName=SharedPrefer.Readname(getApplicationContext());


		if (id.isEmpty()) {
			Toast.makeText(getApplicationContext(), "请输入账号", 1).show();
		} else if (name.isEmpty()) {
			Toast.makeText(getApplicationContext(), "请输入姓名", 1).show();
		}else if (!name.equals(oldName)) {
			Toast.makeText(getApplicationContext(), "请输入正确的姓名", 1).show();
		} else if (newPassword.isEmpty()) {
			Toast.makeText(getApplicationContext(), "请输入新密码", 1).show();
		} else if (password.isEmpty()) {
			Toast.makeText(getApplicationContext(), "请再次输入新密码", 1).show();
		} else if (!password.equals(newPassword)) {
			Toast.makeText(getApplicationContext(), "两次新密码输入不一致,请重新输入", 1)
					.show();
		} else {
			update();
		}

	}

	public void update() {
		final String oldPassword = SharedPrefer
				.ReadPassword(getApplicationContext());
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					String log = UpdatePassword.update(getApplicationContext(),
							id, oldPassword, newPassword);
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
}
