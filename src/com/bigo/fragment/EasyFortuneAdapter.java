package com.bigo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import com.bigo.activity.EasyFortuneActv;
import com.bigo.activity.R;


/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class EasyFortuneAdapter extends FragmentPagerAdapter {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	
	private final FragmentActivity activity;
	private final Intent intent;
	
	public EasyFortuneAdapter(FragmentActivity activity,Intent intent) {
		super(activity.getSupportFragmentManager());
		this.activity = activity;
		this.intent = intent;
	}

	private Bundle extractData() {
		Bundle bundle = new Bundle();
		
		bundle.putString(EasyFortuneActv.PHONE_NUM, intent.getStringExtra (EasyFortuneActv.PHONE_NUM));
		bundle.putString(EasyFortuneActv.PHONE_SUMNUM, intent.getStringExtra (EasyFortuneActv.PHONE_SUMNUM));
		bundle.putString(EasyFortuneActv.AUSP_NUM, intent.getStringExtra (EasyFortuneActv.AUSP_NUM));
		bundle.putString(EasyFortuneActv.GOOD_NUM, intent.getStringExtra (EasyFortuneActv.GOOD_NUM));
		bundle.putString(EasyFortuneActv.BAD_NUM, intent.getStringExtra (EasyFortuneActv.BAD_NUM));
		bundle.putString(EasyFortuneActv.FORECAST_1, intent.getStringExtra (EasyFortuneActv.FORECAST_1));
		bundle.putString(EasyFortuneActv.FORECAST_2, intent.getStringExtra (EasyFortuneActv.FORECAST_2));
		bundle.putString(EasyFortuneActv.FORECAST_3, intent.getStringExtra (EasyFortuneActv.FORECAST_3));
		bundle.putString(EasyFortuneActv.FORECAST_NUM, intent.getStringExtra (EasyFortuneActv.FORECAST_NUM));
		bundle.putString(EasyFortuneActv.BIRTHDAY_OF_WEEK, intent.getStringExtra(EasyFortuneActv.BIRTHDAY_OF_WEEK));
		bundle.putString(EasyFortuneActv.BIRTHDAY, intent.getStringExtra(EasyFortuneActv.BIRTHDAY));
		return bundle;
	}
	
	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a DummySectionFragment (defined as a static inner class
		// below) with the page number as its lone argument.
		Fragment fragment = null;
		
		switch (position){
		case 0:
			fragment = new PhoneFlgmt();
			break;
		case 1:
			fragment = new BirthDayFlgmt();
			break;
		}
		
		Bundle args = extractData();
		args.putInt(EasyFortuneAdapter.ARG_SECTION_NUMBER, position);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return 2;// Show 2 total pages.
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return activity.getString(R.string.result_phone);
		case 1:
			return activity.getString(R.string.result_birthday);
		}
		return null;
	}
}
