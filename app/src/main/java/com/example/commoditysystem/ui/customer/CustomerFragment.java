package com.example.commoditysystem.ui.customer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.commoditysystem.R;
import com.example.commoditysystem.activity.InfoActivity;
import com.example.commoditysystem.adapter.ListViewCustomerAdapter;
import com.example.commoditysystem.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerFragment extends Fragment {
    static List<Customer> customerList;
    private View view;
    private ListView listView;
    private Customer customer = new Customer();
    private int positions;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment customerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerFragment newInstance(String param1, String param2) {
        CustomerFragment fragment = new CustomerFragment();
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
        customerList = new ArrayList<Customer>();
        for (int i=0;i<10;i++){
            Customer customer = new Customer(R.mipmap.ic_launcher,"测试","测试","测试","测试","测试");
            customerList.add(customer);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_customer, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        listView = view.findViewById(R.id.customer_list);
        LayoutInflater inflater = getLayoutInflater();
        ListViewCustomerAdapter customerAdapter = new ListViewCustomerAdapter(customerList,getContext());
        listView.setAdapter(customerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positions = position;
                Intent intent = new Intent(view.getContext(), InfoActivity.class);
                Customer customer = (Customer) parent.getAdapter().getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("tag","customer");
                bundle.putString("name","客户名称：");
                bundle.putString("phone","电话：");
                bundle.putString("address","地址：");
                bundle.putString("time","上次拿货时间：");
                bundle.putString("date","创建时间：");
                bundle.putString("c_name",customer.getCustomer_name());
                bundle.putString("c_phone",customer.getCustomer_phone());
                bundle.putString("c_address",customer.getCustomer_address());
                bundle.putString("c_time",customer.getThe_last_time());
                bundle.putString("c_date",customer.getCustomer_create_date());
                intent.putExtras(bundle);
                startActivityForResult(intent,22,bundle);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu menu = new PopupMenu(view.getContext(),view);
                menu.getMenuInflater().inflate(R.menu.removeitem_menu,menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle()=="修改信息"){
                        }else{
                            AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                            dialog.setIcon(R.mipmap.ic_launcher_round);
                            dialog.setTitle("提示！");
                            dialog.setMessage("是否删除这条信息？");
                            dialog.setCancelable(false);    //设置是否可以通过点击对话框外区域或者返回按键关闭对话框
                            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    customerAdapter.removeItem(position);
                                    Toast.makeText(view.getContext(), "删除成功！", Toast.LENGTH_SHORT).show();
                                }
                            });
                            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(view.getContext(), "取消删除", Toast.LENGTH_SHORT).show();
                                }
                            });
                            dialog.show();
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ListViewCustomerAdapter adapter = new ListViewCustomerAdapter(customerList,getContext());
        switch (requestCode){
            case 2:
                if (resultCode== Activity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    customer.setAvatar(R.mipmap.ic_launcher);
                    customer.setCustomer_name(bundle.getString("customername"));
                    customer.setCustomer_phone(bundle.getString("customerphone"));
                    customer.setCustomer_address(bundle.getString("customeraddress"));
                    customer.setThe_last_time(bundle.getString("customerlast"));
                    customer.setCustomer_create_date(bundle.getString("customercreatedate"));
                    adapter.addItem(customerList.size(),customer);
                }
                break;
            case 22:
                if (resultCode==Activity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    customer.setAvatar(R.mipmap.ic_launcher);
                    customer.setCustomer_name(bundle.getString("customername"));
                    customer.setCustomer_phone(bundle.getString("customerphone"));
                    customer.setCustomer_address(bundle.getString("customeraddress"));
                    customer.setThe_last_time(bundle.getString("customerlast"));
                    customer.setCustomer_create_date(bundle.getString("customercreatedate"));
                    adapter.updateItem(positions,customer);
                }
        }
    }
}