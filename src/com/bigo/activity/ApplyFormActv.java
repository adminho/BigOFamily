package com.bigo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;

import com.bigo.service.MobileContactSrvc;
import com.bigo.service.TakePhotoSrvc;

public class ApplyFormActv extends Activity {

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	
	private MobileContactSrvc mobileContact;
	private TakePhotoSrvc takePhoto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.physiognomy);

		// Set up for manage Mobile Contact
		mobileContact =new MobileContactSrvc(this);  
		// Set up for manage Take Photo
		takePhoto = new TakePhotoSrvc(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		takePhoto.onSaveInstanceState(outState);
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		takePhoto.onRestoreInstanceState(savedInstanceState);
	}
	
	public void onSearchContact(View view){
		mobileContact.startActivityForResult();
	}
	
	@Override
	public void onActivityResult(int requestCode,int resultCode,Intent intent){
		mobileContact.onActivityResult(requestCode, resultCode, intent);
		takePhoto.onActivityResult(requestCode, resultCode, intent);
	}
	
	public void onSearchMobile(View view){
		mobileContact.searchMobile();
	}
	
	/*public void onTakeBigPhoto(View view){
		takePhoto.dispatchTakePictureIntent(TakePhotoMgr.ACTION_TAKE_PHOTO_B);
	}*/
	
	public void onTakeSmallPhoto(View view){
		takePhoto.startActivityForResult(TakePhotoSrvc.ACTION_TAKE_PHOTO_S);
	}

}
