package com.waji.activity;

import com.waji.dao.StartVideo;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BaoyangActivity extends Activity {

	private SurfaceView mSurfaceview;
	StartVideo video1;
	TextView tv_star;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String info = (String) msg.obj;
                tv_star.setText(info);
				tv_star.setTextSize(40);
				tv_star.setTextColor(Color.RED);
				finish();
				break;
			case 0:
				Toast.makeText(getApplicationContext(), "上传视频失败，请检查网络是否连接", 1)
						.show();
				finish();
				break;

			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baoyang);

		mSurfaceview = (SurfaceView) findViewById(R.id.sv_baoyang_video);
		tv_star = (TextView) findViewById(R.id.tv_star);

	}

	public void VideoStart(View view) {
		tv_star.setText("录像中");
		tv_star.setTextSize(40);
		tv_star.setTextColor(Color.BLUE);
		video1 = new StartVideo();
		new Thread() {
			@Override
			public void run() {
				Message msg = new Message();
				super.run();
				try {

					Thread.sleep(1000);
					video1.video(mSurfaceview, getApplicationContext(), 2);
					Thread.sleep(21 * 1000);
					msg.what = 1;
					msg.obj = "录像结束";
					handler.sendMessage(msg); 
				 } catch (Exception e) {
					e.printStackTrace();
					msg.what = 0;
					handler.sendMessage(msg);
					 
				}
			}
		}.start();
	}

	public void Toreturn(View view) {
		finish();
	}
}