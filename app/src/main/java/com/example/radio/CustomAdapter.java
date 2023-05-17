package com.example.radio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Radio> radios;
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, ArrayList<Radio> radios) {
        this.context = applicationContext;
        this.radios = radios;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() { return radios.size(); }

    @Override
    public Radio getItem(int i) {
        return radios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_listview, null);

        TextView radio = (TextView) view.findViewById(R.id.textView);
        TextView url = (TextView) view.findViewById(R.id.url);
        ImageView logo = (ImageView) view.findViewById(R.id.icon);

        radio.setText(radios.get(i).name);
        url.setText(radios.get(i).url);
        logo.setImageResource(radios.get(i).logo);

        return view;
    }
}