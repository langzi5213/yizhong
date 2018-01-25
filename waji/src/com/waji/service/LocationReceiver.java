package com.waji.service;
import com.waji.http.SendLocation;
import com.waji.http.SendState;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;

public class LocationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
//    	NextDay day = new NextDay();
//		day.CreatFile(context);
    	 new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					SendLocation sl=new SendLocation();
					 sl.saveLocation(context);
				} catch (Exception e) {
					 e.printStackTrace();
				} 

			}
		}).start();
    	
        Intent i = new Intent(context, LocationService.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(i);
    }
}
