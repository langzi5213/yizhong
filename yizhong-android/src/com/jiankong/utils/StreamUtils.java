package com.jiankong.utils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {

	public static String readStreamGB(InputStream is) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];

			while ((len = is.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}

			is.close();
			return new String(bos.toByteArray(), "GBK");
		} catch (IOException e) {
			e.printStackTrace(); 
			return "出错了";
		}
	}
	
	public static String readStream(InputStream is) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];

			while ((len = is.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}

			is.close();
			return new String(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
			return "出错了";
		}
	}
}
