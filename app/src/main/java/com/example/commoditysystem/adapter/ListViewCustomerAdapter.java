package com.example.commoditysystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.commoditysystem.R;
import com.example.commoditysystem.model.Customer;

import java.util.Collections;
import java.util.List;

public class ListViewCustomerAdapter extends BaseAdapter {
    private List<Customer> customerList;
    private View view;
    private Context context;

    public ListViewCustomerAdapter(List<Customer> customerList, Context context) {
        this.customerList = customerList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return customerList.size();
    }

    @Override
    public Object getItem(int position) {
        return customerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.customer_item,null);
        ImageView customer_avatar = view.findViewById(R.id.customer_avatar);
        TextView customer_name = view.findViewById(R.id.customer_name);
        TextView customer_phone = view.findViewById(R.id.customer_phone);
        TextView customer_address = view.findViewById(R.id.customer_address);
        customer_avatar.setImageResource(customerList.get(position).getAvatar());
        customer_name.append(customerList.get(position).getCustomer_name());
        customer_phone.append(customerList.get(position).getCustomer_phone());
        customer_address.append(customerList.get(position).getCustomer_address());
        return view;
    }
    public void addItem(int position, Customer customer) {
        customerList.add(position,customer);
        notifyDataSetChanged();
    }
    public void updateItem(int position,Customer customer){
        Collections.replaceAll(customerList,customerList.get(position),customer);
        notifyDataSetChanged();
    }
    public void removeItem(int position){
        customerList.remove(position);
        notifyDataSetChanged();
    }
}
