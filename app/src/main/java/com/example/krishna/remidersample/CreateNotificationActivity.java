package com.example.krishna.remidersample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);
    }

    public void createNotification(View view) {
        Intent intent= new Intent(this,NotificationReceiverActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,(int)System.currentTimeMillis(),intent,0);
        Notification notification=new Notification.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject").setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .addAction(R.mipmap.ic_launcher, "Call", pendingIntent)
                .addAction(R.mipmap.ic_launcher, "More", pendingIntent)
                .addAction(R.mipmap.ic_launcher, "And more", pendingIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
}
