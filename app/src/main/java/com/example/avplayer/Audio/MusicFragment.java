package com.example.avplayer.Audio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.avplayer.R;

import java.util.ArrayList;

public class MusicFragment extends Fragment {

    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_music, container, false);
        rv=(RecyclerView)v.findViewById(R.id.rv_audio);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        AudioAdapter adapter=new AudioAdapter(getContext());
        rv.setAdapter(adapter);
        GetAudioFiles files=new GetAudioFiles(getContext());
        ArrayList<String> list=files.getData();
        adapter.setList(list);
        return v;
    }
}