package com.SerenSolmazZ.KuaforListem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Değişkenlerimizi bütün metodlarda çağırabilmek için global olarak tanımladık.
    ListView listView;
    ArrayList<String> nameArray;//String dizisi oluşturduk.
    ArrayList<Integer> idArray;//Integer dizisi oluşturduk.
    ArrayAdapter arrayAdapter;//Bilgilerimizi güncellemek için array adapter oluşturduk.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(MainActivity.this,"Kayıt eklemek için sağ üst köşedeki menüyü kullanınız.",Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Değişkenlerimizi aktifleştirdik.
        listView = findViewById(R.id.listView);
        //Dizilerimizi oluşturduk ve boş bıraktık.
        nameArray = new ArrayList<String>();
        idArray = new ArrayList<Integer>();

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,nameArray);//Listeye yeni elemanlar eklediğimizde kullanıcıya gösterecek.

        listView.setAdapter(arrayAdapter);//listview'a arrayAdapterdan gelen bilgileri bağladık.

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Bize nereye tıklandığını gösteren olan metod.
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);//Uygulamaya tıkladığımız zaman diğer activity bilgileri yollamamızı sağlar.
                intent.putExtra("kuaforId",idArray.get(position));//position yardımıyla kullanıcı nereye tıkladıysa bilgi de oraya verilecektir.
                intent.putExtra("info","old");//listview içerisindeki bir iteme tıklandığında gösterilecek.
                startActivity(intent);
            }
        });


        getData();//Açılışta çağırdığımız metod.

    }

    public void getData() { //Bu metod verilerimizi çekmek için.

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Kuafors",MODE_PRIVATE,null);//Kuafors adında bir database oluşturduk.

            Cursor cursor = database.rawQuery("SELECT * FROM kuafors", null);//İmleç oluşturduk, bu imleci kullanarak database'de bir query yapabiliyoruz.
            int nameIx = cursor.getColumnIndex("kuaforname");//1.activity'de sadece kuaför ismini gösterdik.
            int idIx = cursor.getColumnIndex("id");//Bir yanlışlık olmaması adına kuaför ismini gösterirken id'ye göre bilgi çekmesini istedik.

            while (cursor.moveToNext()) {
                nameArray.add(cursor.getString(nameIx));//name dizimize name indexlerimizi aktardık.
                idArray.add(cursor.getInt(idIx));//id dizimize id indexlerimizi aktardık.

            }

            arrayAdapter.notifyDataSetChanged();//Yeni veri eklediğimizde bunu bağlı listelerde göstermemizi sağlar.

            cursor.close();//cursorla işimiz bitince kapatıyoruz.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Hangi menüyü göstereceğimizi belirtiyoruz.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //xml dosyası oluşturulduğunda onu activity içinde göstermek için inflater kullanılır.
        MenuInflater menuInflater = getMenuInflater();//Menümüzü activityimize bağladık.
        menuInflater.inflate(R.menu.add_art,menu);//Oluşturduğumuz menüyü de activityimize bağladık.


        return super.onCreateOptionsMenu(menu);
    }

    //Kullanıcı herhangi bir item seçerse ne yapacağımızı belirtiyoruz.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_art_item) { //Eğer add_art_item'e tıklanırsa ne yapılacağını söyleyeceğiz.

            Intent intent = new Intent(MainActivity.this,Main2Activity.class);//O iteme tıklandığında 2.activitye geçmesini söyledik.
            intent.putExtra("info","new");//Sağ üstte yer alan 3 noktaya tıklandığında görünecek.
            startActivity(intent);//Diğer activityimize götürür.
        }

        return super.onOptionsItemSelected(item);
    }
}
