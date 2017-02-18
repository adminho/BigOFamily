package com.bigo.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigo.activity.EasyFortuneActv;
import com.bigo.activity.R;
import com.bigo.util.TAG;


@SuppressLint("NewApi")
public class BirthDayFlgmt extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		Bundle bundle = getArguments();
		View rootView = inflater.inflate(R.layout.birthday_flgmt,container, false);
		
		setView(rootView, bundle);
		return rootView;
	}

	public void setView(View rootView,Bundle bundle){
		int positionFlg = bundle.getInt(EasyFortuneAdapter.ARG_SECTION_NUMBER);
		Log.i(TAG.INFO, "postion page is " + positionFlg);
		
		TextView txtDay = (TextView)rootView.findViewById(R.id.txtBirthDay);
		txtDay.setText(bundle.getString(EasyFortuneActv.BIRTHDAY));
		
		TextView txtForecase1 = (TextView)rootView.findViewById(R.id.txtForecase1);
		txtForecase1.setText(bundle.getString(EasyFortuneActv.FORECAST_1));
		
		TextView txtForecase2 = (TextView)rootView.findViewById(R.id.txtForecase2);
		txtForecase2.setText(bundle.getString(EasyFortuneActv.FORECAST_2));
		
		TextView txtForecase3 = (TextView)rootView.findViewById(R.id.txtForecase3);
		txtForecase3.setText(bundle.getString(EasyFortuneActv.FORECAST_3));
		
	}
	
}