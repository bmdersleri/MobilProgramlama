package com.maku.makuharfnotuhesaplama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Nesnelerimizi tanımlıyoruz.
    ImageView imageVLogo;
    TextView txtUygulamaAdi1, txtUygulamaAdi2;
    Button btnGiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tanımladığımız nesneleri id'leri ile eşleştiriyoruz.
        imageVLogo=findViewById(R.id.imageVLogo);
        txtUygulamaAdi1=findViewById(R.id.txtUygulamaAdi1);
        txtUygulamaAdi2=findViewById(R.id.txtUygulamaAdi2);
        btnGiris=findViewById(R.id.btnGiris);

        //Butona basıldığında diğer Activity'ye geçmesi için aşağıdaki kod satırlarını yazıyoruz.
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecis=new Intent(MainActivity.this,HesapActivity.class);
                startActivity(gecis);
            }
        });
    }
}