package com.waji.daomain;

import android.content.Context;
import android.content.Intent;
 
import com.waji.http.SendLocation;
import com.waji.http.SendState;
import com.waji.local.SharedPrefer;
import com.waji.service.LocationService;
import com.waji.service.PhotoService;
import com.waji.service.StateService;


public class InitOther {

	public void init(final Context context) {
//		NextDay day = new NextDay();
//		day.CreatFile(context);
//	 
		new Thread(new Runnable() {
			@Override
			public void run() {
				 try {SendLocation sl=new SendLocation();
					 sl.saveLocation(context);
					 
				} catch (Exception e) {
					 e.printStackTrace();
				}
				 
			}
		}).start();
		
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
		 Intent photo = new Intent(context, PhotoService.class);
		photo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startService(photo);
		
		Intent state = new Intent(context, StateService.class);
		 state.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startService(state);
		
	 
		Intent location = new Intent(context, LocationService.class);
		location.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startService(location);
	 }
}
