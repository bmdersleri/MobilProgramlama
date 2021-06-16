import 'package:flutter/material.dart';
import 'dil.dart';
import 'giris_sayfasi.dart';

String color = '#19227c';
String hex = color.replaceAll("#", "");
Color col = Color(int.parse(hex, radix: 16)).withOpacity(1.0);

class HakkindaSayfa extends StatefulWidget {
  @override
  _HakkindaSayfaState createState() => _HakkindaSayfaState();
}

class _HakkindaSayfaState extends State<HakkindaSayfa> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Hakkımda"),
        backgroundColor: col,
      ),
      body: Center(
        child: Container(
          alignment: Alignment.center,
          child: Text(
            "Copyright © 2021 Tüm Hakları Saklıdır.\n\nErkut BULUT\n\nİletişim: erkutbulut0915@gmail.com",
            textAlign: TextAlign.center,
            style: TextStyle(fontSize: 20),
          ),
        ),
      ),
    );
  }
}
