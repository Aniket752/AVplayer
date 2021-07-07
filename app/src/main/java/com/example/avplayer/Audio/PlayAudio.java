package com.example.avplayer.Audio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.avplayer.R;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;

public class PlayAudio extends AppCompatActivity {

    SimpleExoPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);
        Intent intent=getIntent();
        player= new SimpleExoPlayer.Builder(this).build();
        String uri=intent.getStringExtra("uri");
        PlayerView playerView=(PlayerView) findViewById(R.id.playaudio);
        playerView.setPlayer(player);
        playerView.showController();
        playerView.setControllerHideOnTouch(false);
        playerView.setControllerShowTimeoutMs(-1);
        MediaItem it=MediaItem.fromUri(uri);
        ArrayList<MediaItem> list=new ArrayList<>();
        list.add(it);
        playerView.setControllerHideOnTouch(false);
        player.addMediaItems(0,list);
        player.prepare();
        player.play();
        player.stop();
        player.pause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.stop();
    }
}