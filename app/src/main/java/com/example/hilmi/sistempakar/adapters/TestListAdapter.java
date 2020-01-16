package com.example.hilmi.sistempakar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.models.GejalaListModels;
import com.example.hilmi.sistempakar.models.TestListModels;

import java.util.ArrayList;

/**
 * Created by User on 12/01/2020.
 */

public class TestListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<TestListModels> recordList;

    public TestListAdapter(Context context, int layout, ArrayList<TestListModels> recordList) {
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
        TextView txtKode, txtNamaGejala;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        TestListAdapter.ViewHolder holder = new TestListAdapter.ViewHolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtKode = (TextView) row.findViewById(R.id.tv_custom_kdgejala);
            holder.txtNamaGejala = (TextView) row.findViewById(R.id.tv_custom_nmgejala);

            row.setTag(holder);
        }
        else {
            holder = (TestListAdapter.ViewHolder)row.getTag();
        }

        TestListModels model = recordList.get(i);

        holder.txtKode.setText(model.getTestKode());
        holder.txtNamaGejala.setText(model.getTestGejala());


        return row;
    }
}