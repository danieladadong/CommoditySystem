package com.example.commoditysystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.commoditysystem.R;
import com.example.commoditysystem.model.Order;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InnerListViewAdapter extends BaseAdapter {
    private List<Order> orderList;
    private Context context;
    private View view;

    public InnerListViewAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    public void add(Order order){
        orderList.add(order);
        notifyDataSetChanged();
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
        view = LayoutInflater.from(context).inflate(R.layout.inner_commodity_item,null);
        TextView textView = view.findViewById(R.id.add_order_commodity);
        textView.setText(orderList.get(position).getOrder_commodity());
        Button btn_sub = view.findViewById(R.id.sub);
        TextView number = view.findViewById(R.id.number);
        number.setText(orderList.get(position).getOrder_count());
        Button btn_add = view.findViewById(R.id.add);
        btn_sub.setOnClickListener(v -> {
            int num = Integer.parseInt(number.getText().toString());
            if(num>1){
                num = num-1;
                orderList.get(position).setOrder_count(""+num);
                number.setText(""+num);

            }else {
                Toast.makeText(parent.getContext(),"数量不能低于1",Toast.LENGTH_SHORT).show();
            }
        });
        btn_add.setOnClickListener(v -> {
            int num = Integer.parseInt(number.getText().toString());
            num = num+1;
            orderList.get(position).setOrder_count(""+num);
            number.setText(""+num);
        });
        return view;
    }
}
