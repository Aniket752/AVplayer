package com.example.avplayer.Video;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.avplayer.Audio.GetAudioFiles;

import java.util.ArrayList;

public class GetVideoFolder {
    Context con;
    ArrayList<String> list=new ArrayList<>();
    public GetVideoFolder(Context con){
        this.con=con;
    }
    public ArrayList<String> getData(){
        Uri uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] reuired=new String[]{
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME
        };
        Cursor cursor=con.getContentResolver().query(
                uri,
                reuired,
                null,
                null,
                MediaStore.Video.Media.DATE_ADDED
        );
        int name=cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()){
            String FolderName=cursor.getString(name);
            if(!list.contains(FolderName)){
                list.add(FolderName);
            }
        }
        return list;
    }
}
