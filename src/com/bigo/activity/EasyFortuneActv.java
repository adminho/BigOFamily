package com.bigo.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bigo.dialog.BirthDayDlg;
import com.bigo.dialog.PhoneListDlg;
import com.bigo.service.FortuneSrvc;
import com.bigo.service.MobileContactSrvc;
import com.bigo.util.CustomToast;

public class EasyFortuneActv extends Activity {

	private MobileContactSrvc mobileContact ;
	private PhoneListDlg phoneListDlg;
	private BirthDayDlg birthDayDlg;
	
	static public final int REQ_CODE = 111;
	static public final String PHONE_NUM = "PHONE_NUM";
	static public final String AUSP_NUM = "AUSP_NUM";
	static public final String GOOD_NUM = "GOOD_NUM";
	static public final String BAD_NUM = "BAD_NUM";
	static public final String FORECAST_1 = "FORECAST_1";
	static public final String FORECAST_2 = "FORECAST_2";
	static public final String FORECAST_3 = "FORECAST_2";
	static public final String FORECAST_NUM = "FORECAST_NUM";
	static public final String BIRTHDAY_OF_WEEK = "BIRTHDAY_OF_WEEK";
	static public final String BIRTHDAY = "BIRTHDAY";
	static public final String PHONE_SUMNUM = "PHONE_SUMNUM";
	
	private final SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", new Locale("TH")); 
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.easy_fortune);
		
		this.mobileContact = new MobileContactSrvc(this);
		this.phoneListDlg = new PhoneListDlg(this);
		this.birthDayDlg = new BirthDayDlg(this);
		this.birthDayDlg.setBirthDayPicker();
	}
	
	public void onSearch(View v){
		Map<String,String>  result = mobileContact.searchMobile();//get list of mobile contact
	    phoneListDlg.showPhoneContact(result);//show results with choice dialog
	}
	
	public void onClickDate(View v) {
		birthDayDlg.showDate(v);
	}
	
	public void onClickTime(View v){
		birthDayDlg.showTime(v);
	}
	
	public void onContact(View v){
		CustomToast.show(this, getString(R.string.toast_wait), Toast.LENGTH_SHORT);
		Intent intent = new Intent(getApplicationContext(),GoogleMapFlgActv.class);
		startActivity(intent);	
	}
	
	public void onConfrim(View view){
		final EditText phoneTxt = (EditText)findViewById(R.id.phoneNumber);
		String number = phoneTxt.getText().toString();//Get mobile phone from layout
		
		Calendar cal = birthDayDlg.getCalendar();//Get calendar after click confirm
		final FortuneSrvc fortuneSrvc = new FortuneSrvc(this,number,cal);
		
		String ausRes = fortuneSrvc.auspiciousNum();//Get auspicious meaning of mibiel phone
		
		StringBuilder goodNum = new StringBuilder();
		StringBuilder badNum = new StringBuilder();
		String dayOfWeek = fortuneSrvc.goodNum(goodNum,badNum);//get good number in mobile according birthday
		
		StringBuilder forecast1 = new StringBuilder();
		StringBuilder forecast2 = new StringBuilder();
		StringBuilder forecast3 = new StringBuilder();
		fortuneSrvc.forcase(forecast1,forecast2,forecast3);//Forecast from phone number with sum (1-9)

		String forecastNum = fortuneSrvc.forcase();//Forecast from phone number only
		
		Intent intent = new Intent(this, ResultFortuneActv.class);
		intent.putExtra(PHONE_NUM,number);
		intent.putExtra(PHONE_SUMNUM, ""+fortuneSrvc.sumNum(number));
		intent.putExtra(AUSP_NUM, ausRes);
		intent.putExtra(GOOD_NUM, goodNum.toString());
		intent.putExtra(BAD_NUM, badNum.toString());
		intent.putExtra(FORECAST_1, forecast1.toString());
		intent.putExtra(FORECAST_2, forecast2.toString());
		intent.putExtra(FORECAST_3, forecast3.toString());
		intent.putExtra(FORECAST_NUM, forecastNum.toString());
		intent.putExtra(BIRTHDAY_OF_WEEK,dayOfWeek);
		intent.putExtra(BIRTHDAY, format.format(cal.getTime()));
		startActivityForResult(intent,EasyFortuneActv.REQ_CODE);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}


}
