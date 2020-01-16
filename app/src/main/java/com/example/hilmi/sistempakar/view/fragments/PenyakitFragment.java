package com.example.hilmi.sistempakar.view.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.adapters.PenyakitListAdapter;
import com.example.hilmi.sistempakar.adminpanel.ScreenUpdate;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.PenyakitListModels;

import java.util.ArrayList;
import java.util.List;

public class PenyakitFragment extends Fragment {

    ListView listview;
    String arrData[][];
    ArrayList<PenyakitListModels> mList;
    PenyakitListAdapter mAdapter = null;
    Cursor cursor;
    SQLiteHelper sqLiteHelper;

    final String TAG ="";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.custom_list_view_item, container, false);

        sqLiteHelper = new  SQLiteHelper(getActivity());
        listview = (ListView) v.findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new PenyakitListAdapter(getActivity(), R.layout.custom_list_penyakit, mList);
        listview.setAdapter(mAdapter);




        //get all data from sqlite
        cursor = this.sqLiteHelper.getData("SELECT * FROM Penyakit");
        mList.clear();
        while (cursor.moveToNext()){
            String kode_penyakit = cursor.getString(0);
            String nama_penyakit = cursor.getString(1);
            String solusi = cursor.getString(2);
            //add to list
            mList.add(new PenyakitListModels(kode_penyakit, nama_penyakit, solusi));
        }
        mAdapter.notifyDataSetChanged();
        if (mList.size()==0){
            //if there is no record in table of database which means listview is empty
            Toast.makeText(getActivity(), "No record found...", Toast.LENGTH_SHORT).show();
        }


        final List<String> list = sqLiteHelper.getListUpdatePenyakit();
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //alert dialog to display options of update and delete
                final CharSequence[] items = {"Update", "Delete"};
                final String selection = list.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            Intent in_update = new Intent(getContext(), ScreenUpdate.class);
                            in_update.putExtra("update_penyakit", selection);
                            startActivity(in_update);
                            Log.d(TAG, "update_penyakit: " + list);
                            Toast.makeText(getActivity(), "Your selected : " + selection, Toast.LENGTH_SHORT).show();
                        }
                        if (i==1){
                            //delete
                            SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                            db.execSQL("delete from Penyakit WHERE kode_penyakit = '" + selection + "'");
                            Toast.makeText(getActivity(), "Berhasil dihapus" + selection, Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
        return v;
    }
}