# Unity ile Food Eater Oyunu Yapma

Food Eater uygulaması tanıtımı:

Food Eater, karakterimizi yönlendirerek zehirli olmayan yemekleri yemeye çalıştığımız bir oyun.
Eğer zehirli olan yiyecekleri yersek oyunumuz başa dönüyor.
Eğer bütün yiyecekleri yiyebilirsek ‘Seviye Tamamlandı’ ibaresiyle karşılaşıyoruz.

Uygulamanın Kodlarının Açıklamaları:

Rigidbody2D rb değişkeni oluşturalım. Sonra void awake fonksiyonunu oluşturuyoruz. Sonrasında fixupdate fonksiyonunu oluşturuyouz. Haraket hızını tanımlıyoruz. movespeed I daha önce tanımlamadığımız için public float movespeed değişkeni oluşturalım. Sonrasında yine public float rotate amount değişkeni oluşturalım. Son olarak float rot değişkeni oluşturalım. Bu karakterimizin rotate yani döndürme değeri olacak. Şimdi ekranda sağa mı sola mı tıklandığını kontrol ettireceğiz yani playerımızın haraketini ayarlayacağız. Bu ekrana basıp basmadığımızı test edecek. mousepos < 0 ise bu sola basıyoruz demektir.
Şimdide collision yani çarpışmayı ayarlayacağız. Eğer food'a çarparsak food u yok et. Eğer danger a çarparsak game ekranını geri yükle işlemlerini yapacak kodları yazıyoruz. Bu aşamada scenemanagement kütüphanesi gelmesi lazım eğer gelmezse kendimiz ekleyelim.
score adında yeni bir değişiken ekleyelim. eğer score 5ten büyükse ekrana level complete yazdıralım.

Youtube Kanalımız: BMDersleri

Bağlantı: https://www.youtube.com/channel/UCIdYgV-XFjv9q0IHtzUTtQw

Konu ile ilgili Youtube Video Linki : https://www.youtube.com/watch?v=Lx00AP5aLh4

Kısa Bağlantı: https://bit.ly/32k9MnJ

Github Adresimiz: https://github.com/bmdersleri

Hazırlayan: Aykut YAŞ
