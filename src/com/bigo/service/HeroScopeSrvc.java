package com.bigo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.app.Activity;
import android.content.res.AssetManager;
import android.widget.Button;
import android.widget.EditText;

import com.bigo.activity.R;
import com.bigo.db.AuspiciousNumTab;
import com.bigo.db.BigOFamilyDB;

public class HeroScopeSrvc {
	private final Activity activity;
	private final Button heroBtn;
	private final EditText numberTxt;
	
	private final Properties properties;
	private final AuspiciousNumTab table;
	
	public HeroScopeSrvc(Activity activity){
		this.activity = activity;
		this.heroBtn = (Button) activity.findViewById(R.id.hero_btn);
		this.numberTxt = (EditText)activity.findViewById(R.id.phoneNumber);
		this.table = BigOFamilyDB.getInstance(activity).getAuspiciousNumTab();
		
		properties = new Properties();
        try {
			/**
			 * getAssets() Return an AssetManager instance for your
			 * application's package. AssetManager Provides access to an
			 * application's raw asset files;
			 */
			AssetManager assetManager = activity.getAssets();
			/**
			 * Open an asset using ACCESS_STREAMING mode. This
			 */
			InputStream inputStream = assetManager.open("heroscope_encoding.properties");
			/**
			 * Loads properties from the specified InputStream,
			 */
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			
		}

	}
	

	public String augur() {
		String number = numberTxt.getText().toString();
		
		int size = number.length();
		if(size==0){
			return "กรอกตัวเลขโทรศัพท์ผิด";
		}
		int sum = 0;
		for(int i=0;i<size;i++){
			//Add the numbers in each digit
			int num = number.charAt(i) - 48; // 0 number --> Ascii = 48
			if(num==-16){
				continue; // space -->Ascii = 32, 32-48=-16
			}
			if(num<0 || num > 9){
				return "กรอกตัวเลขโทรศัพท์ผิด";
			}
			sum += num;
		}
		
		return getFromDB(sum);
	}
	
	private static final String MESSAGE_SUMMARY = "ผลรวมตัวเลขคือ ";
	public String getFromDB(int sum){
//			Map<String,String> map = table.getAllData();
//			String prediction = map.get("" +sum);
//
//			prediction = prediction ==null ? "ไม่มีผลทำนาย" : prediction;
//			return MESSAGE_SUMMARY + sum + "\n" +  prediction;
		return null;
	}

}
