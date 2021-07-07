package com.example.avplayer;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.avplayer.Audio.MusicFragment;
import com.example.avplayer.Video.VideoFragment;

public class adapter extends FragmentStatePagerAdapter {
    Context con;
    int tabcount;
    public adapter(MainActivity mainActivity, @NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.con=con;
        this.tabcount=tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MusicFragment mf=new MusicFragment();
                return mf;
            case 1:
                VideoFragment vf=new VideoFragment();
                return vf;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
