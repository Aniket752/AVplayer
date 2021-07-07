package com.example.avplayer.Video;

import android.net.Uri;

import java.io.Serializable;

public class VideoList  {
    private final Uri uri;
    private final String ori;
    private final String name;
    private final int duration;
    private final int size;
    public VideoList(Uri uri, String name,String ori, int duration, int size) {
        this.uri = uri;
        this.ori=ori;
        this.name = name;
        this.duration = duration;
        this.size = size;
    }

    public String getOri() {
        return ori;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getSize() {
        return size;
    }

    public Uri getUri() {
        return uri;
    }

}
