package com.example.avplayer.Video;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.example.avplayer.R;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.Collections;

import javax.security.auth.Subject;

public class PlayVideo extends AppCompatActivity {

    SimpleExoPlayer player;
    SeekBar volume,brightness,seek;
    RelativeLayout layout;
    float Startx=0,Starty =0;
    GestureDetector gestureDetector;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_play_video);
        volume=(SeekBar) findViewById(R.id.volume);
        brightness=(SeekBar) findViewById(R.id.brightness);
        seek=(SeekBar) findViewById(R.id.seek);
        layout=(RelativeLayout)findViewById(R.id.videoplayer);
        seek.setPadding(0, (int) (Resources.getSystem().getDisplayMetrics().widthPixels/2),0,Resources.getSystem().getDisplayMetrics().widthPixels/14);
        volume.getLayoutParams().width= (int) (Resources.getSystem().getDisplayMetrics().heightPixels/1.4);
        brightness.getLayoutParams().width= (int) (Resources.getSystem().getDisplayMetrics().heightPixels/1.4);
        brightness.getLayoutParams().height=Resources.getSystem().getDisplayMetrics().widthPixels/2;
        volume.getLayoutParams().height=Resources.getSystem().getDisplayMetrics().widthPixels/2;
        Intent intent=getIntent();
        gestureDetector=new GestureDetector(PlayVideo.this,new PlayVideo.SingleTap());
        ArrayList<String> Array= (ArrayList<String>) intent.getBundleExtra("Bundel").getSerializable("Array");
        int pos=0;
        String urip=intent.getStringExtra("uri");
        String Duration=intent.getStringExtra("Duration");
        player= new SimpleExoPlayer.Builder(this).build();
        PlayerView playerView=(PlayerView) findViewById(R.id.exoplayerview);
        playerView.setPlayer(player);
        ArrayList<MediaItem> list=new ArrayList<>();
        for(int i=0;i<Array.size();i++) {
            String uri=Array.get(i);
            list.add(MediaItem.fromUri(Array.get(i)));
        }
        for(int i=0;i<Array.size();i++){
            if(Array.get(i).equals(urip)){
                pos=i;
            }
        }
        for(int i=0;i<Array.size();i++)
            player.addMediaItems(i,Collections.singletonList(list.get(i)));
        player. seekTo(pos, C.TIME_UNSET);
        player.setPlayWhenReady(true);
        player.play();
        player.stop();
        player.pause();
        brightness.setMin(0);
        brightness.setMax(255);
        volume.setMin(0);
        volume.setMax(15);
        AudioManager ad= (AudioManager) getSystemService(PlayVideo.this.AUDIO_SERVICE);
        int vol=ad.getStreamVolume(AudioManager.STREAM_MUSIC);
        volume.setProgress(vol);
        int Brightness= 0;
        try {
            Brightness = Settings.System.getInt(PlayVideo.this.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        int finalBrightness = Brightness;
        brightness.setProgress(finalBrightness);
        player.setSeekParameters(SeekParameters.DEFAULT);
        seek.setMax(Integer.parseInt(Duration));
        seek.setMin(0);
        playerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        playerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(gestureDetector.onTouchEvent(event)){
                    return true;
                }else{
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            seek.setProgress((int) player.getCurrentPosition());
                            Startx=event.getX();
                            Starty=event.getY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            float endy = event.getY();
                            float endx=event.getX();
                            float distancex=Startx-endx;
                            float distancey = Starty - endy;
                            System.out.println(Startx +" "+Resources.getSystem().getDisplayMetrics().widthPixels/2);
                            if(Startx<=Resources.getSystem().getDisplayMetrics().widthPixels/2 && (distancex<0.5  && distancex>=-0.5)){
                                System.out.println(distancey);
                                playerView.hideController();
                                double minydis=0.5;
                                double minxdis=0.5;
                                volume.setVisibility(View.VISIBLE);
                                volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                        ad.setStreamVolume(AudioManager.STREAM_MUSIC,progress,AudioManager.FLAG_SHOW_UI);
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {
                                        volume.setVisibility(View.INVISIBLE);
                                    }
                                });
                            }else if(Startx>Resources.getSystem().getDisplayMetrics().widthPixels/2 && (distancex<0.5 && distancex>=-0.5) ) {
                                double minydis = 0.5;
                                double minxdis = 0.5;
                                brightness.setVisibility(View.VISIBLE);
                                brightness.setMax(255);
                                brightness.setMin(0);
                                final int[] BRIGHTNESS = {0};
                                try {
                                    BRIGHTNESS[0] = Settings.System.getInt(PlayVideo.this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
                                } catch (Settings.SettingNotFoundException e) {
                                    e.printStackTrace();
                                }
                                brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                        playerView.hideController();
                                        Settings.System.getInt(PlayVideo.this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, progress);
                                        WindowManager.LayoutParams layoutParams= getWindow().getAttributes();
                                        layoutParams.screenBrightness=progress/(float) 255;
                                        BRIGHTNESS[0] =progress;
                                        getWindow().setAttributes(layoutParams);
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {
                                        Settings.System.getInt(PlayVideo.this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, BRIGHTNESS[0]);
                                        WindowManager.LayoutParams layoutParams= getWindow().getAttributes();
                                        layoutParams.screenBrightness= BRIGHTNESS[0] /(float) 255;
                                        getWindow().setAttributes(layoutParams);
                                        brightness.setVisibility(View.INVISIBLE);
                                    }
                                });
                            }else if((distancey<=2.5 && distancey>=-2.5) && (distancex>=5 || distancex<=-5)){
                                seek.setVisibility(View.VISIBLE);
                                seek.incrementProgressBy(1);
                                seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                        player.seekTo(progress);
                                        player.setPlayWhenReady(true);
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {
                                        seek.setVisibility(View.GONE);
                                    }
                                });
                            }
                            break;

                    }
                }
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        player.play();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.stop();
    }
    public class SingleTap extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return true;
        }
    }
}