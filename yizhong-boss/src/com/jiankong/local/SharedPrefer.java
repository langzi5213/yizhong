package com.jiankong.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefer {
	public static void SaveToken(Context context, String token)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("token", token);
		editor.commit();
	}

	public static String ReadToken(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		String token = sp.getString("token", "");
		return token;

	}
	
//	public static void SaveWarnState(Context context, String warnstate)
//			throws Exception {
//		SharedPreferences sp = context.getSharedPreferences("Userinfo",
//				Context.MODE_PRIVATE);
//		Editor editor = sp.edit();
//		editor.putString("warnstate", warnstate);
//		editor.commit();
//	}
//
//	public static String ReadWarnState(Context context) {
//		SharedPreferences sp = context.getSharedPreferences("Userinfo",
//				Context.MODE_PRIVATE);
//		String warnstate = sp.getString("warnstate", "");
//		return warnstate;
//
//	}
//	
//	
//	public static void SaveWarnBattery(Context context, String warnBattery)
//			throws Exception {
//		SharedPreferences sp = context.getSharedPreferences("Userinfo",
//				Context.MODE_PRIVATE);
//		Editor editor = sp.edit();
//		editor.putString("warnBattery", warnBattery);
//		editor.commit();
//	}
//
//	public static String ReadWarnBattery(Context context) {
//		SharedPreferences sp = context.getSharedPreferences("Userinfo",
//				Context.MODE_PRIVATE);
//		String warnBattery = sp.getString("warnBattery", "");
//		return warnBattery;
//
//	}
	public static void SaveAutoUpdate(Context context, Boolean toggle) throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Setupinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean("toggle", toggle);
		editor.commit();
	}

	public static Boolean ReadAutoUpdate(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Setupinfo",
				Context.MODE_PRIVATE);
		Boolean  toggle = sp.getBoolean("toggle", false);
		return toggle;

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

	public static void Saveid(Context context, String id)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("id", id);
		editor.commit();
	}

	public static String Readid(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		String id = sp.getString("id", "");
		return id;

	}
	
	public static void Savename(Context context, String name)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("name", name);
		editor.commit();
	}

	public static String Readname(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		String name = sp.getString("name", "");
		return name;

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
	
	
	public static void SaveDate(Context context, String date)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("date", date);
		editor.commit();
	}

	public static String ReadDate(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Userinfo",
				Context.MODE_PRIVATE);
		String date = sp.getString("date", "");
		return date;

	}

//	public static void SaveState(Context context, String state)
//			throws Exception {
//		SharedPreferences sp = context.getSharedPreferences("Userinfo",
//				Context.MODE_PRIVATE);
//		Editor editor = sp.edit();
//		editor.putString("state", state);
//		editor.commit();
//	}
//
//	public static String ReadState(Context context) {
//		SharedPreferences sp = context.getSharedPreferences("Userinfo",
//				Context.MODE_PRIVATE);
//		String state = sp.getString("state", "");
//		return state;
//
//	}

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
	
	
	
	
	
	public static void SaveBrand(Context context, String brand)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("brand", brand);
		editor.commit();
	}

	public static String ReadBrand(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		String brand = sp.getString("brand", "");
		return brand;

	}
	
	public static void SaveModel(Context context, String model)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("model", model);
		editor.commit();
	}

	public static String ReadModel(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		String model = sp.getString("model", "");
		return model;

	}
	
	public static void SaveMachine(Context context, String machine)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("machine", machine);
		editor.commit();
	}

	public static String ReadMachine(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		String machine = sp.getString("machine", "");
		return machine;

	}
	
	public static void SaveVin(Context context, String vin)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("vin", vin);
		editor.commit();
	}

	public static String ReadVin(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		String vin = sp.getString("vin", "");
		return vin;

	}
	
	public static void SavePrice(Context context, String price)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("price", price);
		editor.commit();
	}

	public static String ReadPrice(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		String price = sp.getString("price", "");
		return price;

	}
	public static void SavePhone(Context context, String phone)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("phone", phone);
		editor.commit();
	}

	public static String ReadPhone(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		String phone = sp.getString("phone", "");
		return phone;

	}
	
	public static void SavePhone1(Context context, String phone1)
			throws Exception {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("phone1", phone1);
		editor.commit();
	}

	public static String ReadPhone1(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Machineinfo",
				Context.MODE_PRIVATE);
		String phone1 = sp.getString("phone1", "");
		return phone1;

	}

}
