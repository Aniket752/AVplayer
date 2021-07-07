package com.example.avplayer.Video;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.util.ArrayList;

public class GetVideosintheFolder {

    ArrayList<VideoList> list=new ArrayList<>();
    Context con;
    public GetVideosintheFolder(Context con){
        this.con=con;
    }
    public ArrayList<VideoList> getData(String folder){
        Uri uri;
        if(Build.VERSION_CODES.R<=Build.VERSION.SDK_INT){
            uri=MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        }else{
            uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
        String[] requires=new String[]{
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.ORIENTATION,
                MediaStore.Video.Media.DURATION,
        };
        String selection= MediaStore.Video.Media.BUCKET_DISPLAY_NAME+"==?";
        String[] selectionArgs = new String[] { folder};
        Cursor cursor=con.getContentResolver().query(
                uri,
                requires,
                selection,selectionArgs,
                null
        );
        int dname=cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
        int ori=cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ORIENTATION);
        int id=cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
        int dur=cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);
        int siz=cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
        while (cursor.moveToNext()){
            String name=cursor.getString(dname);
            String orientation=cursor.getString(ori);
            int idd=cursor.getInt(id);
            int size=cursor.getInt(siz);
            int runtime=cursor.getInt(dur);
            Uri contentUri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI, idd);
            list.add(new VideoList(contentUri,name,orientation,runtime,size));
        }
        return list;
    }
}
