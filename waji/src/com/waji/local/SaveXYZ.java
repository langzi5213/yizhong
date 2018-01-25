package com.waji.local;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Environment;

import com.waji.local.SharedPrefer;

public class SaveXYZ {

	public static void save(Context context, float x, float y, float z) {

		try {
			String x1 = String.valueOf(x);
			String y1 = String.valueOf(y);
			String z1 = String.valueOf(z);
			String xyz = "     x:" + x1 + "   y:" + y1 + "     z:" + z1+"\n";

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy—MM—dd");
			SimpleDateFormat formatter1 = new SimpleDateFormat(
					"yyyy—MM—dd  HH:mm:ss ");

			Date curDate = new Date(System.currentTimeMillis());
			String str = formatter.format(curDate);
			String str1 = formatter1.format(curDate);

			String s = str1 + xyz;

			File mFile = new File(Environment.getExternalStorageDirectory()
					.getAbsoluteFile() + "/"  + "QRcode/"+str);

			if (!mFile.exists()) {
				mFile.mkdir();
			}
			File locationFile = new File(mFile + "/" + str + "--.txt");
			if (!mFile.exists()) {
				mFile.mkdir();
			}
			byte[] sb = s.getBytes();
			FileOutputStream fos = new FileOutputStream(locationFile,true);
		 
			fos.write(sb);
			fos.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}
