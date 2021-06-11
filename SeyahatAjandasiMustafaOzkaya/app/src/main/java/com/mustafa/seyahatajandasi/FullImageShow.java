package com.mustafa.seyahatajandasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.mustafa.seyahatajandasi.Singleton.Singleton;

public class FullImageShow extends AppCompatActivity {
    PhotoView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_show);

         photoView = findViewById(R.id.full_image_gridview);


        Singleton singleton = Singleton.getInstance();
        photoView.setImageBitmap(singleton.getBitmap());


    }
}