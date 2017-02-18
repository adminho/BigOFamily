package com.bigo.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

@SuppressLint("DefaultLocale")
public class PropertyDBHelper extends SQLiteOpenHelper {

	private final String queryCreate ;
	private final String tableName; 
	private final Context context;
	
	private static String TABLE_NAME = "NumHoro";
	private static final String HEROSCOPE_PROPERTIES = "heroscope_encoding.properties";
	private static String INSERT_SQL = "insert into " + TABLE_NAME +" (sum,name) values (?,?)";

	public PropertyDBHelper(Context context,String dbName,int dbVersion,String queryCreate) {
		super(context, dbName, null, dbVersion);
		
		this.context = context;
		this.queryCreate = queryCreate;
		
		queryCreate = queryCreate.toLowerCase(Locale.US);
		int firstIndex = queryCreate.indexOf("table")+5;
		int lastIndext = queryCreate.indexOf("(");
		this.tableName = queryCreate.substring(firstIndex,lastIndext).trim();
		Log.w(PropertyDBHelper.class.getName(), "Table Name is "  +this.tableName);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(queryCreate);
		
		Properties prop = null;//load data from database property file into Properties Object
		try {
			AssetManager assetManger = context.getAssets();
			prop = new Properties();
			InputStream inputStream = assetManger.open(HEROSCOPE_PROPERTIES);//open property file
			prop.load(inputStream);//load data from property file.
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(getClass().getName(), e.toString());
			Log.i(getClass().getName(), "Don't any insert to HeroScope Table");
			return;//and exit
		}
		
		Set<Entry<Object,Object>> enrtySet = prop.entrySet();//get all data from the database property file
		SQLiteStatement insertStmt= db.compileStatement(INSERT_SQL);//Create Statement
		
		for(Entry<Object,Object> e:enrtySet){
			insertStmt.bindString(1, e.getKey().toString());//a key inserted to HerroScope(sum)
			insertStmt.bindString(2, e.getValue().toString());//a value inserted to HeroScop(name)
			insertStmt.executeInsert();//insert data to database
		}
		
		//the row ID of the last row inserted, if this insert is successful. -1 otherwise
		insertStmt.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(PropertyDBHelper.class.getName(),
				"Upgread database version from version" + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + this.tableName);
		onCreate(db);
	}

}
