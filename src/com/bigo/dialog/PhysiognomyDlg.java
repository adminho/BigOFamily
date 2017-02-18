package com.bigo.dialog;

import java.util.Collection;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

import com.bigo.activity.R;

public class PhysiognomyDlg {

	public Activity activity; 
	public PhysiognomyDlg(Activity activity){
		this.activity = activity;
	}
	

	public void showConfrim() {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage(activity.getString(R.string.txt_cannot_suport));
		builder.setNeutralButton("OK", null);
		builder.show();		
	}
	
	public void showPhoneContact(final Map<String,String> dataMap) {
		final EditText phoneTxt = (EditText) activity.findViewById(R.id.fPhone);
		
		int size = dataMap.size();
		if(size == 0){//Not found any mobile no 
		  	Toast.makeText(activity, "Not found any moible no ", Toast.LENGTH_LONG).show();
		   	return;//Finish
		 }
		
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
            	dialog.dismiss();
            }
        });
        
        builder.setIcon(R.drawable.smile);
        builder.setTitle("À¡“¬‡≈¢‚∑√»—∑Ï");
        
        AlertDialog alert = builder.create();
        alert.show();
	}
}
