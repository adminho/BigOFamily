package com.bigo.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.bigo.adapter.MainPageAdapter;
import com.bigo.util.CustomToast;

public class MainPageActv extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		
		initView();
	}

	private void initView() {
		final ArrayList<Map<String, Object>> arrList = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> map;
		
		//Create main selected menu list
		/*** Rows 1 ***/
		map = new HashMap<String, Object>();
		map.put("ImageID", R.drawable.phone);//Picture
		map.put("ImageDesc", "ดูดวงงายง่าย");//Description
//		map.put("Activity", PhoneFortuneActv.class);//Call Activity
		map.put("Activity", EasyFortuneActv.class);//Call Activity
		arrList.add(map);
		
		/*** Rows 2 ***/
		map = new HashMap<String, Object>();
		map.put("ImageID", android.R.drawable.btn_star_big_off);//Picture
		map.put("ImageDesc", "ตรวจดวงชะตาจาก\nชื่อ-นามสกุล");//Description
		map.put("Activity", NameFortuneActv.class);//Call Activity
		arrList.add(map);
		
		/*** Rows 3 ***/
		map = new HashMap<String, Object>();
		map.put("ImageID", R.drawable.physiognomy);
		map.put("ImageDesc", "ดูโหวงเฮ้ง และลายมือ");
		map.put("Activity", PhysiognomyActv.class);
		arrList.add(map);	
		
		/*** Rows 4 ***/
		map = new HashMap<String, Object>();
		map.put("ImageID", android.R.drawable.btn_star_big_on);
		map.put("ImageDesc", "ดูกราฟชีวิต");
		map.put("Activity", GraphFortuneActv.class);
		arrList.add(map);	

		/*
		map = new HashMap<String, Object>();
		map.put("ImageID", android.R.drawable.btn_plus);
		map.put("ImageDesc", "ทดสอบtoast");
		map.put("Activity", TestActivity.class);
		arrList.add(map);	
		*/
		
        final ListView lstView1 = (ListView)findViewById(R.id.listView); // listView1
        lstView1.setAdapter(new MainPageAdapter(this,arrList));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onContact(View v){
		CustomToast.show(this, getString(R.string.toast_wait), Toast.LENGTH_SHORT);
		Intent intent = new Intent(getApplicationContext(),GoogleMapFlgActv.class);
		startActivity(intent);	
	}
	
}
