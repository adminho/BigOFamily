package com.bigo.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class FortuneHistoryTab extends AbstractTable {

	private static final String TABLE_NAME = "FortuneHistory";
	private static final String CREATE_SQL = "CREATE TABLE "  + TABLE_NAME + 
			"(_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"HIS_1 TEXT, HIS_2 TEXT, HIS_3 TEXT, " +
			"CREATE_DATE DATETIME)";
	
	private static final String INSERT_SQL = "INSERT INTO "  + TABLE_NAME + 
			" (HIS_1,HIS_2,HIS_3,CREATE_DATE) VALUES (?,?,?,?)";
			
	
	public FortuneHistoryTab(SQLiteOpenHelper db,Context context) {
		super(db, context, TABLE_NAME, CREATE_SQL, INSERT_SQL);
	}

}
