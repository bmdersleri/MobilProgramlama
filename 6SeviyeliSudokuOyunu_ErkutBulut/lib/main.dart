import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:hive/hive.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'giris_sayfasi.dart';

void main() async {
  await Hive.initFlutter('sudoku');
  await Hive.openBox('ayarlar');
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ValueListenableBuilder<Box>(
        valueListenable: Hive.box('ayarlar').listenable(keys: ['karanlik_tema', 'dil']),
        builder: (context, kutu, _) {
          return MaterialApp(
            debugShowCheckedModeBanner: false,
            theme: kutu.get('karanlik_tema', defaultValue: false) ? ThemeData.dark() : ThemeData.light(),
            home: GirisSayfasi(),
          );
        });
  }
}
