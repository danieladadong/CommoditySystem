package com.example.commoditysystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.commoditysystem.R;
import com.example.commoditysystem.model.Commodity;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

public class ListViewCommodityAdapter extends BaseAdapter {
    List<Commodity> commodityList;
    private View view;
    private Context context;

    public ListViewCommodityAdapter(List<Commodity> commodityList, Context context) {
        this.commodityList = commodityList;
        this.context = context;
    }

    public void addItem(int position,Commodity commodity){
        commodityList.add(position,commodity);
        notifyDataSetChanged();
    }
    public void  updateItem(int position,Commodity commodity){
        Collections.replaceAll(commodityList,commodityList.get(position),commodity);
        notifyDataSetChanged();
    }
    public void removeItem(int position){
        commodityList.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return commodityList.size();
    }

    @Override
    public Object getItem(int position) {
        return commodityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.commodity_item,null);
        ImageView commodity_avatar = view.findViewById(R.id.commodit_avatar);
        TextView commodity_name = view.findViewById(R.id.commodit_name);
        TextView commodity_number = view.findViewById(R.id.commodit_number);
        TextView commodity_create_date = view.findViewById(R.id.commodit_create_date);
        TextView commodity_price = view.findViewById(R.id.commodit_price);
        commodity_avatar.setImageResource(commodityList.get(position).getAvatar());
        commodity_name.append(commodityList.get(position).getCommodity_name());
        commodity_number.append(commodityList.get(position).getCommodity_number());
        commodity_create_date.append(commodityList.get(position).getCreate_date());
        commodity_price.append(commodityList.get(position).getCommodity_price());
        return view;
    }
}
