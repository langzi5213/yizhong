package com.waji.service;
 

import com.waji.activity.MainActivity;
import com.waji.local.SharedPrefer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Restart extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			SharedPrefer.SaveLeaveStateContext(context, "leaveoff");
		} catch (Exception e) {
			 e.printStackTrace();
		}
		System.out.println("接收到屏幕退出广播");
		 Intent newIntent = context.getPackageManager()
				.getLaunchIntentForPackage("com.waji.activity");
		context.startActivity(newIntent);
//		 Intent i = new Intent(context, MainActivity.class);
//	        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	        context.startActivity(i);
	}

}
