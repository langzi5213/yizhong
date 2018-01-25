package com.jiankong.interfac;

import android.os.Environment;

public interface PublicString {
  String UpdateUrl="http://www.gcjxglzx.com:8071/update/yizhongversion.aspx";
  
  String AddDeviceUrl = "http://www.gcjxglzx.com:8071/device/save.auth";
  String GetAdvertisementurl="http://10.129.55.55:8080/admin/timg.jpeg";
  String GetDeviceUrl = "http://www.gcjxglzx.com:8071/device/list.auth?&pageSize=10&phone=";
  String LoginUrl = "http://www.gcjxglzx.com:8071/appUser/appLogin";
  String RedisterUrl = "http://www.gcjxglzx.com:8071/appUser/save";
  String UpdatePasswordUrl = "http://www.gcjxglzx.com:8071/appUser/updatePassword";
  String GetStateUrl = "http://www.gcjxglzx.com:8071/deviceState/";
  String GetLocationUrl = "http://www.gcjxglzx.com:8071/deviceLocation/";
  String GetDetailUrl="http://www.gcjxglzx.com:8071/deviceJpg/list.auth?pageSize=500&mac=";
  String SavePath = Environment.getExternalStorageDirectory()+ "/yizhong.apk"; 
}
