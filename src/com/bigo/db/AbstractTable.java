package com.bigo.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bigo.util.DBProperties;
import com.bigo.util.TAG;

public class AbstractTable  {

	private static final String DB_NAME = DBProperties.getDbName();
	// get database version of HeroScop Table

	private final String tableName;
	private final String createSQL;
	private final String insertSQL;

	private final Context context;
	private final SQLiteOpenHelper dbHelper;
	
	public AbstractTable(SQLiteOpenHelper dbHelper, Context context, String tableName, 
			String createSQL,String insertSQL) {
		this.dbHelper = dbHelper;
		this.context = context;
		this.tableName = tableName;
		this.createSQL = createSQL;
		this.insertSQL = insertSQL;
	}

	@Deprecated
	public void onCreate_2(SQLiteDatabase db) {
		db.execSQL(createSQL);

		try {
			AssetManager asstMgr = context.getAssets();
			InputStream inputStream = asstMgr.open(DB_NAME);// Read SQLite File

			File innerDB = new File(db.getPath());// Write to inner SQLite database;
			OutputStream outputStream = new FileOutputStream(innerDB);

			byte buffer[] = new byte[1024];
			while (inputStream.read(buffer) != -1) {
				outputStream.write(buffer);
			}

			outputStream.flush();// Copy a SQLite file into /data/data/package_name/databases/**databaseName**
			outputStream.close();
			inputStream.close();

			// This Solution, ยังไม่ Work
			// ผมมีปัญหาอยู่สองเรื่องคือ
			// 1) sqlitestudio-2.1.5 มันดันเป็น Version 3 ไม่สามารถ Convert ไปยัง Version 4
			// 2) พอเปลี่ยนไปใช้ SQLite Database Browser 2.0 b1 เจอปัญหา Local ภาษาไทยอีก

		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG.ERROR, e.toString());

		} finally {
		}
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(PropertyDBHelper.class.getName(),
				"Upgread database version from version" 
						+ oldVersion + " to " + newVersion + 
				", which will destroy all old data");
		
		db.execSQL("DROP TABLE IF EXISTS " + tableName);
		onCreate(db);
	}
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createSQL);
		relaod(db);
	}

	public SQLiteDatabase getReadableDatabase(){
		return dbHelper.getReadableDatabase();
	}
	
	public void relaod(SQLiteDatabase db) {
		SQLiteDatabase importDb = null;
		Cursor cur = null;

		try {
			AssetManager asstMgr = context.getAssets();
			InputStream inputStream = asstMgr.open(DB_NAME);// Read a SQLite File

			// Write to a temporary SQLite file
			File tempDB = new File(context.getFilesDir().getAbsoluteFile().getAbsolutePath() + "/" + tableName);
			OutputStream outputStream = new FileOutputStream(tempDB);

			byte buffer[] = new byte[1024];
			while (inputStream.read(buffer) != -1) {
				outputStream.write(buffer);
			}

			outputStream.flush();// Copy the SQLite file into the temporary
									// SQLite database;
			outputStream.close();
			inputStream.close();

			// เหตุลผลที่ต้องมี temporary SQLite file
			// เพราะ SQLiteDatabase.openDatabase รับ inputStream =
			// asstMgr.open(DB_NAME); เป็น Argument ไม่่ได้
			// และ asstMgr.open(DB_NAME); มันดันไม่สามารถส่ง string path มาได้
			// Open SQLite database
			importDb = SQLiteDatabase.openDatabase(tempDB.getAbsolutePath(),null, SQLiteDatabase.OPEN_READWRITE);
			// meaning "SELECT * FROM TABLE_NAME;
			cur = importDb.query(tableName, null, null, null, null, null, null);

			int column = cur.getColumnCount();
			if (cur.moveToFirst()) {
				do{//Load all data from the temporary SQLite file into the inner SQLite database
					
					String projection[] = new String[column];
					for (int i = 0; i < column; i++) {
						projection[i] = cur.getString(i);// column i to n
						
					}
					db.execSQL(insertSQL, projection);
					
				}while(cur.moveToNext());
				
			}

			tempDB.delete();// delete temporary file.
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG.ERROR, e.toString());

		} finally {
			if (cur != null) {
				cur.close();
			}
			if (importDb != null) {
				importDb.close();
			}
		}

	}
	
}
