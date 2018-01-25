package com.jiankong.activity;

import java.io.IOException;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.jiankong.camera.CameraManager;
import com.jiankong.httpservice.AddDevices;
import com.jiankong.httpservice.Login;
import com.jiankong.decoding.CaptureActivityHandler;
import com.jiankong.decoding.InactivityTimer;
import com.jiankong.view.ViewfinderView;

public class DevicelistQRcode extends Activity implements Callback {

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;

	
	private Handler handler1 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String info = (String) msg.obj;
				if (info.equals("成功")) {
					Toast.makeText(getApplicationContext(), "添加设备成功", 1).show();
					 
					finish();
				 } else {
					Toast.makeText(getApplicationContext(), info, 0).show();
				}
				break;
			case 0:
				Toast.makeText(getApplicationContext(), "添加用户失败，请检查网络是否连接", 1).show();
                break;
			}
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qrcode);
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
	}

	public void Toreturn(View view) {
		finish();
	}

	public void handleDecode(final Result obj, Bitmap barcode) {
		LinearLayout linear = new LinearLayout(this);
		final EditText et_devicename = new EditText(this);
		final EditText et_username = new EditText(this);
		final EditText et_phone = new EditText(this);

		linear.addView(et_devicename);
		linear.addView(et_username);
		linear.addView(et_phone);
		et_devicename.setWidth(getWallpaperDesiredMinimumWidth());
		et_username.setWidth(getWallpaperDesiredMinimumWidth());
		et_phone.setWidth(getWallpaperDesiredMinimumWidth());
		linear.setOrientation(LinearLayout.VERTICAL);
		et_devicename.setHint("请给挖掘机设置一个名称");
		et_username.setHint("请输入司机姓名");
		et_phone.setHint("请输入司机手机号");

		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		if (barcode == null) {
			dialog.setIcon(null);
		} else {

			Drawable drawable = new BitmapDrawable(barcode);
			dialog.setIcon(drawable);
		}
		dialog.setTitle(" 是否添加下列用户信息");
		dialog.setView(linear);
		dialog.setMessage(obj.getText());
		dialog.setNegativeButton("添加", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String mac = obj.getText();
				System.out.println("扫二维码手机号" + mac);
				String deviceName = et_devicename.getText().toString();
				String username = et_username.getText().toString();
				String phone = et_phone.getText().toString();

				if (deviceName.equals("")) {
					Toast.makeText(getApplicationContext(), "设备名称不能为空！",
							Toast.LENGTH_LONG).show();
				} else {
					adddevice(mac, deviceName,username,phone);
				}
			}
		});
		dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		dialog.create().show();
	}

	
	public void adddevice(final String mac,final String deviceName,final String username,final String phone){
		new Thread(new Runnable() {
			@Override
			public void run() {

				try {
                    String device=AddDevices.add(getApplicationContext(), mac, deviceName,username, phone);
					Message msg = new Message();
					msg.what = 1;
					msg.obj = device;
					handler1.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = 0;
					handler1.sendMessage(msg);
				}
                }
		}).start();
	}
	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

}