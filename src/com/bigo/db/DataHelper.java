package com.bigo.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class DataHelper {

	private static final String DATABASE_NAME = "example.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "table1";

	private final SQLiteDatabase db;

	private final SQLiteStatement insertStmt;
	private static final String INSERT = "insert into " + TABLE_NAME
			+ "(name) values (?)";

	private static final String CREATE = "create table " + TABLE_NAME+ "(_id integer primary key, Name text not null)";
	
	public DataHelper(Context context) {
		PropertyDBHelper databaseHelper = new PropertyDBHelper(context, DATABASE_NAME, DATABASE_VERSION, CREATE);
		this.db = databaseHelper.getWritableDatabase();
		this.insertStmt = this.db.compileStatement(INSERT);
	}

	public long insert(String name){
		this.insertStmt.bindString(1, name);
		return this.insertStmt.executeInsert();
	}
	
	public void deleteAll(){
		this.db.delete(TABLE_NAME, null, null);
	}
	
	public List<String> selectAll(){
		List<String> list = new ArrayList<String>();
		Cursor cursor = null;
		try{
			
			cursor = this.db.query(TABLE_NAME, new String[]{"name"}, null, null, null, null, "name desc");
			
			if(cursor.moveToFirst()){
				do{
					list.add(cursor.getString(0));
					
				}while(cursor.moveToNext());
			}
			
		}
		finally{
			if(cursor!=null && !cursor.isClosed()){
				cursor.close();
			}
		}
		return list;
	}

}
