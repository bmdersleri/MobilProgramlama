package com.mustafa.seyahatajandasi.Singleton;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Singleton{

    private Bitmap bitmap;
    private static Singleton singleton;

    public static  Singleton getInstance(){
           if (singleton == null){
               singleton = new Singleton();
           }
           return singleton;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
