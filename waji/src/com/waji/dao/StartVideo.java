package com.waji.dao;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.CountDownTimer;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.waji.activity.MainActivity;

public class StartVideo {
	MovieRecorder mRecorder;
	SurfaceHolder surfaceHolder;
	SurfaceView surfaceView1;
	 Camera camera;
	 
	public void video(final SurfaceView surfaceView, final Context context,int who) throws Exception  {
		 
		System.out.println("录像");
		if (camera != null) {
			 return;}
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(surfaceHolderCallback);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mRecorder = new MovieRecorder();
		surfaceView1 = surfaceView;
		 mRecorder.startRecording(surfaceView, context,who);
		new Thread() {
			@Override
			public void run() {
				super.run(); 
				
				try {
					Thread.sleep(20*1000);
					 destroyvideo();
				} catch (InterruptedException e) {
					 e.printStackTrace();
				}
			}
		}.start();
		
		
	 
	}

//	public void starvideo(final SurfaceView surfaceView, final Context context) {
//		new Thread() {
//			@Override
//			public void run() {
//				super.run();
//				mRecorder.startRecording(surfaceView, context);
//			}
//		}.start();
//	}

	public void destroyvideo() {
		mRecorder.stopRecording();
		mRecorder.release();
		if (mRecorder != null) {
			mRecorder.release();
		}
System.out.println(" 录像结束");
	}

	Callback surfaceHolderCallback = new Callback() {
		@Override
		public void surfaceDestroyed(SurfaceHolder arg0) {
			surfaceView1 = null;
			surfaceHolder = null;
			mRecorder = null;
		}

		@Override
		public void surfaceCreated(SurfaceHolder arg0) {
			surfaceHolder = arg0;
		}

		@Override
		public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
				int arg3) {
			surfaceHolder = arg0;
		}
	};
}
