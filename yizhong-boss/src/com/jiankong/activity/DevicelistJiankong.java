package com.jiankong.activity;
 
import com.jiankong.activity.R;
  
import com.jiankong.local.SharedPrefer;
import android.app.Activity;
 
import android.os.Bundle;
 
import android.widget.EditText;
 

public class DevicelistJiankong extends Activity {

	private EditText et_id;
	private EditText et_key;

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

	 

}
