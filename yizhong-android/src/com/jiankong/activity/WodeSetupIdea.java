package com.jiankong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class WodeSetupIdea extends Activity {

	private EditText et_idea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wode_idea);
		et_idea = (EditText) findViewById(R.id.et_idea);
	}
	public void tijiao(View v){
		String idea=et_idea.getText().toString();
	}
}