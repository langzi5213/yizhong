package com.jiankong.service;

import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.jiankong.activity.Frament;
import com.jiankong.activity.R;
import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DetailsDao;
import com.jiankong.local.SQLite.DeviceDao;
import com.jiankong.local.SQLite.StateDao;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.Notification.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class WarnService extends Service {
	Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private void sendNotification() {
		DeviceDao devices = new DeviceDao(getApplicationContext());
		String id = SharedPrefer.Readid(getApplicationContext());

		List<Map<String, Object>> user = devices.findDevicrlist(
				getApplicationContext(), id);
		for (int i = 0; i < user.size(); i++) {
			Map<String, Object> usermap = user.get(i);
			String deviceId = (String) usermap.get("deiviceId");
			String devicename = (String) usermap.get("devicename");
			System.out.println("获取是否正常deviceId" +deviceId + devicename);
			String number = null,state = null;
			try {
				DetailsDao details = new DetailsDao(getApplicationContext());
				List<Map<String, Object>> numberlist = details.findNumber(id,
						deviceId);
				Map<String, Object> numbermap = numberlist.get(0);
				  number = (String) numbermap.get("number");
				 

				StateDao states = new StateDao(getApplicationContext());
				List<Map<String, Object>> statelist = states.findState(id,
						deviceId);
				Map<String, Object> statemap = statelist.get(0);
				  state = (String) statemap.get("state");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(devicename+"出现异常");
				continue;
			 }
			System.out.println("获取是否正常state+number" +number+ state);
			
			if (number.equals("签退")
					&& (state.equals("怠速") || state.equals("工作"))) {
				System.out.println("检测到设备不正常" + devicename + number + state);
				NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				Builder builder = new Notification.Builder(this);
				builder.setSmallIcon(R.drawable.ic_launcher);
				builder.setTicker(devicename + "设备状态不正常");
				builder.setContentTitle(devicename + "设备状态不正常");
				builder.setContentText("司机已签退  设备" + state + "中，请确认");
				builder.setDefaults(Notification.DEFAULT_ALL);
				Intent intent = new Intent(this, Frament.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(this,
						0, intent, 0);
				builder.setContentIntent(pendingIntent);
				Notification notification = builder.build();
				manager.notify(100, notification);
			}
		}
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		sendNotification();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
