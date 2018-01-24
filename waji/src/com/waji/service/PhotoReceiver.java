package com.waji.service;

 
import com.waji.dao.TakePicture;
import com.waji.http.SendImage;
import com.waji.local.SharedPrefer;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;

public class PhotoReceiver extends BroadcastReceiver {
	TakePicture stare;
	 

	@Override
	public void onReceive(final Context context, Intent intent) {
//		NextDay day = new NextDay();
//		day.CreatFile(context);
//		 Intent intent1 = new Intent(context,
//		 VideoActivity.class);
//		 intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		 context.startActivity(intent1);
		
		int number = SharedPrefer.ReadNumber(context);
		int number1 = SharedPrefer.ReadNumber1(context);
		
		try {
			if (number == number1) {
				String service = SharedPrefer.ReadService(context);
				System.out.println("开启拍照广播" + number + "....." + number1+"...."+service);
				
				if (service.equals("stop")) {
					SharedPrefer.SaveService(context, "star");
					star(context);
				} else {
					Intent i = new Intent(context, PhotoService.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startService(i);
				}
			} else {
				SharedPrefer.SaveNumber1(context, number);
				Intent i = new Intent(context, PhotoService.class);
				context.startService(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Intent i = new Intent(context, PhotoService.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startService(i);
		}

	}

	public void star(final Context context) { 
		System.out.println("服务中拍照");
		stare = new TakePicture();
//		photo = new TakePhoto();
		new Thread() {
			@Override
			public void run() {
				Message msg = new Message();
				super.run();
				try {
					stare.Startpicture(context);
					Thread.sleep(2000);

//					photo.Startphoto(context, 2);
//					Thread.sleep(2000);
					
				 

					SendImage image = new SendImage();
					image.send(context, "1");
					
					SharedPrefer.SaveService(context, "stop");
					String service = SharedPrefer.ReadService(context);
					System.out.println("结束拍照广播"+"...."+service);
					Intent i = new Intent(context, PhotoService.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startService(i);
				} catch (Exception e) {
					e.printStackTrace();
					Intent i = new Intent(context, PhotoService.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startService(i);
				}
			}
		}.start();
	 }
}
