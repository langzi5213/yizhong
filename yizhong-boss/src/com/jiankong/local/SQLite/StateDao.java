package com.jiankong.local.SQLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StateDao {
	private StateSQLiteOpen helper;

	public StateDao(Context context) {
		helper = new StateSQLiteOpen(context);
	}

	public void add(String id,String deviceId, String state, String date) {
		// System.out.println("保存状态信息" + id + date);
		if (find(id,deviceId)) {
			delete(id,deviceId);
		}
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("id", id);
		values.put("deviceId", deviceId);
		values.put("state", state);
		values.put("date", date);
		long idd = db.insert("states", null, values);
		db.close();
		// System.out.println("保存成功");
	}

	public boolean delete(String id,String deviceId) {
		SQLiteDatabase db = helper.getWritableDatabase();
		int l = db.delete("states", "id=?and deviceId=?", new String[] { id,deviceId });
		db.close();
		return true;
	}

	public boolean find(String id,String deviceId) {

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("states", new String[] { "id","deviceId" }, "id=?and deviceId=?",
				new String[] { id,deviceId }, null, null, null);
		boolean result = cursor.moveToNext();
		cursor.close();
		db.close();
		return result;
	}

	public String finddate(String id,String deviceId) {
		String date = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("states", new String[] { "id", "date","deviceId" },
				"id=?and deviceId=?", new String[] { id,deviceId }, null, null, null);
		while (cursor.moveToNext()) {
			date = cursor.getString(1);
		}
		cursor.close();
		db.close();
		return date;
	}

	public List<Map<String, Object>> findState(String id,String deviceId) {
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Cursor cursor = db.query("states",
				new String[] { "id", "state", "date","deviceId" }, "id=?and deviceId=?",
				new String[] { id,deviceId }, null, null, null);
		cursor.moveToFirst();
		Map<String, Object> map = new HashMap<String, Object>();
		String state=null;
		String date=null;
		try {
			  state = cursor.getString(1);
			  date = cursor.getString(2);
		} catch (Exception e) {
			return null;
		}
		if (state != null && date != null) {
			// System.out.println("从数据库得到的状态数据" + id + state + date);
			map.put("state", state);
			map.put("date", date);
			data.add(map);
		}
		cursor.close();
		db.close();
		return data;
	}
	 

}
