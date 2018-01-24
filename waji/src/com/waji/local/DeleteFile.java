package com.waji.local;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

public class DeleteFile {

	@SuppressLint("SimpleDateFormat")
	public void Delete(Context context) {
		// init(context);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy—MM—dd");
		Date curDate = new Date(System.currentTimeMillis());
		String str = formatter.format(curDate);
		String file = Environment.getExternalStorageDirectory()
				.getAbsoluteFile() + "/QRcode/" + str;
		File mFile = new File(file);
		if (!mFile.exists()) {
			try {
				mFile.mkdir();
				SharedPrefer.SaveNumber(context, 0);

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		find(context, file);

	}

	public void find(Context context, String file) {
		System.out.println("获取文件file" + file);
		String result = null;
		Long size = getAvailableExternalMemorySize(context);
		System.out.println("创建手机文件" + file);
		System.out.println("创建获取的手机可用空间" + size);
		long s = 262144;

		try {
			result = SaveRead.readUserInfoFromFile(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("获取文件result" + file + "\n" + result);
		if (result == null) {
			result = file;
			try {
				SaveRead.saveUserInfoToFile(context, result);
				String result1 = SaveRead.readUserInfoFromFile(context);
				System.out.println("获取文件后result" + result1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 } else {
			String[] infos = result.split("##");
			int k = infos.length;
			if (size < s) {
				for (int i = 0; i < 5; i++) {
					File mFile = new File(infos[i]);
					deleteSDFile(mFile);
					System.out.println("删除" + infos[i]);
				}

				result = infos[5] + "##";
				System.out.println("删除后" + result);
				for (int l = 6; l < k; l++) {
					result = result + infos[l] + "##";
					System.out.println("删除后1" + result);
				}

			}
			if (!file.equals(infos[k-1])) {
				result = result + file;
				try {
					SaveRead.saveUserInfoToFile(context, result);
					String result1 = SaveRead.readUserInfoFromFile(context);
					System.out.println("获取文件后result" + result1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}  
		}
//
//		try {
//			SaveRead.saveUserInfoToFile(context, file);
//			String result1 = SaveRead.readUserInfoFromFile(context);
//			System.out.println("获取文件后result" + result1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	public boolean deleteSDFile(File file) {
		System.out.println("执行删除文件操作");
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File f : files) {
					deleteSDFile(f);
				}
			}
			file.delete();
		}
		return true;
	}

	@SuppressLint("NewApi")
	public static Long getAvailableExternalMemorySize(Context context) {
		File file = Environment.getExternalStorageDirectory();
		StatFs statFs = new StatFs(file.getPath());
		long availableBlocksLong = statFs.getAvailableBlocks();
//		long availableBlocksLong = statFs.getAvailableBlocksLong();   修改前
		return availableBlocksLong;
	}

	public void init(Context context) {
		System.out.println("执行创建操作");

		try {
			SaveRead.saveUserInfoToFile(
					context,
					"/storage/emulated/0/QRcode/2017—08—11##/storage/emulated/0/QRcode/2017—08—12##/storage/emulated/0/QRcode/2017—08—13##/storage/emulated/0/QRcode/2017—08—14##/storage/emulated/0/QRcode/2017—08—15##/storage/emulated/0/QRcode/2017—08—16##/storage/emulated/0/QRcode/2017—08—17##/storage/emulated/0/QRcode/2017—08—18##/storage/emulated/0/QRcode/2017—08—19##/storage/emulated/0/QRcode/2017—08—20##/storage/emulated/0/QRcode/2017—08—21##/storage/emulated/0/QRcode/2017—08—22##/storage/emulated/0/QRcode/2017—08—23##/storage/emulated/0/QRcode/2017—08—24##/storage/emulated/0/QRcode/2017—08—25##/storage/emulated/0/QRcode/2017—08—26##/storage/emulated/0/QRcode/2017—08—27##/storage/emulated/0/QRcode/2017—08—28##/storage/emulated/0/QRcode/2017—08—29##/storage/emulated/0/QRcode/2017—08—30##/storage/emulated/0/QRcode/2017—08—31##/storage/emulated/0/QRcode/2017—09—01##/storage/emulated/0/QRcode/2017—09—02##/storage/emulated/0/QRcode/2017—09—03##/storage/emulated/0/QRcode/2017—09—04##/storage/emulated/0/QRcode/2017—09—05##/storage/emulated/0/QRcode/2017—09—06##/storage/emulated/0/QRcode/2017—09—07");
			System.out.println("创建手机文件");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = null;
		try {
			result = SaveRead.readUserInfoFromFile(context);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("获取文件result" + "\n" + result);
	}
}
