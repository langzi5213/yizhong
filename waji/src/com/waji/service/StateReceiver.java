package com.waji.service;

import com.waji.activity.MainActivity;
import com.waji.http.SendLocation;
import com.waji.http.SendState;
import com.waji.local.SharedPrefer;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.view.WindowManager;

public class StateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
           new Thread(new Runnable() {
			@Override
			public void run() {
				 try {
					 SendState ss=new SendState();
					 ss.sendState(context); 
				} catch (Exception e) {
					 e.printStackTrace();
				} 

			}
		}).start();
    	String leave=SharedPrefer.ReadLeaveState(context);
    	System.out.println("输出"+leave);
    	if(leave.equals("leaveoff")){
    		System.out.println("输出activity"+leave);
    		 Intent newIntent = context.getPackageManager()
    					.getLaunchIntentForPackage("com.waji.activity");
    			context.startActivity(newIntent);
    	}
        Intent i = new Intent(context, StateService.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(i);
    }
   
	 
    
}
