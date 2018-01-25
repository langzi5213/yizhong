package com.jiankong.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import com.jiankong.activity.R;
 
import com.jiankong.local.SQLite.UserDao;
import com.jiankong.view.UserDeleteAdapter;
import com.jiankong.view.UserDeleteListView;
import com.jiankong.view.SlideView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WodeUser extends Activity {

	private EditText et_name;
	private EditText et_phone;
	private EditText et_year;
	
	private static final String TAG = "MainActivity";
	private UserDeleteListView mListView;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wode_user);

		et_name = (EditText) findViewById(R.id.et_siji_name);
		et_phone = (EditText) findViewById(R.id.et_siji_phone);
		et_year = (EditText) findViewById(R.id.et_siji_year);
		mListView = (UserDeleteListView) findViewById(R.id.list_yuangong);
		
		initView();
	}

	
private void initView() {
		
	UserDeleteAdapter mAdapter = new UserDeleteAdapter(this);
		ArrayList<MessageBean> mMessageList = new ArrayList<MessageBean>();
		UserDao dao = new UserDao(getApplicationContext());
		List<Map<String, Object>> userlist = dao.findAllDate(getApplicationContext());
		int usersize=userlist.size();
		for (int i = 0; i < usersize; i++) {
			MessageBean item = new MessageBean();
			Map<String, Object> usermap = userlist.get(i);
			String phone = (String)usermap.get("phone");
			String username = (String)usermap.get("username");
			
			item.phone=phone;
			item.username=username;
			item.iconRes = R.drawable.yuangong;
			
			mMessageList.add(item);
		}
		mAdapter.setmMessageItems(mMessageList);
		mListView.setAdapter(mAdapter);
	}
 	
 
	public class MessageBean {
		public int iconRes;
		public String username;
		public String phone;
		public SlideView slideView;
	}
	public void Toreturn(View view) {
		finish();
	}
	public void tijiao(View view) {
		String name=et_name.getText().toString().trim();
		String phone=et_phone.getText().toString().trim();
		String year=et_name.getText().toString().trim();
		if(name.isEmpty()||phone.isEmpty()||year.isEmpty()){
			Toast.makeText(getApplicationContext(), "请将信息填写完整", 1).show();
		}
		int i=phone.length();
		if(i!=11){
			Toast.makeText(getApplicationContext(), "请输入正确的手机号码", 1).show();
		}
 		 UserDao dao = new  UserDao(getApplicationContext());
 		dao.add(  phone, name);
 		Toast.makeText(getApplicationContext(), "添加成功", 1).show();
 		finish();
		
	}
//	public void adduser(View view) {
//		String phone = user_phone.getText().toString().trim();
//		String name = user_name.getText().toString().trim();
//		AddUserDao dao = new AddUserDao();
//		dao.adduser(getApplicationContext(), phone, name);
// }
}
