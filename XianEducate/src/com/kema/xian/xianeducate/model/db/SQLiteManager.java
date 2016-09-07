package com.kema.xian.xianeducate.model.db;

import com.kema.xian.xianeducate.activity.base.AppConfig;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This is SQLite database manager.
 * 
 * @author hl.ding
 * 
 */
public class SQLiteManager extends SQLiteOpenHelper {

	private String TAG = "SQLiteManager";

	public SQLiteManager(Context context, String name) {
		super(context, name, null, AppConfig.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.e(TAG, "onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.e(TAG, "oldVersion = " + oldVersion);
		Log.e(TAG, "newVersion = " + newVersion);

	}

}
