package com.waji.dao;

import java.io.IOException;

import com.waji.local.SaveImage;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;

import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class TakePhoto {

	private Camera mCamera = null;
	static Context context1;
 

	public void Startphoto(final Context context ) throws Exception {
		System.out.println("前摄开始");
		context1 = context;

		if (mCamera != null) {
			return;
		} else {
			try {
				System.out.println("打开相机");
				mCamera = Camera.open(1);
				mCamera.startPreview();
				surfaceChanged();
				 
				new Thread() {
					@Override
					public void run() {
						super.run();
						try {
							Thread.sleep(200);
							mCamera.stopPreview();
							mCamera.setPreviewCallback(null);
							Thread.sleep(100);
							stopphoto();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}.start();
			} catch (Exception e) {
				System.out.println("打开相机失败");
				e.printStackTrace();
				return;
			}
		}

	}

	public void surfaceChanged() {
		if (mCamera == null) {
			return;
		}
		mCamera.stopPreview();
		mCamera.setPreviewCallback(surfaceHolderCallback);
		mCamera.startPreview();
	}

	public void stopphoto() {
		mCamera.stopPreview();
		mCamera.setPreviewCallback(null);
		try {
			System.out.println("前摄结束");
			if (mCamera != null) {
				mCamera.release();
				mCamera = null;
				System.out.println("前摄结束1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("前摄结束有异常");
			return;
		}
	}

	static PreviewCallback surfaceHolderCallback = new PreviewCallback() {
		@Override
		public void onPreviewFrame(final byte[] data, Camera camera) {
//			if (i == 1)
//				SaveImage.saveServiceimage(context1, data, camera);
//			if (i == 2)
				SaveImage.saveServiceimage(context1, data, camera);
		 }
	};
}

// SurfaceHolder mSurfaceHolder;
// private Camera mCamera = null;
// static Context context1;
// private static int i;
//
// public void Startphoto(final Context context, SurfaceView surfaceView) {
//
// context1 = context;
// open();
// mSurfaceHolder = surfaceView.getHolder(); // 绑定SurfaceView，取得SurfaceHolder对象
// mSurfaceHolder.addCallback(surfaceHolderCallback1);
// mSurfaceHolder.setFixedSize(176, 144); // 预览大小設置
// mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//
// }
//
// Callback surfaceHolderCallback1 = new Callback() {
// @Override
// public void surfaceDestroyed(SurfaceHolder arg0) {
// if (mCamera != null) {
// mCamera.release();
// mCamera = null;
// }
// }
//
// @Override
// public void surfaceCreated(SurfaceHolder arg0) {
// try {
// if (mCamera != null) {
// mCamera.setPreviewDisplay(mSurfaceHolder);
// mCamera.startPreview();
// } else {
// mCamera = Camera.open(1);
// mCamera.setPreviewDisplay(mSurfaceHolder);
// mCamera.startPreview();
// }
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
//
// @Override
// public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
// int arg3) {
// if (mCamera == null) {
// return;
// }
// mCamera.stopPreview();
// mCamera.setPreviewCallback(surfaceHolderCallback);
// mCamera.startPreview();
// }
// };
//
// public void open() {
// mCamera = Camera.open(1);
// }
//
// public void stop() {
// if (mCamera != null) {
// mCamera.release();
// mCamera = null;
//
// }
// }
//
// PreviewCallback surfaceHolderCallback = new PreviewCallback() {
// @Override
// public void onPreviewFrame(final byte[] data, Camera camera) {
//
// SaveImage.saveServiceimage(context1, data, mCamera);
// }
// };
//
// }

