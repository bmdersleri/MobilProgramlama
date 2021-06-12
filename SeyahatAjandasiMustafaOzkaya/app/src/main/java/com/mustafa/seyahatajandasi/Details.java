package com.mustafa.seyahatajandasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mustafa.seyahatajandasi.Adapter.ListViewAdapter;
import com.mustafa.seyahatajandasi.MapsActivity.Maps;
import com.mustafa.seyahatajandasi.Model.MapsModel;

import java.util.ArrayList;

public class Details extends AppCompatActivity implements ListViewAdapter.OnClickButton {

    BottomNavigationView bottomNavigationView;
    ListView listView;

    ListViewAdapter adapter;

     ArrayList<MapsModel> modelArrayList;
     ArrayList<String> mapsId;

    SQLiteDatabase database;

    String fileID = "";


    // Listview Null ise ImageView ve TextView Gözükmesini için
      ImageView showImage;
      TextView showText;



      int listViewPositon = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        showImage = findViewById(R.id.first_image_map);
        showText = findViewById(R.id.adres_ekle);       // Listview null ise


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        modelArrayList = new ArrayList<>();
        mapsId = new ArrayList<>();
        listView = findViewById(R.id.listView);
        Intent intent = getIntent();
        fileID = intent.getStringExtra("id");  // Klasörün id alıyorum.




       //bottomNavigationView.setVisibility(View.VISIBLE);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.maps_menu:
                        // Haritalara Gidecek..
                        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                        if (!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) && !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                            buildAlertMessageNoGps();
                        }
                        else{
                            Intent toMaps = new Intent(Details.this, Maps.class);
                            toMaps.putExtra("fileid",fileID);
                            toMaps.putExtra("info","new");
                            startActivity(toMaps);
                        }

                        break;
                    case R.id.home_menu:
                        Intent toHome = new Intent(Details.this,MainActivity.class);
                        toHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(toHome);
                        break;
                }
                return true;
            }
        });

        adapter = new ListViewAdapter(Details.this,modelArrayList); // Listview için Adapter oluşturduk
        adapter.setButton2(Details.this);
        listView.setAdapter(adapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent toMapAddImage = new Intent(Details.this,MapAddImage.class);
               toMapAddImage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               toMapAddImage.putExtra("mapId",mapsId.get(position));
               startActivity(toMapAddImage);
           }
       });

        getData();

    }


    public void getData(){

         try {

             database = Details.this.openOrCreateDatabase("Location",MODE_PRIVATE,null);
             database.execSQL("CREATE TABLE IF NOT EXISTS gps(id VARCHAR, fileid VARCHAR, adres VARCHAR,latitude VARCHAR,longitude VARCHAR)");

             Intent intent = getIntent();
             String file = intent.getStringExtra("id");
             if (file != null){
                 //Cursor cursor = database.rawQuery("SELECT * FROM gps WHERE fileid = ?", new String[]{fileID});
                 Cursor cursor = database.rawQuery("SELECT * FROM gps WHERE fileid = ?", new String[]{file});
                 int Id  = cursor.getColumnIndex("id");
                 int adressId = cursor.getColumnIndex("adres");
                 int latitudeId = cursor.getColumnIndex("latitude");
                 int longitudeId = cursor.getColumnIndex("longitude");

                 while (cursor.moveToNext()){
                     String id = cursor.getString(Id);

                     String address1 = cursor.getString(adressId);
                     Double latitude1 = Double.parseDouble(cursor.getString(latitudeId));
                     Double longitude1 = Double.parseDouble(cursor.getString(longitudeId));

                     MapsModel mapsModel = new MapsModel(address1,latitude1,longitude1);

                     modelArrayList.add(mapsModel);

                     mapsId.add(id); // Yeni activity gönderecem ve SQLite ona göre kayıt yaptıracam.
                 }
                 adapter.notifyDataSetChanged();
                 cursor.close();
             }


         }catch (Exception e){
             e.printStackTrace();
             System.out.println(e.getLocalizedMessage());
         }

        if (listView.getCount() == 0){
            // Listview İçi boş değil ise yapılacak işlemi

            showImage.setVisibility(View.VISIBLE);
            showText.setVisibility(View.VISIBLE);

        }
        else {
            showImage.setVisibility(View.INVISIBLE);
            showText.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void listener(int position, Activity activity) {   // Listview bulunan Button Tıklanma Özelliği metodu.
         // Kaydettiğim konumu buradan Maps gideceğim

         Intent intent = new Intent(activity,Maps.class);
         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         intent.putExtra("maps",modelArrayList.get(position));
         intent.putExtra("listId",mapsId.get(position));
         intent.putExtra("info","old");
         intent.putExtra("fileid",fileID);
         startActivity(intent);

    }

    @Override
    public void imageSettingsClick(int position, ImageView imageView) {
        // Listview ayarlar iconuna tıklandığında ContextMenu açılacak..

        listViewPositon = position;

        registerForContextMenu(imageView);
        openContextMenu(imageView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("İşlemlerden Birini Şeçiniz");
        menu.add(0,0,menu.NONE,"İsmini Düzenle");
        menu.add(0,1,menu.NONE,"Sil");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

          switch (item.getItemId()){
              case 0:
                     // your code

                  AlertDialog.Builder builder = new AlertDialog.Builder(this);
                  EditText text = new EditText(this);
                  text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                  builder.setView(text);
                  builder.setTitle("Yeni İsim Giriniz");
                  builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                             String newText = text.getText().toString();
                             setUpdateDatabe(mapsId.get(listViewPositon),newText);
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
                   // your code

                  selectDataDelete(mapsId.get(listViewPositon));
                  Intent intent = getIntent();
                  finish();
                  startActivity(intent);
                  break;
          }
        return true;
    }


    public void selectDataDelete(String id){

        try {

            database = Details.this.openOrCreateDatabase("Location",MODE_PRIVATE,null);

            String sorgu = "DELETE FROM gps WHERE id = ?";

            SQLiteStatement sqLiteStatement = database.compileStatement(sorgu);

            sqLiteStatement.bindString(1,id);

            sqLiteStatement.execute();


        }catch (Exception e){

        }

    }

    public void setUpdateDatabe(String id, String text1){

        try {

            database = Details.this.openOrCreateDatabase("Location",MODE_PRIVATE,null);
            //String sorgu = "UPDATE gps SET adres = texf WHERE id = ?";

            ContentValues contentValues = new ContentValues();
             contentValues.put("adres",text1);

          database.update("gps",contentValues,"id=?",new String[]{id});


        }catch (Exception e){
           e.printStackTrace();
        }


    }


    private void buildAlertMessageNoGps() {   // Konum kapalı ise konumu açmak için mesaj oluşturdum
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setMessage("Cihazınızın konum hizmeti kapalıdır, Konum özelliğin açmak ister misiniz?");
        builder.setCancelable(false);
        builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Hayır, Teşekkürler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }







}