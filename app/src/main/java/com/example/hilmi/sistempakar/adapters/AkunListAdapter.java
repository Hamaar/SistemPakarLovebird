package com.example.hilmi.sistempakar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.models.AkunListModels;
import com.example.hilmi.sistempakar.models.GejalaListModels;

import java.util.ArrayList;

/**
 * Created by User on 12/01/2020.
 */

public class AkunListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<AkunListModels> recordList;

    public AkunListAdapter(Context context, int layout, ArrayList<AkunListModels> recordList) {
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
        TextView ID,username, password, fullname;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        AkunListAdapter.ViewHolder holder = new AkunListAdapter.ViewHolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.ID = (TextView) row.findViewById(R.id.tv_custom_ID);
            holder.username = (TextView) row.findViewById(R.id.tv_custom_username);
            holder.password = (TextView) row.findViewById(R.id.tv_custom_password);
            holder.fullname = (TextView) row.findViewById(R.id.tv_custom_fullname);

            row.setTag(holder);
        }
        else {
            holder = (AkunListAdapter.ViewHolder)row.getTag();
        }

        AkunListModels model = recordList.get(i);

        holder.ID.setText(model.getID());
        holder.username.setText(model.getUsername());
        holder.password.setText(model.getPassword());
        holder.fullname.setText(model.getFullname());



        return row;
    }
}