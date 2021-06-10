package com.falezz.rabiakaraca_oyun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class OyunEkrani extends AppCompatActivity {
    private ConstraintLayout cl;
    private TextView textViewSkor;
    private TextView textViewOyunaBasla;
    private ImageView anakarakter;
    private ImageView saridaire;
    private ImageView siyahkare;
    private ImageView kirmiziücgen;

    //Pozisyonlar
    private int anakarakterX;
    private int anakarakterY;
    private int saridaireX;
    private int saridaireY;
    private int siyahkareX;
    private int siyahkareY;
    private int kirmiziücgenX;
    private int kirmiziücgenY;



    //Boyutlar
    private int ekranGenisligi;
    private int ekranYuksekligi;
    private int anakarakterGenisligi;
    private int anakarakterYuksekligi;

    //Hızlar
    private int anakarakterHiz;
    private int saridaireHiz;
    private int siyahkareHiz;
    private int kirmiziücgenHiz;

    //Kontroller
    private boolean dokunmaKontrol=false;
    private boolean baslangicKontrol=false;
    private Timer timer = new Timer();
    private Handler handler = new Handler();

    private int skor = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_ekrani);

        cl = findViewById(R.id.cl);
        textViewSkor = findViewById(R.id.textViewSkor);
        textViewOyunaBasla = findViewById(R.id.textViewOyunaBasla);
        anakarakter = findViewById(R.id.anakarakter);
        saridaire = findViewById(R.id.saridaire);
        siyahkare  =findViewById(R.id.siyahkare);
        kirmiziücgen = findViewById(R.id.kirmiziücgen);

        //Cisimleri Ekranın Dışına Çıkarma
        siyahkare.setX(-80);
        siyahkare.setY(-80);
        saridaire.setX(-80);
        saridaire.setY(-80);
        kirmiziücgen.setX(-80);
        kirmiziücgen.setY(-80);

        cl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(baslangicKontrol){

                    if (motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                        Log.e("MotionEvent","Ekrana dokunuldu");
                        dokunmaKontrol = true;
                    }
                    if (motionEvent.getAction()== MotionEvent.ACTION_UP){
                        Log.e("MotionEvent","Ekranı bıraktı");
                        dokunmaKontrol=false;
                    }

                }else{
                    baslangicKontrol = true;
                    textViewOyunaBasla.setVisibility(View.INVISIBLE);
                    anakarakterX =(int) anakarakter.getX();
                    anakarakterY =(int) anakarakter.getY();

                    anakarakterGenisligi = anakarakter.getWidth();
                    anakarakterYuksekligi =anakarakter.getHeight();
                    ekranGenisligi=cl.getWidth();
                    ekranYuksekligi=cl.getHeight();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    anakarakterHaraketEttirme();
                                    cisimleriHarakEtettir();
                                    carpismaKontrol();

                                }
                            });

                        }
                    },0,20);

                }

                return true;
            }
        });


    }
    public void anakarakterHaraketEttirme(){

        anakarakterHiz = Math.round(ekranYuksekligi/60);

        if(dokunmaKontrol){
            anakarakterY-=anakarakterHiz;
        }
        else {
            anakarakterY+=anakarakterHiz;
        }
        if(anakarakterY <=0){
            anakarakterY =0 ;
        }
        if(anakarakterY >= ekranYuksekligi-anakarakterYuksekligi){
            anakarakterY = ekranYuksekligi-anakarakterYuksekligi;
        }
        anakarakter.setY(anakarakterY);
    }

    public void cisimleriHarakEtettir(){
        saridaireHiz = Math.round(ekranGenisligi/60);
        siyahkareHiz = Math.round(ekranGenisligi/60);
        kirmiziücgenHiz = Math.round(ekranGenisligi/50);

        siyahkareX-=siyahkareHiz;

        if(siyahkareX < 0){
            siyahkareX = ekranGenisligi +20 ;
            siyahkareY = (int) Math.floor(Math.random()*ekranYuksekligi);
        }

        siyahkare.setX(siyahkareX);
        siyahkare.setY(siyahkareY);

        saridaireX-=saridaireHiz ;

        if(saridaireX < 0){
            saridaireX = ekranGenisligi +20 ;
            saridaireY = (int) Math.floor(Math.random()*ekranYuksekligi);
        }

        saridaire.setX(saridaireX);
        saridaire.setY(saridaireY);

        kirmiziücgenX-=kirmiziücgenHiz;

        if(kirmiziücgenX < 0){
            kirmiziücgenX = ekranGenisligi +20 ;
            kirmiziücgenY = (int) Math.floor(Math.random()*ekranYuksekligi);
        }

        kirmiziücgen.setX(kirmiziücgenX);
        kirmiziücgen.setY(kirmiziücgenY);

    }

    public void carpismaKontrol(){

        int saridaireMerkezX = saridaireX + saridaire.getWidth()/2;
        int saridaireMerkezY = saridaireY + saridaire.getHeight()/2;

        if(0 <=saridaireMerkezX && saridaireMerkezX <= anakarakterGenisligi && anakarakterY<=saridaireMerkezY
                && saridaireMerkezY<=anakarakterY+anakarakterYuksekligi){
            skor+=20;
            saridaireX=-10;

        }

        int kirmiziücgenMerkezX = kirmiziücgenX +kirmiziücgen.getWidth()/2;

        int kirmiziücgenMerkezY = kirmiziücgenY+ kirmiziücgen.getHeight()/2;

        if(0 <=kirmiziücgenMerkezX && kirmiziücgenMerkezX <= anakarakterGenisligi && anakarakterY<=kirmiziücgenMerkezY
                && kirmiziücgenMerkezY<=anakarakterY+anakarakterYuksekligi){

            skor+=50;
            kirmiziücgenX =-10;

        }

        int siyahkareMerkezX = siyahkareX + siyahkare.getWidth()/2;
        int siyahkareMerkezY = siyahkareY + siyahkare.getHeight()/2;

        if(0 <=siyahkareMerkezX && siyahkareMerkezX <= anakarakterGenisligi && anakarakterY<=siyahkareMerkezY
                && siyahkareMerkezY<=anakarakterY+anakarakterYuksekligi){

            siyahkareX=-10;

            //Timer Durdurma.
            timer.cancel();
            timer= null;

            Intent intent = new Intent(OyunEkrani.this,SonucEkrani.class);
            intent.putExtra("skor",skor);
            startActivity(intent);

        }
        textViewSkor.setText(String.valueOf(skor));
    }

}

