package com.example.commoditysystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.commoditysystem.R;
import com.example.commoditysystem.model.Suppers;

import java.util.Collections;
import java.util.List;

public class ListViewSuppersAdapter extends BaseAdapter {
    private List<Suppers> suppersList;
    private View view;
    private Context context;

    public ListViewSuppersAdapter(List<Suppers> suppersList, Context context) {
        this.suppersList = suppersList;
        this.context = context;
    }
    public void addItem(int position,Suppers suppers){
        suppersList.add(position,suppers);
        notifyDataSetChanged();
    }
    public void updateItem(int position,Suppers suppers){
        Collections.replaceAll(suppersList,suppersList.get(position),suppers);
        notifyDataSetChanged();
    }
    public void removeItem(int position){
        suppersList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return suppersList.size();
    }

    @Override
    public Object getItem(int position) {
        return suppersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.suppers_item,null);
        ImageView suppers_avatar = view.findViewById(R.id.suppers_avatar);
        TextView suppers_name = view.findViewById(R.id.suppers_name);
        TextView suppers_phone = view.findViewById(R.id.suppers_phone);
        TextView suppers_address = view.findViewById(R.id.suppers_address);
        suppers_avatar.setImageResource(suppersList.get(position).getAvatar());
        suppers_name.append(suppersList.get(position).getSuppers_name());
        suppers_phone.append(suppersList.get(position).getSuppers_phone());
        suppers_address.append(suppersList.get(position).getSuppers_address());
        return view;
    }
}
