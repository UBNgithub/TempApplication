package com.mygeekbranch.tempapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class WeatherFireBaseService extends FirebaseMessagingService {
    private  int messageId = 0;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        Log.d("WeatherMessage", message.getNotification().getBody());
        String title = message.getNotification().getTitle();
        if (title == null){
            title = "Weather Message";
        }

        String text = message.getNotification().getBody();
        // Нотификация
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "2")
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(title)
                .setContentText(text);
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());

    }
}

