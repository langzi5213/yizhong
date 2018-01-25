package com.waji.local;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.waji.http.SendImage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Environment;

public class SaveImage {
	// public static void saveimage(Context context, final byte[] data,
	// Camera camera) {
	// System.out.println("保存照片到手机");
	// String number = SharedPrefer.ReadNumber(context) + 1 + "";
	// String fileName = number + ".jpg";
	//
	// File sdRoot = Environment.getExternalStorageDirectory();
	// String dir = "/QRcode/"
	// + new SimpleDateFormat("yyyy—MM—dd").format(new Date())
	// .toString() + "/";
	// SendImage.serviceImagePath =sdRoot+ dir+fileName;
	// File mkDir = new File(sdRoot, dir);
	// if (!mkDir.exists())
	// mkDir.mkdirs();
	// File pictureFile = new File(sdRoot, dir + fileName);
	// if (!pictureFile.exists()) {
	// try {
	// pictureFile.createNewFile();
	// Camera.Parameters parameters = camera.getParameters();
	// Size size = parameters.getPreviewSize();
	// YuvImage image = new YuvImage(data,
	// parameters.getPreviewFormat(), size.width, size.height,
	// null);
	// FileOutputStream filecon = new FileOutputStream(pictureFile);
	// image.compressToJpeg(
	// new Rect(0, 0, image.getWidth(), image.getHeight()),
	// 90, filecon);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }

	@SuppressLint("SimpleDateFormat")
	public static void saveServiceimage(Context context, byte[] data,
			Camera camera) {
		System.out.println("保存照片到手机");
		String number = SharedPrefer.ReadNumber(context) + 1 + "";
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());
		String str = formatter.format(curDate);
		File sdRoot = Environment.getExternalStorageDirectory();
		String dir = "/QRcode/"
				+ new SimpleDateFormat("yyyy—MM—dd").format(new Date())
						.toString() + "/";
		String fileName = number + "--" + str + ".jpg";

		SendImage.serviceImagePath = sdRoot + dir + fileName;

		File mkDir = new File(sdRoot, dir);

		if (!mkDir.exists())
			mkDir.mkdirs();
		File pictureFile = new File(sdRoot, dir + fileName);
		if (!pictureFile.exists()) {
			try {
				pictureFile.createNewFile();
				Camera.Parameters parameters = camera.getParameters();
				Size size = parameters.getPreviewSize();
				YuvImage image = new YuvImage(data,
						parameters.getPreviewFormat(), size.width, size.height,
						null);
				FileOutputStream filecon = new FileOutputStream(pictureFile);
				image.compressToJpeg(
						new Rect(0, 0, image.getWidth(), image.getHeight()),
						90, filecon);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
