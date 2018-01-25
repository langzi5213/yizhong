package com.jiankong.service;

import com.jiankong.httpservice.GetDevices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.SystemClock;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, Intent intent) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					GetDevices.get(context);
					SystemClock.sleep(3 * 1000);
					Intent i = new Intent(context, WarnService.class);
					 i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startService(i);
                 } catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();
		
		
		
		Intent i1 = new Intent(context, AlarmService.class);
		 i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startService(i1);
	}
}
