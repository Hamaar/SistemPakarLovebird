package com.example.hilmi.sistempakar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.models.Penyakit;
import com.example.hilmi.sistempakar.models.Solusi;

import java.util.ArrayList;

/**
 * Created by User on 11/01/2020.
 */

public class GejalaAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Penyakit> recordList;
    private ArrayList<Solusi> recodListSolusi;

    public GejalaAdapter(Context context, int layout, ArrayList<Penyakit> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        //        ImageView imageView;
        TextView txtKode, txtNamaPenyakit, txtSolusi;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        GejalaAdapter.ViewHolder holder = new GejalaAdapter.ViewHolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtKode = (TextView) row.findViewById(R.id.tv_kd_penyakit);
            holder.txtNamaPenyakit = (TextView) row.findViewById(R.id.tv_penyakit);
            holder.txtSolusi = (TextView) row.findViewById(R.id.tv_solusi);

            row.setTag(holder);
        }
        else {
            holder = (GejalaAdapter.ViewHolder)row.getTag();
        }

        Penyakit model = recordList.get(i);
        Solusi modelSolusi = recodListSolusi.get(i);

        holder.txtKode.setText(model.getKode_penyakit());
        holder.txtNamaPenyakit.setText(model.getNama_penyakit());
        holder.txtSolusi.setText(modelSolusi.getNama_solusi());



        return row;
    }
}