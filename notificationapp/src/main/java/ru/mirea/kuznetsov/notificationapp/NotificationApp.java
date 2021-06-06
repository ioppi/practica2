package ru.mirea.kuznetsov.notificationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import ru.mirea.koskin.notificationapp.R;

public class NotificationApp extends AppCompatActivity {
    private static final String CHANNEL_ID = "ru.mirea.asd.notification.ANDROID";
    private int IDENTIFICATE_MSG = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_app);


    }
    public void onClickSendNotification(View view) {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Create PendingIntent
        Intent resultIntent = new Intent(this, NotificationApp.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "My " +
                    "Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        // Create Notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Title")
                        .setContentText("Notification text for MIREA")
                        .setWhen(System.currentTimeMillis())
                        .setProgress(100, 50, false)
                        .setContentIntent(resultPendingIntent);
        Notification notification = builder.build();
        // Show Notification
        notificationManager.notify(IDENTIFICATE_MSG++, notification);
    }
}