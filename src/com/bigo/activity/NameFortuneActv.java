package com.bigo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bigo.dialog.NameDlg;
import com.bigo.util.TAG;

public class NameFortuneActv extends Activity{

	private NameDlg dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.name_fortune);
		
		dialog = new NameDlg(this);
	}

	public void onConfirm(View view){
		Log.i(TAG.INFO, "show the dialog when confrim");
		dialog.showHeroScopeResult("");//show results with simple dialog box
	}
	
	public void onSelectDay(View view){
		dialog.showDay();
	}
}
