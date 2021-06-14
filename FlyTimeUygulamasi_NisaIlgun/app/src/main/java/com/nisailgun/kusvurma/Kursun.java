package com.nisailgun.kusvurma;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.nisailgun.kusvurma.OyunEkrani.ekranBuyuklukX;
import static com.nisailgun.kusvurma.OyunEkrani.ekranBuyuklukY;

public class Kursun {

    int x,y,genislik,yukseklik;
    Bitmap kursun;

    Kursun(Resources res) {
        kursun=BitmapFactory.decodeResource(res, R.drawable.kursun);
        genislik=kursun.getWidth();
        yukseklik=kursun.getHeight();
        
        genislik /=4;
        yukseklik /=4;

        genislik *=(int) ekranBuyuklukX;
        yukseklik *=(int) ekranBuyuklukY;

        kursun=Bitmap.createScaledBitmap(kursun,genislik,yukseklik,false);
    }
    Rect getCarpismaKontrol() {
        return new Rect(x,y,x+genislik,y+yukseklik);
    }
}
