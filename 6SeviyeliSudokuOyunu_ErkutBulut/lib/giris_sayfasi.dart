import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:hive/hive.dart';
import 'package:hive_flutter/hive_flutter.dart';

import 'sudoku_sayfasi.dart';
import 'dil.dart';
import 'hakkinda.dart';

String color = '#1565c0';
String hex = color.replaceAll("#", "");
Color col = Color(int.parse(hex, radix: 16)).withOpacity(1.0);

class GirisSayfasi extends StatefulWidget {
  GirisSayfasi({Key key}) : super(key: key);

  @override
  _GirisSayfasiState createState() => _GirisSayfasiState();
}

class _GirisSayfasiState extends State<GirisSayfasi> {
  Box _sudokuKutu;
  Future<Box> _kutuAc() async {
    _sudokuKutu = await Hive.openBox("sudoku");
    return await Hive.openBox('tamamlanan_sudokular');
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<Box>(
      future: _kutuAc(),
      builder: (context, snapshot) {
        if (snapshot.hasData)
          return Scaffold(
            appBar: AppBar(
              backgroundColor: (col),
              title: Text(dil['giris_title']),
              actions: <Widget>[
                IconButton(
                    icon: Icon(Icons.account_circle),
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (_) => HakkindaSayfa()),
                      );
                    }),
                IconButton(
                  icon: Icon(Icons.invert_colors),
                  onPressed: () {
                    Box kutu = Hive.box('ayarlar');
                    kutu.put(
                      'karanlik_tema',
                      !kutu.get('karanlik_tema', defaultValue: false),
                    );
                  },
                ),
                if (_sudokuKutu.get('sudokuRows') != null)
                  IconButton(
                    icon: Icon(Icons.play_arrow),
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (_) => SudokuSayfasi()),
                      );
                    },
                  ),
                PopupMenuButton(
                  icon: Icon(Icons.add_circle_outline),
                  onSelected: (deger) {
                    if (_sudokuKutu.isOpen) {
                      _sudokuKutu.put('seviye', deger);
                      _sudokuKutu.put('sudokuRows', null);
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (_) => SudokuSayfasi()),
                      );
                    }
                  },
                  itemBuilder: (context) => <PopupMenuEntry>[
                    PopupMenuItem(
                      value: dil['seviye_secin'],
                      child: Text(
                        dil['seviye_secin'],
                        style: TextStyle(
                          fontWeight: FontWeight.bold,
                          color: Theme.of(context).textTheme.bodyText1.color,
                        ),
                      ),
                      enabled: false,
                    ),
                    for (String k in sudokuSeviyeleri.keys)
                      PopupMenuItem(
                        value: k,
                        child: Text(k),
                      ),
                  ],
                ),
              ],
            ),
            body: ValueListenableBuilder<Box>(
              valueListenable: snapshot.data.listenable(),
              builder: (context, box, _) {
                return Column(
                  children: <Widget>[
                    if (snapshot.data.length == 0)
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Text(
                          dil['tamamlanan_yok'],
                          textAlign: TextAlign.center,
                          style: GoogleFonts.roboto(
                            textStyle: TextStyle(fontSize: 24.0),
                          ),
                        ),
                      ),
                    for (Map eleman in snapshot.data.values.toList().reversed.take(50))
                      ListTile(
                        onTap: () {},
                        title: Text("${eleman['tarih']}"),
                        subtitle: Text("${Duration(seconds: eleman['sure'])}".split('.').first),
                      )
                  ],
                );
              },
            ),
          );
        return Center(child: CircularProgressIndicator());
      },
    );
  }
}
