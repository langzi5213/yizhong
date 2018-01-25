package com.waji.local;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.content.Context;

	public class SaveRead {
		
		 public static void saveUserInfoToFile(Context context, String data ) throws Exception {
			 FileOutputStream fos = context.openFileOutput("info.txt",Context.MODE_PRIVATE);
		     fos.write((data + "##" ).getBytes());
			 fos.close();
			 System.out.println("创建手机文件成功");
		}

		 
		public static String readUserInfoFromFile(Context context) throws Exception {
			File file = new File(context.getFilesDir(), "info.txt");
			FileInputStream fis = new FileInputStream(file);

			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = br.readLine();
			fis.close();
			br.close();
			return line;
		}
	}

