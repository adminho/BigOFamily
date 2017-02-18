package com.bigo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bigo.db.AuspiciousNumTab;
import com.bigo.db.BigOFamilyDB;
import com.bigo.db.GoodNumTab;
import com.bigo.db.OneToHundredNumTab;
import com.bigo.db.OneToNineNumTab;
import com.bigo.util.TAG;

public class TestActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		BigOFamilyDB db = BigOFamilyDB.getInstance(this);
		GoodNumTab tab1 = db.getGooNumTab();
		
//		Log.i(TAG.INFO,"????" + tab1.getMeaing("Sunday", 0));
//		Log.i(TAG.INFO,">>>>> " +tab1.getMeaing("Monday", 1));
		
		
		OneToNineNumTab tab2 = db.getOneToNineNumTab();
		Log.i(TAG.INFO,">>>>> " +tab2.getMeaing(1, 1));
		
		AuspiciousNumTab tab3 = db.getAuspiciousNumTab();
		Log.i(TAG.INFO,"XXX " + tab3.getMeaing(50));
		
		OneToHundredNumTab tab4 = db.getOneToHundredNumTab();
		Log.i(TAG.INFO,"fff " + tab4.getMeaing(8));

	}

	private void intit() {
		// CustomToast.show(this, "ทดสอบ ชอบทดลอง");
		// use this to start and trigger a service
		Intent i = new Intent(this, MyService.class);
		// potentially add data to the intent
		i.putExtra("KEY1", "Value to be used by the service");
		startService(i);
	}

}
