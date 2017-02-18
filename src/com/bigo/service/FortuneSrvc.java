package com.bigo.service;

import java.util.Calendar;

import android.app.Activity;

import com.bigo.activity.R;
import com.bigo.db.AuspiciousNumTab;
import com.bigo.db.BigOFamilyDB;
import com.bigo.db.GoodNumTab;
import com.bigo.db.OneToHundredNumTab;
import com.bigo.db.OneToNineNumTab;

public class FortuneSrvc {
	private final Activity activity;
	private final AuspiciousNumTab ausTab;
	private final GoodNumTab goodNumTab;
	private final OneToNineNumTab oneNineTab;
	private final OneToHundredNumTab oneHundredTab;
	private final BigOFamilyDB db;
	private final String phoneNum;
	private final Calendar birthDay;
	
	public FortuneSrvc(Activity activity,String phoneNum,Calendar birthDay){
		this.activity = activity;
		this.phoneNum = phoneNum;
		this.birthDay = birthDay;
		
		db = BigOFamilyDB.getInstance(activity);
		this.ausTab = db.getAuspiciousNumTab();
		this.goodNumTab = db.getGooNumTab();
		this.oneNineTab = db.getOneToNineNumTab();
		this.oneHundredTab = db.getOneToHundredNumTab();
	}
	
	public int sumNum(String txtNum){
		if(txtNum==null ){
			throw new RuntimeException("sumNum parameter is null");
		}
		
		char []array = txtNum.toCharArray();//Convert to Ascii Code
		int len = array.length;
		
		int sum = 0;
		for(int i=0;i<len;i++){
			
			int num = array[i]-48;//Ascii 0(zero) code is 48
			if(num>9 || num<0){
				continue; //skip
			}
			
			sum += num;//summary
		}
		
		return sum;
	}

	public String auspiciousNum() {
		int sumPhNum = sumNum(phoneNum);
		return ausTab.getMeaing(sumPhNum);
		
	}
	
	private int sumDay(Calendar cal){
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int dayMount = cal.get(Calendar.DAY_OF_MONTH);
		
		String dayNumber = "" + dayMount + month + year;//Concat to become a number string
		int sum = sumNum(dayNumber);
		while( (sum=sumNum(""+sum))>=10);//while loop until 1 digit
		
		return sum;
	}
	
	public String forcase(){
		if(phoneNum==null){
			return activity.getString(R.string.txt_no_result_fortune);
		}
		
		int sum = sumNum(phoneNum);
		String result =  oneHundredTab.getMeaing(sum);
		
		if(result==null){
			return activity.getString(R.string.txt_no_result_fortune);
		}
		return result;
	}

	public void forcase(StringBuilder forcase1,StringBuilder forcase2,StringBuilder forcase3){
		int sum = sumDay(birthDay);
		forcase1.append(oneNineTab.getMeaing(sum, 0));
		forcase2.append(oneNineTab.getMeaing(sum, 1));
		forcase3.append(oneNineTab.getMeaing(sum, 2));
	}
	
	private final String DAY_ARRAY[] = {"Sunday","Monday","Tuesday","Wednesday","Wednesday-Night","Thursday","Friday","Saturday"};
	private String getDayOfWeek(Calendar cal){
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		String dayStr = "";
		//		int hour = cal.get(Calendar.HOUR);
		
		if(dayWeek<=3){
			dayStr = DAY_ARRAY[dayWeek-1];
		} else if(dayWeek>=5){
			dayStr = DAY_ARRAY[dayWeek];
		} else { 
			dayStr = DAY_ARRAY[dayWeek-1];
		}
		
		return dayStr;		
	}
	
	public String goodNum(StringBuilder goodNum,StringBuilder badNum){
		String dayOfWeek = getDayOfWeek(birthDay);
		goodNum.append(goodNumTab.getGoodNum(dayOfWeek));
		badNum.append( goodNumTab.getBadNum(dayOfWeek));
		return dayOfWeek;
	}

	
}
