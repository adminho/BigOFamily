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
public class PhoneFlgmt extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		Bundle bundle = getArguments();
		View rootView = inflater.inflate(R.layout.phone_flgmt,container, false);
		
		setView(rootView, bundle);
		return rootView;
	}
	
	public void setView(View rootView,Bundle bundle){
		int positionFlg = bundle.getInt(EasyFortuneAdapter.ARG_SECTION_NUMBER);
		Log.i(TAG.INFO, "postion page is " + positionFlg);
		
		TextView txtPhone = (TextView)rootView.findViewById(R.id.txtPhone);
		String phone = bundle.getString( EasyFortuneActv.PHONE_NUM);
		if(phone==null ||"".equals(phone)){
			txtPhone.setText( "��ҹ�ѧ������͡������" );
			return;
		}else{
			txtPhone.setText( phone );
		}
		
		TextView txtSumPhone = (TextView)rootView.findViewById(R.id.txtSumPhone);
		txtSumPhone.setText("������ͧ����Ţ��� " + bundle.getString(EasyFortuneActv.PHONE_SUMNUM));
		
		TextView txtAuspPhone = (TextView)rootView.findViewById(R.id.txtAuspPhone);
		String auspPhone = bundle.getString(EasyFortuneActv.AUSP_NUM);
		if(txtAuspPhone==null ||"".equals(txtAuspPhone)){
			txtAuspPhone.setText("����դ�������");
		}else{
			txtAuspPhone.setText( auspPhone);
		}
		
		TextView txtDay = (TextView)rootView.findViewById(R.id.txtBirthDay);
		txtDay.setText("�س�Դ�ѹ " +  bundle.getString(EasyFortuneActv.BIRTHDAY_OF_WEEK));
		
				
		TextView txtGodNum = (TextView)rootView.findViewById(R.id.txtGoodNum);
		String goodNum = bundle.getString(EasyFortuneActv.GOOD_NUM);
		String badNum = bundle.getString(EasyFortuneActv.BAD_NUM);
		
		String  msg = "����Ţ������÷��������ǧ�س��� " + goodNum +
				"\n����Ţ������÷�����������ǧ��� " +  badNum;
		txtGodNum.setText(msg);
		
		TextView txtFortune = (TextView)rootView.findViewById(R.id.txtFortune);
		String fortune = bundle.getString(EasyFortuneActv.FORECAST_NUM);
		if(fortune == null || "".equals(fortune)){
			txtFortune.setText("����ռš�÷ӧҹ");
		} else {
			txtFortune.setText(fortune);
		}
	}

}