
package com.nvmvzv.uzaysavasi.uzaysavasi.uzaysavasi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class RecordActivity  extends AppCompatActivity {




// GERİ TUŞUNUN BOŞ İŞLEM YAPMASINI SAĞLAR
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    String PUANDDD = "";
    public static String GemiID = "1";


// DEĞİŞKEN METOD TANIMLAMALARI YAPILIYOR
    TextView txt;
    Handler handle = null;
    Runnable runnable = null;
    int zaman;


    String line;
    boolean netvar = true;
    BufferedReader reader;

    Boolean baglantivarmi = true;

    String icString;

    String htmlString = "";

    String satirr = "";

    public int SorSayisi = 1;

    String SSoru = "";
    String CCevap = "";

    String AllData = "";

    String AllDDD = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



// TOAST İLE MESAJ GÖSTERİLİYOR

        Toast.makeText(RecordActivity.this,
                "", Toast.LENGTH_SHORT)
                .show();


// BAŞKA ACTIVITYDE OLAN BİR DEĞİŞKEN DEĞERİ ÇEKİLİYOR
        PUANDDD = OyunActivity.PuanN.toString();



// TEXTVIEW NESNESİNE TEXT TANIMLANIYOR
        TextView tvtc = (TextView) findViewById(R.id.textViewSKOR);
        tvtc.setText(" Skor " + PUANDDD.toString() + " puan");



        //////////

// BUTTONA TIKLAMA İÇİN DİNLEME YAPILIYR

        final Button btnClickTkrr = (Button) findViewById(R.id.buttonYeniden);
        btnClickTkrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



// BAŞKA ACTIVITY YE YÖNLENDİRİKLİYOR
                Intent intent = new Intent(getApplicationContext(), ShipActivity.class);
                startActivity(intent);



            }

        });

                //////////


        final Button btnClickMe = (Button) findViewById(R.id.button);
        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextView tvtv = (TextView) findViewById(R.id.editText);
                String NICKNAME = tvtv.getText().toString();


                String data;




// İLGİLİ URL YE DATA POST EDİLİYOR VE DÖNEN DEĞER ALINIYOR 


// TRY İLE HATA ESNASINDA KOD BLOĞU ATLANIYOR
                try {



                    data = URLEncoder.encode("ad", "UTF-8")
                            + "=" + URLEncoder.encode(NICKNAME.toString(), "UTF-8");


                    data += "&" + URLEncoder.encode("puan", "UTF-8")
                            + "=" + URLEncoder.encode(PUANDDD.toString(), "UTF-8");

                    URL url = new URL("http://uzaysavaslari.nvmvzv.com/Ekle.aspx");

                    //http://www.yesilmavimor.net/Detail.aspx?dtl=2318229+Çocuklara+seçtiğiniz+isimler+karakterlerini+etkiliyor

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(100000);
                    conn.setConnectTimeout(100000);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Content-Length", String.valueOf(data.length()));
                    conn.setDoInput(true);
                    conn.setDoOutput(true);


                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());

                    out.writeBytes(data);

                    out.flush();

                    out.close();
                    // Defined URL  where to send data



// DÖNEN VERİ OKUNUYOR
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    StringBuilder sb = new StringBuilder();

                    while ((line = reader.readLine()).indexOf("<html") < 0) {

                        //sb.append(line);

                        AllData += line.toString();

                    }


                    String sonucc = AllData.toString();
                    String[] Sonn = sonucc.split("@");


// STRING TEXT İŞLEMLERİ YAPILIYOR TTEXT BÖLÜNÜYOR

                    String Mesaj = Sonn[1].toString();

                    TextView tvt = (TextView) findViewById(R.id.textSonuc);
                    tvt.setText(Mesaj.toString());

                    try {


// 1 SANİYE BEKLİYOR VE ACTIVITY DEĞİŞİYOR
                        Thread.sleep(1000);

                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }




            }

        });





    }
}

