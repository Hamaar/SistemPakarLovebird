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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.adapters.GejalaListAdapter;
import com.example.hilmi.sistempakar.adapters.SolusiListAdapter;
import com.example.hilmi.sistempakar.adminpanel.ScreenAdminDetil;
import com.example.hilmi.sistempakar.adminpanel.ScreenUpdate;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.GejalaListModels;
import com.example.hilmi.sistempakar.models.SolusiListModels;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.hilmi.sistempakar.database.SQLiteHelper.TABLE_GEJALA;
import static com.example.hilmi.sistempakar.database.SQLiteHelper.TABLE_SOLUSI;
import static com.example.hilmi.sistempakar.database.SQLiteHelper.sqLiteDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class SolusiFragment extends Fragment {


    ListView listview;
    ArrayList<SolusiListModels> mList;
    SolusiListAdapter mAdapter = null;
    Cursor cursor;
    SQLiteHelper sqLiteHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.custom_list_view_item, container, false);

        sqLiteHelper = new SQLiteHelper(getActivity());
        listview = (ListView) v.findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new SolusiListAdapter(getActivity(), R.layout.custom_list_solusi, mList);
        listview.setAdapter(mAdapter);


        //get all data from sqlite
        cursor = this.sqLiteHelper.getData("SELECT * FROM Solusi");
        mList.clear();
        while (cursor.moveToNext()) {
            String kode_solusi = cursor.getString(0);
            String nama_solusi = cursor.getString(1);
            //add to list
            mList.add(new SolusiListModels(kode_solusi, nama_solusi));
        }
        mAdapter.notifyDataSetChanged();
        if (mList.size() == 0) {
            //if there is no record in table of database which means listview is empty
            Toast.makeText(getActivity(), "No record found...", Toast.LENGTH_SHORT).show();
        }


        final List<String> listsolusi = sqLiteHelper.getListUpdateSolusi();
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //alert dialog to display options of update and delete
                final String selection = listsolusi.get(position);
                final CharSequence[] items = {"Update", "Delete"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            //update
                            Intent in_update = new Intent(getContext(), ScreenUpdate.class);
                            in_update.putExtra("update_solusi", selection);
                            startActivity(in_update);
                            Log.d(TAG, "update_solusi: " + selection);
                            Toast.makeText(getActivity(), "Your selected : " + selection, Toast.LENGTH_SHORT).show();
                        }
                        if (i == 1) {

                            //delete
                            SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                            db.execSQL("delete from Solusi WHERE kode_solusi = '" + selection + "'");
                            Toast.makeText(getActivity(), "Berhasil dihapus" + selection, Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "hapus: " + db + selection + listview + sqLiteHelper);

//                            showDialogDelete(listsolusi.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
        return v;
    }



    public void showDialogDelete(final String kode_solusi){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(getActivity());
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure to delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    sqLiteHelper.deleteData(kode_solusi);
                    Toast.makeText(getActivity(), "Delete successfully", Toast.LENGTH_SHORT).show();




                }
                catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateRecordList();
            }
        });
        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateRecordList() {
        //get all data from sqlite
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM Solusi");
        mList.clear();
        while (cursor.moveToNext()){
            String kode_solusi = cursor.getString(0);
            String solusi = cursor.getString(1);

            mList.add(new SolusiListModels(kode_solusi, solusi));
        }
        mAdapter.notifyDataSetChanged();
    }
}
