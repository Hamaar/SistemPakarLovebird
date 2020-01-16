package com.example.hilmi.sistempakar.view.activity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.database.SQLiteHelper;

public class ScreenLihatDataDetil extends AppCompatActivity {

    TextInputLayout hintKode, hintNama, hintYa;
    TextInputEditText edtKode,edtNama, edtYA, edtTDK;
    Button btnUpdateGejala, btnUpdatePenyakit, btnUpdateKeputusan;
    ProgressDialog pd;





    protected Cursor cursor;
    private SQLiteDatabase sqlGEJALA, sqlPENYAKIT, sqlKEPUTUSAN, sqlPENYAKITSOLUSI, sqlSOLUSI;
    SQLiteHelper sqLiteHelper;

    String TAG = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_lihat_data_detil);

        setTitle("Detail Data");
        pd = new ProgressDialog(this);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());


        edtKode = (TextInputEditText) findViewById(R.id.edt_update_kode);
        edtNama = (TextInputEditText) findViewById(R.id.edt_update_name);
        edtYA = (TextInputEditText) findViewById(R.id.edt_update_Ya);


        hintKode = (TextInputLayout) findViewById(R.id.hint_update_kode);
        hintNama = (TextInputLayout) findViewById(R.id.hint_update_name);
        hintYa = (TextInputLayout) findViewById(R.id.hint_update_ya);





        //PENYAKIT
        if(getIntent().hasExtra("update_penyakit")){
            sqlPENYAKIT = sqLiteHelper.getWritableDatabase();
            cursor = sqlPENYAKIT.rawQuery("SELECT * FROM Penyakit WHERE kode_penyakit= '"
                    + getIntent().getStringExtra("update_penyakit") + "'", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToPosition(0);
                hintKode.setHint("Kode Penyakit");
                hintNama.setHint("Nama Penyakit");
                edtKode.setText(cursor.getString(0).toString());
                edtNama.setText(cursor.getString(1).toString());
                edtYA.setText(cursor.getString(2).toString());

                Log.d(TAG, "sqLitePENYAKIT: " + sqlPENYAKIT + cursor);
                System.out.print(edtKode);
                System.out.print(edtNama);
            }
        }
        //GEJALA
        else if(getIntent().hasExtra("update_gejala")){
            sqlGEJALA = sqLiteHelper.getReadableDatabase();
            cursor = sqlGEJALA.rawQuery("SELECT * FROM Gejala WHERE kode_gejala = '"
                    + getIntent().getStringExtra("update_gejala") + "'", null);
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                pd.dismiss();
                cursor.moveToPosition(0);
                hintKode.setHint("Kode Gejala");
                hintNama.setHint("Nama Gejala");
                edtKode.setText(cursor.getString(0));
                edtNama.setText(cursor.getString(1));

                System.out.print(edtKode);
                System.out.print(edtNama);

                //disable
                edtYA.setVisibility(View.INVISIBLE);
                hintYa.setVisibility(View.INVISIBLE);
                Log.d(TAG, "onCreate: " + sqlGEJALA + sqLiteHelper);
                Log.e(TAG, "onCreate: " + sqlGEJALA );
            }
        }

        else if (getIntent().hasExtra("update_keputusan")){
            sqlKEPUTUSAN = sqLiteHelper.getReadableDatabase();
            cursor = sqlKEPUTUSAN.rawQuery("SELECT * FROM Keputusan WHERE id= '"
                    + getIntent().getStringExtra("update_keputusan") + "'", null);
//        cursor = form_lihat_gejala.rawQuery("SELECT kode_penyakit, nama_penyakit FROM tbl_penyakit INNER JOIN tbl_solusi ON kode_penyakit = kode_solusi ", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToPosition(0);

                //alias
                hintKode.setHint("Id");
                hintNama.setHint("Kode Penyakit");
                hintYa.setHint("Kode Gejala");

                edtKode.setText(cursor.getString(0).toString());
                edtNama.setText(cursor.getString(1).toString());
                edtYA.setText(cursor.getString(2).toString());

                Log.d(TAG, "penyakit: " + sqlKEPUTUSAN + cursor);
                System.out.print(edtKode);
                System.out.print(edtNama);
                System.out.print(edtYA);
            }
        }
//        //tbl kode penyakit solusi
//        else if(getIntent().hasExtra("kode_penyakit_solusi")){
//            sqlPENYAKITSOLUSI = dbHELPER.getReadableDatabase();
//            cursor = sqlPENYAKITSOLUSI.rawQuery("SELECT * FROM penyakit_solusi WHERE kode_solusi= '"
//                    + getIntent().getStringExtra("kode_penyakit_solusi") + "'", null);
//            cursor.moveToFirst();
//            if (cursor.getCount() > 0) {
//                cursor.moveToPosition(0);
//                hintKode.setHint("Kode Penyakit Solusi");
//                hintNama.setHint("Kode Solusi");
//                edtKode.setText(cursor.getString(0).toString());
//                edtNama.setText(cursor.getString(1).toString());
//
//                Log.d(TAG, "PenyakitSolusiFragment: " + sqlPENYAKITSOLUSI + cursor);
//                System.out.print(edtKode);
//                System.out.print(edtNama);
//
//                edtYA.setVisibility(View.INVISIBLE);
//            }
//        }
        else if(getIntent().hasExtra("update_solusi")){
            sqlSOLUSI = sqLiteHelper.getReadableDatabase();
            cursor = sqlSOLUSI.rawQuery("SELECT * FROM Solusi WHERE kode_solusi= '"
                    + getIntent().getStringExtra("update_solusi") + "'", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToPosition(0);
                hintKode.setHint("Kode Solusi");
                hintNama.setHint("Solusi");
                edtKode.setText(cursor.getString(0).toString());
                edtNama.setText(cursor.getString(1).toString());
                Log.d(TAG, "sqlSolusi: " + sqlSOLUSI + cursor);
                System.out.print(edtKode);
                System.out.print(edtNama);
                //disable
                hintYa.setVisibility(View.INVISIBLE);
                edtYA.setVisibility(View.INVISIBLE);
            }
        }
        else if(getIntent().hasExtra("update_akun")){
            sqlSOLUSI = sqLiteHelper.getReadableDatabase();
            cursor = sqlSOLUSI.rawQuery("SELECT * FROM User WHERE username= '"
                    + getIntent().getStringExtra("update_akun") + "'", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToPosition(0);
                hintKode.setHint("UserName");
                hintNama.setHint("Password");
                hintYa.setHint("FullName");
                edtKode.setFocusableInTouchMode(true);
                edtKode.setKeyListener(null);
                edtKode.setText(cursor.getString(0).toString());
                edtNama.setText(cursor.getString(1).toString());
                edtYA.setText(cursor.getString(2).toString());
                Log.d(TAG, "sqlSolusi: " + sqlSOLUSI + cursor);
                System.out.print(edtKode);
                System.out.print(edtNama);
            }
        }


    }
}
