package com.waji.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.waji.dao.CameraPreview;
import com.waji.http.SendImage;
import com.waji.local.SharedPrefer;
import com.waji.utils.ByteBitmap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QianTuiActivity extends Activity {

	private Camera mCamera;
	private CameraPreview mPreview;
	private TextView tv_qiandao;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String info = (String) msg.obj;
				if (info.equals("成功！")) {
					Toast.makeText(getApplicationContext(), "签退成功", 1).show();
				}

				try {
					SharedPrefer.SaveLeaveStateContext(getApplicationContext(),
							"leave");
				} catch (Exception e) {
					e.printStackTrace();
				}

				// Intent intent = new Intent(getApplicationContext(),
				// MainActivity.class);
				// startActivity(intent);
				finish();
				break;
			case 0:
				Toast.makeText(getApplicationContext(), "签到失败，请检查网络是否连接", 1)
						.show();
				finish();
				break;

			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qiandao);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);

		mCamera = getCameraInstance();
		mPreview = new CameraPreview(this, mCamera);
		preview.addView(mPreview);

	}

	public void photostart(View view) {
		mCamera.autoFocus(new AutoFocusCallback() {
			@Override
			public void onAutoFocus(boolean success, Camera camera) {
				mCamera.takePicture(null, null, mPicture);
				// new Thread() {
				// @Override
				// public void run() {
				// super.run();
				// try {
				// Thread.sleep(5000);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }
				// finish();
				// }
				// }.start();

			}
		});

	}

	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open(1);
		} catch (Exception e) {

		}
		return c;
	}

	private PictureCallback mPicture = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			ByteBitmap bit = new ByteBitmap();
			Bitmap bitmap = bit.Bytes2Bimap(data);
			Bitmap bitmap1 = bit.compress(bitmap);

			byte[] data2 = bit.Bitmap2Bytes(bitmap1);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy—MM—dd");
			SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");
			Date curDate = new Date(System.currentTimeMillis());
			String str = formatter.format(curDate);
			String str1 = formatter1.format(curDate);

			File mFile = new File(Environment.getExternalStorageDirectory()
					.getAbsoluteFile() + "/QRcode/" + str);

			if (!mFile.exists()) {
				mFile.mkdir();
			}
			String file = mFile + "/" + str1 + ".jpg";
			SendImage.serviceImagePath = file;
			File pictureFile = new File(file);

			try {
				FileOutputStream fos = new FileOutputStream(pictureFile);
				fos.write(data2);
				fos.close();
				SharedPrefer.SaveNumber(getApplicationContext(), 0);
				SendImgae();
			} catch (Exception e) {
				Log.d("error", "Error accessing file: " + e.getMessage());
			}
		}
	};

	public void SendImgae() {
		new Thread() {
			@Override
			public void run() {
				Message msg = new Message();
				super.run();
				try {
					SendImage send = new SendImage();
					String result = send.send(getApplicationContext(), "-2");
					msg.what = 1;
					msg.obj = result;
					handler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
					msg.what = 0;
					handler.sendMessage(msg);
				}
				finish();
			}
		}.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
	}

	public void Toreturn(View view) {
		try {
			SharedPrefer
					.SaveLeaveStateContext(getApplicationContext(), "leave");
		} catch (Exception e) {
			 e.printStackTrace();
		}
		finish();
	}
}