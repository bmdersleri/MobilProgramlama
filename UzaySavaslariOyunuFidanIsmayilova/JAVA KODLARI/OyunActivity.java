
package com.nvmvzv.uzaysavasi.uzaysavasi.uzaysavasi;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Px;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.util.Random;

public class OyunActivity extends AppCompatActivity {


// GERİ TUŞUNUN BOŞ İŞLEM YAPMASINI SAĞLAR
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



// DEĞİŞKEN VE GÖRSEL TANIMLAMALARI
    int LazerTIP = 0;
    int Sbt;

    int delayZAMAN = 35;
    int delaySAYI = 0;

    int xxx;
    int yyy;

    String line;
    boolean netvar = true;
    BufferedReader reader;

    Boolean baglantivarmi = true;


    RelativeLayout mainLayout;
    ImageView imagexx;

// ACTIVITY BAŞLADIĞINDA İLK OKUNNAN KOD BLOĞU
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


// BAŞKA ACTIVITY DEN DEĞER ÇEKİYOR
        String Gemi = ShipActivity.GemiID.toString();

        if(Gemi.equals("1")==true){

// RESİM NESNESİNE GÖRSEL TANIMLANIYOR
            ImageView iv = (ImageView)findViewById(R.id.imageViewANA);
            iv.setImageResource(R.drawable.spaceship01);

        }

        if(Gemi.equals("2")==true){

            ImageView iv = (ImageView)findViewById(R.id.imageViewANA);
            iv.setImageResource(R.drawable.spaceship02);

        }

        if(Gemi.equals("3")==true){

            ImageView iv = (ImageView)findViewById(R.id.imageViewANA);
            iv.setImageResource(R.drawable.spaceship03);

        }

// EKRAN YÜKSEKLİĞİ VE GENİŞLİĞİ ALINIYOR
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        final int heightXXX = metrics.heightPixels;
        final int widthXXX = metrics.widthPixels;
// RASGELE SAYI DEĞERİ ÜRETİLİYOR
        Random rand = new Random();
        final int n = rand.nextInt(widthXXX - 20) + 20;
        final int n2 = rand.nextInt(widthXXX - 5) + 5;



        ImageView imageDUSMAN3 = (ImageView) findViewById(R.id.Dusman1);
        imageDUSMAN3.setImageResource(R.drawable.b1);


        ImageView imageDUSMAN2 = (ImageView) findViewById(R.id.Dusman2);
        imageDUSMAN2.setImageResource(R.drawable.c1);


        timerr2(heightXXX ,widthXXX ,n ,n2, zamann );


        mainLayout = (RelativeLayout) findViewById(R.id.zemin2);

        imagexx = (ImageView) findViewById(R.id.imageViewANA);
        imagexx.setZ(11);
        imagexx.bringToFront();
        imagexx.setOnTouchListener(onTouchListener(heightXXX ,widthXXX ,n ,n2, zamann));

        ///////////////////////////////////////////////////////////////////////////


        ///////////////////////////////////////


        ImageView  laserr = (ImageView) findViewById(R.id.imagelazer);
        laserr.setBottom(0);
        laserr.setRight(0);




        final Button btnxxx = (Button) findViewById(R.id.buttonFIRE);
        btnxxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LazerTIP = 1;
                Sbt = 1;

               ImageView imagexxL = (ImageView) findViewById(R.id.imageViewANA);


                RelativeLayout.LayoutParams layoutParamsz = (RelativeLayout.LayoutParams) imagexxL.getLayoutParams();


                TextView asax = (TextView) findViewById(R.id.textViewFIREadet);
                String[] Canca = asax.getText().toString().split("X");

                int SonCan = Integer.parseInt(Canca[1].toString());

                if(SonCan > 0){

                    SonCan = SonCan - 1;


                    TextView asaxx = (TextView) findViewById(R.id.textViewFIREadet);
                    asaxx.setText("X" + String.valueOf(SonCan));


                    timerr(layoutParamsz.topMargin, layoutParamsz.leftMargin);

                }

                //Sbt = 0;
                LazerTIP = 0;



            }

        });


    }





    TextView txt;
    Handler handle = null;
    Runnable runnable = null;


    TextView txtw;
    Handler handlew = null;
    Runnable runnablew = null;

    int zamanw;
    int zamann;

    int LazerUST = 0;
    int LazerSOL = 0;

    public void timerr(final int ttop,final int lleft){



        zamanw = 0;
        handlew = new Handler();
        runnablew = new Runnable() {

            @Override
            public void run() {

                zamanw = zamanw + 20;

                if (zamanw == zamanw){

                    handlew.removeCallbacks(runnablew);



                    if(ttop - zamanw < 0){

                        LazerTIP = 0;
                        Sbt = 0;

                    }



                    if(Sbt == 1){

                        LazerTIP = 1;


                        LazerSOL = lleft - 200;
                        LazerUST = ttop - zamanw;



                    }else{

                        LazerTIP = 0;


                        LazerSOL = lleft + 100;
                        LazerUST = ttop - zamanw;


                    }


                    /////////////////////////////////////////////////////////////////


                    String Shipp = ShipActivity.GemiID.toString();


                    if(Shipp.equals("1") == true && LazerTIP == 0){

                        ImageView imageCC = (ImageView) findViewById(R.id.imagelazer);
                        imageCC.setImageResource(R.drawable.lazer1);


                        Sbt = 0;
                    }
                    if(Shipp.equals("2") == true && LazerTIP == 0){

                        ImageView imageCC = (ImageView) findViewById(R.id.imagelazer);
                        imageCC.setImageResource(R.drawable.lazer2);

                        Sbt = 0;
                    }
                    if(Shipp.equals("3") == true && LazerTIP == 0){

                        ImageView imageCC = (ImageView) findViewById(R.id.imagelazer);
                        imageCC.setImageResource(R.drawable.lazer2);

                        Sbt = 0;
                    }
                    if(LazerTIP == 1){

                        ImageView imageCC = (ImageView) findViewById(R.id.imagelazer);
                        imageCC.setImageResource(R.drawable.lazer0);

                        Sbt = 1;
                    }





                    ///////////////////////////////////////////////////////////////

// RESİM KONUMLARI DEĞİŞTİRİLİYOR

                        ImageView image = (ImageView) findViewById(R.id.imagelazer);
                        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(image.getLayoutParams());
                        marginParams.setMargins(LazerSOL, ttop - zamanw,0,0 );
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
                        image.setLayoutParams(layoutParams);


                    ////////////////////////////


                    ///////////////////////////////////////////////////////////////

// 5 MİLİSANİYEDE BİR İLGİLİ KODU ÇALIŞTIRIR
                }
                handlew.postDelayed(runnablew, 5);

            }
        };
        runnablew.run();





    }

    public static String PuanN = "";

    int zam;
    boolean baslangic = false;


    int sonnz = 0;
    int sonn2z = 0;


    int vurdu1 = 0;
    int vurdu2 = 0;


    int TOPLAMPUAN = 0;
    int DUSMAN1puan = 0;
    int DUSMAN2puan = 0;

// TIMER ADIYLA YARATILAN VOID DE MILISANİYELER İLE GÖRSELLER KONUMLANDIRILIYOR

    public void timerr2(final int heightXXXz,final int widthXXXz,final int nz, final int n2z, final int zamann){

        sonnz = nz;
        sonn2z = n2z;



        handle = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                zam = zam + 10;

                if (zamann == zamann){

                    handle.removeCallbacks(runnable);

                    delaySAYI++;

                    ///////////////////////////////////////////////////////////////

                    TextView sss = (TextView) findViewById(R.id.textViewSCORE);
                    sss.setText(Html.fromHtml(String.valueOf(TOPLAMPUAN)));

                    PuanN = Integer.toString(TOPLAMPUAN).toString();

                    ImageView image = (ImageView) findViewById(R.id.Dusman1);
                    ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(image.getLayoutParams());


                    ImageView image2 = (ImageView) findViewById(R.id.Dusman2);
                    ViewGroup.MarginLayoutParams marginParams2 = new ViewGroup.MarginLayoutParams(image2.getLayoutParams());


                    if(TOPLAMPUAN > 100000){

// İLGİLİ PUAN GEÇİLİRSE LAZER İMAJI DEĞİŞTİRİLİYOR
                        ImageView imageC = (ImageView) findViewById(R.id.imagelazer);
                        imageC.setImageResource(R.drawable.lazer2);
                    }





                    ///////////////////////////////////////////////////////////////

                    if(baslangic == true){


                        Random randx = new Random();

                        sonnz = randx.nextInt(widthXXXz - 200 - 5) + 5;

                        sonn2z = randx.nextInt(widthXXXz - 200 - 20) + 20;



                        image2.setVisibility(View.VISIBLE);
                        image.setVisibility(View.VISIBLE);


                    }

                    /////////////////

                    if(image.getTop() <= (heightXXXz-370)){



                        marginParams.setMargins(sonnz, zam, 0, 0);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
                        image.setLayoutParams(layoutParams);

                        baslangic = false;

                    }else{


                        zam = 0;

                        marginParams.setMargins(sonnz, 0,0, 0 );
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
                        image.setLayoutParams(layoutParams);


                        TextView asa = (TextView) findViewById(R.id.textViewCAN);
                        String Cancan = asa.getText().toString();


                        if(Cancan.equals("x1") == true){


                            TextView asa1 = (TextView) findViewById(R.id.textViewCAN);
                            asa1.setText("GAME OVER");

                            try {


                                Thread.sleep(1000);


                                Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
                                startActivity(intent);



                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }



// CAN DEĞERLERİ DEĞİŞTİRİLİYOR 3 CAN HAKKIN VAR                         }

                        if(Cancan.equals("x2") == true){


                            TextView asa2 = (TextView) findViewById(R.id.textViewCAN);
                            asa2.setText("x1");

                        }

                        if(Cancan.equals("x3") == true){


                            TextView asa3 = (TextView) findViewById(R.id.textViewCAN);
                            asa3.setText("x2");

                        }


                        baslangic = true;


                    ///////////////////////////////////////////////////////////////



                }


                    if(image2.getTop() <= (heightXXXz-370)){




                        marginParams2.setMargins( sonn2z, zam,0,0 );
                        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(marginParams2);
                        image2.setLayoutParams(layoutParams2);



                        baslangic = false;

                    }else{


                        zam = 0;

                        marginParams2.setMargins( sonn2z, 0,0,0 );
                        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(marginParams2);
                        image2.setLayoutParams(layoutParams2);


                        TextView asa = (TextView) findViewById(R.id.textViewCAN);
                        String Cancan = asa.getText().toString();


                        if(Cancan.equals("x1") == true){

// OYUN BİTTİ TEXTVIEW NESNESİNE YAZDIRILIYOR
                            TextView asa1 = (TextView) findViewById(R.id.textViewCAN);
                            asa1.setText("GAME OVER");

                            try {

// 1 SN YE BEKLE VE İLGLİ ACTIVITYE GEÇ
                                Thread.sleep(1000);


                                Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
                                startActivity(intent);



                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }



                        }

                        if(Cancan.equals("x2") == true){


                            TextView asa2 = (TextView) findViewById(R.id.textViewCAN);
                            asa2.setText("x1");

                        }

                        if(Cancan.equals("x3") == true){


                            TextView asa3 = (TextView) findViewById(R.id.textViewCAN);
                            asa3.setText("x2");

                        }


                        baslangic = true;
                    }

                    ///////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////

                    if(LazerUST <= image.getTop()) {

                        if (LazerSOL >= image.getLeft() && LazerSOL <= (image.getLeft() + 200)) {

                            if (vurdu2 == 1) {

                                //vurdu1 = 0;
                                vurdu2 = 0;

                                baslangic = true;


                                zam = 0;


                                //////////////////////////////

// RASGELE DÜŞMAN GEMİSİ GÖRSELLERİ DEĞİŞİYOR

                                int resimsec = 0;

                                Random randresim = new Random();
                                resimsec = randresim.nextInt(15);


                                if(resimsec == 1) {

                                    ImageView imageDUSMAN21 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN21.setImageResource(R.drawable.a1);

                                    DUSMAN1puan = 100;
                                }

                                if(resimsec == 2) {

                                    ImageView imageDUSMAN22 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN22.setImageResource(R.drawable.b1);

                                    DUSMAN1puan = 110;

                                }

                                if(resimsec == 3) {

                                    ImageView imageDUSMAN23 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN23.setImageResource(R.drawable.c1);

                                    DUSMAN1puan = 120;

                                }
                                if(resimsec == 4) {

                                    ImageView imageDUSMAN24 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN24.setImageResource(R.drawable.a4);

                                    DUSMAN1puan = 400;
                                }

                                if(resimsec == 5) {

                                    ImageView imageDUSMAN25 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN25.setImageResource(R.drawable.b3);

                                    DUSMAN1puan = 300;

                                }

                                if(resimsec == 6) {

                                    ImageView imageDUSMAN26 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN26.setImageResource(R.drawable.c2);

                                    DUSMAN1puan = 200;

                                }

                                if(resimsec == 7) {

                                    ImageView imageDUSMAN27 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN27.setImageResource(R.drawable.c3);

                                    DUSMAN1puan = 300;

                                }

                                if(resimsec == 8) {

                                    ImageView imageDUSMAN28 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN28.setImageResource(R.drawable.a6);

                                    DUSMAN1puan = 600;

                                }

                                if(resimsec == 9) {

                                    ImageView imageDUSMAN29 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN29.setImageResource(R.drawable.b6);

                                    DUSMAN1puan = 600;

                                }

                                if(resimsec == 10) {

                                    ImageView imageDUSMAN210 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN210.setImageResource(R.drawable.c6);

                                    DUSMAN1puan = 600;

                                }

                                if(resimsec == 11) {

                                    ImageView imageDUSMAN211 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN211.setImageResource(R.drawable.a7);

                                    DUSMAN1puan = 700;

                                }

                                if(resimsec == 12) {

                                    ImageView imageDUSMAN212 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN212.setImageResource(R.drawable.b7);

                                    DUSMAN1puan = 700;

                                }

                                if(resimsec == 13) {

                                    ImageView imageDUSMAN213 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN213.setImageResource(R.drawable.c7);

                                    DUSMAN1puan = 700;

                                }

                                if(resimsec == 14) {

                                    ImageView imageDUSMAN214 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN214.setImageResource(R.drawable.a8);

                                    DUSMAN1puan = 800;

                                }

                                if(resimsec == 15) {

                                    ImageView imageDUSMAN215 = (ImageView) findViewById(R.id.Dusman1);
                                    imageDUSMAN215.setImageResource(R.drawable.b8);

                                    DUSMAN1puan = 800;

                                }
                                //////////////////////////////
//                                marginParams0.setMargins( sonnz, 0,0, marginParams0.topMargin );
//                                RelativeLayout.LayoutParams layoutParams0 = new RelativeLayout.LayoutParams(marginParams0);
//                                image.setLayoutParams(layoutParams0);

                                TOPLAMPUAN += DUSMAN1puan;

                            } else {


                                vurdu1 = 1;

                                image.setVisibility(View.GONE);


                            }


                        }
                    }

                    if(LazerUST <= image2.getTop()) {


                        if (LazerSOL >= image2.getLeft() && LazerSOL <= (image2.getLeft() + 200)) {


                            if (vurdu1 == 1) {

                                vurdu1 = 0;
                                //vurdu2 = 0;

                                baslangic = true;

                                zam = 0;


                                //////////////////////////////

                                int resimsec = 0;

                                Random randresim = new Random();
                                resimsec = randresim.nextInt(15);
// HER BİR DÜŞMAN GEMİSİ (İMAJ DOSYASI) FARKLI PUAN TANIMLANIYOR VE RASGELE SEÇİLİYOR

                                if(resimsec == 1) {

                                    ImageView imageDUSMAN11 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN11.setImageResource(R.drawable.a1);

                                    DUSMAN2puan = 100;
                                }

                                if(resimsec == 2) {

                                    ImageView imageDUSMAN12 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN12.setImageResource(R.drawable.b1);

                                    DUSMAN2puan = 110;

                                }

                                if(resimsec == 3) {

                                    ImageView imageDUSMAN13 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN13.setImageResource(R.drawable.c1);

                                    DUSMAN2puan = 120;

                                }

                                if(resimsec == 4) {

                                    ImageView imageDUSMAN14 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN14.setImageResource(R.drawable.a5);

                                    DUSMAN2puan = 500;


                                    Toast.makeText(OyunActivity.this,
                                            "Düşman Süpersonic Jetleri Gönderiyor", Toast.LENGTH_SHORT)
                                            .show();

                                }

                                if(resimsec == 5) {

                                    ImageView imageDUSMAN15 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN15.setImageResource(R.drawable.b3);

                                    DUSMAN2puan = 300;

                                }

                                if(resimsec == 6) {

                                    ImageView imageDUSMAN16 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN16.setImageResource(R.drawable.c4);

                                    DUSMAN2puan = 400;

                                }

                                if(resimsec == 7) {

                                    ImageView imageDUSMAN17 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN17.setImageResource(R.drawable.c3);

                                    DUSMAN1puan = 300;

                                }

                                if(resimsec == 8) {

                                    ImageView imageDUSMAN18 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN18.setImageResource(R.drawable.a6);

                                    DUSMAN1puan = 600;


                                    Toast.makeText(OyunActivity.this,
                                            "Saldırılar hızlanacak", Toast.LENGTH_SHORT)
                                            .show();

                                }

                                if(resimsec == 9) {

                                    ImageView imageDUSMAN19 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN19.setImageResource(R.drawable.b6);

                                    DUSMAN1puan = 600;

                                }

                                if(resimsec == 10) {

                                    ImageView imageDUSMAN110 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN110.setImageResource(R.drawable.c6);

                                    DUSMAN1puan = 600;

                                }

                                if(resimsec == 11) {

                                    ImageView imageDUSMAN111 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN111.setImageResource(R.drawable.a7);

                                    DUSMAN1puan = 700;


                                    Toast.makeText(OyunActivity.this,
                                            "Ateş gelmeye devam ediyorlar", Toast.LENGTH_SHORT)
                                            .show();

                                }

                                if(resimsec == 12) {

                                    ImageView imageDUSMAN112 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN112.setImageResource(R.drawable.b7);

                                    DUSMAN1puan = 700;

                                }

                                if(resimsec == 13) {

                                    ImageView imageDUSMAN113 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN113.setImageResource(R.drawable.c7);

                                    DUSMAN1puan = 700;

                                }

                                if(resimsec == 14) {

                                    ImageView imageDUSMAN114 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN114.setImageResource(R.drawable.a8);

                                    DUSMAN1puan = 800;

// TOAST İLE MESAJ GÖSTERİLİYOR

                                    Toast.makeText(OyunActivity.this,
                                            "Dikkat Kruvazör Saldırısı", Toast.LENGTH_SHORT)
                                            .show();
                                }

                                if(resimsec == 15) {

                                    ImageView imageDUSMAN115 = (ImageView) findViewById(R.id.Dusman2);
                                    imageDUSMAN115.setImageResource(R.drawable.b8);

                                    DUSMAN1puan = 800;

                                }

                                //////////////////////////////
//                                marginParams2.setMargins( sonn2z, 0,0, marginParams20.topMargin );
//                                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(marginParams2);
//                                image2.setLayoutParams(layoutParams2);

                                TOPLAMPUAN += DUSMAN2puan;

                            }else{


                                vurdu2 = 1;

                                image2.setVisibility(View.GONE);


                            }

                        }


                    }

// İLGİLİ PUAN ÇEKİLİRSE TİMER ARALIĞI AZALIYOR OYUN HIZLANIYOR

                if(TOPLAMPUAN > 100000){

                        delayZAMAN = 20;

                }

                if(TOPLAMPUAN > 300000){

                        delayZAMAN = 10;

                }

                }
                handle.postDelayed(runnable, delayZAMAN);

            }
        };
        runnable.run();





    }


    int xDelta = 0;
    int yDelta = 0;

    int x;
    int y;


// DOKUNMA ETKİNLİĞİ DİNLENİYOR
    public View.OnTouchListener onTouchListener(final int heightXXXz,final int widthXXXz,final int nz, final int n2z, final int zamann) {
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {



                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
// DOKUNMA ANI

                        //timerr2(heightXXXz ,widthXXXz ,nz ,n2z, zamann);


                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

      // MARGIN KONUMLARI DEĞİŞİYOR
                  timerr(lParams.topMargin, lParams.leftMargin);

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:

                        break;

                    case MotionEvent.ACTION_MOVE:


                        //timerr2(heightXXXz ,widthXXXz ,nz ,n2z, zamann);

                        RelativeLayout.LayoutParams layoutParamsz = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        layoutParamsz.leftMargin = x - xDelta;
                        layoutParamsz.topMargin = y - yDelta;
                        //layoutParams.rightMargin = 0;
                        //layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParamsz);
                        break;
                }
                mainLayout.invalidate();
                return true;
            }
        };
    }


}

