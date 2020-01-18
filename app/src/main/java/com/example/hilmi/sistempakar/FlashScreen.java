package com.example.hilmi.sistempakar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hilmi.sistempakar.database.SessionHelper;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.Gejala;
import com.example.hilmi.sistempakar.models.Keputusan;
import com.example.hilmi.sistempakar.models.Penyakit;
import com.example.hilmi.sistempakar.models.Solusi;
import com.example.hilmi.sistempakar.models.User;

public class FlashScreen extends AppCompatActivity {

    private int waktu_flash = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_screen);

        if(SessionHelper.getInstance(this).getAppFirstTime()){
            if (SessionHelper.getInstance(this) == null){
                SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit("P09","TIDAK ADA RULEBASE", "RULEBASE TIDAK AKTIV"));
            }
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit("P01","Penyakit snot",
                    "1. Memisahkan burung yang terkena snot dari lovebird lainya\n" +
                    "2. Jaga Kebersihan kandang yang terkena snot\n" +
                    "3. Berikan antibioting dan anti bakteri ataupun obat cair khusu lovebird"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit("P02","Penyakit diare",
                    "1. Pindahkan burung agak jauh dari burung lainya (Karantina)\n" +
                    "2. Berikan obat diare atau vitamin untuk lovebird"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit("P03","Penyakit nyilet",
                    "1.Karantina lovebird di tempat yang tenang dan sejuk" +
                    "2.Burung jangan dimandikan hingga burung sembuh dari nyilet\n" +
                    "3.Berikan obat antinyilet\n" +
                    "4.Berikan Makanan Extra Fooding kesukaan burung lovebird seperti"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit("P04","penyakit egg binding",
                    "1.Berikan asupan nutrisi yang cukup\n" +
                    "2.Menjaga kebersihan kandang\n" +
                    "3.Memberikan ruang yang cukup bersar\n" +
                    "4.Airminum ganti setiap pagi dan sore hari"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit("P05","Penyakit berak kapur",
                    "1.Karantina lovebird supaya tidak menyebar penyakitnya pada burung lainya\n" +
                    "2.Selalu jaga kerbersihan kandang\n" +
                    "3.Berikan vitamin seperti medoxy, koleridin, s"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit("P06","Penyakit kutu",
                    "1.Jaga kebersihan kandang\n" +
                            "Mandikan burung dengan menggunakan shampo burung atau shampo anti kutu"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit("P07","penyakit ganguan pernafasan",
                    "mata"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit("P08","Penyakit Bubul",
                    "1.Kebersihan sangkar terutama, tempat bertengger burung yang biasa disebut tangkringan\n" +
                            "Gunakan salep untuk mengobati kaki burung yang terkena penyakit bubul"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit("P09","TIDAK ADA RULEBASE",
                    "RULEBASE TIDAK AKTIV"));


            SQLiteHelper.getInstance(this).addGejala(new Gejala("G01","Mata mengeluarkan cairan"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G02","Pembengkakan didaerah bagian mata membengkak"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G03","Mata terlihat sayup"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G04","kotoran cair"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G05","warna putih seperti kapur"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G06","kotoran putih seperti kapur"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G07","kotoran hijau seperti cacing"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G08","Tulang dada terlihat menonjol"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G09","Bagian Perut terlihat membesar"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G10","Bulu terlihat rusak"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G11","Bulu mengembang"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G12","Bulu terlihat adanya kutu"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G13","Hidung burung mengeluarkan cairan"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G14","Bagian kaki burung membengkak"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G15","kaki burung lemas"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G16","Kaki kuku burung terlihat memanjang"));

            //table keputusan
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P01","G01,G02,G03"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P02","G03,G04,G11"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P03","G08,G09"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P04","G03,G11,G11"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P05","G04,G10,G11,G06"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P06","G10,G11,G12"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P07","G03,G04,G13"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P08","G11,G14,G16"));




            SQLiteHelper.getInstance(this).addTableSolsui(new Solusi("S01","Kaki kuku burung terlihat memanjang"));
            SQLiteHelper.getInstance(this).addTableSolsui(new Solusi("S02","Kaki kuku burung terlihat memanjang"));
            SQLiteHelper.getInstance(this).addTableSolsui(new Solusi("S03","Kaki kuku burung terlihat memanjang"));
            SQLiteHelper.getInstance(this).addTableSolsui(new Solusi("S04","Kaki kuku burung terlihat memanjang"));
            SQLiteHelper.getInstance(this).addTableSolsui(new Solusi("S05","Kaki kuku burung terlihat memanjang"));
            SQLiteHelper.getInstance(this).addTableSolsui(new Solusi("S06","Kaki kuku burung terlihat memanjang"));
            SQLiteHelper.getInstance(this).addTableSolsui(new Solusi("S07","Kaki kuku burung terlihat memanjang"));
            SQLiteHelper.getInstance(this).addTableSolsui(new Solusi("S08","Kaki kuku burung terlihat memanjang"));


            //HASIL KEPUTUSAN




            SQLiteHelper.getInstance(this).addUSER(new User("admin","admin", "hilmi hidayat"));


            SessionHelper.getInstance(this).setAppFirstTime(false);
        }


        //handler
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {

                                          //setelah loading maka akan langsung berpindah ke home activity
                                          Intent home = new Intent(FlashScreen.this, IndexUtama.class);
                                          startActivity(home);
                                          finish();

                                      }
                                  },

                waktu_flash);

    }
}