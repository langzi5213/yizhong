package com.jiankong.activity;

import java.util.List;
import java.util.Map;
 
import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DetailsDao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DevicelistNumberlist extends Activity implements
SwipeRefreshLayout.OnRefreshListener{

	private ListView lv;
	private TextView tv_name;
	private SwipeRefreshLayout mSwipeLayout;
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_list_number);
		
		lv = (ListView)findViewById(R.id.lv_number_list);
		tv_name=(TextView) findViewById(R.id.tv_number_name);
		mSwipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_number_list);
		
		mSwipeLayout.setOnRefreshListener(this);
		mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);

		initEvent();
	 }
	
	public void onRefresh() {
        initEvent();
		mSwipeLayout.setRefreshing(false);
	}

	private void initEvent() {
		Intent intent = getIntent();
	    final String deviceId = intent.getStringExtra("deviceId");
	    final String name = intent.getStringExtra("devicename");
	    System.out.println("接收到数据"+deviceId+name);
	     tv_name.setText(name);
	 
		String id=SharedPrefer.Readid(getApplicationContext());
		DetailsDao dao = new DetailsDao(this);
	    lv.setAdapter(new SimpleAdapter(this, dao.findAllNumber(id, deviceId),
				R.layout.number_item, new String[] {"number","date","battery"}, new int[] {
	    	R.id.tv_number_number,R.id.tv_number_date,R.id.tv_number_battery }));
		
		final List<Map<String, Object>> list = dao.findAllNumber(id, deviceId);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id1) {
				switch (position) {
				default: {
					 String number = (String) list.get(position).get("number");
					String date = (String) list.get(position).get("date");
					String battery = (String) list.get(position).get("battery");
					Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
					intent.putExtra("name", name);
					intent.putExtra("number", number);
					intent.putExtra("battery", battery);
					intent.putExtra("deviceId", deviceId);
					intent.putExtra("date", date); 
					 
					startActivity(intent);
					break;
				}

				}
			}
		});
	}

	public void Toreturn(View view) {
		finish();
	}
	 
}
