package com.example.pdfokuma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class HomeActivity extends AppCompatActivity {

    ListView lv_pdf;
    public static ArrayList<File> fileList=new ArrayList<>();
    PdfAdapter obj_adapter;
    public static int REQUEST_PERMISSION=1;
    boolean boolean_permission;
    File myfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimary));

        lv_pdf=(ListView)findViewById(R.id.listView_pdf);

        myfile= new File(Environment.getExternalStorageDirectory().toString());

        permission_fn();

        lv_pdf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),ViewPDFFiles.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    private void filter(String text) {
        ArrayList<File> filterList=new ArrayList<>();

        for (File item:fileList){
            if (item.getName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }

        }
        obj_adapter.filteredList(filterList);
    }

    private void permission_fn() {

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),

                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){

            if ((ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this,

                    Manifest.permission.READ_EXTERNAL_STORAGE))){
            }
            else{

                ActivityCompat.requestPermissions(HomeActivity.this, new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION);
            }

        }
        else {
            boolean_permission =true;

            getfile(myfile);

            //filelist bizim arraylist'imiz
            obj_adapter = new PdfAdapter(getApplicationContext(),fileList);

            //adaptörü liste görünümüne ayarlıyoruz
            lv_pdf.setAdapter(obj_adapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==REQUEST_PERMISSION){

            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                boolean_permission=true;
                getfile(myfile);
                obj_adapter=new PdfAdapter(getApplicationContext(),fileList);
                lv_pdf.setAdapter(obj_adapter);
            }
            else {
                Toast.makeText(this, "Lütfen İzin Verin", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<File> getfile(File myfile){

        File listFile[]=myfile.listFiles();

        if(listFile !=null && listFile.length>0){
            for (int i=0;i<listFile.length;i++){

                if (listFile[i].isDirectory()){

                    getfile(listFile[i]);
                }

                else {
                    boolean booleanpdf=false;

                    if (listFile[i].getName().endsWith(".pdf")){

                        for (int j=0;j<fileList.size();j++){

                            if(fileList.get(j).getName().equals(listFile[i].getName())){
                                booleanpdf=true;
                            }
                            else {

                            }
                        }
                        if (booleanpdf){
                            booleanpdf=false;
                        }
                        else {
                            fileList.add(listFile[i]);
                        }
                    }
                }
            }
        }
        return fileList;
    }

    /** Menü Tanımlama **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ana_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    /** Menüdeki item'lara erişim ve tıklama özelliği **/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.hakkinda:
                Intent hak=new Intent(HomeActivity.this,HakkimizdaActivity.class);
                startActivity(hak);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}