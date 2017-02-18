package com.bigo.dialog;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigo.activity.R;

public class PhoneListDlg {

	public final Activity activity; 
	public PhoneListDlg(Activity activity){
		this.activity = activity;
	}
	
	public void showPhoneContact(final Map<String,String> dataMap) {
		final EditText phoneTxt = (EditText) activity.findViewById(R.id.phoneNumber);
		final TextView txtView = (TextView) activity.findViewById(R.id.mobileOper);
		
		int size = dataMap.size();
		if(size == 0){//Not found any mobile no 
		  	Toast.makeText(activity, "Not found any moible no ", Toast.LENGTH_LONG).show();
		   	return;//Finish
		 }
		   
		final Set<String> set = dataMap.keySet();//get person name
		final String []nameArray = set.toArray(new String[size]);
		
		Collection<String> values = dataMap.values();
		final String []detailArry = values.toArray(new String[size]);//get detail --> person name (08xxxxxx)
		
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setSingleChoiceItems(
        		detailArry,
        		-1,// does not select anything
        		
        new DialogInterface.OnClickListener() {
             
            @Override
            public void onClick( DialogInterface dialog, int which) {
            	String mobileCont = detailArry[which];
            	String number = mobileCont.substring( mobileCont.indexOf("(")+1,mobileCont.length()-1);
            	
            	phoneTxt.setText(number);//Set phone number
//            	TelephonyManager tm = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
            	txtView.setVisibility(View.VISIBLE);
            	txtView.setText(nameArray[which]);//Set person name
            	
            	dialog.dismiss();
            }
        });
        
        builder.setIcon(R.drawable.smile);
        builder.setTitle("หมายเลขโทรศัท์");

        AlertDialog alert = builder.create();
        alert.show();
	}
	
	public void showHeroScopeResult(String result) {
		AlertDialog.Builder alertbox = new AlertDialog.Builder(activity);
		alertbox.setNeutralButton("OK", null);
		alertbox.setMessage(result);
		alertbox.setTitle("ผลการดูดวง");
		alertbox.setIcon(android.R.drawable.btn_star_big_on);
//		alertbox.setIcon(R.drawable.smile);
//		alertbox.setIcon(android.R.drawable.ic_dialog_info);
		alertbox.show();
	}

	public static String DAY[] = {"ไม่เลือก","อาทิตย์","จันทร์","อังคาร","พุทธ","พฤหัส","ศุกร์","เสาร์"};
	
	public void showDay(){
		final Button btn = (Button) activity.findViewById(R.id.btn_day_of_week);
		
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setSingleChoiceItems(
        		DAY,
        		-1,// does not select anything
        		
        new DialogInterface.OnClickListener() {
             
            @Override
            public void onClick( DialogInterface dialog, int which) {
            	String text = null;
            	
            	if(which==0){
            		text = activity.getString(R.string.btn_brith_day_of_week);
            	}else{
            		text = DAY[which];
            	}
            	
            	btn.setText("คุณเกิดวัน " + text);//Set day
            	dialog.dismiss();
            }
        });
        
        builder.setIcon(R.drawable.smile);
        builder.setTitle("หมายเลขโทรศัท์");
        
        AlertDialog alert = builder.create();
        alert.show();
	}

}
