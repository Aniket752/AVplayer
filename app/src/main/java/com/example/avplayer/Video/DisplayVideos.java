package com.example.avplayer.Video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.avplayer.R;

import java.util.ArrayList;

public class DisplayVideos extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<VideoList> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_videos);
        rv=(RecyclerView)findViewById(R.id.Rv_VideoFiles);
        Intent intent=getIntent();
        String folder=intent.getStringExtra("Folder");
        GetVideosintheFolder videosintheFolder=new GetVideosintheFolder(DisplayVideos.this);
        list=videosintheFolder.getData(folder);
        rv.setLayoutManager(new LinearLayoutManager(this));
        InsideFolderAdapter adapter=new InsideFolderAdapter(DisplayVideos.this);
        rv.setAdapter(adapter);
        adapter.getList(list);
    }
}