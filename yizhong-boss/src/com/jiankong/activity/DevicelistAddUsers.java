package com.jiankong.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import com.jiankong.httpservice.AddDevices;
import com.jiankong.local.SQLite.DeviceDao;
import com.jiankong.view.ShebeiDeleteListView;
import com.jiankong.view.ShebeiDeleteAdapter;
import com.jiankong.view.SlideView;
 

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
 

public class DevicelistAddUsers extends Activity  {

	private EditText user_id;
	private EditText user_devicename;
	private EditText user_username;
	private EditText user_phone;
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.devicelist_addusers);

		user_id = (EditText) findViewById(R.id.et_user_id);
		user_devicename = (EditText) findViewById(R.id.et_user_devicename);
		user_username = (EditText) findViewById(R.id.et_user_username);
		user_phone = (EditText) findViewById(R.id.et_user_phone);
	 } 
	 
 
	 
	public void Toreturn(View view) {
		finish();
	}

	public void adduser(View view) {
		
		final String mac = user_id.getText().toString().trim();
		final String deviceName = user_devicename.getText().toString().trim();
		final String username = user_username.getText().toString().trim();
		final String phone = user_phone.getText().toString().trim();
		new Thread(new Runnable() {
			@Override
			public void run() {
				 try {
					 AddDevices.add(getApplicationContext(), mac, deviceName,username, phone);
				} catch (Exception e) {
					 e.printStackTrace();
				} 

			}
		}).start();
		
 }
	public void code(View view) {
		Intent intent = new Intent(this, DevicelistQRcode.class);
		startActivity(intent);

	}
}