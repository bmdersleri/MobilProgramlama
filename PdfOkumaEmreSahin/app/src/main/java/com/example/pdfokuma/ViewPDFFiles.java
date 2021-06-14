package com.example.pdfokuma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.net.URI;

public class ViewPDFFiles extends AppCompatActivity {

    PDFView pdfView;
    int position=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdffiles);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimary));

        pdfView=(PDFView)findViewById(R.id.pdfView);

        position=getIntent().getIntExtra("position",-1);

        displayPDF();

    }

    /** PDF Görüntüleme **/
    private void displayPDF() {

        pdfView.fromFile(HomeActivity.fileList.get(position))

                .enableSwipe(true)
                .enableAnnotationRendering(true)
                .spacing(3)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }



    /** Menü Tanımlama **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.uc_nokta,menu);

        return super.onCreateOptionsMenu(menu);
    }


    /** Menüdeki item'lara erişim ve tıklama özelliği **/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.karanlik_tema:
                pdfView.fromFile(HomeActivity.fileList.get(position))

                        .enableSwipe(true)
                        .enableAnnotationRendering(true)
                        .nightMode(true)
                        .spacing(3)
                        .scrollHandle(new DefaultScrollHandle(this))
                        .load();

                break;

            case R.id.aydinlik_tema:
                pdfView.fromFile(HomeActivity.fileList.get(position))

                        .enableSwipe(true)
                        .enableAnnotationRendering(true)
                        .nightMode(false)
                        .spacing(3)
                        .scrollHandle(new DefaultScrollHandle(this))
                        .load();

                break;

            case R.id.yatay_mod:
                pdfView.fromFile(HomeActivity.fileList.get(position))

                        .enableSwipe(true)
                        .enableAnnotationRendering(true)
                        .swipeHorizontal(true)
                        .spacing(3)
                        .scrollHandle(new DefaultScrollHandle(this))
                        .load();
                break;

            case R.id.hakkinda:
                Intent hak1=new Intent(ViewPDFFiles.this,HakkimizdaActivity.class);
                startActivity(hak1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}