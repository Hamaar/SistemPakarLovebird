package com.example.hilmi.sistempakar.adminpanel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.Gejala;
import com.example.hilmi.sistempakar.models.Keputusan;

import static com.example.hilmi.sistempakar.database.SQLiteHelper.KEY_CARA;
import static com.example.hilmi.sistempakar.database.SQLiteHelper.KEY_ID;
import static com.example.hilmi.sistempakar.database.SQLiteHelper.KEY_KODE_GEJALA;
import static com.example.hilmi.sistempakar.database.SQLiteHelper.KEY_KODE_PENYAKIT;
import static com.example.hilmi.sistempakar.database.SQLiteHelper.KEY_NAMA_GEJALA;
import static com.example.hilmi.sistempakar.database.SQLiteHelper.KEY_NAMA_PENYAKIT;
import static com.example.hilmi.sistempakar.database.SQLiteHelper.TABLE_GEJALA;
import static com.example.hilmi.sistempakar.database.SQLiteHelper.TABLE_PENYAKIT;

public class ScreenAdminDetil extends AppCompatActivity {

    TextInputEditText edtKode, edtNama, edtYA;
    TextInputLayout hintKode, hintNama, hintYa;

    SQLiteDatabase databaseSQL;
    protected Cursor cursor;

    String TAG = "";


    private SQLiteDatabase database;
    SQLiteHelper sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_admin_detil);

        this.sqLiteHelper = new SQLiteHelper(getApplicationContext());
        sqLiteHelper = SQLiteHelper.getInstance(this);


        edtKode = (TextInputEditText) findViewById(R.id.edt_kode);
        edtNama = (TextInputEditText) findViewById(R.id.edt_nama);
        edtYA = (TextInputEditText) findViewById(R.id.edt_YA);
        hintKode = (TextInputLayout) findViewById(R.id.hintKode);
        hintNama = (TextInputLayout) findViewById(R.id.hintNama);
        hintYa = (TextInputLayout) findViewById(R.id.hintYa);


        //redirect GET GEJALA
        sqLiteHelper = new SQLiteHelper(this);
        //handle Lihat Table Gejala

        if (getIntent().hasExtra("nama_gejala")) {
            SQLiteDatabase db2 = sqLiteHelper.getReadableDatabase();
//            cursor = db2.rawQuery("SELECT * FROM " + TABLE_GEJALA + " WHERE " + KEY_KODE_GEJALA + "=?", new String[] { KEY_KODE_GEJALA + ""});
            cursor = db2.rawQuery("select * from " + TABLE_GEJALA ,  null);
            cursor.moveToFirst();
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToPosition(0);
                edtKode.setText(cursor.getString(0));
                edtNama.setText(cursor.getString(1));

                Log.d(TAG, "sqLiteGEJALA: " + db2 + cursor + sqLiteHelper);
                System.out.print(edtKode);
                System.out.print(edtNama);

                hintYa.setVisibility(View.INVISIBLE);
                edtYA.setVisibility(View.INVISIBLE);
            }
        } else if (getIntent().hasExtra("penyakit")) {
            //GET PENYAKIT
            SQLiteDatabase sqLitePENYAKIT = sqLiteHelper.getReadableDatabase();
            cursor = sqLitePENYAKIT.rawQuery("SELECT * FROM penyakit WHERE penyakit= '"
                    + getIntent().getStringExtra("penyakit") + "'", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToPosition(0);
                hintKode.setHint("Kode Penyakit");
                hintNama.setHint("Nama Penyakit");
                edtKode.setText(cursor.getString(0).toString().trim());
                edtNama.setText(cursor.getString(1).toString());

                Log.d(TAG, "sqLitePENYAKIT: " + sqLitePENYAKIT + cursor);
                System.out.print(edtKode);
                System.out.print(edtNama);

                Log.d(TAG, "sqLitePENYAKIT: " + sqLitePENYAKIT + cursor);
                hintYa.setVisibility(View.INVISIBLE);
                edtYA.setVisibility(View.INVISIBLE);
            }
        }
        else if(getIntent().hasExtra("screen_penyakit_solusi")){
            //GET SOLUSI
            SQLiteDatabase db2 = sqLiteHelper.getReadableDatabase();
//            cursor = db2.rawQuery("SELECT * FROM " + TABLE_PENYAKIT + " WHERE " + KEY_KODE_PENYAKIT +    new String[] { KEY_KODE_PENYAKIT + "'")};




            cursor = db2.rawQuery("SELECT * FROM " + TABLE_PENYAKIT + " WHERE " + KEY_KODE_PENYAKIT + "=?", new String[] { KEY_KODE_GEJALA + ""});

                if (cursor.getCount()>0) {
                    cursor.moveToPosition(0);

                    hintKode.setHint("ID"); hintKode.setVisibility(View.INVISIBLE);
                    hintNama.setHint("Kode Penyakit");
                    hintYa.setHint("Solusi");

                    edtKode.setText(cursor.getString(0).toString());
                    edtNama.setText("nama_penyakit "+getIntent().getExtras().getString("nama_penyakit"));
//                    edtNama.setText(cursor.getString(1).toString());
                    edtYA.setText(cursor.getString(2).toString());


                    Log.d(TAG, "sqlMaster: " + db2 + cursor + edtKode + edtNama);
                    System.out.print(edtKode);
                    System.out.print(edtNama);
                    System.out.print(edtYA);
                    Toast.makeText(this, "" + db2 + cursor, Toast.LENGTH_SHORT).show();

            }
        }
    }
}