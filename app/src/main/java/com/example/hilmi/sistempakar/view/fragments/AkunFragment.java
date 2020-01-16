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
import com.example.hilmi.sistempakar.adapters.AkunListAdapter;
import com.example.hilmi.sistempakar.adapters.GejalaListAdapter;
import com.example.hilmi.sistempakar.adminpanel.ScreenUpdate;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.AkunListModels;
import com.example.hilmi.sistempakar.models.GejalaListModels;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment {


    ListView listview;
    ArrayList<AkunListModels> mList;
    AkunListAdapter mAdapter = null;
    Cursor cursor;
    SQLiteHelper sqLiteHelper;
    final String TAG = "AkunFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.custom_list_view_item, container, false);

        sqLiteHelper = new  SQLiteHelper(getActivity());
        listview = (ListView) v.findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new AkunListAdapter(getActivity(), R.layout.custom_list_akun, mList);
        listview.setAdapter(mAdapter);





        //get all data from sqlite
        cursor = this.sqLiteHelper.getData("SELECT * FROM User");
        mList.clear();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            String fullname = cursor.getString(3);
            //add to list
            mList.add(new AkunListModels(id, username, password, fullname));
        }
        mAdapter.notifyDataSetChanged();
        if (mList.size()==0){
            //if there is no record in table of database which means listview is empty
            Toast.makeText(getActivity(), "No record found...", Toast.LENGTH_SHORT).show();
        }

        final List<String> list = sqLiteHelper.getListUpdateAkun();
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //alert dialog to display options of update and delete
                final CharSequence[] items = {"Update", "Delete"};
                final String seletion = list.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            //update
                            Intent in_update = new Intent(getContext(), ScreenUpdate.class);
                            in_update.putExtra("update_akun", seletion );
                            startActivity(in_update);
                            Log.d(TAG, "update_akun: " + seletion);
                            Toast.makeText(getActivity(), "Opsi" +  seletion, Toast.LENGTH_SHORT).show();
                            //show update dialog
                        }
                        if (i==1){
                            //delete
                            SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                            db.execSQL("delete from User WHERE username = '" + seletion + "'");
                            Toast.makeText(getActivity(), "Berhasil dihapus" + seletion, Toast.LENGTH_SHORT).show();
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