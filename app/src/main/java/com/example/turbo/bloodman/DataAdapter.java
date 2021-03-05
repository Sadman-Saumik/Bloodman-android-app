package com.example.turbo.bloodman;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends ArrayAdapter {
    List list=new ArrayList();
    public DataAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler
    {
        TextView NAME,PHONE;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutHandler layoutHandler;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler=new LayoutHandler();
            layoutHandler.NAME=(TextView)row.findViewById(R.id.TextName);
            layoutHandler.PHONE=(TextView)row.findViewById(R.id.TextPhone);
            row.setTag(layoutHandler);

        }
        else
        {
            layoutHandler=(LayoutHandler)row.getTag();
        }

        DonorListData donorListData=(DonorListData)this.getItem(position);
        layoutHandler.NAME.setText(donorListData.getName());
        layoutHandler.PHONE.setText(donorListData.getPhone());

        return row;
    }
}
