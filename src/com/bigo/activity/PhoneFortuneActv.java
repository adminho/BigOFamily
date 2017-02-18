package com.bigo.activity;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.bigo.activity.R;
import com.bigo.dialog.PhoneListDlg;
import com.bigo.service.HeroScopeSrvc;
import com.bigo.service.MobileContactSrvc;
import com.bigo.util.CustomToast;
import com.bigo.util.TAG;

public class PhoneFortuneActv extends Activity{

	private HeroScopeSrvc heroScope;
	private MobileContactSrvc mobileContact ;
	private PhoneListDlg dialog;
	private final Activity activity = this;
	
	private final OnClickListener listener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			CustomToast.show(activity, "ÃÍ«Ñ¡¤ÃÙè", Toast.LENGTH_LONG);
			Intent intent = new Intent(getApplicationContext(),GoogleMapFlgActv.class);
			startActivity(intent);			
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_fortune);
		
		this.heroScope = new HeroScopeSrvc(this);
		this.mobileContact = new MobileContactSrvc(this);
		this.dialog = new PhoneListDlg(this);
		
		TextView contView = (TextView) findViewById(R.id.contactId);
		contView.setOnClickListener(listener);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	public void onSearch(View view){
		Log.i(TAG.INFO, "show the dialog when search");
		Map<String,String>  result = mobileContact.searchMobile();//get list of mobile contact
	    dialog.showPhoneContact(result);//show results with choice dialog
	}
	
	public void onConfirm(View view){
		Log.i(TAG.INFO, "show the dialog when confrim");
		String result = heroScope.augur();//get prediction 
		dialog.showHeroScopeResult(result);//show results with simple dialog box
	}
	
	public void onSelectDay(View view){
		dialog.showDay();
	}
}
