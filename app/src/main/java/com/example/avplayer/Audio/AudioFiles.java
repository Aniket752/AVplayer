package com.example.avplayer.Audio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.avplayer.R;

import java.util.ArrayList;

public class AudioFiles extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<AudioFileData> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_files);
        rv=(RecyclerView)findViewById(R.id.rv_audiofiles);
        rv.setLayoutManager(new LinearLayoutManager(AudioFiles.this));
        AudioFileDataAdapter adapte=new AudioFileDataAdapter(this);
        rv.setAdapter(adapte);
        Intent intent=getIntent();
        String folder=intent.getStringExtra("Folder");
        GettingAudioData Data= new GettingAudioData(getApplicationContext());
        list=Data.getData(folder);
        adapte.getList(list);
    }
}