package com.jiankong.local.SQLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DeviceDao {
	private DeviceSQLiteOpen helper;

	public DeviceDao(Context context) {
		helper = new DeviceSQLiteOpen(context);
	}

	public long add(String id, String deviceId, String devicename,
			String username, String phone) {
		if (find(id, deviceId)) {
			return 0;
		}

		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("id", id);
		values.put("deviceId", deviceId);
		values.put("devicename", devicename);
		values.put("username", username);
		values.put("phone", phone);

		long idd = db.insert("devices", null, values);
		db.close();
		return idd;
	}

	public boolean find(String id, String deviceId) {

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("devices", new String[] { "id", "deviceId" },
				"id=?and deviceId=?", new String[] { id, deviceId }, null,
				null, null);
		boolean result = cursor.moveToNext();
		cursor.close();
		db.close();
		return result;
	}

	public boolean delete(String id, String deviceId) {
		SQLiteDatabase db = helper.getWritableDatabase();
		int l = db.delete("devices", "id=?and deviceId=?", new String[] { id,
				deviceId });
		db.close();
		return true;
	}

	public String findname(String id, String deviceId) {
		String name = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("devices", new String[] { "id", "deviceId",
				"devicename" }, "id=?and deviceId=?", new String[] { id,
				deviceId }, null, null, null);
		while (cursor.moveToNext()) {
			name = cursor.getString(2);
		}
		cursor.close();
		db.close();
		return name;
	}

	public List<Map<String, Object>> findDevicrlist(Context context,String id) {
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Cursor cursor = db.query("devices", new String[] { "id", "deviceId",
				"devicename" }, "id=?", new String[] { id}, null, null, null);
		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();

			String deiviceId = cursor.getString(1);
			String devicename = cursor.getString(2);
			// System.out.println("获取到的userlist"+id);
			map.put("deiviceId", deiviceId);
			map.put("devicename", devicename);
			data.add(map);
		}

		cursor.close();
		db.close();
		return data;
	}

	public List<Map<String, Object>> findSiji(Context context,String id) {
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Cursor cursor = db.query("devices", new String[] { "deviceId", "devicename",
				"username", "phone","id" }, "id=?", new String[] {id}, null, null, null);
		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();

			String deviceId = cursor.getString(0);
			String devicename = cursor.getString(1);
			String username = cursor.getString(2);
			String phone = cursor.getString(3);

			// System.out.println("获取次数状态" + id+name+number + state);

			map.put("deviceId", deviceId);
			map.put("devicename", devicename);
			map.put("username", username);
			map.put("phone", phone);

			data.add(map);
		}

		cursor.close();
		db.close();
		return data;
	}

	public List<Map<String, Object>> findAllData(Context context,String id) {
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Cursor cursor = db.query("devices", new String[] { "deviceId", "devicename",
				"username", "phone","id" }, "id=?",  new String[] {id}, null, null, null);
		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			try {

				String deviceId = cursor.getString(0);
				String devicename = cursor.getString(1);
				String username = cursor.getString(2);
				String phone = cursor.getString(3);
				 
				
				String number = findnumber(context, id,deviceId);
				String state = findstate(context, id,deviceId);
				map.put("deviceId", deviceId);
				map.put("devicename", devicename);
				map.put("username", username);
				map.put("phone", phone);
				map.put("number", number);
				map.put("state", state);

				data.add(map);
			} catch (Exception e) {
				return null;
			}

		}

		cursor.close();
		db.close();
		return data;
	}

	public String findnumber(Context context, String id,String deviceId) {
		 DetailsDao details = new DetailsDao(context);
		 List<Map<String, Object>> numberlist = details.findNumber(id,deviceId);
		 Map<String, Object> numbermap;
			try {
				numbermap = numberlist.get(0);
			} catch (Exception e) {
				return null;
			}
			final String number = (String) numbermap.get("number");
			//System.out.println("得到的number"+number);
			return number;
		 
		 
		 
//		number = details.findNumber(id,deviceId);
//		if (number == null) {
//			number = details.findNumber(id,deviceId);
//		}
//		System.out.println("得到的number"+number);
//		return number;
		

	}

	public String findstate(Context context, String id,String deviceId) {
		StateDao details = new StateDao(context);
		List<Map<String, Object>> statelist = details.findState(id,deviceId);
		Map<String, Object> statemap;
		try {
			statemap = statelist.get(0);
		} catch (Exception e) {
			return null;
		}
		final String state = (String) statemap.get("state");
	//	System.out.println("得到的state"+state);
		return state;
	}

}
