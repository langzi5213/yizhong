package com.waji.local;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.os.Environment;

public class SaveQRcode {
	public void saveQRcode(Context context, byte[] data) {

		File mFile = new File(Environment.getExternalStorageDirectory()
				.getAbsoluteFile() + "/QRcode");
		if (!mFile.exists()) {
			mFile.mkdir();
		}

		File pictureFile = new File(mFile + "/" + "qrcode.jpg");

		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
			fos.write(data);
			fos.close();
		} catch (Exception e) {
		}

	}
}
