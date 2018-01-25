package com.jiankong.local.SQLite;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts.Photo;
import android.text.TextUtils;

public class UserDao {
	private UserSQLiteOpen helper;
	
	public UserDao(Context context) {
	 
		helper = new UserSQLiteOpen(context);
	}

	public long add(String phone, String username) {
		if (find(phone)) {
			return 0;
		}

		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("phone", phone);
		values.put("username", username);
		long idd = db.insert("users", null, values);
		db.close();
		return idd;
	}

	public boolean find(String phone) {

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("users", new String[] { "phone" }, "phone=?",
				new String[] { phone }, null, null, null);
		boolean result = cursor.moveToNext();
		cursor.close();
		db.close();
		return result;
	}

	public boolean delete(String phone) {
		SQLiteDatabase db = helper.getWritableDatabase();
		int l = db.delete("users", "phone=?", new String[] { phone });
		db.close();
		return true;
	}

	public List<Map<String, Object>> findAllDate(Context context) {
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Cursor cursor = db.query("users", new String[] { "phone", "username" },
				null, null, null, null, null);
		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();

			String phone = cursor.getString(0);
			String username = cursor.getString(1);
			// System.out.println("获取到的userlist"+phone);
			map.put("phone", phone);
			map.put("username", username);
			data.add(map);
		}

		cursor.close();
		db.close();
		return data;
	}

	
}
