package com.bigo.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigo.activity.R;

public class CustomToast {

	public static void show(Activity activity, String message, int length){
		show(activity, message, length,Gravity.CENTER);//Default, Toast will show at center of any screens
	}
	
	public static void show(Activity activity, String message, int length,int gravity){
		LayoutInflater inflater = activity.getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) activity.findViewById(R.id.toast_layout_root));

		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText(message);

		Toast toast = new Toast(activity.getApplicationContext());
		toast.setGravity(gravity, 0, 0);
		toast.setDuration(length);
		toast.setView(layout);
		toast.show();
	}
}
