package com.nisailgun.kusvurma;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.nisailgun.kusvurma.OyunEkrani.ekranBuyuklukX;
import static com.nisailgun.kusvurma.OyunEkrani.ekranBuyuklukY;

public class Kus {
    public int hiz=20;
    public boolean vurulduKus=true;
    int x=0,y,genislik,yukseklik,kusSay=1;
    Bitmap kus1,kus2,kus3,kus4;

    Kus(Resources res) {
        kus1= BitmapFactory.decodeResource(res , R.drawable.kus1);
        kus2= BitmapFactory.decodeResource(res , R.drawable.kus2);
        kus3= BitmapFactory.decodeResource(res , R.drawable.kus3);
        kus4= BitmapFactory.decodeResource(res , R.drawable.kus4);

        genislik= kus1.getWidth();
        yukseklik=kus1.getHeight();

        genislik /=6;
        yukseklik /=6;

        genislik*=(int) ekranBuyuklukX;
        yukseklik*=(int) ekranBuyuklukY;

        kus1=Bitmap.createScaledBitmap(kus1,genislik,yukseklik,false);
        kus2=Bitmap.createScaledBitmap(kus2,genislik,yukseklik,false);
        kus3=Bitmap.createScaledBitmap(kus3,genislik,yukseklik,false);
        kus4=Bitmap.createScaledBitmap(kus4,genislik,yukseklik,false);

        y=-yukseklik;
    }
    Bitmap getKus(){
        if(kusSay==1) {
            kusSay++;
            return kus1;
        }
        if(kusSay==2) {
            kusSay++;
            return kus2;
        }
        if(kusSay==3) {
            kusSay++;
            return kus3;
        }
        kusSay=1;
        return kus4;
    }
    Rect getCarpismaKontrol() {
        return new Rect(x,y,x+genislik,y+yukseklik);
    }
}
