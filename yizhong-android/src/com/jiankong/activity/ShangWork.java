package com.jiankong.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class ShangWork extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shang_work);

	}

	public void Toreturn(View view) {
		finish();
	}

}
