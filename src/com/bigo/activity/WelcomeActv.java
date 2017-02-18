package com.bigo.activity;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bigo.util.CustomToast;
import com.bigo.util.DBProperties;

public class WelcomeActv extends Activity {
	
	private Handler handler;
	private Runnable runnable;
	private long delay_time;
	private long time = 3000L;
	private final Class<MainPageActv> clssIntent = MainPageActv.class;
//	private final Class<EasyFortuneActv> clssIntent = EasyFortuneActv.class;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);//No Title
		setContentView(R.layout.welcome);
		
		initView();//initialize Welcome view
		
		//Create Thread for sleeping
		handler = new Handler(); 
		
		runnable = new Runnable() { 
	        @Override
			public void run() { 
	        	Intent intent = new Intent(WelcomeActv.this,clssIntent);
				startActivity(intent);
				finish();
	         } 
	    };
		
	    try {//load all database properties from a file
			DBProperties.reloadAll(getApplicationContext());
			
		} catch (IOException e) {
			e.printStackTrace();
			Log.w(this.getClass().getName(), "Cannot load the database property file.");
		}
	}

	private void initView() {
		Resources res = getResources();
		Drawable drawable = res.getDrawable(R.drawable.welcome);
		
		/*TextView txtHead1 = (TextView) findViewById(R.id.welcome);
		Animation slideHeader1 = AnimationUtils.loadAnimation(this, R.anim.anim_slide_header);
		txtHead1.startAnimation(slideHeader1);//slide text from right to left.
		
		TextView txtHead2 = (TextView) findViewById(R.id.brand);
		Animation slideHeader2 = AnimationUtils.loadAnimation(this, R.anim.anim_slide_header);
		txtHead2.startAnimation(slideHeader2);//slide text from right to left.*/

		LinearLayout layout =(LinearLayout)findViewById(R.id.layout_welcome);
		Animation slideAnimat = AnimationUtils.loadAnimation(this, R.anim.anim_slide_header);
		layout.startAnimation(slideAnimat);//slide layout from right to left.*/
		
		ImageView view = (ImageView) findViewById(R.id.welcome_img);
		view.setImageDrawable(drawable);
		Animation welcome = AnimationUtils.loadAnimation(this, R.anim.anim_welcome_pic);
		view.startAnimation(welcome);//slide image from left to right. 

//		Toast.makeText(this, getString(R.string.toast_next_slide), Toast.LENGTH_SHORT).show();//show message
		CustomToast.show(this, getString(R.string.toast_wait), Toast.LENGTH_SHORT, Gravity.BOTTOM);
	}

	public void onApplyForm(View view){
		startActivity(new Intent(getApplicationContext(),clssIntent));
	}
	
	@Override
	public void onResume() {
		super.onResume();
		delay_time = time;
	    handler.postDelayed(runnable, delay_time);
	    time = System.currentTimeMillis();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		handler.removeCallbacks(runnable);
	    time = delay_time - (System.currentTimeMillis() - time);
	}

}
