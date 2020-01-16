package com.example.hilmi.sistempakar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hilmi.sistempakar.adapters.GejalaListAdapter;
import com.example.hilmi.sistempakar.adapters.TestListAdapter;
import com.example.hilmi.sistempakar.adminpanel.ScreenUpdate;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.GejalaListModels;
import com.example.hilmi.sistempakar.models.TestListModels;

import java.util.ArrayList;
import java.util.List;

public class Test extends AppCompatActivity {

    ListView listview;
    String arrData[][];
    ArrayList<TestListModels> mList;
    TestListAdapter mAdapter = null;
    Cursor cursor;
    SQLiteHelper sqLiteHelper;

    final String TAG ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        sqLiteHelper = new  SQLiteHelper(getApplicationContext());
        listview = (ListView) findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new TestListAdapter(getApplicationContext(), R.layout.custom_list_gejala, mList);
        listview.setAdapter(mAdapter);



        //get all data from sqlite
        cursor = this.sqLiteHelper.getData("SELECT * FROM Gejala");
        while (cursor.moveToNext()){
            String kode_gejala = cursor.getString(0);
            String nama_gejala = cursor.getString(1);
            //add to list
            mList.add(new TestListModels(kode_gejala, nama_gejala));
        }
        mAdapter.notifyDataSetChanged();


        final List<String> list = sqLiteHelper.getListGejala();
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //alert dialog to display options of update and delete
                final CharSequence[] items = {"Update", "Delete"};
                final String seletion = list.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            Intent in_update = new Intent(getApplicationContext(), ScreenUpdate.class);
                            in_update.putExtra("update_gejala", seletion );
                            startActivity(in_update);
                            Log.d(TAG, "penyakit: " + seletion);
                            Toast.makeText(getApplicationContext(), "Opsi" +  seletion, Toast.LENGTH_SHORT).show();

                        }
                        if (i==1){
                            SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                            db.execSQL("delete from Gejala WHERE nama_gejala = '" + seletion + "'");
                            Toast.makeText(getApplicationContext(), "Berhasil dihapus" + seletion, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return true;
            }
        });
    }
}