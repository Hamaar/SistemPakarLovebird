package com.example.hilmi.sistempakar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.models.GejalaListModels;
import com.example.hilmi.sistempakar.models.PenyakitListModels;

import java.util.ArrayList;

/**
 * Created by User on 12/01/2020.
 */

public class PenyakitListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<PenyakitListModels> recordList;

    public PenyakitListAdapter(Context context, int layout, ArrayList<PenyakitListModels> recordList) {
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
        TextView txtKode, txtNama, txtSolusi;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        PenyakitListAdapter.ViewHolder holder = new PenyakitListAdapter.ViewHolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtKode = (TextView) row.findViewById(R.id.tv_kd_penyakit);
            holder.txtNama = (TextView) row.findViewById(R.id.tv_penyakit);
            holder.txtSolusi = (TextView) row.findViewById(R.id.tv_solusi);


            row.setTag(holder);
        }
        else {
            holder = (PenyakitListAdapter.ViewHolder)row.getTag();
        }

        PenyakitListModels model = recordList.get(i);

        holder.txtKode.setText(model.getKode_penyakit());
        holder.txtNama.setText(model.getNama_penyakit());
        holder.txtSolusi.setText(model.getSolusi());



        return row;
    }
}