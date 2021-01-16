package com.tp.notificationownessay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "channel1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Notification(View view) {
        String nameC = getString(R.string.NotificationChannel);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            final int importance = NotificationManager.IMPORTANCE_DEFAULT;
            //We create an intent to dial a number
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0606060606"));
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, nameC, importance);
            channel.setDescription("a test channel");
            NotificationCompat.Builder notifBuilder;
            notifBuilder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_baseline_call_split_24)
                    .setContentTitle("test").setContentText("test de notification");
            //We parse the intent to the notification builder in case of a click on it in the form of a pending intent
            //that has the same permissions given to our app; with parameter, the intent that holds our data
            notifBuilder.setContentIntent(PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT));
            notifBuilder.setChannelId(CHANNEL_ID);
            NotificationManager mNotificationManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(channel);
            mNotificationManager.notify(01, notifBuilder.build());
        } else {
            final int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationCompat.Builder notifBuilder;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:0606060606"));
            notifBuilder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_baseline_call_split_24)
                    .setContentTitle("test en cas de version<8").setContentText("test de notification en API<26");
            notifBuilder.setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
            notifBuilder.setSound(Uri.parse(String.valueOf(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))));
            NotificationManager mNotificationManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(02, notifBuilder.build());
        }
    }

}