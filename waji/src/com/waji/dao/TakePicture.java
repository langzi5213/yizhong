package com.waji.dao;

import com.waji.local.SaveImage;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
public class TakePicture {
	private Camera mCamera = null;
	static Context context1;
 

	public void Startpicture(final Context context ) throws Exception{
		System.out.println("后摄拍照");
		 
		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
		context1 = context;
		try {
			mCamera = Camera.open();
			mCamera.startPreview();
		} catch ( Exception e) {
			e.printStackTrace();
			return;
		}
		
		surfaceChanged();
		new Thread() {
			public void run() {
				super.run();
				try {

					Thread.sleep(200);
					mCamera.stopPreview();
					mCamera.setPreviewCallback(null);
					Thread.sleep(100);
					stoppicture();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void surfaceChanged() {
		if (mCamera == null) {
			return;
		}
		mCamera.stopPreview();
		mCamera.setPreviewCallback(surfaceHolderCallback);
		mCamera.startPreview();
	}

	public void stoppicture() {
		mCamera.stopPreview();
		mCamera.setPreviewCallback(null);
		try {
			 if (mCamera != null) {
				 mCamera.release();
				mCamera = null;
			 }
		} catch ( Exception e) {
			e.printStackTrace();
			 return;
		}
		
		
	}

	static PreviewCallback surfaceHolderCallback = new PreviewCallback() {
		@Override
		public void onPreviewFrame(final byte[] data, Camera camera) {
//			if (i == 1)   
//				SaveImage.saveimage(context1,  data, camera);
//			 
//			if (i == 2) 
				SaveImage.saveServiceimage(context1,  data, camera);
			 
		}
	};
}
