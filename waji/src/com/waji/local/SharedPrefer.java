package com.waji.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefer {

	public static void SaveNumber(Context context, int number) throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Numberinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt("number", number);
		editor.commit();
	}

	public static int ReadNumber(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Numberinfo",
				Context.MODE_PRIVATE);
		int number = sp.getInt("number", 0);
		return number;

	}
	
	public static void SaveCollectNumber(Context context, long number) throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Numberinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putLong("collectnumber", number);
		editor.commit();
	}

	public static long ReadcollectNumber(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Numberinfo",
				Context.MODE_PRIVATE);
		long number = sp.getLong("collectnumber", 0);
		return number;

	}
	
	
	
	public static void SaveAvgNumber(Context context, float number) throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Numberinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putFloat("avgnumber", number);
		editor.commit();
	}

	public static float ReadAvgNumber(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Numberinfo",
				Context.MODE_PRIVATE);
		float number = sp.getFloat("avgnumber", 0);
		return number;

	}
	
	public static void SaveNumber1(Context context, int number1) throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt("number1", number1);
		editor.commit();
	}

	public static int ReadNumber1(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		int number1 = sp.getInt("number1", 0);
		return number1;

	}

	public static void Savehour(Context context, int hour) throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt("hour", hour);
		editor.commit();
	}

	public static int Readhour(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		int hour = sp.getInt("hour", 0);
		return hour;

	}

	public static void SaveUsername(Context context, String username)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("username", username);
		editor.commit();
	}

	public static String ReadUsername(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		String username = sp.getString("username", "");
		return username;

	}
	
	public static void SaveLeaveStateContext(Context context, String Leave)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("Leave", Leave);
		editor.commit();
	}

	public static String ReadLeaveState(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		String Leave = sp.getString("Leave", "");
		return Leave;

	}
	
	public static void SaveFile(Context context, String file)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("file", file);
		editor.commit();
	}

	public static String ReadFile(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		String file = sp.getString("file", "");
		return file;

	}
	
	 

	public static void SavePassword(Context context, String password)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("password", password);
		editor.commit();
	}

	public static String ReadPassword(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		String password = sp.getString("password", "");
		return password;

	}

	public static void SaveState(Context context, String state)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("state", state);
		editor.commit();
	}

	public static String ReadState(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		String state = sp.getString("state", "");
		return state;

	}
	
	public static void SaveAddress(Context context, String address)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("address", address);
		editor.commit();
	}

	public static String ReadAddress(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		String address = sp.getString("address", "");
		return address;

	}
	

	public static void SaveLongitude(Context context, double longitude)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putFloat("longitude", (float)longitude);
		editor.commit();
	}

	public static double ReadLongitude(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		  float longitude = sp.getFloat("longitude", 0);
		return longitude;

	}

	public static void SaveLatitude(Context context, double latitude)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putFloat("latitude", (float) latitude);
		editor.commit();
	}

	public static double ReadLatitude(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		float latitude = sp.getFloat("latitude", 0);
		return latitude;

	}
	
	public static void SaveService(Context context, String service)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("service", service);
		editor.commit(); 
	}

	public static String ReadService(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		String service = sp.getString("service", "");
		return service;

	}
	
	public static void SaveBattery(Context context, int battery)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt("battery", battery);
		editor.commit(); 
	}

	public static int ReadBattery(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		int battery = sp.getInt("battery", 0);
		return battery;

	}
	
	public static void SaveBatteryState(Context context, int batterystate)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt("batterystate", batterystate);
		editor.commit(); 
	}

	public static int ReadBatteryState(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		int batterystate = sp.getInt("batterystate", 0);
		return batterystate;

	}
	 

}
