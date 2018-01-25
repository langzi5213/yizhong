package com.jiankong.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DetailsDao;
import com.squareup.picasso.Picasso;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class DetailsActivity extends Activity {

	private TextView tv_name;
	private TextView tv_data;
	private TextView tv_number;
	private TextView tv_battery;
	private TextView tv_location;
	private ImageView mImage1;
	private ImageView mImage2;
	private VideoView videoview;
	private Button bt_playvideo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		tv_name = (TextView) findViewById(R.id.tv_details_name);
		tv_data = (TextView) findViewById(R.id.tv_details_data);
		tv_number = (TextView) findViewById(R.id.tv_details_number);
		tv_battery = (TextView) findViewById(R.id.tv_details_battery);
		tv_location = (TextView) findViewById(R.id.tv_details_location);
		mImage1 = (ImageView) findViewById(R.id.iv_details1);
		mImage2 = (ImageView) findViewById(R.id.iv_details2);

		videoview = (VideoView) findViewById(R.id.videoView1);
		bt_playvideo = (Button) findViewById(R.id.bt_playVideo);
		videoview.setMediaController(new MediaController(this));
		Uri uri = Uri.parse("http://10.129.55.55:8080/admin/27.mp4");
		videoview.setVideoURI(uri);
		videoview.requestFocus();
		
		 
		init();
	}

//	Date getDateWithDateString(String dateString) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		Date date = null;
//		try {
//			date = dateFormat.parse(dateString);
//		} catch (ParseException e) {
//
//			e.printStackTrace();
//		}
//		return date;
//	}

	public void init() {
		Intent intent = getIntent();
		String deviceId = intent.getStringExtra("deviceId");
		String number = intent.getStringExtra("number");
		String battery = intent.getStringExtra("battery");
		String date = intent.getStringExtra("date");

		final String name = intent.getStringExtra("name");
		tv_name.setText(name);
		tv_number.setText(number);
		tv_battery.setText(battery);
		tv_data.setText(date);

		DetailsDao dao = new DetailsDao(getApplicationContext());
		String id = SharedPrefer.Readid(getApplicationContext());

		Map<String, Object> find = dao.findAddress(id, deviceId, date);
		String address = (String) find.get("address");
		tv_location.setText(address);
		System.out.println("获取地址" + deviceId + date + address);

		Map<String, Object> url = dao.findurl(id, deviceId, date);
		String imageUrl = (String) url.get("imageUrl");

		if (imageUrl == null) {
			Toast.makeText(getApplicationContext(), "设备网络状况差,上传照片失败", 0).show();
			mImage1.setVisibility(View.GONE);
		} else {
			Picasso.with(DetailsActivity.this).load(imageUrl).into(mImage1);
		}
	}

	public void playVideo(View view) {
		videoview.start();
		bt_playvideo.setVisibility(View.GONE);
        
	}

	public void Toreturn(View view) {
		finish();
	}

	public void change1(View view) {
		init();

	}

	public void change2(View view) {
		init();

	}
}
