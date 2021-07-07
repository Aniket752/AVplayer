package com.example.avplayer.Audio;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class Channel extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Createchannel();
    }

    public void Createchannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel=new NotificationChannel("Audio","Music", NotificationManager.IMPORTANCE_HIGH);
            channel.canBypassDnd();
            channel.setDescription("Music is playing");
            channel.setBypassDnd(true);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
