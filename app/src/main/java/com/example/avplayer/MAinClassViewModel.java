package com.example.avplayer;

import androidx.lifecycle.ViewModel;

public class MAinClassViewModel extends ViewModel {
    int pgno;
    public void setData(int pg){
        this.pgno=pg;
    }
    public int getPgno(){
        return this.pgno;
    }
}
