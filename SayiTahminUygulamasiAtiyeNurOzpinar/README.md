                                              SAYI TAHMİN UYGULAMASI
Yapmış olduğum sayı tahmin adlı uygulamada kısaca öncelikle uygulamadan bahsedicek olursam uygulamamız kullanıcının girdiği üst sınıra göre random sayılar üretmekte ve (iki adet)Kullanıcıdan tahmin almaktadır kullanıcı üçüncü sayı için tahmin girmektedir.Kullanıcının sayıyı tahmin etmesi için kullanıcıya 3 hak tanımlanmıştır. Kullanıcı Bu üç hak içersinde dogru tahmini yaparsa pyunu kazanır. Sonuç olarakta tebrikler kazandınız adında bir çıktı alır.Eğer Kullanıcı bu 3 hak içinde tahmini doğru tutturamazsa oyunu kaybeder ve çıktı olarakta kaybettiniz Yeni random sayılar atandı ifadesini görür.Kullanıcının oyunu kaybetmesi durumunda tekrar uygulama birinci ve ikincisayı texview'lerin edittextlerine yeni random sayılar atanır.Uygulamayı daha detaylı anlatıcak olursakta öncelikle uygulamanın tasarım kısmı yapılmıstır.Buton'lar textview'ler edittext'er eklenmiştir.
İlk olak üst sınır adında bir textview Bunun yanınada kullanıcının sayı girrebilmesi için bir edittext konulmuştur.(konulan edittex number tipinde seçilmiştir nedeni ise kullanıcın sayı gireceği için)Daha sonra bu oluşturulan edittext ve textview'in yanına bir adet sayıları üret adında bir buton eklenmiştir.Buton üst kullanıcıdan üst sayı alındıktan sonra kullanıcının butona basıp random sayılar üretmesi içindir. Burada üretilecek olan random sayılar için Birinci ve ikinci sayı adında  iki adet text view  ve bunun yanına üretilen sayıların görüntülenmesi için edittex’ler konulmuştur. 
Buraya kadar yapılan işlemler kullanıcının üst sınır girmesi ve bu üst sınıra göre random sayılar atanmasını sağlanma kısmıdır. Bu işlemlerin sonrasında üçüncü sayı adında Bir adet daha text view ve onun yanınada bir adet number tipinde textview eklenmiştir. Eklenen textview’in text’ine belirsizlik anlamına gelen ‘**’ ifadesi konulmuştur. Nedeni ise üçüncü sayıyı kullanıcı tahmin edeceği içindir. Bu işleminde sonrasında  Kullanıcının tahminini gireceği bir tahmin adında bir edittex ve tahmin girmesi içinde number tipinde bir edittext konulmuştur.Kullanıcı tahminini buraya girecektir ve Kullanıcınınn tahmini için hak tanımlanması yapılmmıştır. Kullanıcının sayıyı bilmesi için kullanıcıya 3 hak tverilmiştir Kullanıcı Bu haklar içinde eğer tahmini doğru yaparsa tebrikler yazar eger tahmini yanlış yaparsa hak sayıyısı bir azalır her seferinde hak  sayısı sıfır olduğundada kullanıcı oyunu kaybeder ve uygulama tekrar kullanıcıya random sayılar atacr. Sözel olarak anlattığım bu kısmın kod tarafında anlatımı ise şu şekildedir;
Öncelikli oluşturalan edittext,textview’lerin tanımlanmaları yapılmış ve herbiri bir değişkene atanmıştır. buton,buton2 eventListener kullanımı için butonların id üzerinden Button sınıfı değişkenlere atanması. Daha sonra random sayı üreten fonksiyonun çağırılması işlemi yapıldı.Text olarak t4 Edit Textinden tahmin edilen değer çekilmiş ve intigera dönüştürülmüştür. Deneme değişkeni kullanıcıya 3 hak vermek için if-else yapısı içinde kullanılmıştır. 3 Hakkın bitiminde otomatik olarak yeni random sayılar üretmek için RandomNumb() fonksiyonu çağırılmış ve deneme değeri tekrar 3 değerine sıfırlanmıştır.

Eğer t4 değişkeni ile alınan tahmin değeri random sayıların tutulduğu dizi değişkeninin 3. elemanına eşitse Kullanıcı oyunu kazanır. ses değişkeni random sayı aralığını belirlemek için t1 EditText değişkeninden, kullanıcı tarafından alınan bir değerdir.Bu değer integera dönüştürülerek Random modülünün nextInt metoduna 'n' parametresi olarak verilmiştir. Bu sayede 0-n arası farklı sayılar elde edilmektedir. boolean tipinde durum değişkeni tanımlayıp
değerini false yapıldı. Durum değişkeni while dönüsüne girilebilsin diye false yapıldı. Dizini j. Elemanına tahmin edilen sayı eşitmi değilmi bunun kontrolünü sağlamak için bir if-else döngüsü(koşul) oluşturuldu. Eğer koşul sağlandıysa for döngüsünü kırıp tekrar while döngüsüne girer. Eğer dizinin son elemanına gelindiyse durum true yapılır ve while döngüsünden üretilen çıktı sayı dizinin içersine alınır.

Youtube Kanalımız: BMDersleri

Bağlantı: https://www.youtube.com/channel/UCIdYgV-XFjv9q0IHtzUTtQw

Kısa Bağlantı: https://bit.ly/32k9MnJ

Github Adresimiz: https://github.com/bmdersleri

Uygulamanın Youtube Linki:  https://play.google.com/store/apps/details?id=com.saytahmnuygulamas

Hazırlayan: Atiye Nur Özpınar




