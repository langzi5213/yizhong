package com.jiankong.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class ShangJishou extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shang_jishou);

	}

	public void Toreturn(View view) {
		finish();
	}

}
