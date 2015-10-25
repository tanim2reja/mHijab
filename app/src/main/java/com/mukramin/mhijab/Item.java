package com.mukramin.mhijab;

import android.graphics.Bitmap;

/**
 * Created by Tanim reja on 10/21/2015.
 */
public class Item {
    Bitmap bm ;
    String title;



    public Item(Bitmap bm,String title){
        this.bm=bm;
        this.title=title;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
