package com.example.krishna.remidersample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText etTime;
    private Button setAlarm;
    private Button notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTime=(EditText) findViewById(R.id.etTime);
        setAlarm=(Button) findViewById(R.id.btnAlarm);
        notification=(Button) findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CreateNotificationActivity.class));
            }
        });
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time=Integer.parseInt(etTime.getText().toString());
                Intent intent=new Intent(MainActivity.this,Alarm.class);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+time*1000,pendingIntent);

            }
        });
    }

    public void reminder(View view) {
        startActivity(new Intent(MainActivity.this,RemiderCall.class));
    }

//    public void eminder(View view) {
//        startActivity(new Intent(MainActivity.this,ReminderActivity.class));
//    }

    public void setReminder(View view) {
        startActivity(new Intent(MainActivity.this,ReminderActivity.class));
    }
}
