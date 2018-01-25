package com.jiankong.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jiankong.activity.ActivitySplash;
import com.jiankong.activity.WodeSetup;
import com.jiankong.interfac.PublicString;
import com.jiankong.utils.StreamUtils;

import android.R.integer;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

public class UpdateManager {
	private static UpdateManager manager = null;
	String Url;
	String TAG;

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
					"com.jiankong.activity", 0).versionCode;
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
					"com.jiankong.activity", 0).versionName;
		} catch (Exception e) {
			ToMain();
			System.out.println("获取版本名异常！");
		}
		return versionName;
	}

	public String getServerVersion() {
		String serverJson = null;

		HttpURLConnection connect = null;
		System.out.println("获取服务器版本getServerVersion");
		try {
			URL url = new URL(PublicString.UpdateUrl);
			connect = (HttpURLConnection) url.openConnection();
			connect.setReadTimeout(2000);
			connect.setConnectTimeout(2000);

			int responseCode = connect.getResponseCode();
			System.out.println("网络返回码.........." + responseCode);
			if (responseCode == 200) {
				InputStream is = connect.getInputStream();
				serverJson = StreamUtils.readStream(is);
				System.out.println("结果"+serverJson);
				return serverJson;
			}
		} catch (IOException e) {
			e.printStackTrace();
			new Thread() {
				public void run() {
					Message msg = new Message();
					msg.what = 2;
					if (TAG.equals("SplashActivity")) {
						ActivitySplash.handler.sendMessage(msg);
					}
					if (TAG.equals("WodeSetup")) {
						WodeSetup.handler.sendMessage(msg);
					}
				};
			}.start();

		} catch (Exception e) {
			System.out.println("执行ToMain");
			ToMain();
			System.out.println("获取服务器版本号异常！" + e);
		}
		return serverJson;
	}

	public boolean CheckVersion(final Context context, String tag) {
		TAG = tag;

		new Thread() {
			public void run() {
				Looper.prepare();
				System.out.println("获取服务器版本CheckVersion");
				try {
					String serverJson = manager.getServerVersion();
					if (serverJson == null) {
						return;
					} else {
						JSONArray array = new JSONArray(serverJson);
						System.out.println("array"+array);
						JSONObject object = array.getJSONObject(0);
						System.out.println("object"+object);
						String Version = object.getString("version");
						System.out.println("version"+Version);
						Url = object.getString("url");
						String serverVersionName = object
								.getString("versionName");
						String message = object.getString("message");
						int serverVersion = Integer.parseInt(Version);

						int version = getVersion(context);
						String versionName = manager.getVersionName(context);

						if (version < serverVersion) {
							AlertDialog.Builder builder = new Builder(context);
							builder.setTitle("检测到新版本,是否更新？");
							builder.setMessage("当前版本：" + versionName + "\n\n"
									+ "更新版本：" + serverVersionName + "\n\n"
									+ "更新内容：" + message);
							builder.setPositiveButton("立即更新",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int arg1) {
											new Thread() {
												public void run() {
													Looper.prepare();
													downloadApkFile(context);
													Looper.loop();
												};
											}.start();
										}
									});
							builder.setNegativeButton("下次再说",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int arg1) {
											if (TAG.equals("SplashActivity")) {
												ToMain();
											}
											if (TAG.equals("WodeSetup")) {
												new Thread() {
													public void run() {
														Message msg = new Message();
														msg.what = 3;
														WodeSetup.handler
																.sendMessage(msg);
													};
												}.start();
											}

										}
									});
							builder.show();
						} else {
							ToMain();
						}
					}
				} catch (Exception e) {
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
				if (TAG.equals("SplashActivity")) {
					ActivitySplash.handler.sendMessage(msg);
				}
				if (TAG.equals("WodeSetup")) {
					WodeSetup.handler.sendMessage(msg);
				}

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
					if (TAG.equals("SplashActivity")) {
						ActivitySplash.handler.sendMessage(msg);
					}
					if (TAG.equals("WodeSetup")) {
						WodeSetup.handler.sendMessage(msg);
					}

				}
				bis.close();
				bos.close();
				connect.disconnect();
			}

		} catch (Exception e) {
			ToMain();
			Toast.makeText(context, "下载新版本时出错，请稍候再试", 1).show();
			System.out.println("下载出错！" + e);
		}
	}
}
