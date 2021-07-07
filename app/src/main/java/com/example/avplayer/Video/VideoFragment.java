package com.example.avplayer.Video;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.avplayer.Audio.AudioAdapter;
import com.example.avplayer.R;

import java.util.ArrayList;

public class VideoFragment extends Fragment {

    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_video, container, false);
        rv=(RecyclerView)v.findViewById(R.id.rv_video);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        VideoAdapter adapter=new VideoAdapter(getContext());
        rv.setAdapter(adapter);
        GetVideoFolder folder=new GetVideoFolder(getContext());
        ArrayList<String> list=folder.getData();
        adapter.setList(list);
        return v;
    }
}