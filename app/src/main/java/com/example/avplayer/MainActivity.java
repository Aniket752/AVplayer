package com.example.avplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    TabLayout tb;
    ViewPager vp;
    MAinClassViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkpermission();
    }
    public void checkpermission(){
        if(checkSelfPermission(READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            start();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{READ_EXTERNAL_STORAGE},2);
        }
    }
    public void start(){
        tb=(TabLayout)findViewById(R.id.tb_layout);
        vp=(ViewPager)findViewById(R.id.vp_page);
        tb.addTab(tb.newTab().setText("Music"));
        tb.addTab(tb.newTab().setText("Videos"));
        adapter dapter=new adapter(this,getSupportFragmentManager(),tb.getTabCount());
        vp.setAdapter(dapter);
        viewModel=new ViewModelProvider(this).get(MAinClassViewModel.class);
        vp.setCurrentItem(viewModel.getPgno());
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tb));
        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
                viewModel.setData(tab.getPosition()+1);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==2){
            boolean result=grantResults[0]==PackageManager.PERMISSION_GRANTED;
            if(result){
                start();
            }else{
                Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
            }
        }
    }
}