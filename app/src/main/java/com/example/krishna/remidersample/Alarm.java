package com.example.krishna.remidersample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by pc41 on 15-02-2018.
 */

public class Alarm extends BroadcastReceiver {
    Context context;
    Uri uriAlarm;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        Toast.makeText(context, "Alarm.............", Toast.LENGTH_SHORT).show();
        Vibrator vibrator=(Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(10000);
//        uriAlarm= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        createNotification();

    }

    public void createNotification() {
        Intent intent= new Intent(context,NotificationReceiverActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,(int)System.currentTimeMillis(),intent,0);
        Notification notification=new Notification.Builder(context)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND)
//                .setDefaults(RingtoneManager.TYPE_RINGTONE)
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher, "Call", pendingIntent)
                .addAction(R.mipmap.ic_launcher, "More", pendingIntent)
                .addAction(R.mipmap.ic_launcher, "And more", pendingIntent).build();
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
}
