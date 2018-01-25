package com.jiankong.local.SQLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LocationDao {
	private LocationSQLiteOpen helper;

	public LocationDao(Context context) {
		helper = new LocationSQLiteOpen(context);
	}

	public void add(String id,String deviceId, String devicename, String latitude,
			String longitude, String date) {
		if (find(id,deviceId)) {
			delete(id,deviceId);
		}
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("id", id);
		values.put("deviceId", deviceId);
		values.put("devicename", devicename);
		values.put("latitude", latitude);
		values.put("longitude", longitude);
		values.put("date", date);
		long idd = db.insert("location", null, values);
		db.close();
	}

	public boolean delete(String id,String deviceId) {
		SQLiteDatabase db = helper.getWritableDatabase();
		int l = db.delete("location", "id=?and deviceId=?", new String[] { id,deviceId });
		db.close();
		return true;
	}

	public boolean find(String id,String deviceId) {

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("location",
				new String[] { "id","deviceId" }, "id=?and deviceId=?",
				new String[] { id ,deviceId}, null, null, null);
		boolean result = cursor.moveToNext();
		cursor.close();
		db.close();
		return result;
	}

	public List<Map<String, Object>> findAllData(String id) {
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Cursor cursor = db.query("location", new String[] { "devicename",
				"latitude", "longitude","id" }, "id=?", new String[]{id}, null, null, null);
		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();

			String devicename = cursor.getString(0);
			String latitude = cursor.getString(1);
			String longitude = cursor.getString(2);
		 
 map.put("devicename", devicename);
			map.put("latitude", latitude);
			map.put("longitude", longitude);
			data.add(map);
		}

		cursor.close();
		db.close();
		return data;
	}

	public String findid(String latitude, String longitude) {
		String id = null;
		SQLiteDatabase db = helper.getReadableDatabase();

		Cursor cursor = db.query("location", new String[] { "deviceId",
				"latitude", "longitude" }, "latitude=?and longitude=?",
				new String[] { latitude, longitude }, null, null, null);

		while (cursor.moveToNext()) {
			id = cursor.getString(0);

		}

		cursor.close();
		db.close();
		return id;
	}

}
