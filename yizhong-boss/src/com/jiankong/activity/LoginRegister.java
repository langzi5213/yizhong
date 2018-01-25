package com.jiankong.activity;

import com.jiankong.activity.R;
import com.jiankong.httpservice.Register;
import com.jiankong.local.SharedPrefer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginRegister extends Activity {
	private EditText et_number;
	private EditText et_captcha;
	private EditText et_password;
	private EditText et_password1;
	private EditText et_name;
	
	
	private Handler handler1 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String info = (String) msg.obj;
				try {
					SharedPrefer.Saveid(getApplicationContext(), info);
				} catch (Exception e) {
					 e.printStackTrace();
				}
				Toast.makeText(getApplicationContext(), "注册成功，请登录", 1)
				.show();
				 Intent intent = new Intent(getApplicationContext(),
						ActivityLogin.class);
				startActivity(intent);
				finish();
				break;
			case 0:
				Toast.makeText(getApplicationContext(), "注册失败，请稍后再试", 1)
				.show();
                break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register);

		et_number = (EditText) findViewById(R.id.et_number);
		et_password = (EditText) findViewById(R.id.et_password);
		et_password1 = (EditText) findViewById(R.id.et_password1);
		et_name = (EditText) findViewById(R.id.et_name);
	}


	public void register(View view) {

		final String id = et_number.getText().toString();
		final String password = et_password.getText().toString();
		final String password1 = et_password1.getText().toString();
		final String name = et_name.getText().toString();
		try {
			SharedPrefer.Savename(getApplicationContext(), name);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(id.length()!=11){
			Toast.makeText(getApplicationContext(), "请输入正确的手机号码", 1).show();
		}
		else if (password.equals(password1)) {
			new Thread(new Runnable() { 
				@Override
				public void run() {
					Message msg = new Message();
					try {
						 Register.register(id, password, name);
						 msg.what = 1;
						 msg.obj = id;
                          handler1.sendMessage(msg);
					} catch (Exception e) {
						msg.what = 0;
						handler1.sendMessage(msg);
						
						e.printStackTrace();
						return;
					}

				}
			}).start();
		}
		else{
			Toast.makeText(getApplicationContext(), "两次输入的密码不正确，请确认", 1).show();
		}

	}
}
