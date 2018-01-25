package com.waji.utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
	
	public static byte[] File2Bytes(File file) {
		int byte_size = 1024;
		byte[] b = new byte[byte_size];
		try {
		FileInputStream fileInputStream = new FileInputStream(file);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
		byte_size);
		for (int length; (length = fileInputStream.read(b)) != -1;) {
		outputStream.write(b, 0, length);
		}
		fileInputStream.close();
		outputStream.close();
		return outputStream.toByteArray();
		} catch (IOException e) {
		e.printStackTrace();
		}
		return null;
		}
}
