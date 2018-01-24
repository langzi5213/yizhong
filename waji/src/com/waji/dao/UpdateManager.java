package com.waji.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.waji.activity.SplashActivity;
import com.waji.daomain.PublicString;
import com.waji.utils.StreamUtils;
import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.os.Message;


public class UpdateManager {
	private static UpdateManager manager = null;
	String Url;

	private UpdateManager() {
	}

	public static UpdateManager getInstance() {
		manager = new UpdateManager();
		return manager;
	}

	public int getVersion(Context context) {
		int version = 0;
		try {
			version = context.getPackageManager().getPackageInfo(
					"com.waji.activity", 0).versionCode;
		} catch (Exception e) {
			System.out.println("获取版本号异常！");
			ToMain();
		}
		return version;
	}

	public String getVersionName(Context context) {
		String versionName = null;
		try {
			versionName = context.getPackageManager().getPackageInfo(
					"com.waji.activity", 0).versionName;
		} catch (Exception e) {
			ToMain();
			System.out.println("获取版本名异常！");
		}
		return versionName;
	}

	public String getServerVersion() {
		String serverJson = null;

		HttpURLConnection connect = null;
		try {
			URL url = new URL(PublicString.UpdateUrl);
			connect = (HttpURLConnection) url.openConnection();
			connect.setReadTimeout(2000);
			connect.setConnectTimeout(2000);

			int responseCode = connect.getResponseCode();
			System.out.println("更新版本返回码"+responseCode);
			InputStream is = connect.getInputStream();
			serverJson = StreamUtils.readStream(is);
			System.out.println("更新版本结果"+serverJson);
			if (responseCode == 200) {
//				InputStream is = connect.getInputStream();
//				serverJson = StreamUtils.readStream(is);
//				System.out.println("更新版本结果"+serverJson);
				return serverJson;
			}
		} catch (IOException e) {
			e.printStackTrace();
			new Thread() {
				public void run() {
					Message msg = new Message();
					msg.what = 2;
					SplashActivity.handler.sendMessage(msg);
				};
			}.start();

		} catch (Exception e) {
			ToMain();
			e.printStackTrace();
			System.out.println("获取服务器版本号异常！" + e);
		}
		return serverJson;
	}

	public boolean CheckVersion(final Context context) {

		new Thread() {
			public void run() {
				Looper.prepare();
				try {
					 String serverJson = manager.getServerVersion();
					if (serverJson == null) {
						return;
					} else {
						JSONArray array = new JSONArray(serverJson);
						JSONObject object = array.getJSONObject(0);
						String getServerVersion = object.getString("version");
						Url = object.getString("url");
						String getServerVersionName = object
								.getString("versionName");
						String message = object.getString("message");
						
						int version = getVersion(context);
						int serverVersion = Integer.parseInt(getServerVersion);
						System.out.println("两个版本号" + version + serverVersion);
						if (version < serverVersion) {
							new Thread() {
								public void run() {
									Looper.prepare();
									downloadApkFile(context);
									Looper.loop();
								};
							}.start();
						} else {
							ToMain();
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
					ToMain();
					System.out.println("获取服务器版本线程异常！" + e);
				}
				Looper.loop();
			};
		}.start();

		return false;
	}

	public void ToMain() {
		new Thread() {
			public void run() {
				Message msg = new Message();
				msg.what = 0;
				SplashActivity.handler.sendMessage(msg);
			};
		}.start();

	}

	public void downloadApkFile(Context context) {

		try {
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())) {
				URL serverURL = new URL(Url);
				HttpURLConnection connect = (HttpURLConnection) serverURL
						.openConnection();
				BufferedInputStream bis = new BufferedInputStream(
						connect.getInputStream());
				File apkfile = new File(PublicString.SavePath);
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(apkfile));

				int fileLength = connect.getContentLength();
				int downLength = 0;
				int progress = 0;
				int n;
				byte[] buffer = new byte[1024];
				while ((n = bis.read(buffer, 0, buffer.length)) != -1) {
					bos.write(buffer, 0, n);
					downLength += n;
					progress = (int) (((float) downLength / fileLength) * 100);
					Message msg = new Message();
					msg.what = 1;
					msg.arg1 = progress;
					SplashActivity.handler.sendMessage(msg);

				}
				bis.close();
				bos.close();
				connect.disconnect();
			}

		} catch (Exception e) {
			ToMain();
			System.out.println("下载出错！" + e);
		}
	}
}
