package com.bigo.db;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bigo.util.TAG;

public class GoodNumTab extends AbstractTable {
	
	private static final String TABLE_NAME = "GoodNum";
	private static final String CREATE_SQL = "CREATE TABLE "  + TABLE_NAME + 
			"(_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ 
		    " DAY TEXT NOT NULL, " + 
		    " FRIENDLY INTEGER NOT NULL, " +
		    " ENEMY    INTEGER NOT NULL)";
	
	private static final String INSERT_SQL = "INSERT INTO "  + TABLE_NAME + 
			" (_ID,DAY,FRIENDLY,ENEMY) VALUES (?,?,?,?)";
	
	public GoodNumTab(SQLiteOpenHelper db,Context context) {
		super(db, context, TABLE_NAME, CREATE_SQL, INSERT_SQL);
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
			
			
			cursor = db.query(TABLE_NAME, new String[]{"DAY","FRIENDLY","ENEMY"}, null, null, null, null, "DAY DESC");
			
			if(cursor.moveToFirst()){
				do{
					allData.put(cursor.getString(0), new String[]{cursor.getString(1),cursor.getString(2)});
					
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
	
	public String getBadNum(String day){
		Map<String,String[]> data = getAllData();
		String meaning[] = data.get(day);
		return meaning[1];//index = 1, get ENEMY
	}
	
	public String getGoodNum(String day){
		Map<String,String[]> data = getAllData();
		String meaning[] = data.get(day);
		return meaning[0];//index = 0, get FRIENDLY	
	}

}
