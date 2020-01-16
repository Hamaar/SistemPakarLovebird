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
import com.example.hilmi.sistempakar.adapters.KeputusanListAdapter;
import com.example.hilmi.sistempakar.adminpanel.ScreenAdminDetil;
import com.example.hilmi.sistempakar.adminpanel.ScreenUpdate;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.GejalaListModels;
import com.example.hilmi.sistempakar.models.KeputusanListModels;

import java.util.ArrayList;
import java.util.List;

import static com.example.hilmi.sistempakar.database.SQLiteHelper.TABLE_GEJALA;
import static com.example.hilmi.sistempakar.database.SQLiteHelper.TABLE_KEPUTUSAN;
import static com.example.hilmi.sistempakar.database.SQLiteHelper.sqLiteDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeputusanFragment extends Fragment {



    ListView listview;
    String arrData[][];
    ArrayList<KeputusanListModels> mList;
    KeputusanListAdapter mAdapter = null;
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
        mAdapter = new KeputusanListAdapter(getActivity(), R.layout.custom_list_keputusan, mList);
        listview.setAdapter(mAdapter);



        //get all data from sqlite
        cursor = this.sqLiteHelper.getData("SELECT * FROM Keputusan");
        while (cursor.moveToNext()){
            String strID = cursor.getString(0);
            String kode_keputusan = cursor.getString(1);
            String kode_gejala = cursor.getString(2);

            //add to list
            mList.add(new KeputusanListModels( strID, kode_keputusan, kode_gejala));
        }
        mAdapter.notifyDataSetChanged();


        final List<String> list = sqLiteHelper.getListUpdateKeputusan();
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //alert dialog to display options of update and delete
                final CharSequence[] items = {"Update", "Delete"};
                final String seletion = list.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                dialog.setTitle(R.string.opsi);
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            Intent in_update = new Intent(getContext(), ScreenUpdate.class);
                            in_update.putExtra("update_keputusan", seletion );
                            startActivity(in_update);
                            Log.d(TAG, "update_keputusan: " + seletion);
                            Toast.makeText(getActivity(), "Opsi" +  seletion, Toast.LENGTH_SHORT).show();

                        }
                        if (i==1){
                            SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                            db.execSQL("delete from Keputusan WHERE id = '" + seletion + "'");
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