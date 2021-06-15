package com.mustafa.seyahatajandasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mustafa.seyahatajandasi.Adapter.GridviewAdapter;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements GridviewAdapter.ClickPencil {
   GridView gridView;
   EditText folderName;

   ArrayList<String> arrayDosya;
   ArrayList<String> arrayId;
   GridviewAdapter adapter;

   //Database
    SQLiteDatabase database;
    SQLiteStatement sqLiteStatement;


    int folderPosition;



    //Gridview Boş ise icon ve text yazısı gözükmesi için
    ImageView folder_icon_;
    TextView text_inform;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        folder_icon_ = findViewById(R.id.folder_create);
        text_inform = findViewById(R.id.text_folder_create);


        gridView = findViewById(R.id.gridView);
        arrayDosya = new ArrayList<>();
        arrayId = new ArrayList<>();



        adapter = new GridviewAdapter(this,arrayDosya);
        adapter.setClickPencil(MainActivity.this);
        gridView.setAdapter(adapter);
         registerForContextMenu(gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Intent intent = new Intent(MainActivity.this,Details.class);
                 intent.putExtra("id",arrayId.get(position));
                 startActivity(intent);
            }
        });

        getDatabase();
    }




    public void dataDelete(String id){

        try {

            database = this.openOrCreateDatabase("Folder",MODE_PRIVATE,null);

            String sorguDelete = "DELETE FROM file WHERE id = ?";
            sqLiteStatement = database.compileStatement(sorguDelete);
            sqLiteStatement.bindString(1,id);


            sqLiteStatement.execute();

        }catch (Exception e){
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         // Menu'yü Activity bağladık.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_file,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         // Menu'deki itemleri tıklandığında ne olacağını yazdık

        if (item.getItemId() == R.id.add_file){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            folderName = new EditText(this);
            folderName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_CAP_WORDS | InputType.TYPE_TEXT_VARIATION_NORMAL);
            builder.setTitle("Klasör Oluşturun");
            folderName.setHint("Klasör adı giriniz.");
            folderName.setMaxEms(13);
            builder.setView(folderName);
            builder.setPositiveButton("Oluştur", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // SQLite kodu buraya yazılacak.

                    String dosyaAdi = folderName.getText().toString();  // Dosya nın adını alıcaz

                       setDatabase(dosyaAdi);
                       Intent intent = getIntent();
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       startActivity(intent);

                }
            });
            builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }



    public void setDatabase(String folderName){ // SQLite veri kaydediyoruz
          try {
              UUID uuid = UUID.randomUUID();
              String fileId = uuid.toString();
              database = this.openOrCreateDatabase("Folder",MODE_PRIVATE,null);
              database.execSQL("CREATE TABLE IF NOT EXISTS file(id VARCHAR, filename VARCHAR)");

              String sorgu = "INSERT INTO file(id,filename) VALUES (?,?)";
              sqLiteStatement = database.compileStatement(sorgu);
              sqLiteStatement.bindString(1,fileId);
              sqLiteStatement.bindString(2,folderName);

              sqLiteStatement.execute();
          }catch (Exception e){
              Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
          }
    }

    public void getDatabase(){   // SQLite veri çekiyoruz.

         try {

             database = this.openOrCreateDatabase("Folder",MODE_PRIVATE,null);
             Cursor cursor = database.rawQuery("SELECT * FROM file",null);
             int fileId = cursor.getColumnIndex("filename");
             int id = cursor.getColumnIndex("id");

             while (cursor.moveToNext()){

                    arrayDosya.add(cursor.getString(fileId));
                    arrayId.add(cursor.getString(id));
             }
           adapter.notifyDataSetChanged();
          cursor.close();
         }catch (Exception e){
             Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
             System.out.println(e.getMessage());
         }

         if (gridView.getCount() == 0){
              folder_icon_.setVisibility(View.VISIBLE);
              text_inform.setVisibility(View.VISIBLE);
         }
         else{
             folder_icon_.setVisibility(View.INVISIBLE);
             text_inform.setVisibility(View.INVISIBLE);
         }




    }


    @Override
    public void onPencil(int position, ImageView imageView) {

          //registerForContextMenu(imageView);
            folderPosition = position;
          openContextMenu(imageView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Birini Seçiniz");
        menu.add(Menu.NONE, 0, Menu.NONE, "Klasör İsmini Düzenle");
        menu.add(Menu.NONE, 1, Menu.NONE, "Sil");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case 0:
                // Gridview seçilen klasör düzenlenecek..

                  AlertDialog.Builder builder = new AlertDialog.Builder(this);
                  EditText text = new EditText(this);

                  text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                  builder.setTitle("Yeni Klasör İsmini Giriniz");
                  builder.setView(text);

                  builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {

                          String newText = text.getText().toString();
                          setUpdateFolderDatabe(arrayId.get(folderPosition),newText);
                          Intent intent = getIntent();
                          finish();
                          startActivity(intent);

                      }
                  });
                  builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          dialog.cancel();
                      }
                  });
                builder.show();


                break;
            case 1:
                // Gridview seçilen klasör siliniyor..

                dataDelete(arrayId.get(folderPosition));
                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }

        return super.onContextItemSelected(item);
    }


    public void setUpdateFolderDatabe(String id, String newtext) {

        try {

            //String sorgu = "UPDATE gps SET adres = texf WHERE id = ?";

            database = this.openOrCreateDatabase("Folder", MODE_PRIVATE, null);

            ContentValues contentValues = new ContentValues();
            contentValues.put("filename", newtext);

            database.update("file", contentValues, "id=?", new String[]{id});


        } catch (Exception e) {
            e.printStackTrace();
        }
    }







}