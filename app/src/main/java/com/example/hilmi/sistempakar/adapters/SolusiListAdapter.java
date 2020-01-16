package com.example.hilmi.sistempakar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.models.GejalaListModels;
import com.example.hilmi.sistempakar.models.SolusiListModels;

import java.util.ArrayList;

/**
 * Created by User on 12/01/2020.
 */

public class SolusiListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<SolusiListModels> recordList;

    public SolusiListAdapter(Context context, int layout, ArrayList<SolusiListModels> recordList) {
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
        TextView txtKodeSolusi, txtSolusi;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        SolusiListAdapter.ViewHolder holder = new SolusiListAdapter.ViewHolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtKodeSolusi = (TextView) row.findViewById(R.id.tv_custom_kdsolusi);
            holder.txtSolusi = (TextView) row.findViewById(R.id.tv_custom_solusi);

            row.setTag(holder);
        }
        else {
            holder = (SolusiListAdapter.ViewHolder)row.getTag();
        }

        SolusiListModels model = recordList.get(i);

        holder.txtKodeSolusi.setText(model.getKode_solusi());
        holder.txtSolusi.setText(model.getNama_solusi());


        return row;
    }
}