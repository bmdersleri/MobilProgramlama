package com.nvmvzv.uzaysavasi.uzaysavasi.uzaysavasi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;

public class ShipActivity  extends AppCompatActivity {


    public static String GemiID = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        final ImageButton btnxxx = (ImageButton) findViewById(R.id.imageButton);
        btnxxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GemiID = "1";


                Intent intent = new Intent(getApplicationContext(), OyunActivity.class);
                startActivity(intent);

            }

            });

        final ImageButton btnxxx2 = (ImageButton) findViewById(R.id.imageButton2);
        btnxxx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                GemiID = "2";


                Intent intent = new Intent(getApplicationContext(), OyunActivity.class);
                startActivity(intent);


            }

        });

        final ImageButton btnxxx3 = (ImageButton) findViewById(R.id.imageButton3);
        btnxxx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                GemiID = "3";


                Intent intent = new Intent(getApplicationContext(), OyunActivity.class);
                startActivity(intent);

            }

        });



    }
}