package com.example.geoman;

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

public class OyunEkraniActivity extends AppCompatActivity {

    private ConstraintLayout cl;
    private TextView textViewSkor;
    private TextView textViewOyunaBasla;
    private ImageView saridaire;
    private ImageView kirmiziucgen;
    private ImageView siyahkare;
    private ImageView anakarakter;

    private int anakarakterX;
    private int anakarakterY;
    private int saridaireX;
    private int saridaireY;
    private int kirmiziucgenX;
    private int kirmiziucgenY;
    private int siyahkareX;
    private int siyahkareY;

    private int ekranGenisligi;
    private int ekranYuksekligi;
    private int anakarakterGenisligi;
    private int anakarakterYuksekligi;

    private int anakarakterHiz;
    private int saridaireHiz;
    private int kirmiziucgenHiz;
    private int siyahkareHiz;

    private int skor = 0;


    private boolean dokunmaKontrol=false;
    private boolean baslangicKontrol=false;

    private Timer timer = new Timer();
    private Handler handler =new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_ekrani);

        cl = findViewById(R.id.cl);
        textViewSkor = findViewById(R.id.textViewSkor);
        textViewOyunaBasla = findViewById(R.id.textViewOyunaBasla);
        saridaire = findViewById(R.id.saridaire);
        kirmiziucgen = findViewById(R.id.kirmiziucgen);
        siyahkare = findViewById(R.id.siyahkare);
        anakarakter = findViewById(R.id.anakarakter);

        siyahkare.setY(-80);
        siyahkare.setX(-80);
        saridaire.setY(-80);
        saridaire.setX(-80);
        kirmiziucgen.setY(-80);
        kirmiziucgen.setX(-80);



        cl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (baslangicKontrol){

                    if (event.getAction()== MotionEvent.ACTION_DOWN ){
                        Log.e("MotionEvent","Ekrana dokunuldu");
                        dokunmaKontrol=true;


                    }
                    if (event.getAction()== MotionEvent.ACTION_UP ){
                        Log.e("MotionEvent","Ekranı bıraktı");
                        dokunmaKontrol=false;


                    }

                }else{
                    baslangicKontrol= true;

                    textViewOyunaBasla.setVisibility(View.INVISIBLE);

                    anakarakterX = (int) anakarakterX;
                    anakarakterY = (int) anakarakterY;

                    anakarakterYuksekligi = anakarakter.getHeight();
                    anakarakterGenisligi= anakarakter.getWidth();
                    ekranGenisligi=cl.getWidth();
                    ekranYuksekligi=cl.getHeight();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    anakarakterHarketEttirme();
                                    CisimleriHareketEttir();
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
    public void anakarakterHarketEttirme(){

        anakarakterHiz=Math.round(ekranYuksekligi/60);

        if (dokunmaKontrol){
            anakarakterY-=anakarakterHiz;
        }else{
            anakarakterY+=anakarakterHiz;
        }

        if (anakarakterY<=0){
            anakarakterY=0;

        }
        if (anakarakterY >= ekranYuksekligi - anakarakterYuksekligi ){
            anakarakterY = ekranYuksekligi - anakarakterYuksekligi;

        }
        anakarakter.setY(anakarakterY);
    }
    public void CisimleriHareketEttir(){

        siyahkareHiz=Math.round(ekranGenisligi/45);
        saridaireHiz=Math.round(ekranGenisligi/60);
        kirmiziucgenHiz=Math.round(ekranGenisligi/30);

        siyahkareX-=siyahkareHiz;

        if (siyahkareX < 0){
            siyahkareX = ekranGenisligi +20;
            siyahkareY = (int) Math.floor((Math.random())*ekranYuksekligi);

        }

        siyahkare.setX(siyahkareX);
        siyahkare.setY(siyahkareY);


        saridaireX-=saridaireHiz;

        if (saridaireX < 0){
            saridaireX = ekranGenisligi +20;
            saridaireY = (int) Math.floor((Math.random())*ekranYuksekligi);

        }

        saridaire.setX(saridaireX);
        saridaire.setY(saridaireY);



        kirmiziucgenX-=kirmiziucgenHiz;

        if (kirmiziucgenX < 0){
            kirmiziucgenX = ekranGenisligi +20;
            kirmiziucgenY = (int) Math.floor((Math.random())*ekranYuksekligi);

        }

        kirmiziucgen.setX(kirmiziucgenX);
        kirmiziucgen.setY(kirmiziucgenY);



    }
    public void carpismaKontrol(){
        int saridaireMerkezX = saridaireX + saridaire.getWidth()/2;
        int saridaireMerkezY = saridaireY + saridaire.getHeight()/2;

        if (0 <= saridaireMerkezX && saridaireMerkezX <= anakarakterGenisligi && anakarakterY <= saridaireMerkezY &&
                saridaireMerkezY <= anakarakterY + anakarakterYuksekligi){
            skor +=20;
            saridaireX = -10;



        }
        int kirmiziucgenMerkezX = kirmiziucgenX + kirmiziucgen.getWidth()/2;
        int kirmiziucgenMerkezY = kirmiziucgenY + kirmiziucgen.getHeight()/2;

        if (0 <= kirmiziucgenMerkezX && kirmiziucgenMerkezX <= anakarakterGenisligi && anakarakterY <= kirmiziucgenMerkezY &&
                kirmiziucgenMerkezY <= anakarakterY + anakarakterYuksekligi){
            skor +=50;
            kirmiziucgenX = -10;



        }
        int siyahkareMerkezX = siyahkareX + siyahkare.getWidth()/2;
        int siyahkareMerkezY = siyahkareY + siyahkare.getHeight()/2;

        if (0 <= siyahkareMerkezX && siyahkareMerkezX <= anakarakterGenisligi && anakarakterY <= siyahkareMerkezY &&
                siyahkareMerkezY <= anakarakterY + anakarakterYuksekligi){

            siyahkareX = -10;
            timer.cancel();
            timer = null;

            Intent intent = new Intent(OyunEkraniActivity.this, com.example.geoman.SonucEkraniActivity.class);
            intent.putExtra("Skor",skor);
            startActivity(intent);

        }

        textViewSkor.setText(String.valueOf(skor));
    }
}
