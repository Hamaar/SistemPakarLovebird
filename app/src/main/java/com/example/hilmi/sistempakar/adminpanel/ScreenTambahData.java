package com.example.hilmi.sistempakar.adminpanel;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
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

public class ScreenTambahData extends AppCompatActivity {

    TextInputLayout hintKode, hintName, hint3;
    TextInputEditText edtKode, edtNama, edtYA;
    Button btnAddGejala, btnAddPenyakit, btnAddKeputusan, btnAddPenyakitSolusi, btnAddSolusi;

    SQLiteHelper sqLiteHelper;
    String TAG = "";

    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_tambah_data);


        setTitle("Tambah Data");

        sqLiteHelper = new SQLiteHelper(this);

        edtKode = (TextInputEditText) findViewById(R.id.edt_add_kode);
        edtNama = (TextInputEditText) findViewById(R.id.edt_add_nama);
        edtYA = (TextInputEditText) findViewById(R.id.edt_add_ya);
        hintKode = (TextInputLayout) findViewById(R.id.hint_add_kode);
        hintName = (TextInputLayout) findViewById(R.id.hint_add_nama);
        hint3 = (TextInputLayout) findViewById(R.id.hint_add_ya);
        btnAddData = (Button) findViewById(R.id.btn_add_DATA);


//====================FAB_ADD_DATA==================================================================
        if (getIntent().hasExtra("fab_add_gejala")) {
            setTitle("Add Gejala");
            hintKode.setHint("Kode Gejala");
            hintName.setHint("Nama Gejala");
            hint3.setVisibility(View.INVISIBLE);
            edtYA.setVisibility(View.INVISIBLE);
        } else if (getIntent().hasExtra("fab_add_penyakit")) {
            setTitle("Add Penyakit");
            hintKode.setHint("Kode Penyakit");
            hintName.setHint("Nama Penyakit");
            hint3.setHint("Solusi");
        } else if (getIntent().hasExtra("fab_add_solusi")) {
            setTitle("Add Solusi");
            hintKode.setHint("Kode Solusi");
            hintName.setHint("Solusi");
            hint3.setVisibility(View.INVISIBLE);
            edtYA.setVisibility(View.INVISIBLE);
        } else if (getIntent().hasExtra("fab_add_rulebase")) {
            setTitle("Add RuleBase");
            hintKode.setHint("Kode Penyakit ");
            hintName.setHint("Kode IF GEJALA");
            hint3.setVisibility(View.INVISIBLE);
            hint3.setHint("Jika Tidak");
        }


        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent().hasExtra("fab_add_gejala")) {
                    //write database
                    SQLiteDatabase sql_add_gejala = sqLiteHelper.getWritableDatabase();
                    sql_add_gejala.execSQL("INSERT INTO Gejala (kode_gejala, nama_gejala) VALUES('" +
                            edtKode.getText().toString() + "', '" +
                            edtNama.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();

                    Snackbar.make(view, "Berhasil", Snackbar.LENGTH_LONG).show();

                    Log.d(TAG, "btnTambahData: " + sql_add_gejala + edtKode + edtNama);
                    System.out.print("tambah data" + sql_add_gejala);
                } else if (getIntent().hasExtra("fab_add_penyakit")) {
                    SQLiteDatabase sql_addPenyakit = sqLiteHelper.getWritableDatabase();
                    sql_addPenyakit.execSQL("INSERT INTO Penyakit (kode_penyakit, nama_penyakit, cara) VALUES('" +
                            edtKode.getText().toString() + "', '" +
                            edtNama.getText().toString() + "', '" +
                            edtYA.getText().toString() + "')");

                    Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "addPenyakit: " + sql_addPenyakit + edtKode + edtNama);
                    System.out.print("tambah data" + sql_addPenyakit);
                } else if (getIntent().hasExtra("fab_add_solusi")) {
                    SQLiteDatabase sql_add_addSolusi = sqLiteHelper.getWritableDatabase();
                    sql_add_addSolusi.execSQL("INSERT INTO Solusi (kode_solusi, solusi) VALUES('" +
                            edtKode.getText().toString() + "', '" +
                            edtNama.getText().toString() + "')");
                    Log.d(TAG, "addSolusi: " + sql_add_addSolusi + edtKode + edtNama);
                    System.out.print("tambah data" + sql_add_addSolusi);
                    Toast.makeText(getApplicationContext(), "" + sql_add_addSolusi, Toast.LENGTH_SHORT).show();

                } else if (getIntent().hasExtra("fab_add_penyakit_solusi")) {
                    SQLiteDatabase sql_addPenyakitSolusit = sqLiteHelper.getWritableDatabase();
                    sql_addPenyakitSolusit.execSQL("INSERT INTO penyakit_solusi (kode_penyakit_solusi, kode_solusi) VALUES('" +
                            edtKode.getText().toString() + "', '" +
                            edtNama.getText().toString() + "')");
                    Log.d(TAG, "addPenyakitSolusi: " + sql_addPenyakitSolusit + edtKode + edtNama);
                    System.out.print("tambah data" + sql_addPenyakitSolusit);
                }
                //TABLE KEPUTUSAN
                else if (getIntent().hasExtra("fab_add_rulebase")) {
                    SQLiteDatabase sql_addKeputusan = sqLiteHelper.getWritableDatabase();
                    sql_addKeputusan.execSQL("INSERT INTO Keputusan (kode_penyakit, kode_gejala) VALUES('" +
                            edtKode.getText().toString() + "', '" +
                            edtNama.getText().toString() + "')");
                    Log.d(TAG, "addKeputusan: " + sql_addKeputusan + edtKode + edtNama);
                    System.out.print("tambah data" + sql_addKeputusan);
                }
            }
        });
    }
}


//    }
//
//    public void btnTambahGejala(){
//        String keyEmptyAddKode = edtKode.getText().toString();
//        String keyEmptyAddNama = edtNama.getText().toString();
//
//
//
//        //write database
//        SQLiteDatabase sql_add_gejala = sqLiteHelper.getWritableDatabase();
//        sql_add_gejala.execSQL("INSERT INTO gejala (kode_gejala, nama_gejala) VALUES('" +
//                edtKode.getText().toString() + "', '" +
//                edtNama.getText().toString() + "')");
//
//        if (keyEmptyAddKode.isEmpty()) {
//            Toast.makeText(getApplication(), "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
//        } else
//            Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
//
//        Log.d(TAG, "btnUpdateGejala: " + sql_add_gejala + edtKode + edtNama);
//        System.out.print("tambah data" + sql_add_gejala);
//    }
//
//
//    public void addPenyakit(){
//        String keyEmptyAddKode = edtKode.getText().toString();
//        String keyEmptyAddNama = edtNama.getText().toString();
//
//        //write database
//        SQLiteDatabase sql_addPenyakit = sqLiteHelper.getWritableDatabase();
//        sql_addPenyakit.execSQL("INSERT INTO penyakit (kode_penyakit, penyakit) VALUES('" +
//                edtKode.getText().toString() + "', '" +
//                edtNama.getText().toString() + "')");
//
//        if (keyEmptyAddKode.isEmpty()) {
//            Toast.makeText(getApplication(), "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
//        } else
//            Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
//
//        Log.d(TAG, "addPenyakit: " + sql_addPenyakit + edtKode + edtNama);
//        System.out.print("tambah data" + sql_addPenyakit);
//
//    }
//
//    public void addKeputusan(){
//        String keyEmptyAddKode = edtKode.getText().toString();
//        String keyEmptyAddNama = edtNama.getText().toString();
//
//        //write database
//        SQLiteDatabase sql_addKeputusan = sqLiteHelper.getWritableDatabase();
//        sql_addKeputusan.execSQL("INSERT INTO keputusan (kode_keputusan, ya, tidak) VALUES('" +
//                edtKode.getText().toString() + "', '" +
//                edtNama.getText().toString() + "', '" +
//                edtYA.getText().toString() + "')");
//
//        if (keyEmptyAddKode.isEmpty()) {
//            Toast.makeText(getApplication(), "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
//        } else
//            Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
//
//        Log.d(TAG, "addKeputusan: " + sql_addKeputusan + edtKode + edtNama);
//        System.out.print("tambah data" + sql_addKeputusan);
//    }
//
//
//    public void addPenyakitSolusi(){
//        String keyEmptyAddKode = edtKode.getText().toString();
//        String keyEmptyAddNama = edtNama.getText().toString();
//
//        //write database
//        SQLiteDatabase sql_addPenyakitSolusit = sqLiteHelper.getWritableDatabase();
//        sql_addPenyakitSolusit.execSQL("INSERT INTO penyakit_solusi (kode_penyakit_solusi, kode_solusi) VALUES('" +
//                edtKode.getText().toString() + "', '" +
//                edtNama.getText().toString() + "')");
//
//        if (keyEmptyAddKode.isEmpty()) {
//            Toast.makeText(getApplication(), "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
//        } else
//            Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
//
//        Log.d(TAG, "addPenyakitSolusi: " + sql_addPenyakitSolusit + edtKode + edtNama);
//        System.out.print("tambah data" + sql_addPenyakitSolusit);
//    }
//
//    public void addSolusi(){
//        String keyEmptyAddKode = edtKode.getText().toString();
//        String keyEmptyAddNama = edtNama.getText().toString();
//
//        //write database
//        SQLiteDatabase sql_add_addSolusi = sqLiteHelper.getWritableDatabase();
//        sql_add_addSolusi.execSQL("INSERT INTO solusi (kode_solusi, solusi) VALUES('" +
//                edtKode.getText().toString() + "', '" +
//                edtNama.getText().toString() + "')");
//
//        if (keyEmptyAddKode.isEmpty()) {
//            Toast.makeText(getApplication(), "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
//        } else
//            Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
//
//        Log.d(TAG, "addSolusi: " + sql_add_addSolusi + edtKode + edtNama);
//        System.out.print("tambah data" + sql_add_addSolusi);
//    }
//}
