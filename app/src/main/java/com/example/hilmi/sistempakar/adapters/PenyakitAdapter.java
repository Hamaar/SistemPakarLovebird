package com.example.hilmi.sistempakar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hilmi.sistempakar.R;

/**
 * Created by User on 11/01/2020.
 */

public class PenyakitAdapter extends BaseAdapter {
    private Context ctx;
    private String[][] list;

    public PenyakitAdapter(Context context, String[][] li) {
        ctx = context;
        list = li;
    }

    @Override
    public int getCount() {
        return list.length;
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.custom_list_penyakit, null);

        //getImage


        // getShow Data listview
        TextView ttKode = (TextView) view.findViewById(R.id.tv_kd_penyakit);
        ttKode.setText(list[position][0].toString());
        TextView txtName = (TextView) view.findViewById(R.id.tv_penyakit);
        txtName.setText(list[position][1].toString());
//        TextView txtSolusi = (TextView) view.findViewById(R.id.tv_solusi);
//        txtSolusi.setText(list[position][2].toString());


        return view;
    }
}
