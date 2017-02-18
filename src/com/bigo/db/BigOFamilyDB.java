package com.bigo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bigo.util.DBProperties;

public class BigOFamilyDB extends SQLiteOpenHelper {

	private static final String DB_NAME = DBProperties.getDbName();
	// get database version of HeroScop Table
	private static final int DB_VERSION = Integer.valueOf(DBProperties.getDbVerersion());

	private final AuspiciousNumTab auspiciousNumTab;
	private final GoodNumTab gooNumTab;
	private final OneToNineNumTab oneToNineNumTab;
	private final OneToHundredNumTab oneToHundredNumTab;

	static private BigOFamilyDB bigOFamilyDB;
	
	static public BigOFamilyDB getInstance(Context context){
		if(bigOFamilyDB==null){
			bigOFamilyDB = new BigOFamilyDB(context);
		}
		return bigOFamilyDB;
	}
	
	private BigOFamilyDB(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.auspiciousNumTab = new AuspiciousNumTab(this,context);
		this.gooNumTab = new GoodNumTab(this,context);
		this.oneToNineNumTab = new OneToNineNumTab(this,context);
		this.oneToHundredNumTab = new OneToHundredNumTab(this,context);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		auspiciousNumTab.onCreate(db);
		gooNumTab.onCreate(db);
		oneToNineNumTab.onCreate(db);
		oneToHundredNumTab.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		auspiciousNumTab.onUpgrade(db,oldVersion,newVersion);
		gooNumTab.onUpgrade(db,oldVersion,newVersion);
		oneToNineNumTab.onUpgrade(db,oldVersion,newVersion);
		oneToHundredNumTab.onUpgrade(db,oldVersion,newVersion);
	}
	
	public AuspiciousNumTab getAuspiciousNumTab() {
		return auspiciousNumTab;
	}

	public GoodNumTab getGooNumTab() {
		return gooNumTab;
	}

	public OneToNineNumTab getOneToNineNumTab() {
		return oneToNineNumTab;
	}

	public OneToHundredNumTab getOneToHundredNumTab() {
		return oneToHundredNumTab;
	}

	
}
