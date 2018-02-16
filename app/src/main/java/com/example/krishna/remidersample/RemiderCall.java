package com.example.krishna.remidersample;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class RemiderCall extends AppCompatActivity {

    private LinearLayout btnReminderCalender, btnReminderTime;
    private String reminderTime = "", reminderDate = "", reminderDescription = "";
    private int mYear, mMonth, mDay, hours, minutes, sYear, sMonth, sDay, sHour, sMinute;
    private TextView setReminderDate, setReminderTime, textReminderType;

    private Button setReminderBtn;

    final static int RQS_1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remider_call);
        initialView();
        onClickBtn();
    }

    private void initialView() {

        setReminderBtn = (Button) findViewById(R.id.btn_set_reminder);
        btnReminderCalender = (LinearLayout) findViewById(R.id.ln_reminder_calender);
        btnReminderTime = (LinearLayout) findViewById(R.id.ln_reminder_time);
        setReminderDate = (TextView) findViewById(R.id.set_reminder_date);
        setReminderTime = (TextView) findViewById(R.id.set_reminder_time);
    }

    public void onClickBtn() {
        btnReminderCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        btnReminderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (bookingDate.equals("")) {
//                    CustomSnackBar customSnackBar = new CustomSnackBar();
//                    customSnackBar.make(v, "Please Enter Service Date First", Snackbar.LENGTH_SHORT);
//                } else {
//                    showTimePicker(v);
//                }
                showTimePicker(v);
            }
        });
        setReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar current = Calendar.getInstance();
                Calendar cal = Calendar.getInstance();
                cal.set(sYear,sMonth,sDay,sHour,sMinute,00);

                if (reminderDate.equals("")) {
                    Toast.makeText(RemiderCall.this, "Please enter Reminder date", Toast.LENGTH_SHORT).show();

                } else if (reminderTime.equals("")) {
                    Toast.makeText(RemiderCall.this, "Please enter Reminder time", Toast.LENGTH_SHORT).show();
                }
                else if(cal.compareTo(current) <= 0){
                    //The set Date/Time already passed
                    Toast.makeText(getApplicationContext(),
                            "Please enter right time and not the past time and present time",
                            Toast.LENGTH_LONG).show();
                }else if (sDay == mDay && sYear == mYear && sMonth == mMonth && sHour <= hours && sMinute <= minutes) {
                    Toast.makeText(RemiderCall.this, "Please enter right time and not the past time", Toast.LENGTH_SHORT).show();

                }else{
                    setAlarm(cal);
                }
            }
        });

    }
    private void setAlarm(Calendar targetCal){


        Toast.makeText(RemiderCall.this, "Alarm is set " + targetCal.getTime(), Toast.LENGTH_SHORT).show();

//        Intent intent = new Intent(getBaseContext(), Alarm.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
//        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        //////////////***********************use one alarm************///////////////
//
////        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
//
//        /////////////////////////**********use repeating alarm**********//////////////
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, 10);
//
//
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5*1000, pendingIntent);



//        AlarmManager mgrAlarm = (AlarmManager)getSystemService(ALARM_SERVICE);
//        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
//
//        for(int i = 0; i < 10; ++i)
//        {
//            Intent intent2 = new Intent(getBaseContext(), Alarm.class);
//            // Loop counter `i` is used as a `requestCode`
//            PendingIntent pi = PendingIntent.getBroadcast(getBaseContext(), i, intent2, 0);
//            // Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
//            mgrAlarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                    SystemClock.elapsedRealtime() + 60000 * i,
//                    pi);
//
//            intentArray.add(pi);
//        }

        Intent intent = new Intent(RemiderCall.this, Alarm.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        final int _id = (int) System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10);
        PendingIntent appIntent = PendingIntent.getBroadcast(this, _id, intent,PendingIntent.FLAG_ONE_SHOT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), appIntent);
        Intent intent1=new Intent(RemiderCall.this,MainActivity.class);
        startActivity(intent1);





    }


    //////////////////////////*******showDatePicker**********//////////////////////////
    private void showDatePicker() {
        Calendar mcurrentDate = Calendar.getInstance();
        mYear = mcurrentDate.get(Calendar.YEAR);
        mMonth = mcurrentDate.get(Calendar.MONTH);
        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDatePicker = new DatePickerDialog(RemiderCall.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                // TODO Auto-generated method stub
                sMonth = selectedmonth;
                sDay = selectedday;
                sYear = selectedyear;
                selectedmonth = selectedmonth + 1;
                if (selectedday >= 0 && selectedday < 10 && selectedmonth >= 0 && selectedmonth < 10) {
                    setReminderDate.setText("0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                } else if (selectedday >= 0 && selectedday < 10) {
                    setReminderDate.setText("0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                } else if (selectedmonth >= 0 && selectedmonth < 10) {
                    setReminderDate.setText(selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                } else {
                    setReminderDate.setText(selectedday + "/" + selectedmonth + "/" + selectedyear);
                }
                reminderDate = setReminderDate.getText().toString();
            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select date");
        mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        mDatePicker.show();
    }

    //////////////////////////*******showTimePicker**********//////////////////////////
    private void showTimePicker(View v) {
        final View view = v;
        final String times;
        final Calendar mcurrentTime = Calendar.getInstance();
        hours = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        minutes = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(RemiderCall.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                sHour = selectedHour;
                sMinute = selectedMinute;
                hours = hours+0;
                if (selectedHour >= 0 && selectedHour < 12) {
                    if (selectedMinute >= 0 && selectedMinute < 10 && selectedHour >= 0 && selectedHour < 10) {
                        setReminderTime.setText("0" + selectedHour + " : " + " 0" + selectedMinute + " AM");
                    } else if (selectedMinute >= 0 && selectedMinute < 10) {
                        setReminderTime.setText(selectedHour + " : " + " 0" + selectedMinute + " AM");
                    } else if (selectedHour >= 0 && selectedHour < 10) {
                        setReminderTime.setText("0" + selectedHour + " : " + selectedMinute + " AM");
                    } else {
                        setReminderTime.setText(selectedHour + " : " + selectedMinute + " AM");
                    }
                } else {
                    if (selectedHour == 12) {
                        if (selectedMinute >= 0 && selectedMinute < 10 && selectedHour >= 0 && selectedHour < 10) {
                            setReminderTime.setText("0" + selectedHour + " : " + " 0" + selectedMinute + " PM");
                        } else if (selectedMinute >= 0 && selectedMinute < 10) {
                            setReminderTime.setText(selectedHour + " : " + " 0" + selectedMinute + " PM");
                        } else if (selectedHour >= 0 && selectedHour < 10) {
                            setReminderTime.setText("0" + selectedHour + " : " + selectedMinute + " PM");
                        } else {
                            setReminderTime.setText(selectedHour + " : " + selectedMinute + " PM");
                        }
                    } else {
                        selectedHour = selectedHour - 12;
                        if (selectedMinute >= 0 && selectedMinute < 10 && selectedHour >= 0 && selectedHour < 10) {
                            setReminderTime.setText("0" + selectedHour + " : " + " 0" + selectedMinute + " PM");
                        } else if (selectedMinute >= 0 && selectedMinute < 10) {
                            setReminderTime.setText(selectedHour + " : " + " 0" + selectedMinute + " PM");
                        } else if (selectedHour >= 0 && selectedHour < 10) {
                            setReminderTime.setText("0" + selectedHour + " : " + selectedMinute + " PM");
                        } else {
                            setReminderTime.setText(selectedHour + " : " + selectedMinute + " PM");
                        }
                    }
                }
                reminderTime = setReminderTime.getText().toString();
            }
        }, hours, minutes, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
        // TODO Auto-generated method stub

    }

}
