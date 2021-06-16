
package com.nvmvzv.uzaysavasi.uzaysavasi.uzaysavasi;

import android.content.Intent;
import android.net.Uri;
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

public class MenuActivity  extends AppCompatActivity {


// GERİ TUŞUNUN BOŞ İŞLEM YAPMASINI SAĞLAR

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

// METOD TANIMLAMALARI YAPILIYOR

    TextView txt;
    Handler handle = null;
    Runnable runnable = null;
    int zaman;


    String line;
    boolean netvar = true;
    BufferedReader reader;

// DEĞİŞKENLER TANIMLANIYOR

    Boolean baglantivarmi = true;

    String icString;

    String htmlString = "";

    String satirr = "";

    public int SorSayisi = 1;

    String SSoru = "";
    String CCevap = "";

    String AllData = "";

    String AllDDD = "";

    String NICKNAME = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


// TOAST KULLANILARAK MESAJ GÖSTERİMİ YAPILIYOR

        Toast.makeText(MenuActivity.this,
                "", Toast.LENGTH_SHORT)
                .show();
        ///////////

// WEB ORTAMINA VERİ POST EDİP GÖNEN DEĞERLERİ ÇAĞIRAN KOD

        String data;

// TRY METODUYLA HATA DURUMUNDA KOD PARÇACIĞI ATLANIYOR
        try {



            data = URLEncoder.encode("x", "UTF-8") + "=" + URLEncoder.encode("x", "UTF-8");

// VERİ POST EDİLEN URL

            URL url = new URL("http://uzaysavaslari.nvmvzv.com/SkorCek.aspx");

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


            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();

// İLGİLİ TEXTİ OKUYANA KADAR SATIR SATIR VERİ OKUR

            while ((line = reader.readLine()).indexOf("<html") < 0) {

                //sb.append(line);

                AllData += line.toString();

            }


            String sonucc = AllData.toString();

            if(sonucc.indexOf("€") >= 0){


// TEXT İŞLEMLERİ YAPILIYOR SPLIT İLE TEXTLER BÖLÜNÜP DEĞER DÜZENLENİYOR

                String[] Sonn = sonucc.split("€");

                String[] Sonuc0101 = Sonn[1].toString().split("@");

                String[] Sonuc0102 = Sonn[2].toString().split("@");

                String[] Sonuc0103 = Sonn[3].toString().split("@");



                String Mesaj1 = Sonuc0101[0].toString() + " - " + Sonuc0101[1].toString() + " Puan  " + Sonuc0101[2].toString() + "  ";
                String Mesaj2 = Sonuc0102[0].toString() + " - " + Sonuc0102[1].toString() + " Puan  " + Sonuc0102[2].toString() + "  ";
                String Mesaj3 = Sonuc0103[0].toString() + " - " + Sonuc0103[1].toString() + " Puan  " + Sonuc0103[2].toString() + "  ";


// TEXTVIEW NESNESINE SETTEXT İLE MESAJ TANIMLANIYOR

                TextView tvt = (TextView) findViewById(R.id.textView2);
                tvt.setText(Mesaj1.toString() + "\n" + Mesaj2.toString() + "\n" + Mesaj3.toString() + "\n");


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



        //////////




// BUTTONUN TIKLANMASINI LISTENER İLE DİNLEYEN KOD BLOĞU
        final Button btnxxx = (Button) findViewById(R.id.button);
        btnxxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

// İLGİLİ ACTIVITYE GEÇMESİNİ SAĞLAR
                Intent intent = new Intent(getApplicationContext(), ShipActivity.class);
                startActivity(intent);



            }

        });



        //////////


        Button btnClickMe3 = (Button) findViewById(R.id.button2);
        btnClickMe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

// TARAYICIDA İLGİLİ SAYFAYI AÇAR

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://bc.vc/FKrZqho"));
                startActivity(browserIntent);

            }

        });



        //////////




    }
}

