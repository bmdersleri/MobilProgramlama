package com.nisailgun.kusvurma;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

public class OyunActivity extends AppCompatActivity {
    private OyunEkrani oyunGorunumu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point=new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        oyunGorunumu=new OyunEkrani(this,point.x,point.y);

        setContentView(oyunGorunumu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        oyunGorunumu.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        oyunGorunumu.resume();
    }
}