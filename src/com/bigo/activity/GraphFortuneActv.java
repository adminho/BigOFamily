package com.bigo.activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class GraphFortuneActv extends Activity {

	private int year,month,day,hour,minute;
	Button btnTime,btnDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graph_fortune);
		
		setCurrentView();
	}


	public void setCurrentView() {
		btnTime = (Button) findViewById(R.id.btnTime);
		btnDate = (Button) findViewById(R.id.btnDate);
		
		//get current date
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		
		// set current date into date picker
		btnDate.setText(day + "-" + month + "-" + year);
		btnTime.setText(hour + ":" + minute);
	}
	
	public void onConfrim(View view){
		Intent intent = new Intent(this,DisplayGraphActv.class);
		startActivity(intent);
	}
	
	public void onClickDate(View v) {
		DatePickerDialog dialog = new DatePickerDialog(this, datePickerListener, year, month, day);
		dialog.show();
	}
	
	public void onClickTime(View v){
		TimePickerDialog dialog = new TimePickerDialog(this, timePickerListener, hour, minute, true);
		dialog.show();
	}
	
	private final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			btnDate.setText(selectedDay + "-" + selectedMonth + "-" + selectedYear);
		}
	};
	
	private final TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			btnTime.setText(hourOfDay + ":" + minute);
		}
	};
	
	
}
