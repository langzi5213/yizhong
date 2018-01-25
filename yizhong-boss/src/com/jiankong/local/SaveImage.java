package com.jiankong.local;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class SaveImage {
	
	public String save(Context context, byte[] data){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy—MM—dd HH:mm:ss");
		 
		Date curDate = new Date(System.currentTimeMillis());
		String str = formatter.format(curDate);
		 String path = "/sdcard/" + str + ".jpg";
 		 File pictureFile = new File(path);

		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
			fos.write(data);
			fos.close();
		} catch (Exception e) {
			Log.d("error", "Error accessing file: " + e.getMessage());
		}
		return path;
	
	}
	
	

}
