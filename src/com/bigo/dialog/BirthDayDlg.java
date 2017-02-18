package com.bigo.dialog;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.bigo.activity.R;

public class BirthDayDlg {

	private final Calendar cal = Calendar.getInstance();
	private int year,month,day,hour,minute;
	private final Button btnTime,btnDate;
	private final Activity activity;
	
	public BirthDayDlg(Activity activity){
		this.activity = activity;
		btnTime = (Button) activity.findViewById(R.id.btnTime);
		btnDate = (Button) activity.findViewById(R.id.btnDate);
		
	}
	
	public void setBirthDayPicker() {
		//get current date
		final Calendar c = Calendar.getInstance(Locale.getDefault());
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		
		// set current date into date picker
		btnDate.setText(day + "-" + month + "-" + year);
		btnTime.setText(hour + ":" + minute);
	}
	
	private final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			cal.set(selectedYear, selectedMonth, selectedDay);
			btnDate.setText(selectedDay + "-" + selectedMonth + "-" + selectedYear);
		}
	};
	
	private final TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
			cal.set(Calendar.MINUTE,minute);
			
			String strMinute = "" + minute;
			if(minute<10){
				strMinute = "0" + minute;
			}
			btnTime.setText(hourOfDay + ":" + strMinute);
		}
	};
	
	public void showDate(View v) {
		DatePickerDialog dialog = new DatePickerDialog(activity, datePickerListener, year, month, day);
		dialog.show();
	}
	
	public void showTime(View v){
		TimePickerDialog dialog = new TimePickerDialog(activity, timePickerListener, hour, minute, true);
		dialog.show();
	}

	public Calendar getCalendar() {
		return this.cal;
	}
	
}
