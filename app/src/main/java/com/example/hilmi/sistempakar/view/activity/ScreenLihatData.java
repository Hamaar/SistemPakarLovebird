package com.example.hilmi.sistempakar.view.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hilmi.sistempakar.ScreenLogin;
import com.example.hilmi.sistempakar.adapters.GejalaListAdapter;
import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.adapters.PenyakitListAdapter;
import com.example.hilmi.sistempakar.adminpanel.ScreenAdmin;
import com.example.hilmi.sistempakar.adminpanel.ScreenUpdate;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.GejalaListModels;
import com.example.hilmi.sistempakar.models.PenyakitListModels;

import java.util.ArrayList;
import java.util.List;

public class ScreenLihatData extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    protected Cursor cursorgejala, cursorpenyakit ;

    String[] daftar_gejala;
    String[] daftar_penyakit;




    ListView listview;
    Spinner spinner;


    //penyakit custom
    ArrayList<GejalaListModels> mListGejala;
    ArrayList<PenyakitListModels> mListPenyakit;
    GejalaListAdapter mAdapterGejala = null;
    PenyakitListAdapter mAdapterPenyakit = null;

    final String TAG = "ScreenLihatData";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_lihat_data);
        setTitle("Lihat Data Gejala Dan Penyakit");

        spinner = (Spinner) findViewById(R.id.spinner);
        listview = (ListView) findViewById(R.id.listView);
        sqLiteHelper = new SQLiteHelper(this);


//----------Custom ListView Gejala------------------
        mListGejala = new ArrayList<>();
        mAdapterGejala = new GejalaListAdapter(getApplicationContext(), R.layout.custom_list_gejala, mListGejala);
        listview.setAdapter(mAdapterGejala);
        //get all data from sqlite
        cursorgejala = this.sqLiteHelper.getData("SELECT * FROM Gejala");
        mListGejala.clear();
        while (cursorgejala.moveToNext()){
            String kode_gejala = cursorgejala.getString(0);
            String nama_gejala = cursorgejala.getString(1);
            //add to list
            mListGejala.add(new GejalaListModels(kode_gejala, nama_gejala));
        }
        mAdapterGejala.notifyDataSetChanged();
//--------akhir-----------------------------------



//----------Custom ListView Penyakit------------------
        mListPenyakit = new ArrayList<>();
        mAdapterPenyakit = new PenyakitListAdapter(getApplicationContext(), R.layout.custom_list_penyakit, mListPenyakit);
        listview.setAdapter(mAdapterPenyakit);
        //get all data from sqlite
        cursorpenyakit = this.sqLiteHelper.getData("SELECT * FROM Penyakit");
        mListPenyakit.clear();
        while (cursorpenyakit.moveToNext()){
            String kode_penyakit = cursorpenyakit.getString(0);
            String nama_penyakit = cursorpenyakit.getString(1);
            String solusi = cursorpenyakit.getString(2);
            //add to list
            mListPenyakit.add(new PenyakitListModels(kode_penyakit, nama_penyakit, solusi));
        }
        mAdapterPenyakit.notifyDataSetChanged();
//--------akhir-----------------------------------



//===================================================================================
//listview klik gejala dan penyakit
//        final List<String> list = sqLiteHelper.getListUpdateGejala();
//        final List<String> listPenyakit = sqLiteHelper.getListUpdatePenyakit();
//        listview.setSelector(R.color.blue);
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
//                final String seletionGejala = list.get(arg2);
//                final String selectionPenyakit = listPenyakit.get(arg2);
//                int pos = spinner.getSelectedItemPosition();
//
//               if (cursorgejala == cursorgejala) {
//                 Toast.makeText(getApplicationContext(), "Opsi" +  seletionGejala, Toast.LENGTH_SHORT).show();
//
//               }
//                if (pos == 0 ) {
//                    //update
////                    Intent in_update = new Intent(getApplicationContext(), ScreenLihatDataDetil.class);
////                    in_update.putExtra("update_gejala", seletionGejala );
////                    startActivity(in_update);
////                    Log.d(TAG, "gejaala: " + seletionGejala);
////                    Toast.makeText(getApplicationContext(), "Opsi" +  seletionGejala, Toast.LENGTH_SHORT).show();
//                }
//
//                if(pos == 1) {
////                    //view penyakit
//                    Intent in_update = new Intent(getApplicationContext(), ScreenLihatDataDetil.class);
//                    in_update.putExtra("update_penyakit", selectionPenyakit );
//                    startActivity(in_update);
//                    Log.d(TAG, "penyakit: " + selectionPenyakit);
//                    Toast.makeText(getApplicationContext(), "Opsi" +  selectionPenyakit, Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//            }
//        });



        final List<String> listSpiner = new ArrayList<>();
        listSpiner.add("GEJALA");
        listSpiner.add("PENYAKIT");
        final ArrayAdapter<String> adapterSiner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listSpiner);
        adapterSiner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSiner);
        adapterSiner.notifyDataSetChanged();
        //spinner select
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(final AdapterView<?> parent, View view, int pos, long id) {
                        Object item = parent.getItemAtPosition(pos);
                        System.out.println(item.toString());     //prints the text in spinner item.
                        if (item.equals("GEJALA")){
                            listview.setAdapter(mAdapterGejala);
                            mAdapterGejala.notifyDataSetChanged();
                        }
                        else{
                            listview.setAdapter(mAdapterPenyakit);
                            mAdapterPenyakit.notifyDataSetChanged();
                        }
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
    }



    //OPTIONS MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_login) {

            Intent i = new Intent(getApplicationContext(), ScreenLogin.class);
//            Intent i = new Intent(getApplicationContext(), ScreenAdmin.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


