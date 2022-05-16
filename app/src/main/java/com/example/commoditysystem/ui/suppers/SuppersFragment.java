package com.example.commoditysystem.ui.suppers;

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
import com.example.commoditysystem.adapter.ListViewSuppersAdapter;
import com.example.commoditysystem.model.Suppers;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuppersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuppersFragment extends Fragment {
    static List<Suppers> suppersList;
    private ListView listView;
    private View view;
    private Suppers suppers = new Suppers();
    static private int positions;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SuppersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuppersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuppersFragment newInstance(String param1, String param2) {
        SuppersFragment fragment = new SuppersFragment();
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
        suppersList = new ArrayList<Suppers>();
        for (int i=0;i<10;i++){
            Suppers suppers = new Suppers(R.mipmap.ic_launcher,"测试","测试","测试","测试","测试");
            suppersList.add(suppers);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_suppers, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        listView = view.findViewById(R.id.suppers_list);
        LayoutInflater inflater = getLayoutInflater();
        ListViewSuppersAdapter suppersAdapter = new ListViewSuppersAdapter(suppersList,getContext());
        listView.setAdapter(suppersAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positions = position;
                Intent intent = new Intent(parent.getContext(),InfoActivity.class);
                Suppers suppers = (Suppers) parent.getAdapter().getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("tag","suppers");
                bundle.putString("name","客户名称：");
                bundle.putString("phone","电话：");
                bundle.putString("address","地址：");
                bundle.putString("time","上次拿货时间：");
                bundle.putString("date","创建时间：");
                bundle.putString("s_name",suppers.getSuppers_name());
                bundle.putString("s_phone",suppers.getSuppers_phone());
                bundle.putString("s_address",suppers.getSuppers_address());
                bundle.putString("s_time",suppers.getLast_time());
                bundle.putString("s_date",suppers.getCreate_date());
                intent.putExtras(bundle);
                startActivityForResult(intent,44,bundle);
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
                                    suppersAdapter.removeItem(position);
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
        ListViewSuppersAdapter adapter = new ListViewSuppersAdapter(suppersList,getContext());
        switch (requestCode){
            case 4:
                if (resultCode== Activity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    suppers.setAvatar(R.mipmap.ic_launcher);
                    suppers.setSuppers_name(bundle.getString("suppersname"));
                    suppers.setSuppers_phone(bundle.getString("suppersphone"));
                    suppers.setSuppers_address(bundle.getString("suppersaddress"));
                    suppers.setLast_time(bundle.getString("supperslast"));
                    suppers.setCreate_date(bundle.getString("supperscreatedate"));
                    adapter.addItem(suppersList.size(),suppers);
                }
                break;
            case 44:
                if (resultCode==Activity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    suppers.setAvatar(R.mipmap.ic_launcher);
                    suppers.setSuppers_name(bundle.getString("suppersname"));
                    suppers.setSuppers_phone(bundle.getString("suppersphone"));
                    suppers.setSuppers_address(bundle.getString("suppersaddress"));
                    suppers.setLast_time(bundle.getString("supperslast"));
                    suppers.setCreate_date(bundle.getString("supperscreatedate"));
                    adapter.updateItem(positions,suppers);
                }
                break;
        }
    }
}