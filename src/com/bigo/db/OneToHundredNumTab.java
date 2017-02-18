package com.bigo.db;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bigo.util.TAG;

public class OneToHundredNumTab extends AbstractTable {
	
	private static final String TABLE_NAME = "OneToHundredNum";
	private static final String CREATE_SQL = "CREATE TABLE "  + TABLE_NAME + " (_ID integer primary key, SUM_NUM integer not null, FORECASE text not null)";
	private static final String INSERT_SQL = "INSERT INTO "  + TABLE_NAME + " (_ID,SUM_NUM,FORECASE) VALUES (?,?,?)";
			
	
	public OneToHundredNumTab(SQLiteOpenHelper db,Context context) {
		super(db, context, TABLE_NAME, CREATE_SQL, INSERT_SQL);
	}
	
	private static Map<String, String> allData = null;

	public Map<String, String> getAllData() {
		if (allData != null) {
			return allData;// //return cashed data
		}

		allData = new HashMap<String, String>();
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = null;
		try {

			cursor = db.query(TABLE_NAME,  new String[]{"SUM_NUM","FORECASE"}, null, null, null, null, "SUM_NUM DESC");
			
			if (cursor.moveToFirst()) {
				do {
					allData.put(cursor.getString(0),cursor.getString(1));

				} while (cursor.moveToNext());
			}

		} catch (Exception e) {
			Log.e(TAG.ERROR, e.toString());
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

		return allData;
	}

	public String getMeaing(int sumNum) {
		Map<String, String> data = getAllData();
		return data.get(""+sumNum);
	}

}
