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
import com.example.hilmi.sistempakar.adapters.GejalaListAdapter;
import com.example.hilmi.sistempakar.adminpanel.ScreenUpdate;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.GejalaListModels;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GejalaFragment extends Fragment {


    ListView listview;
    String arrData[][];
    ArrayList<GejalaListModels> mList;
    GejalaListAdapter mAdapter = null;
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
        mAdapter = new GejalaListAdapter(getActivity(), R.layout.custom_list_gejala, mList);
        listview.setAdapter(mAdapter);



        //get all data from sqlite
        cursor = this.sqLiteHelper.getData("SELECT * FROM Gejala");
        while (cursor.moveToNext()){
            String kode_gejala = cursor.getString(0);
            String nama_gejala = cursor.getString(1);
            //add to list
            mList.add(new GejalaListModels(kode_gejala, nama_gejala));
        }
        mAdapter.notifyDataSetChanged();


        final List<String> list = sqLiteHelper.getListUpdateGejala();
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
                            in_update.putExtra("update_gejala", seletion );
                            startActivity(in_update);
                            Log.d(TAG, "penyakit: " + seletion);
                            Toast.makeText(getActivity(), "Opsi" +  seletion, Toast.LENGTH_SHORT).show();
                        }
                        if(i==1){
                            //delete
                            SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                            db.execSQL("delete from Gejala WHERE kode_gejala = '" + seletion + "'");
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