package com.bigo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProfileHistoryTab extends AbstractTable {
	
	private static final String TABLE_NAME = "ProfileHistory";
	private static final String CREATE_SQL = "CREATE TABLE "  + TABLE_NAME + 
			"(_ID INTEGER  PRIMARY KEY AUTOINCREMENT, " +
			" PHONE_NUM TEXT, " +
			" BIRTHDAY  DATETIME )";
	
	private static final String INSERT_SQL = "INSERT INTO "  + TABLE_NAME + 
			" (PHONE_NUM,BIRTHDAY) VALUES (?,?)";
			
	
	public ProfileHistoryTab(SQLiteOpenHelper db,Context context) {
		super(db, context, TABLE_NAME, CREATE_SQL, INSERT_SQL);
	}
	
	public void getAllData(StringBuilder phoneNumber,StringBuilder day, StringBuilder date){
		SQLiteDatabase db = null;
		Cursor cur = null;
		try{
			db = getReadableDatabase();
			db.query(TABLE_NAME, new String[]{"PHONE_NUM","BIRTHDAY"}, null, null, null, null, null);
			if(cur.moveToFirst()){
				phoneNumber.append(cur.getString(0));
				String brithDay = cur.getString(1);
//				phoneNumber.append();				
			}
		} finally{
			cur.close();
			db.close();
		}
		
		
		
		return ;
	}
}
