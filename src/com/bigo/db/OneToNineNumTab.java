package com.bigo.db;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bigo.util.TAG;

public class OneToNineNumTab extends AbstractTable {
	
	private static String TABLE_NAME = "OneToNineNum";
	private static String CREATE_SQL = "CREATE TABLE "  + TABLE_NAME + 
			"(_ID INTEGER PRIMARY KEY ," +
		    " SUM_NUM    INTEGER NOT NULL, " +
		    " FORECASE_1 TEXT    NOT NULL, " +
		    " FORECASE_2 TEXT    NOT NULL, " +
		    " FORECASE_3 TEXT    NOT NULL)"; 
	
	private static String INSERT_SQL = "INSERT INTO "  + TABLE_NAME + 
			" (_ID,SUM_NUM,FORECASE_1,FORECASE_2,FORECASE_3) VALUES (?,?,?,?,?)";
	
	Context context;
	public OneToNineNumTab(SQLiteOpenHelper db,Context context) {
		super(db, context, TABLE_NAME, CREATE_SQL, INSERT_SQL);
		this.context = context;
	}

	private static Map<String,String[]> allData=null;
	
	public Map<String,String[]> getAllData(){
		if(allData!=null){
			return allData;////return cashed data
		}
		
		allData = new HashMap<String, String[]>();
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor cursor = null;
		try{
			
			
			cursor = db.query(TABLE_NAME, new String[]{"SUM_NUM","FORECASE_1","FORECASE_2","FORECASE_3"}, null, null, null, null, "SUM_NUM DESC");
			
			if(cursor.moveToFirst()){
				do{
					allData.put(cursor.getString(0), new String[]{cursor.getString(1),cursor.getString(2),cursor.getString(3)});
					
				}while(cursor.moveToNext());
			}
			
		}catch(Exception e){
			Log.e(TAG.ERROR, e.toString());
		}finally{
			if(cursor!=null && !cursor.isClosed()){
				cursor.close();
			}
			if(db!=null){
				db.close();
			}
		}
		
		return allData;
	}
	
	public String getMeaing(int sumNum,int index){
		Map<String,String[]> data = getAllData();
		String meaning[] = data.get(""+sumNum);
		//index = 0, get FORECASE_1
		//index = 1, get FORECASE_2
		//index = 2, get FORECASE_3
		return meaning[index];
	}
}
