package com.waji.interfac;

 
import com.baidu.mapapi.SDKInitializer;
import com.waji.utils.LocationService;
 

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;
 

public class MyLeanCloudApp extends Application {
	public LocationService locationService;
    public Vibrator mVibrator;
 
    @Override
    public void onCreate() {
        super.onCreate();
//        AVOSCloud.initialize(this,"ukCtHDUawSJEXanxwS3WheNM-gzGzoHsz","R8S656Nbk0HCvOt9nsrJYFGK");
//        AVOSCloud.setDebugLogEnabled(true);
//        AVAnalytics.enableCrashReport(this, true);
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());  
    }
}
