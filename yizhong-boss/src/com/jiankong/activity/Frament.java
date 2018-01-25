package com.jiankong.activity;

 
import com.jiankong.service.AlarmService;
import com.jiankong.service.WarnService;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Frament extends Activity {

	private FragmentManager fm;
	private FragmentTransaction ft;
	
	private TextView tv_yonghu;
	private TextView tv_shangcheng;
	private TextView tv_wode;

	private Button bt_yonghu;
	private Button bt_shangcheng;
	private Button bt_wode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frament_activity);

		tv_yonghu = (TextView) findViewById(R.id.tv_yonghu);
		tv_shangcheng = (TextView) findViewById(R.id.tv_shangcheng);
		tv_wode = (TextView) findViewById(R.id.tv_wode);

		bt_yonghu = (Button) findViewById(R.id.bt_yonghu);
		bt_shangcheng = (Button) findViewById(R.id.bt_shangcheng);
		bt_wode = (Button) findViewById(R.id.bt_wode);

		Intent i = new Intent(getApplicationContext(), AlarmService.class);
		startService(i);
		Intent i1 = new Intent(getApplicationContext(), WarnService.class);
		startService(i1);
	    home();
	}

	public void home() {
		tv_yonghu.setTextColor(0xFF0088FF);
		tv_shangcheng.setTextColor(Color.BLACK);
		tv_wode.setTextColor(Color.BLACK);
		bt_yonghu.setBackgroundResource(R.drawable.yonghu2);
		bt_shangcheng.setBackgroundResource(R.drawable.shangcheng1);
		bt_wode.setBackgroundResource(R.drawable.wode1);

		fm = getFragmentManager();
		ft = fm.beginTransaction();
		ft.replace(R.id.fl_feament, new FramentDeviceList());
		ft.commit();
	}

	public void yonghu(View v) {
		home();
	}

	public void shangcheng(View v) {
		tv_yonghu.setTextColor(Color.BLACK);
		tv_shangcheng.setTextColor(0xFF0088FF);
		tv_wode.setTextColor(Color.BLACK);
		bt_yonghu.setBackgroundResource(R.drawable.yonghu1);
		bt_shangcheng.setBackgroundResource(R.drawable.shangcheng2);
		bt_wode.setBackgroundResource(R.drawable.wode1);

		fm = getFragmentManager();
		ft = fm.beginTransaction();
		ft.replace(R.id.fl_feament, new FramentShangcheng());
		ft.commit();
	}

	public void wode(View v) {
		tv_yonghu.setTextColor(Color.BLACK);
		tv_shangcheng.setTextColor(Color.BLACK);
		tv_wode.setTextColor(0xFF0088FF);
		bt_yonghu.setBackgroundResource(R.drawable.yonghu1);
		bt_shangcheng.setBackgroundResource(R.drawable.shangcheng1);
		bt_wode.setBackgroundResource(R.drawable.wode2);

		fm = getFragmentManager();
		ft = fm.beginTransaction();
		ft.replace(R.id.fl_feament, new FramentWode());
		ft.commit();
	}
}