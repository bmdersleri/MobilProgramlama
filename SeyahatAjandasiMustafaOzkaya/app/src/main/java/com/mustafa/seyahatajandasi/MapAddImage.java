package com.mustafa.seyahatajandasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mustafa.seyahatajandasi.Adapter.ImageAdapter;
import com.mustafa.seyahatajandasi.MapsActivity.Maps;
import com.mustafa.seyahatajandasi.Singleton.Singleton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class MapAddImage extends AppCompatActivity {
    GridView addressImage;

    Uri imageUri;
    Bitmap selections;

    SQLiteDatabase database;

    ArrayList<Bitmap> bitmaps;
    ImageAdapter adapter;

    String mapsID = ""; // ListView id çekiyoruz
    String id = "";

    // GridView eğer boş ise icon ve yazı gözükecek
    ImageView back_icon;
    TextView back_text;

    // Gridview Delete işlemi yapmak için id çektim

     ArrayList<String> gridviewId;
    int gridviewPositon = 0;


BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_add_image);

        gridviewId = new ArrayList<>();



        back_icon = findViewById(R.id.back_image);
        back_text = findViewById(R.id.back_text);


        try {
            database = this.openOrCreateDatabase("ImageData",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS img(id VARCHAR, mapsid VARCHAR, images BLOB)");
        }catch (Exception e){
            e.printStackTrace();
        }

        addressImage = findViewById(R.id.map_gallery); // GridView tanımladık
        Intent intent = getIntent();
        mapsID = intent.getStringExtra("mapId");
        bitmaps = new ArrayList<>();

        UUID uuid = UUID.randomUUID();
        id = uuid.toString();


        adapter = new ImageAdapter(this,bitmaps);
        addressImage.setAdapter(adapter);
        addressImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent full_image = new Intent(MapAddImage.this,FullImageShow.class);
                Singleton singleton = Singleton.getInstance();
                singleton.setBitmap(bitmaps.get(position));
                startActivity(full_image);
            }
        });
        addressImage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   gridviewPositon = position;
                  registerForContextMenu(addressImage);
                  openContextMenu(addressImage);
                return true;
            }
        });
        getImageData();


        // Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottom_image_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_image_add:
                        if (ContextCompat.checkSelfPermission(MapAddImage.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(MapAddImage.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                        }
                        else{
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent,2);
                        }
                        break;

                }
                return true;
            }
        });






    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            if (Build.VERSION.SDK_INT >= 28){
                 ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),imageUri);
                try {
                    selections = ImageDecoder.decodeBitmap(source);
                    if (imageUri != null) {
                        setImageData();
                    }
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    selections = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                    if (imageUri != null) {
                        setImageData();
                    }
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setImageData(){

         try {

             database = this.openOrCreateDatabase("ImageData",MODE_PRIVATE,null);
             database.execSQL("CREATE TABLE IF NOT EXISTS img(id VARCHAR, mapsid VARCHAR, images BLOB)");

             String sorgu = "INSERT INTO img(id,mapsid,images) VALUES (?,?,?)";
             SQLiteStatement sqLiteStatement = database.compileStatement(sorgu);

            if (selections != null) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                Bitmap newImage = makeSmallerImage(selections,1000);
                newImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                byte[] bytes = outputStream.toByteArray();
                
                sqLiteStatement.bindString(1, id);
                sqLiteStatement.bindString(2, mapsID);
                sqLiteStatement.bindBlob(3, bytes);
            }

             sqLiteStatement.execute();

         }catch (Exception e){
             Toast.makeText(this, "Resmi boyutunda sorun olabilir veya başka bir şey", Toast.LENGTH_SHORT).show();
             System.out.println(e.getLocalizedMessage());
             e.printStackTrace();
         }

    }


    public void getImageData(){

         try {
             database = this.openOrCreateDatabase("ImageData",MODE_PRIVATE,null);

             Cursor cursor = database.rawQuery("SELECT * FROM img WHERE mapsid = ?", new String[]{mapsID});

             int imageId = cursor.getColumnIndex("images");
             int ID = cursor.getColumnIndex("id");
             while (cursor.moveToNext()){
                    byte[] bytes = cursor.getBlob(imageId);
                    Bitmap bitmap1 = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    bitmaps.add(bitmap1);
                    gridviewId.add(cursor.getString(ID));
             }
             adapter.notifyDataSetChanged();
             cursor.close();
         }catch (Exception e){
             Toast.makeText(this, "Resmin Hepsini Çekilmiyor..", Toast.LENGTH_SHORT).show();
             System.out.println(e.getLocalizedMessage());
         }

         if (addressImage.getCount() != 0){
                 back_icon.setVisibility(View.INVISIBLE);
                 back_text.setVisibility(View.INVISIBLE);
         }
         else{
             back_icon.setVisibility(View.VISIBLE);
             back_text.setVisibility(View.VISIBLE);
         }



    }
public Bitmap makeSmallerImage(Bitmap bitmap, int maxSize){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float bitmapRatoin = (float) width/(float)height;

        if (bitmapRatoin > 1){
             width = maxSize;
             height = (int) (width/bitmapRatoin);
        }
        else{
            height = maxSize;
            width = (int) (height*bitmapRatoin);
        }
        return Bitmap.createScaledBitmap(bitmap,width,height,true);
}


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("İşlemi Seçiniz");
        menu.add(0,0, Menu.NONE,"Sil");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                  String id = gridviewId.get(gridviewPositon);
                imageDatabaseDelete(id);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                Toast.makeText(this, "Silindi...", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void imageDatabaseDelete(String id){

        try {
            database = this.openOrCreateDatabase("ImageData",MODE_PRIVATE,null);

              String sorgu = "DELETE FROM img WHERE id = ?";
              SQLiteStatement sqLiteStatement = database.compileStatement(sorgu);

              sqLiteStatement.bindString(1,id);

              sqLiteStatement.execute();


        }catch (Exception e){
            Toast.makeText(this, "Silinemedi..", Toast.LENGTH_SHORT).show();
        }


    }


}





