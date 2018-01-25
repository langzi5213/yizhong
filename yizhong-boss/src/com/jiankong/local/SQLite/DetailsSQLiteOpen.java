package com.jiankong.local.SQLite;



 

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DetailsSQLiteOpen  extends SQLiteOpenHelper {
	
	public DetailsSQLiteOpen(Context context) {
		
		super(context, "details.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table details (id varchar(20) ,deviceId varchar(20) ,number varchar(20) ,battery varchar(20) , address varchar(20),videoUrl varchar(20),imageUrl varchar(20),date varchar(20)) ");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}
}
