package com.example.hadibull2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Sonuc extends AppCompatActivity {
    private ImageView imageViewSonuc;
    private TextView textViewSonuc;
    private Button buttonTekrar;
    private boolean sonuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);
        imageViewSonuc= findViewById(R.id.imageViewSonuc);
        textViewSonuc=findViewById(R.id.textViewSonuc);
        buttonTekrar=findViewById(R.id.buttonTekrar);

        sonuc = getIntent().getBooleanExtra("sonuc", false);

        if(sonuc){
            imageViewSonuc.setImageResource(R.drawable.ic_baseline_thumb_up_24);
            textViewSonuc.setText("Tebrikler Buldunuz");
        }else{
            imageViewSonuc.setImageResource(R.drawable.ic_baseline_thumb_down_24);
            textViewSonuc.setText("Maalesef Bulamadınız");
            textViewSonuc.setTextColor(Color.RED);

        }

        buttonTekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Sonuc.this,Tahmin.class);
                startActivity(i);
                finish();
            }
        });
    }
}