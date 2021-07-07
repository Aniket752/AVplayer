package com.example.avplayer.Audio;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.core.content.PermissionChecker;

import java.security.Permission;
import java.security.Permissions;
import java.util.ArrayList;

public class GetAudioFiles {
    Context con;
    GetAudioFiles(Context con){
        this.con=con;
    }
    ArrayList<String > list=new ArrayList<>();
    public ArrayList<String> getData(){
        Uri uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] requrie=new String[]{
                MediaStore.Audio.Media.BUCKET_DISPLAY_NAME,
        };
        Cursor cursor=con.getContentResolver().query(
                uri,
                requrie,
                null,
                null,
                MediaStore.Audio.Media.DISPLAY_NAME
        );
        int name=cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()){
            String FolderName=cursor.getString(name);
            if(!list.contains(FolderName)){
                list.add(FolderName);
            }
        }
        return list;
    }

}
