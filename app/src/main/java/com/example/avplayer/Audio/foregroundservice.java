package com.example.avplayer.Audio;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class foregroundservice extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("Inside");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            System.out.println("Services");
            Intent intent1 = new Intent(this, PlayAudio.class);
            PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intent1}, 0);
            Notification notifiction = new NotificationCompat.Builder(this, "Audio").setSubText("Hellow").setContentTitle("Music").setContentIntent(pendingIntent).build();
            startForeground(1,notifiction);
        }else{
            Intent intent1 = new Intent(this, foregroundservice.class);
            PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intent1}, 0);
            Notification.Builder builder=new Notification.Builder(this).setContentIntent(pendingIntent);
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
