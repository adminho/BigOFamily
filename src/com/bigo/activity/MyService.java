package com.bigo.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.bigo.util.TAG;

public class MyService extends Service {
	
	

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(TAG.INFO, "##############  on Create...");
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String key = (String) intent.getExtras().get("KEY1");
		Log.i(TAG.INFO, "##############  onStartCommand.." + key);
	    return Service.START_NOT_STICKY;
	}

	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
