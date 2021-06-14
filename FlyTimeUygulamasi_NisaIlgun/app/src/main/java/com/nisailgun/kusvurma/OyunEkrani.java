package com.nisailgun.kusvurma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OyunEkrani extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean İsPlaying,oyunBitti=false;
    private int ekranX, ekranY,skor=0;
    public static float ekranBuyuklukX, ekranBuyuklukY;
    private Arkaplan arkaplan1, arkaplan2;
    private Ucak ucak;
    private Paint paint;
    private Kus[] kuslar;
    private SharedPreferences prefs;
    private Random rastgele;
    private List<Kursun> kursunlar;
    private OyunActivity activity;

    public OyunEkrani(OyunActivity activity, int ekranX, int ekranY) {
        super(activity);
        this.activity=activity;
        prefs=activity.getSharedPreferences("oyun",Context.MODE_PRIVATE);
        this.ekranX=ekranX;
        this.ekranY=ekranY;
        ekranBuyuklukX=1920f /ekranX;
        ekranBuyuklukY=1080f /ekranY;


        arkaplan1=new Arkaplan(ekranX,ekranX,getResources());
        arkaplan2=new Arkaplan(ekranX,ekranY,getResources());

        ucak=new Ucak(this,ekranY,getResources());
        kursunlar= new ArrayList<>();

        arkaplan2.x=ekranX;

        paint=new Paint();

        paint.setTextSize(128);

        paint.setColor(Color.WHITE);

        kuslar=new Kus[4];

        for(int i=0; i < 4; i++)  {
            Kus kus=new Kus(getResources());
            kuslar[i] =kus;
        }
        rastgele=new Random();
    }

    @Override
    public void run() {
        while(İsPlaying){
            update();
            draw();
            sleep();


        }
    }
    private void update(){
        arkaplan1.x -=10*ekranBuyuklukX;
        arkaplan2.x -=10*ekranBuyuklukX;

        if(arkaplan1.x +arkaplan1.arkaplan.getWidth() <0){
            arkaplan1.x=ekranX;
        }
        if(arkaplan2.x +arkaplan2.arkaplan.getWidth() <0){
            arkaplan2.x=ekranX;
        }
        if(ucak.yukariGit)
            ucak.y -= 30 * ekranBuyuklukY;
        else
            ucak.y +=30 * ekranBuyuklukY;
        if(ucak.y < 0)
            ucak.y=0;
        if(ucak.y >= ekranY - ucak.yukseklik)
            ucak.y=ekranY- ucak.yukseklik;

        List<Kursun> sacma =new ArrayList<>();

        for (Kursun kursun :kursunlar) {
            if(kursun.x> ekranX)
                sacma.add(kursun);
            kursun.x +=50*ekranBuyuklukX;

            for(Kus kus :kuslar) {
                if(Rect.intersects(kus.getCarpismaKontrol(),kursun.getCarpismaKontrol())) {
                    skor++;
                    kus.x=-500;
                    kursun.x=ekranX+500;
                    kus.vurulduKus=true;
                }

            }
        }
        for (Kursun kursun : sacma)
            kursunlar.remove(kursun);

        for(Kus kus:kuslar) {
            kus.x -=kus.hiz;

            if(kus.x + kus.genislik < 0) {
                if(!kus.vurulduKus) {
                    oyunBitti=true;
                    return;
                }

                int rastgeleyeBagli=(int) (30 * ekranBuyuklukX);
                kus.hiz=rastgele.nextInt(rastgeleyeBagli);

                if(kus.hiz <10 * ekranBuyuklukX)
                    kus.hiz=(int) (10 * ekranBuyuklukX);

                kus.x =ekranX;
                kus.y=rastgele.nextInt(ekranY-kus.yukseklik);

                kus.vurulduKus = false;
            }
            if(Rect.intersects(kus.getCarpismaKontrol(),ucak.getCarpismaKontrol())) {
                oyunBitti=true;
                return;
            }
        }

    }
    private void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas =getHolder().lockCanvas();
            canvas.drawBitmap(arkaplan1.arkaplan, arkaplan1.x, arkaplan1.y, paint);
            canvas.drawBitmap(arkaplan2.arkaplan, arkaplan2.x, arkaplan2.y, paint);

            for(Kus kus: kuslar)
                canvas.drawBitmap(kus.getKus(),kus.x,kus.y,paint);

            canvas.drawBitmap(ucak.getUcak(),ucak.x,ucak.y,paint);

            canvas.drawText(skor+"",ekranX/2f,164,paint);

            if(oyunBitti) {
                İsPlaying =false;
                canvas.drawBitmap(ucak.getKaza(), ucak.x,ucak.y,paint);
                kaydetEnYuksekSkor();
                bekleSonraCik();
                getHolder().unlockCanvasAndPost(canvas);
                return;
            }


            for (Kursun kursun:kursunlar)
                canvas.drawBitmap(kursun.kursun, kursun.x, kursun.y,paint);

            getHolder().unlockCanvasAndPost(canvas);
        }

    }

    private void bekleSonraCik() {
        try {
            Thread.sleep(2000);
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void kaydetEnYuksekSkor() {
        if(prefs.getInt("yuksekskor",0)<skor) {
            SharedPreferences.Editor editor=prefs.edit();
            editor.putInt("yuksekskor",skor);
            editor.apply();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        İsPlaying=true;
        thread=new Thread(this);
        thread.start();

    }
    public void pause (){
        try {
            İsPlaying=false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(event.getX() <ekranX/2){
                    ucak.yukariGit =true;
                }
                break;
            case MotionEvent.ACTION_UP:
                ucak.yukariGit=false;
                if(event.getX() > ekranX/2)
                    ucak.atesEt++;
                break;

        }
        return true;
    }

    public void Kursun() {
        Kursun kursun=new Kursun(getResources());
        kursun.x =ucak.x+ucak.genislik;
        kursun.y=ucak.y+(ucak.yukseklik/2);
        kursunlar.add(kursun);

    }
}
