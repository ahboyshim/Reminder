package com.example.shim.reminder;

/**
 * Created by shim on 21/3/2015.
 */
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    private static final int MY_NOTIFICATION_ID=1;
    NotificationManager notificationManager;
    Notification myNotification;


    @Override
    public void onReceive(Context context, Intent intent) {

        Intent myIntent = new Intent(context, remindNow.class);
        myIntent.setAction(Long.toString(System.currentTimeMillis()));
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0, myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        myNotification = new NotificationCompat.Builder(context)
                .setContentTitle("Coffee Helper")
                .setContentText("You are reminded to buy somethings...")
                .setTicker("Notification from Coffee Helper!")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher)
                .build();

        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);


    }

}