package com.bigo.db;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bigo.util.DBProperties;

public class HeroScopeTable {

	private final String DATABASE_NAME;
	private final int DATABASE_HEROSCOPE_VERSION;
	
	private final PropertyDBHelper databaseHelper;
	
	private static String TABLE_NAME = "Hero_scope";
	private static String CREATE_SQL = "create table "  + TABLE_NAME + " (_id integer primary key, sum integer not null, name text not null)";
	
	public HeroScopeTable(Context context) {
		DATABASE_NAME = DBProperties.getDbName();//get database name
		DATABASE_HEROSCOPE_VERSION = 1;//get database version of HeroScop Table
		
		this.databaseHelper = new PropertyDBHelper(context, DATABASE_NAME, DATABASE_HEROSCOPE_VERSION, CREATE_SQL);
	}
	
	public void deleteAll(){
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.delete(TABLE_NAME, null, null);
		db.close();
	}
	
	private static Map<String, String> allData=null;
	
	public Map<String,String> gettAllData(){
		if(allData!=null){
			return allData;////return cashed data
		}
		
		allData = new HashMap<String, String>();
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		Cursor cursor = null;
		
		try{
			
			cursor =db.query(TABLE_NAME, new String[]{"sum,name"}, null, null, null, null, "sum desc");
			
			if(cursor.moveToFirst()){
				do{
					allData.put(cursor.getString(0), cursor.getString(1));
					
				}while(cursor.moveToNext());
			}
			
		}
		finally{
			if(cursor!=null && !cursor.isClosed()){
				cursor.close();
			}
			if(db!=null){
				db.close();
			}
		}
		
		return allData;
	}

}
