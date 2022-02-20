package com.example.sqliteogrenmeprojesi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //try - catch
        //hata yapılırsa veritabanını sıfırlamak için uygulamayı sil tekrar başlat
        try {

            val veritabani = this.openOrCreateDatabase("Urunler", Context.MODE_PRIVATE , null)

            veritabani.execSQL("CREATE TABLE IF NOT EXISTS urunler (id INTEGER PRIMARY KEY , isim VARCHAR , fiyat INT)")  //dosya oluşturma  primary key: oto id verme
            // veri ekleme isim ve fiyat (id otomatik veriliyor)
            //veritabani.execSQL("INSERT INTO urunler (isim,fiyat) VALUES ('Ayakkabi', 100)")
            //veritabani.execSQL("INSERT INTO urunler (isim,fiyat) VALUES ('Elbise', 150)")
            //veritabani.execSQL("INSERT INTO urunler (isim,fiyat) VALUES ('Tshirt', 50)")
            //veritabani.execSQL("INSERT INTO urunler (isim,fiyat) VALUES ('Atkı', 200)")
           //veritabani.execSQL("INSERT INTO urunler (isim,fiyat) VALUES ('Bere', 10)")


            //*******Veri silme işlemi********
            //veritabani.execSQL("DELETE FROM urunler WHERE id = 5 ")    //id' si 5 olanı sil

            //******Güncelleme*******
            //veritabani.execSQL("UPDATE urunler SET fiyat = 250 WHERE isim = 'Elbise' ")

            //Ürün ismi değiştirme-güncelleme
            //veritabani.execSQL("UPDATE urunler SET isim = 'YeniAyakkabi' WHERE id = 1 ")

            //veri okuma
            val cursor = veritabani.rawQuery("SELECT * FROM urunler", null)  //herşeyi okur

            //val cursor = veritabani.rawQuery("SELECT * FROM urunler WHERE 'Bere' ", null)    //filtreleme 'isim'
            //val cursor = veritabani.rawQuery("SELECT * FROM urunler WHERE id = 3 ", null)    //filtreleme 'id'
            //val cursor = veritabani.rawQuery("SELECT * FROM urunler WHERE isim LIKE 'A%' ", null)    //Baş harfi A ile başlayanlar (%i sonu i ile biten)


            val idColumnIndex = cursor.getColumnIndex("id")
            val isimColumnIndex = cursor.getColumnIndex("isim")
            val fiyatColumnIndex = cursor.getColumnIndex("fiyat")


            while(cursor.moveToNext()){

                println("ID : ${cursor.getInt(idColumnIndex)}")
                println("Isim : ${cursor.getString(isimColumnIndex)}")
                println("Fiyat : ${cursor.getInt(fiyatColumnIndex)}")

            }
            cursor.close()


        }  catch (e : Exception){

            e.printStackTrace()
        }


    }

}