package com.ornekdenem.mobluygulma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class NormalOyunAktivity extends AppCompatActivity {

    private TextView txtViewil,txtViewilBilgi,txtToplamPuan,txtSuree;
    private EditText editTxtTahmin;
    private Button btnHarfAls,btnTahminEts,btnTekrarOyna;
    private String iller[]={"adana","adıyaman", "afyon", "ağrı", "amasya", "ankara", "antalya", "artvin",
            "aydın", "balıkesir", "bilecik", "bingöl", "bitlis", "bolu", "burdur", "bursa", "çanakkale",
            "çankırı", "çorum", "denizli", "diyarbakır", "edirne", "elazığ", "erzincan", "erzurum", "eskişehir",
            "gaziantep", "giresun", "gümüşhane", "hakkari", "hatay", "ısparta", "mersin", "istanbul", "izmir",
            "kars", "kastamonu", "kayseri", "kırklareli", "kırşehir", "kocaeli", "konya", "kütahya", "malatya",
            "manisa", "kahramanmaraş", "mardin", "muğla", "muş", "nevşehir", "niğde", "ordu", "rize", "sakarya",
            "samsun", "siirt", "sinop", "sivas", "tekirdağ", "tokat", "trabzon", "tunceli", "şanlıurfa", "uşak",
            "van", "yozgat", "zonguldak", "aksaray", "bayburt", "karaman", "kırıkkale", "batman", "şırnak",
            "bartın", "ardahan", "ığdır", "yalova", "karabük", "kilis", "osmaniye", "düzce"};

    private Random rndIl,rndHarf;
    private int rndIlNumber,rndNumberHarf,baslangicHarfSayisi,toplamSure=90000;
    private String gelenIl,ilBoyutu,editTxtGelenTahmin;
    private ArrayList<Character> ilHarfleri;
    private float maximumPuan=100.0f,azaltilacakPuan,toplamPuan=0,bolumToplamPuan=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_oyun_aktivity);
        txtViewilBilgi=(TextView)findViewById(R.id.txtViewilBilgi);
        txtViewil=(TextView)findViewById(R.id.txtViewil);
        editTxtTahmin=(EditText)findViewById(R.id.editTxtTahmin);
        txtToplamPuan=(TextView) findViewById(R.id.textViewToplamPuan);
        txtSuree=(TextView)findViewById(R.id.txtSure);
        btnHarfAls=(Button)findViewById(R.id.btnHarfAl);
        btnTahminEts=(Button)findViewById(R.id.btnTahminEt);
        btnTekrarOyna=(Button)findViewById(R.id.bntTekrarOyna);


        new CountDownTimer(toplamSure, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtSuree.setText((millisUntilFinished/60000)+":"+((millisUntilFinished%60000) / 1000));

            }

            @Override
            public void onFinish() {
                txtSuree.setText("0:00");
                editTxtTahmin.setEnabled(false);
                btnHarfAls.setEnabled(false);
                btnTahminEts.setEnabled(false);
                btnTekrarOyna.setVisibility((View.VISIBLE));

                Toast.makeText(getApplicationContext(),"Oyun BİTTİ\nToplam Puanınız:"+bolumToplamPuan,Toast.LENGTH_LONG).show();

            }
        }.start();

        rndHarf=new Random();
        //rastgele il çekme
        randomDegerlerBelirle();

    }

    public void btnYenidenOyna(View v){
        Intent tekraroyna=new Intent(this,NormalOyunAktivity.class);
        finish();
        startActivity(tekraroyna);

    }



    public void btnTahminEt(View v){
        editTxtGelenTahmin=editTxtTahmin.getText().toString();

        if (!TextUtils.isEmpty(editTxtGelenTahmin)){
            if(editTxtGelenTahmin.equals(gelenIl)) {
                bolumToplamPuan+=toplamPuan;
                Toast.makeText(getApplicationContext(),"Tebrikler Doğru Tahminde Bulundunuz",Toast.LENGTH_SHORT).show();
                txtToplamPuan.setText("Toplam Bölüm Puanı: "+bolumToplamPuan);


                editTxtTahmin.setText("");
                randomDegerlerBelirle();

            }else
                Toast.makeText(getApplicationContext(),"Yanlış Tahminde Bulundunuz",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(),"Tahmin Değeri Boş Olamaz",Toast.LENGTH_SHORT).show();

    }

    public void btnHarfAl(View v){
    if (ilHarfleri.size()>0){
        randomHarfAl();
        toplamPuan-=azaltilacakPuan;
        Toast.makeText(getApplicationContext(),"Kalan Puan = "+toplamPuan,Toast.LENGTH_SHORT).show();

    }else
        Toast.makeText(getApplicationContext(),"Harf Kalmadı..",Toast.LENGTH_SHORT).show();


    }


    private void randomDegerlerBelirle(){
        ilBoyutu ="";
        rndIl= new Random();
        rndIlNumber=rndIl.nextInt(iller.length);
        gelenIl= iller[rndIlNumber];
        System.out.println(rndIlNumber+ "="+gelenIl);

        txtViewilBilgi.setText(gelenIl.length() +" Harfli ilimiz");


        if(gelenIl.length()>=5&&gelenIl.length()<=7)
            baslangicHarfSayisi=1;
        else if (gelenIl.length()>=8&&gelenIl.length()<10)
            baslangicHarfSayisi=2;
        else if(gelenIl.length()>=10)
            baslangicHarfSayisi=3;
        else
            baslangicHarfSayisi=0;


        for (int i = 0; i<gelenIl.length(); i++){
            if (i<gelenIl.length()-1)
                ilBoyutu+="_ ";
            else
                ilBoyutu +="_";
        }

        txtViewil.setText(ilBoyutu);

        ilHarfleri=new ArrayList<>();

        for(char c : gelenIl.toCharArray())
            ilHarfleri.add(c);


        for (int c=0;c<baslangicHarfSayisi;c++){
            randomHarfAl();

        }

        azaltilacakPuan=maximumPuan/ilHarfleri.size();
        toplamPuan=maximumPuan;


    }

    private void randomHarfAl(){
        rndNumberHarf=rndHarf.nextInt(ilHarfleri.size());
        String[] txtHarfler=txtViewil.getText().toString().split(" ");
        char[] gelenilHarfler=gelenIl.toCharArray();

        for (int i=0;i<gelenIl.length();i++){
            if (txtHarfler[i].equals("_")&&gelenilHarfler[i]==ilHarfleri.get(rndNumberHarf)){
                txtHarfler[i]=String.valueOf(ilHarfleri.get(rndNumberHarf));
                ilBoyutu="";

                for (int j=0;j<gelenIl.length();j++){
                    if (j==i)
                        ilBoyutu+=txtHarfler[j]+" ";
                    else if (j<gelenIl.length()-1)
                        ilBoyutu+=txtHarfler[j]+" ";
                    else
                        ilBoyutu+=txtHarfler[j];
                }
                break;
            }

        }

        txtViewil.setText(ilBoyutu);
        ilHarfleri.remove(rndNumberHarf);
    }


}