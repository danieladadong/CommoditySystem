package com.example.commoditysystem.ui.commodity;

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
import com.example.commoditysystem.adapter.ListViewCommodityAdapter;
import com.example.commoditysystem.model.Commodity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommodityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommodityFragment extends Fragment {
    static List<Commodity> commodityList;
    private ListView listView;
    private View view;
    private Commodity commodity = new Commodity();
    static private int positions;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CommodityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment commodityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommodityFragment newInstance(String param1, String param2) {
        CommodityFragment fragment = new CommodityFragment();
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
        commodityList = new ArrayList<Commodity>();
        for (int i=0;i<10;i++){
            Commodity commodity = new Commodity(R.mipmap.ic_launcher,"测试","测试","测试","测试","测试");
            commodityList.add(commodity);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        listView = view.findViewById(R.id.commodity_list);
        ListViewCommodityAdapter commodityAdapter = new ListViewCommodityAdapter(commodityList,getContext());
        listView.setAdapter(commodityAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(parent.getContext(), InfoActivity.class);
                positions = position;
                Commodity commodity = (Commodity) parent.getAdapter().getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("tag","commodity");
                bundle.putString("name","商品名称：");
                bundle.putString("count","当前库存：");
                bundle.putString("suppers","供货商：");
                bundle.putString("price","价格：");
                bundle.putString("date","创建时间：");
                bundle.putString("c_name",commodity.getCommodity_name());
                bundle.putString("c_count",commodity.getCommodity_number());
                bundle.putString("c_suppers",commodity.getCommodity_suppers());
                bundle.putString("c_price",commodity.getCommodity_price());
                bundle.putString("c_date",commodity.getCreate_date());
                intent.putExtras(bundle);
                startActivityForResult(intent,11);
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
                                    commodityAdapter.removeItem(position);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_commodity, container, false);
        System.out.println("执行CreateView");
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ListViewCommodityAdapter adapter = new ListViewCommodityAdapter(commodityList,getContext());
        switch (requestCode){
            case 1:
                if(resultCode== Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    commodity.setAvatar(R.mipmap.ic_launcher);
                    commodity.setCommodity_name(bundle.getString("commodityname"));
                    commodity.setCommodity_number(bundle.getString("commoditynumber"));
                    commodity.setCommodity_price(bundle.getString("commodityprice"));
                    commodity.setCreate_date(bundle.getString("commoditycreatedate"));
                    commodity.setCommodity_suppers(bundle.getString("commoditysuppers"));
                    adapter.addItem(commodityList.size(),commodity);
                }
                break;
            case 11:
                if (resultCode==Activity.RESULT_OK){
                    System.out.println(data.toString());
                    Bundle bundle = data.getExtras();
                    System.out.println("onActivityForResult");
                    commodity.setAvatar(R.mipmap.ic_launcher);
                    commodity.setCommodity_name(bundle.getString("commodityname"));
                    commodity.setCommodity_number(bundle.getString("commoditynumber"));
                    commodity.setCommodity_price(bundle.getString("commodityprice"));
                    commodity.setCreate_date(bundle.getString("commoditycreatedate"));
                    commodity.setCommodity_suppers(bundle.getString("commoditysuppers"));
                    System.out.println(commodity.getCommodity_name());
                    adapter.updateItem(positions,commodity);
                }
                break;
        }
    }
}