package com.jiankong.local.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocationSQLiteOpen extends SQLiteOpenHelper {
	
	public LocationSQLiteOpen(Context context) {
		
		super(context, "location.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table location (id varchar(20) , deviceId varchar(20) ,devicename varchar(20),  latitude varchar(20),longitude varchar(20),date varchar(20)) ");
	}

	@Override 
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}
}
