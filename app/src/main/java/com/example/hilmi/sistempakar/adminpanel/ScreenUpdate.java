package com.example.hilmi.sistempakar.adminpanel;

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
import android.widget.Toast;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.database.SQLiteHelper;

public class ScreenUpdate extends AppCompatActivity {

    TextInputLayout hintKode, hintNama, hintYa;
    TextInputEditText edtKode,edtNama, edtYA, edtTDK;
    Button btnUpdateGejala, btnUpdatePenyakit, btnUpdateKeputusan;
    ProgressDialog pd;



    String TAG = "";

    protected Cursor cursor;
    private SQLiteDatabase sqlGEJALA, sqlPENYAKIT, sqlKEPUTUSAN, sqlPENYAKITSOLUSI, sqlSOLUSI;
    SQLiteHelper sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_update);
        setTitle("Update Data");
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

                return;
            }
        }
        //GEJALA
        else if(getIntent().hasExtra("update_gejala")){
            setTitle("Update Data Gejala");
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
                return;
            }
        }

        else if (getIntent().hasExtra("update_keputusan")){
            setTitle("Update Data Keputusan");
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
            setTitle("Update Data Admin");
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
            setTitle("Update Data Akun");
            sqlSOLUSI = sqLiteHelper.getWritableDatabase();
            cursor = sqlSOLUSI.rawQuery("SELECT * FROM User WHERE id= '"
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







        btnUpdateGejala = (Button) findViewById(R.id.update_gejala);
        btnUpdateGejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getIntent().hasExtra("update_penyakit")){
                    sqlPENYAKIT = sqLiteHelper.getWritableDatabase();
                    sqlPENYAKIT.execSQL("UPDATE Penyakit set nama_penyakit='" + edtNama.getText().toString()
                            + "', nama_penyakit='" + edtNama.getText().toString()
                            + "', cara='" + edtYA.getText().toString()
                            + "' WHERE kode_penyakit='" + edtKode.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(), "Data Berhasil Di Update" + sqlPENYAKIT, Toast.LENGTH_SHORT).show();
                    System.out.print("update:" + edtKode + edtNama);
                }
                else if (getIntent().hasExtra("update_gejala")){
                    //update table gejala
                    SQLiteDatabase dbGejala = sqLiteHelper.getWritableDatabase();
                    dbGejala.execSQL("UPDATE gejala set kode_gejala='" + edtKode.getText().toString()
                            + "', nama_gejala='" + edtNama.getText().toString()
                            + "' WHERE kode_gejala='" + edtKode.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(), "Data Berhasil Di Update" + sqlGEJALA, Toast.LENGTH_SHORT).show();
                    System.out.print("update:" + edtKode + edtNama);
                }
                else if (getIntent().hasExtra("update_keputusan")){
                    SQLiteDatabase  sqlKEPUTUSAN = sqLiteHelper.getWritableDatabase();
                    sqlKEPUTUSAN.execSQL("UPDATE Keputusan set kode_penyakit='" + edtNama.getText().toString()
                            + "', kode_gejala='" + edtYA.getText().toString()
                            + "' WHERE id='" + edtKode.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(), "Data Berhasil Di Update" + sqlKEPUTUSAN, Toast.LENGTH_SHORT).show();
                    System.out.print("update:" + edtKode + edtNama);
                }
//                else if (getIntent().hasExtra("kode_penyakit_solusi")){
//                    SQLiteDatabase sqlPENYAKITSOLUSI = sqLiteHelper.getWritableDatabase();
//                    sqlPENYAKITSOLUSI.execSQL("UPDATE penyakit_solusi set kode_solusi='" + edtNama.getText().toString()
//                            + "', kode_penyakit_solusi='" + edtKode.getText().toString()
//                            + "' WHERE kode_penyakit_solusi='" + edtKode.getText().toString() + "'");
//                    Toast.makeText(getApplicationContext(), "Data Berhasil Di Update" + sqlPENYAKITSOLUSI, Toast.LENGTH_SHORT).show();
//                    System.out.print("update:" + sqlPENYAKITSOLUSI + edtKode + edtNama);
//                }
                else if(getIntent().hasExtra("update_solusi")){
                    SQLiteDatabase sqlSOLUSI = sqLiteHelper.getWritableDatabase();
                    sqlSOLUSI.execSQL("UPDATE Solusi set solusi='" + edtNama.getText().toString()
                            + "', solusi='" + edtNama.getText().toString()
                            + "' WHERE kode_solusi='" + edtKode.getText().toString() + "'");
                    System.out.print("update:" + sqlSOLUSI + sqlSOLUSI + edtKode + edtNama);
                    Toast.makeText(getApplicationContext(), "Data Berhasil Di Update" + sqlSOLUSI, Toast.LENGTH_SHORT).show();
                    sqLiteHelper.close();
                }
                else if(getIntent().hasExtra("update_akun")){
                    SQLiteDatabase sqlUpdatAkun = sqLiteHelper.getWritableDatabase();
                    sqlUpdatAkun.execSQL("UPDATE User set password='" + edtNama.getText().toString()
                            + "', password='" + edtNama.getText().toString()
                            +"', username='" + edtKode.getText().toString()
                            +"', fullname='" + edtYA.getText().toString()
                            + "' WHERE username='" + edtKode.getText().toString() + "'");
                    System.out.print("update:" + sqlUpdatAkun + sqLiteHelper + edtKode + edtNama + edtYA);

                    Toast.makeText(getApplicationContext(), "Data Berhasil Di Update" + sqlUpdatAkun, Toast.LENGTH_SHORT).show();
                    sqLiteHelper.close();
                }
            }
        });
    }




}
