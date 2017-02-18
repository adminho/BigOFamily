package com.bigo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;

public class DBProperties {
	private static final String APPLICATION_PROPERTIES = "application.properties";
	public static AssetManager assetManger;
	private static Properties prop; 
	
	private DBProperties(){
	}
	
	public static void reloadAll(Context context) throws IOException{//Must read first
		assetManger = context.getAssets();
		prop = new Properties();
		InputStream inputStream = assetManger.open(APPLICATION_PROPERTIES);
		prop.load(inputStream);	
		inputStream.close();
	}

	public static String getDbName(){
		return prop.getProperty("DB.NAME");
	}
	
	public static String getDbVerersion(){
		return prop.getProperty("DB.VERSION");
	}
}
