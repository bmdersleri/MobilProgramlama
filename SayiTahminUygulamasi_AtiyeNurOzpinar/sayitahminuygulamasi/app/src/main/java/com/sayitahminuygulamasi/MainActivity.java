package com.sayitahminuygulamasi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;



public class MainActivity extends AppCompatActivity {
    public EditText t1,t2,t3,t4,t5;
    public int sayi,tahmin,sinir;
    public int deneme = 3;
    public Button buton,buton2;
    public String ses,tahminStr;
    public boolean döndür;
    public int[]dizi = new int [3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         *
         * t1,t2,t3,t4,t5 Edit textlerin tanımlanması
         * buton,buton2 eventListener kullanımı için butonların id üzerinden Button sınıfı değişkenlere atanması.
         *
         * */
        t1 =(EditText)findViewById(R.id.editTextNumber);
        t2 = (EditText)findViewById(R.id.editTextNumber2);
        t3 = (EditText)findViewById(R.id.editTextNumber3);
        t4 = (EditText)findViewById(R.id.editTextNumber5);
        t5 = (EditText)findViewById(R.id.editTextNumber6);
        döndür = false;

        buton = findViewById(R.id.button);
        buton2 = findViewById(R.id.button2);

        /*
         *
         * buton değişkenine onClickListener ekelenerek random sayılar üretilmesi sağlanmıştır
         * buton2 değişkenine onClickListener eklenerek oluşturulan random sayılar 3 kere tahmin edilmiştir.
         * */
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * Random sayi üreten fonksiyonun çağırılması
                 *
                 */
                RandomNumb();
            }
        });

        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * Text olarak t4 Edit Textinden tahmin edilen değer çekilmiş ve intigera dönüştürülmüştür.
                 * deneme değişkeni kullanıcıya 3 hak vermek için if-else yapısı içinde kullanılmıştır. 3 Hakkın bitiminde otomatik olarak
                 * yeni random sayılar üretmek için RandomNumb() fonksiyonu çağırılmış ve deneme değeri tekrar 3 değerine sıfırlanmıştır.
                 * Eğer t4 değişkeni ile alınan tahmin değeri random sayıların tutulduğu dizi değişkeninin 3. elemanına eşitse
                 * Kullanıcı oyunu kazanır.
                 */


                try{
                    tahminStr = t4.getText().toString();
                    tahmin = Integer.parseInt(tahminStr);
                }catch (NumberFormatException tfe){
                    t5.setText("Lütfen Geçerli Bir Sayı Giriniz!!!");
                }
                if(deneme>0){
                    if(tahmin == dizi[2]){
                        t5.setText(" Tebrikler Bildiniz.");
                    }else{
                        t5.setText("Geriye "+ deneme +"  Hakkınız Kaldı!!" );
                        deneme = deneme - 1;
                    }
                }else{
                    RandomNumb();
                    t5.setText("Kaybettiniz. Yeni Sayılar Atandı!!" );
                    deneme = 3;
                }
            }
        });
    }
    /*
     * ses değişkeni random sayı aralığını belirlemek için t1 EditText değişkeninden, kullanıcı tarafından alınan bir değerdir.
     * Bu değer integera dönüştürülerek Random modülünün nextInt metoduna 'n' parametresi olarak verilmiştir. Bu sayede
     * 0-n arası farklı sayılar elde edilmektedir.
     */
    public void RandomNumb(){
        try{
            ses = t1.getText().toString();
            sinir = Integer.parseInt(ses);
        }catch (NumberFormatException tfe){
            t5.setText("Lütfen Geçerli Bir Sayı Giriniz!!!");
        }

        Random rnd = new Random();

        boolean durum =false;
        /*boolean tipinde durum değişkeni tanımlayıp
        değerini false yaptık*/
        for (int i = 0; i<dizi.length; i++)
        //dizinin  index elemanlarına ulaşmak için
        {
            durum = false;
            //durumu while döngüsüne girilebilsin diye false yaptık.
            while(durum==false){
                sayi = rnd.nextInt(sinir);
                //durum false iken while içine gir ve nextint metodunu kullanarak
                // random clasından randombir sayı üret dedik
                //üst sınırımızıda bu metodun içine paremetre olarak atadık.
                for(int j = 0; j<dizi.length; j++ )
                //kontrol için açılan for döngüsü
                {
                    if(dizi[j] ==sayi){
                        break;
                        //dizinin j. elemanı üretilen sayıya eşitmi
                    }
                    //eğer eşitse for döngüsü kırılıp tekrar while döngüsüne girer
                    if(j==dizi.length-1){
                        durum=true;
                    }
                    //yukarıdaki ifadede eğer dizinin son elemanına gelindiys
                    // durumu true yapıp while döngüsünden çıkığ üretilen
                    // sayiyi dizinin içersinee alacak
                }
            }
            dizi[i]=sayi;
            //dizinin indeksinci elemanına sayıyı atadık.
        }


        t2.setText(String.valueOf(dizi[0]));

        t3.setText(String.valueOf(dizi[1]));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}