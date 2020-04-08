package com.example.json;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ButtonsActivity extends AppCompatActivity  implements TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener{
    TextView mtextView_datepicker;
    Button mbtn_datepicker;
    Context mContext = this;

    TextView mtextView_timepicker;
    Button mbtn_timepicker;

    TextView time;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons);

        time = findViewById(R.id.textView_timepicker);
        date = findViewById(R.id.textView_datepicker);


        mtextView_datepicker = (TextView) findViewById(R.id.textView_datepicker);

        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        mbtn_datepicker = (Button) findViewById(R.id.btn_datepicker);
        mbtn_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        mtextView_datepicker.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, (month + 1), day);

                datePickerDialog.show();
            }
        });

        mtextView_timepicker = (TextView) findViewById(R.id.textView_timepicker);

        Calendar calendar2 = Calendar.getInstance();

        final int hour = calendar2.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar2.get(Calendar.MINUTE);

        mbtn_timepicker = (Button) findViewById(R.id.btn_timepicker);
        mbtn_timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mtextView_timepicker.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));

                timePickerDialog.show();
            }
        });



        Button buttonTimePicker = findViewById(R.id.button_timepicker);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDatePicker();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimePicker();
            }
        });

        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                startAlarm(c);
            }
        });



    }



    private void startDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                mContext,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    private void startTimePicker(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                mContext,
                this,
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                false
        );
        timePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String mdate = dayOfMonth + " " + month + " " + year;
        Calendar c = Calendar.getInstance();
        date.setText(mdate);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR,year);
    }



    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String mtime = hourOfDay + " " + minute;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        time.setText(mtime);

    }


    public void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }


}
