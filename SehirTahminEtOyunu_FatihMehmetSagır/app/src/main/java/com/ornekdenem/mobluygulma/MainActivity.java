package com.ornekdenem.mobluygulma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnAnasayfaayar(View v){
        switch (v.getId()){
            case R.id.btnNormalOyun:
                Intent intent = new Intent(this,NormalOyunAktivity.class);
                startActivity(intent);
                break;
            case R.id.btnHakkinda:
                Intent intent1 = new Intent(this,HakkindaActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnCikis:
                cikisYap();

        }

    }

    public void cikisYap(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void onBackPressed(){
        cikisYap();
    }
}