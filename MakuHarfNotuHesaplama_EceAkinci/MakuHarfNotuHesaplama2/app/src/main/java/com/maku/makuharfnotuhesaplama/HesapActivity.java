package com.maku.makuharfnotuhesaplama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class HesapActivity extends AppCompatActivity {

    //Bu Activity'de yer alan nesneleri tanımlıyoruz.
    TextView txtVSinav;
    EditText editTxtVize, editTxtFinal;
    Button buttonHesaplama;
    TextView txtVSonuc, txtVHarfNotu;
    ImageView imageViewMaku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hesap);

        //Yukarıda tanımladığımız nesneleri id'leri ile eşleştiriyoruz.
        txtVSinav=findViewById(R.id.txtVSinav);
        editTxtVize=findViewById(R.id.editTxtVize);
        editTxtFinal=findViewById(R.id.editTxtFinal);
        buttonHesaplama=findViewById(R.id.buttonHesaplama);
        txtVSonuc=findViewById(R.id.txtVSonuc);
        txtVHarfNotu=findViewById(R.id.txtVHarfNotu);
        imageViewMaku=findViewById(R.id.imageViewMaku);

        //Butona tıklandığında yapılmasını istediğimiz işlemleri ".setOnClickListener" metodunun içine yazıyoruz.
        buttonHesaplama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Girilen değerleri atamak istediğimiz değişkenleri double veri tipinde tanımlıyoruz.
                double vizeNotu, finalNotu, sonuc;

                //Klavyeden girilen değer String ifade olarak algılanacağı için Double tipine döndürmek için gereken metodları kullanıyoruz.
                vizeNotu=Double.parseDouble(editTxtVize.getText().toString());
                finalNotu=Double.parseDouble(editTxtFinal.getText().toString());

                //Vize notunun %40'ı, final notunun da %60'ı alındığı için işlemimizi yapıp sonuc değişkenine atıyoruz.

                sonuc=(vizeNotu*0.4) + (finalNotu*0.6);
                txtVSonuc.setText("Ortalamanız: "+sonuc);

                if (sonuc <=100 && sonuc>89){       //Eğer ortalama 90 ile 100 arasında ise harf notu AA
                    txtVHarfNotu.setText("Harf Notunuz: AA");
                }
                else if(sonuc<= 89 && sonuc>84){    //Eğer ortalama 85 ile 89 arasında ise harf notu BA
                    txtVHarfNotu.setText("Harf Notunuz: BA");
                }
                else if(sonuc<= 84 && sonuc>74){    //Eğer ortalama 75 ile 84 arasında ise harf notu BB
                    txtVHarfNotu.setText("Harf Notunuz: BB");
                }
                else if(sonuc<= 74 && sonuc>69){    //Eğer ortalama 70 ile 74 arasında ise harf notu CB
                    txtVHarfNotu.setText("Harf Notunuz: CB");
                }
                else if(sonuc<= 69 && sonuc>59){    //Eğer ortalama 60 ile 69 arasında ise harf notu CC
                    txtVHarfNotu.setText("Harf Notunuz: CC");
                }
                else if(sonuc<= 59 && sonuc>54){    //Eğer ortalama 55 ile 59 arasında ise harf notu DC
                    txtVHarfNotu.setText("Harf Notunuz: DC");
                }
                else if(sonuc<= 54 && sonuc>49){    //Eğer ortalama 50 ile 54 arasında ise harf notu DD
                    txtVHarfNotu.setText("Harf Notunuz: DD");
                }
                else if(sonuc<= 49 && sonuc>39){    //Eğer ortalama 40 ile 49 arasında ise harf notu FD
                    txtVHarfNotu.setText("Harf Notunuz: FD");
                }
                else if(sonuc<= 39 && sonuc>=0){    //Eğer ortalama 0 ile 39 arasında ise harf notu FF
                    txtVHarfNotu.setText("Harf Notunuz: FF");
                }
                else{ //Eğer girilen değer 0 ile 100 arasında değil ise bunu ekrana yazdır.
                    txtVHarfNotu.setText("Ortalama ve Harf Notu Hesaplanamadı Tekrar Deneyiniz!");
                }
            }
        });
    }
}