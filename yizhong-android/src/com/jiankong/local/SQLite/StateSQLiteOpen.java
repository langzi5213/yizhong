package com.jiankong.local.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StateSQLiteOpen extends SQLiteOpenHelper {
	
	public StateSQLiteOpen(Context context) {
		
		super(context, "states.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table states (id varchar(20), deviceId varchar(20),state varchar(20), date varchar(20)) ");
	}

	@Override 
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}
}
