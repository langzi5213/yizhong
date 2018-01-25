package com.jiankong.httpservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.jiankong.interfac.PublicString;
import com.jiankong.local.SharedPrefer;
import com.jiankong.utils.ByteBitmap;
import com.jiankong.utils.StreamUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class GetAdvertisement {
	public static Bitmap get() throws Exception {
		HttpURLConnection connect = null;
 
			URL url = new URL(PublicString.GetAdvertisementurl);
			connect = (HttpURLConnection) url.openConnection();
			connect.setRequestMethod("GET");
			connect.setReadTimeout(2000);
			connect.setConnectTimeout(2000);

			int responseCode = connect.getResponseCode();
			//System.out.println("获取广告返回码:" + responseCode);
			if (responseCode == 200) {
				InputStream is = connect.getInputStream();
				Bitmap result = BitmapFactory.decodeStream(is);
				//System.out.println("获取广告结果" + result);
				ByteBitmap byt = new ByteBitmap();
				byte[] da = byt.Bitmap2Bytes(result);
				
				 
				 
				saveAdvertisement(da);
			}
			return null;
	 }

	public static void saveAdvertisement(byte[] data) {

		File mFile = new File(Environment.getExternalStorageDirectory()
				.getAbsoluteFile() + "/Advertisement");
		if (!mFile.exists()) {
			mFile.mkdir();
		}

		File pictureFile = new File(mFile + "/" + "1.jpg");

		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
			fos.write(data);
			fos.close();
		} catch (Exception e) {
		}

	}

}
