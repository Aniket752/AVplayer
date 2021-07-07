package com.example.avplayer.Audio;

import android.net.Uri;

public class AudioFileData {
    private String name;
    private String artist;
    private String album;
    private int id;
    private int duration;
    private Uri uri;
    public AudioFileData(Uri uri,String name,String artist,String album,int id,int duration){
        this.name=name;
        this.uri=uri;
        this.artist=artist;
        this.album=album;
        this.duration=duration;
        this.id=id;
    }

    public Uri getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getId() {
        return id;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }
}
