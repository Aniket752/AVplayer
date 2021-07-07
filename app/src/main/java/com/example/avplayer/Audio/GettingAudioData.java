package com.example.avplayer.Audio;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

public class GettingAudioData {
    Context con;
    ArrayList<AudioFileData> list=new ArrayList<>();
    public GettingAudioData(Context con){
        this.con=con;
    }
    public ArrayList<AudioFileData> getData(String folder){
        Uri uri;
        uri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] needed=new String[]{
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DISPLAY_NAME,
        };
        String argrument=MediaStore.Audio.Media.BUCKET_DISPLAY_NAME+"==?";
        String[] arg=new String[]{
                folder
        };
        Cursor cursor=con.getContentResolver().query(
                uri,
                needed,
                argrument,
                arg,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER
        );
        int id=cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
        int duration=cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
        int album=cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);
        int artist=cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
        int name=cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
        while(cursor.moveToNext()){
            String Sname=cursor.getString(name);
            String Salbume=cursor.getString(album);
            String Sartist= cursor.getString(artist);
            int Sduration=cursor.getInt(duration);
            int sid=cursor.getInt(id);
            Uri contenturi= ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,sid);
            list.add(new AudioFileData(contenturi,Sname,Salbume,Sartist,Sduration,sid));
        }
        return list;
    }
}
