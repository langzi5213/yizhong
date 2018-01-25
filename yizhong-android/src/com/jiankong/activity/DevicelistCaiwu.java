package com.jiankong.activity;
 
import com.jiankong.activity.R;
 
import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DeviceDao;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class DevicelistCaiwu extends Activity implements
SwipeRefreshLayout.OnRefreshListener{

	private TextView zmaoli;
	private TextView zchanzhi;
	private TextView zyouhao;
	 private TextView zfeiyong;
	 private ListView lv;
	 private SwipeRefreshLayout mSwipeLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_caiwu_list);

		zmaoli = (TextView) findViewById(R.id.tv_caiwu_zmaoli);
		zchanzhi = (TextView) findViewById(R.id.tv_caiwu_zchanzhi);
		zfeiyong = (TextView) findViewById(R.id.tv_caiwu_zfeiyong);
		zyouhao = (TextView) findViewById(R.id.tv_caiwu_zyouhao);

		lv = (ListView)  findViewById(R.id.lv_caiwu_list);
		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_caiwu_list);

		mSwipeLayout.setOnRefreshListener(DevicelistCaiwu.this);
		mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
		initEvent();
	 }
	private void initEvent() {
		DeviceDao dao = new DeviceDao(getApplicationContext());
		String id=SharedPrefer.Readid(getApplicationContext());
		lv.setAdapter(new SimpleAdapter(this, dao
				.findDevicrlist(getApplicationContext(),id), R.layout.device_caiwu_item, new String[] {
				 "devicename"  }, new int[] { R.id.tv_caiwu_name, }));
	}
	public void onRefresh() {
		 
		initEvent();
	}
	 
public void Toreturn(View view){
	finish();
}
}
