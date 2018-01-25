package com.jiankong.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DeviceDao;
import com.jiankong.view.ShebeiDeleteListView;
import com.jiankong.view.ShebeiDeleteAdapter;
import com.jiankong.view.SlideView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
 
import android.widget.ListView;
 

public class WodeMydevice extends Activity  {

	 
	
	 private ListView lv;
    private static final String TAG = "MainActivity";
	private ShebeiDeleteListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wode_mydevice);

		 
		mListView = (ShebeiDeleteListView) findViewById(R.id.list_shebei);
        
		initView(); 
	} 
	private void initView() {
		String id=SharedPrefer.Readid(getApplicationContext());
		ShebeiDeleteAdapter mAdapter = new ShebeiDeleteAdapter(this);
		ArrayList<MessageBean> mMessageList = new ArrayList<MessageBean>();
		DeviceDao dao = new DeviceDao(getApplicationContext());
		List<Map<String, Object>> userlist = dao.findAllData(getApplicationContext(),id);
		int usersize=userlist.size();
		for (int i = 0; i < usersize; i++) {
			MessageBean item = new MessageBean();
			Map<String, Object> usermap = userlist.get(i);
			String deiviceId = (String)usermap.get("deiviceId");
			String state = (String)usermap.get("state");
			String number = (String)usermap.get("number");
			String phone = (String)usermap.get("phone");
			String username = (String)usermap.get("username");
			String devicename = (String)usermap.get("devicename");
			
			item.deiviceId=deiviceId;
			item.state=state;
			item.number=number;
			item.phone=phone;
			item.username=username;
			item.devicename=devicename;
			item.iconRes = R.drawable.xiaowaji1;
			
             mMessageList.add(item);
		}
		mAdapter.setmMessageItems(mMessageList);
		mListView.setAdapter(mAdapter);
	}
 	
 
	public class MessageBean {
		public int iconRes;
		public String deiviceId;
		public String state;
		public String number;
		public String username;
		public String phone;
		public String devicename;
		 
		public SlideView slideView;
	}
	public void Toreturn(View view) {
		finish();
	}

	 
	public void adddevice(View view) {
		Intent intent = new Intent(this, DevicelistAddUsers.class);
		startActivity(intent);

	}
}