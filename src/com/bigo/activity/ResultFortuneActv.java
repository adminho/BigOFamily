package com.bigo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bigo.fragment.EasyFortuneAdapter;

public class ResultFortuneActv extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_fortune);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		EasyFortuneAdapter flgAdapter = new EasyFortuneAdapter(this,getIntent());

		// Set up the ViewPager with the sections adapter.
		ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(flgAdapter);
		
		
	}

	public void onClickBtn(View v){
		finish();//Black to home
	}

}
