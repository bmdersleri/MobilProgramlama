package com.dersukilic.qrkodolusturmauygulamasi

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.popup_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> Toast.makeText(applicationContext, "Hazirlayan:Silan Dersu Kilic, Mail:silandersu25@gmail.com",
                    Toast.LENGTH_SHORT).show()
        }
        return false
    }

    private lateinit var qrCodeIV:ImageView
    private lateinit var dataEdt:EditText
    private lateinit var generateQrBtn: MaterialButton

    var bitmap :Bitmap? = null
    var qrgEnCoder:QRGEncoder?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        qrCodeIV = findViewById(R.id.idIVQrkod)
        dataEdt = findViewById(R.id.idEdt)
        generateQrBtn = findViewById(R.id.idBtnOlusturQR)


        generateQrBtn.setOnClickListener {
            if (TextUtils.isEmpty(dataEdt.text.toString())){
                Toast.makeText(this@MainActivity,
                    "QR Kodu olusturmak icin bir metin giriniz.",Toast.LENGTH_SHORT).show()
            }else{
                val manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val display = manager.defaultDisplay
                val point = Point()
                display.getSize(point)
                val width = point.x
                val height = point.y
                var dimen = if (width< height) width else height

                dimen = dimen *3/4

                qrgEnCoder = QRGEncoder(dataEdt.text.toString(),
                    null,QRGContents.Type.TEXT,dimen)

                try {
                    bitmap = qrgEnCoder!!.encodeAsBitmap()
                    qrCodeIV.setImageBitmap(bitmap)

                }catch (e:Exception){
                    Log.e("Tag",e.toString())
                }

            }
        }

    }

}