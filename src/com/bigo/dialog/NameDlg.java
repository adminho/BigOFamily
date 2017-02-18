package com.bigo.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;

import com.bigo.activity.R;

public class NameDlg {
	
	public Activity activity; 
	public NameDlg(Activity activity){
		this.activity = activity;
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

	public void showHeroScopeResult(String string) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage(activity.getString(R.string.txt_cannot_suport));
		builder.setNeutralButton("OK", null);
		builder.create().show();				
	}
}
