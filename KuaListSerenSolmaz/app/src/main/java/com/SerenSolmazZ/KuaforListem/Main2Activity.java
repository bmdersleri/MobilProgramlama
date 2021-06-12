package com.SerenSolmazZ.KuaforListem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    //Değişkenlerimize diğer metodlardan da erişebilmek için yine global olarak tanımladık.
    Bitmap selectedImage;
    ImageView imageView;
    EditText kuaforIsmi, TelefonNo, Adresss;
    Button button;
    SQLiteDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(Main2Activity.this,"Lütfen kayıt eklemek için kutucukları doldurunuz.",Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Değişkenlerimizi aktif ettik.
        imageView = findViewById(R.id.imageView);
        kuaforIsmi = findViewById(R.id.artNameText);
        TelefonNo = findViewById(R.id.painterNameText);
        Adresss = findViewById(R.id.yearText);
        button = findViewById(R.id.button);

        database = this.openOrCreateDatabase("Kuafors",MODE_PRIVATE,null);//Database oluşturma işlemi. (Kuafors adında database oluşturmuştuk.)


        Intent intent = getIntent();//Diğer activitymizden bilgi almamızı sağlar.
        String info = intent.getStringExtra("info");//Diğer activitymizden infomuzu aldık.

        if (info.matches("new")) {
            kuaforIsmi.setText("");
            TelefonNo.setText("");
            Adresss.setText("");
            button.setVisibility(View.VISIBLE);

            Bitmap selectImage = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.selectimage);
            imageView.setImageBitmap(selectImage);


        } else {
            int artId = intent.getIntExtra("kuaforId",1);
            button.setVisibility(View.INVISIBLE);

            try {
                //Kullanıcının seçtiği id'yi alma işlemi.
                Cursor cursor = database.rawQuery("SELECT * FROM kuafors WHERE id = ?",new String[] {String.valueOf(artId)});//Sorgumuzda id'yi aldık.
                //id'deki o verileri cursor ile bulacağız.
                int kuaforNameIx = cursor.getColumnIndex("kuaforname");
                int telefonNoIx = cursor.getColumnIndex("telefonno");
                int adresIx = cursor.getColumnIndex("adres");
                int imageIx = cursor.getColumnIndex("image");

                while (cursor.moveToNext()) {

                    kuaforIsmi.setText(cursor.getString(kuaforNameIx));
                    TelefonNo.setText(cursor.getString(telefonNoIx));
                    Adresss.setText(cursor.getString(adresIx));

                    byte[] bytes = cursor.getBlob(imageIx);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    imageView.setImageBitmap(bitmap);

                }

                cursor.close();

            } catch (Exception e) {

            }


        }


    }

    //Resim seçmek için onClick metodumuz.
    public void selectImage(View view) {

        //İzin yoksa istiyoruz.
        //Kullanıcı uygulamayı ilk kullandığında bu izni bir defalık isteyecek.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){   //İzin olup olmadığını kontrol etme işlemi.

            //İzin verilmişse burada ne yapacağımızı söyleyeceğiz.
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);

        } else {

            //Kullanıcının galerisine gideceğiz.
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);    //Intent yardımıyla kullanıcının galeriye gitmesini sağlıyoruz.
            startActivityForResult(intentToGallery,2);        //Kulanıcı bir resimle geri dönecek ve bize bir sonuç verecek.
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery,2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {        //request kodumuzu kontrol edeceğiz ve kullanıcının bir şey seçip seçmediğini belirteceğiz.

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {

            Uri imageData = data.getData();//Resmin nereye kayıtlı olduğunun yolunu alma işlemi.


            try {
                //sdk versiyonumuzu kontrol edeceğiz.
                if (Build.VERSION.SDK_INT >= 28) { //sdk 28'den büyük eşitse burası çalışacak.

                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),imageData);   //uri alıp bitmap'e çevirmek için bir sınıf.
                    selectedImage = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(selectedImage);//Seçilen resmimizi imageView'a aktardık.

                } else { //sdk 28'den küçükse burası çalışacak.
                    //Burayı otomatik try-catch bloklarına alabiliyoruz.Burada android eski telefonlarda da çalıştığı için hata çıkabileceğini söyledi.
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);//Eski telefonlar için resim çekme öncesi.
                    imageView.setImageBitmap(selectedImage);//Seçilen resmimizi imageView'a aktardık.
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    public void save(View view) {

        String kuaforName = kuaforIsmi.getText().toString();
        String kuaforNo = TelefonNo.getText().toString();
        String adres = Adresss.getText().toString();

        Bitmap smallImage = makeSmallerImage(selectedImage,300);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();                         //OutputStream sınıfından bir nesne türettik.
        smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);                     //Fotoğrafı veriye dönüştürme işlemini yaparken kullanacağımız özellikleri belirtir.
        //Öncelikle formatımızı PNG olarak belirledik.

        byte[] byteArray = outputStream.toByteArray();//Görseli veriye çevirme işlemi.

        //Artık küçük bir resime sahibiz ve resmimizi de veriye çevirmiş olduk.

        //Buradan sonra artık veri tabanımızı oluşturuyoruz ve kaydetme işlemi yapıyoruz.


        try {

            database = this.openOrCreateDatabase("Kuafors",MODE_PRIVATE,null);//Database oluşturuyoruz. Kuafors adında database oluşturduk.
            database.execSQL("CREATE TABLE IF NOT EXISTS kuafors (id INTEGER PRIMARY KEY,kuaforname VARCHAR, telefonno VARCHAR, adres VARCHAR, image BLOB)");


            String sqlString = "INSERT INTO kuafors (kuaforname, telefonno, adres, image) VALUES (?, ?, ?, ?)";//Veritabanımıza ekleme sorgumuz.

            //Bu sql stringimize SQLite'da çalıştırılabilecek bir değere getiriyoruz.
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);//sql sorgumuzu compile eder.

            //Sırasıyla soru işaretlerimize karşılık gelecek verilerimizi ekliyoruz.
            sqLiteStatement.bindString(1, kuaforName);
            sqLiteStatement.bindString(2, kuaforNo);
            sqLiteStatement.bindString(3,adres);
            sqLiteStatement.bindBlob(4,byteArray);
            sqLiteStatement.execute();


        } catch (Exception e) {

        }

        //finish();
        //Bir sonraki bağlantımızda hata almamak için burada databasemizi kapatıyoruz.

        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//Daha önce açılmış bütün activitylerin kapatılmasını sağlar.
        startActivity(intent);//Activitymizi yeniden başlatır.

        Toast.makeText(Main2Activity.this,"Kayıt eklenmiştir.",Toast.LENGTH_LONG).show();

    }
    public Bitmap makeSmallerImage(Bitmap image, int maximumSize) {//Resim küçültme işlemi.

        int width = image.getWidth();//Bitmaplerin genişliğini almak.
        int height = image.getHeight();//Bitmaplerin uzunluğunu almak.

        float bitmapRatio = (float) width / (float) height;//Aradaki oranı hesaplıyoruz.

        if (bitmapRatio > 1) { //Resim yataysa burası çalışacak.
            width = maximumSize;
            height = (int) (width / bitmapRatio);
            //Uzunluğu düzelttik.

        } else { //Resim dikeyse de burası çalışacak.
            height = maximumSize;
            width = (int) (height * bitmapRatio);
            //Genişliği düzelttik.
        }
        return Bitmap.createScaledBitmap(image,width,height,true);//Görseli,genişliği,uzunluğu ve filtrelemeyi sorar. Bu metod bize küçültülmüş bir bitmap verir.
    }
}
