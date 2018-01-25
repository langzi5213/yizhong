package com.jiankong.local.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DeviceSQLiteOpen extends SQLiteOpenHelper {
	
	public DeviceSQLiteOpen(Context context) {
		
		super(context, "devices.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table devices (id varchar(20),deviceId varchar(20),devicename varchar(20),username varchar(20), phone varchar(20)) ");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}
}
