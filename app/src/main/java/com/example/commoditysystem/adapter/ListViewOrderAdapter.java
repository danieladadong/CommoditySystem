package com.example.commoditysystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.commoditysystem.R;
import com.example.commoditysystem.model.Order;

import java.util.Collections;
import java.util.List;

public class ListViewOrderAdapter extends BaseAdapter {
    private List<Order> orderList;
    private View view;
    private Context context;

    public ListViewOrderAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.order_item,null);
        ImageView order_avatar = view.findViewById(R.id.order_avatar);
        TextView order_commodity_name = view.findViewById(R.id.order_commodity_name);
        TextView order_commodity_count = view.findViewById(R.id.order_commodity_count);
        TextView order_price = view.findViewById(R.id.order_price);
        TextView order_commodity_money = view.findViewById(R.id.order_commodity_money);
        TextView order_customer_name = view.findViewById(R.id.order_customer_name);
        order_avatar.setImageResource(orderList.get(position).getOrder_avatar());
        order_commodity_name.append(orderList.get(position).getOrder_commodity());
        order_commodity_count.append(orderList.get(position).getOrder_count());
        order_price.append(orderList.get(position).getOrder_price());
        order_commodity_money.append(orderList.get(position).getOrder_money());
        order_customer_name.append(orderList.get(position).getOrder_customer());
        return view;
    }

    public void addItem(int position, Order order) {
        orderList.add(position,order);
        notifyDataSetChanged();
    }
}
