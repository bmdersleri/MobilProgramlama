package com.idealkilo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.google.firebase.auth.FirebaseAuth;


public class About extends AppCompatActivity {
    private TextView hakkımda, iletisim;
    private Toolbar actionbarAbout;
    private FirebaseAuth auth;

    public void init(){
        actionbarAbout = (Toolbar) findViewById(R.id.actionbarAbout);
        setSupportActionBar(actionbarAbout);
        getSupportActionBar().setTitle("Hakkında");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        auth = FirebaseAuth.getInstance();

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){

            Intent intent=new Intent(getApplicationContext(), com.idealkilo.MainActivity.class);
            NavUtils.navigateUpTo(this,intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
    }

}