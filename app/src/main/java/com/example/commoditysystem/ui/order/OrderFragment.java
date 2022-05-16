package com.example.commoditysystem.ui.order;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.commoditysystem.R;
import com.example.commoditysystem.activity.InfoActivity;
import com.example.commoditysystem.adapter.ListViewOrderAdapter;
import com.example.commoditysystem.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {
    static List<Order> orderList;
    private View view;
    private ListView listView;
    private Order order = new Order();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        orderList = new ArrayList<Order>();
        for (int i=0;i<10;i++){
            Order order = new Order(R.mipmap.ic_launcher,"测试","测试","测试","测试","测试","测试");
            orderList.add(order);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        listView = view.findViewById(R.id.order_list);
        LayoutInflater inflater = getLayoutInflater();
        ListViewOrderAdapter orderAdapter = new ListViewOrderAdapter(orderList,getContext());
        listView.setAdapter(orderAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ListViewOrderAdapter adapter = new ListViewOrderAdapter(orderList,getContext());
        switch (requestCode){
            case 3:
                if (resultCode== Activity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    order.setOrder_avatar(R.mipmap.ic_launcher);
                    order.setOrder_customer(bundle.getString("ordercustomername"));
                    order.setOrder_commodity(bundle.getString("ordercommodity"));
                    order.setOrder_count(bundle.getString("ordercount"));
                    order.setOrder_price(bundle.getString("orderprice"));
                    order.setOrder_money(bundle.getString("ordermoney"));
                    order.setCreate_date(bundle.getString("ordercreatedate"));
                    adapter.addItem(orderList.size(),order);
                }
                break;
        }
    }
}