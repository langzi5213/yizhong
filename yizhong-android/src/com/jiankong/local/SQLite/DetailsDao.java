package com.jiankong.local.SQLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DetailsDao {
	private DetailsSQLiteOpen helper;

	public DetailsDao(Context context) {
		helper = new DetailsSQLiteOpen(context);
	}

	public long add(String id, String deviceId, String number, String battery,
			String address, String imageUrl, String videoUrl, String date) {
		if (find(deviceId, date)) {
			return 0;
		}

		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("id", id);
		values.put("deviceId", deviceId);
		values.put("number", number);
		values.put("battery", battery);
		values.put("address", address);
		values.put("imageUrl", imageUrl);
		values.put("videoUrl", videoUrl);
		values.put("date", date);

		long idd = db.insert("details", null, values);
		db.close();
		return idd;
	}

	public boolean find(String deviceId, String date) {

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("details",
				new String[] { "deviceId", "date" }, "deviceId=?and date=?",
				new String[] { deviceId, date }, null, null, null);
		boolean result = cursor.moveToNext();
		cursor.close();
		db.close();
		return result;
	}

	public boolean delete(String id) {
		SQLiteDatabase db = helper.getWritableDatabase();
		int l = db.delete("details", "id=?", new String[] { id });
		System.out.println("查找数据库" + l);
		db.close();
		return true;
	}

	public List<Map<String, Object>> findAllNumber(String id, String deviceId) {

		SQLiteDatabase db = helper.getReadableDatabase();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Cursor cursor = db.query("details", new String[] { "id", "number",
				"date", "battery", "deviceId" }, "id=?and deviceId=?",
				new String[] { id, deviceId }, null, null, null);

		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			String number = cursor.getString(1);
			String date = cursor.getString(2);
			String battery = cursor.getString(3);

			map.put("number", number);
			map.put("date", date);
			map.put("battery", battery);
			list.add(map);

		}
		cursor.close();
		db.close();
		return list;
	}

	public List<Map<String, Object>> findNumber(String id, String deviceId) {

		SQLiteDatabase db = helper.getReadableDatabase();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Cursor cursor = db.query("details", new String[] { "id", "number",
				"deviceId" }, "id=?and deviceId=?",
				new String[] { id, deviceId }, null, null, null);
 		cursor.moveToFirst();
		Map<String, Object> map = new HashMap<String, Object>();
		String number = null;

		try {
			number = cursor.getString(1);
		} catch (Exception e) {
			cursor.close();
			db.close();
			return null;
		}
		if (number != null) {
			map.put("number", number);
			data.add(map);
		}
		cursor.close();
		db.close();
		return data;
	}

	public Map<String, Object> findAddress(String id, String deviceId,
			String date) {

		SQLiteDatabase db = helper.getReadableDatabase();
		Map<String, Object> map = new HashMap<String, Object>();
		Cursor cursor = db.query("details", new String[] { "id", "deviceId",
				"date", "address" }, "id=?and deviceId=?and date=?",
				new String[] { id, deviceId, date }, null, null, null);
		while (cursor.moveToNext()) {
			String address = cursor.getString(3);
			map.put("address", address);
		}
		cursor.close();
		db.close();
		return map;
	}

	public Map<String, Object> findurl(String id, String deviceId, String date) {

		SQLiteDatabase db = helper.getReadableDatabase();
		Map<String, Object> map = new HashMap<String, Object>();
		Cursor cursor = db.query("details", new String[] { "id", "deviceId",
				"date", "imageUrl" }, "id=?and deviceId=?and date=?",
				new String[] { id, deviceId, date }, null, null, null);
		while (cursor.moveToNext()) {
			String imageUrl = cursor.getString(3);
			map.put("imageUrl", imageUrl);

		}
		cursor.close();
		db.close();
		return map;
	}
}
