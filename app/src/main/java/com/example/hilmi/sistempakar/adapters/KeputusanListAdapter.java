package com.example.hilmi.sistempakar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.models.KeputusanListModels;
import com.example.hilmi.sistempakar.models.Penyakit;

import java.util.ArrayList;

/**
 * Created by User on 12/01/2020.
 */

public class KeputusanListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<KeputusanListModels> recordList;

    public KeputusanListAdapter(Context context, int layout, ArrayList<KeputusanListModels> recordList) {
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
        TextView  ID, txtKode, txtNamaPenyakit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        KeputusanListAdapter.ViewHolder holder = new KeputusanListAdapter.ViewHolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.ID = (TextView) row.findViewById(R.id.tv_custom_ID);
            holder.txtKode = (TextView) row.findViewById(R.id.tv_custom_kdkeputusan);
            holder.txtNamaPenyakit = (TextView) row.findViewById(R.id.tv_custom_keputusan);

            row.setTag(holder);
        }
        else {
            holder = (KeputusanListAdapter.ViewHolder)row.getTag();
        }

        KeputusanListModels model = recordList.get(i);

        holder.ID.setText(model.getID());
        holder.txtKode.setText(model.getKode_keputusan());
        holder.txtNamaPenyakit.setText(model.getKode_gejala());




        return row;
    }
}