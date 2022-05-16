package com.example.commoditysystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.commoditysystem.R;
import com.example.commoditysystem.adapter.InnerListViewAdapter;
import com.example.commoditysystem.adapter.ListViewOrderAdapter;
import com.example.commoditysystem.model.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    private static InnerListViewAdapter adapter;
    private static List<Order> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        switch (bundle.getString("tag")){
            case "commodity":
                setContentView(R.layout.activity_addcommodity);
                this.getSupportActionBar().setTitle("新建商品");
                EditText commodityName = findViewById(R.id.commodityname);
                EditText commodityNumber = findViewById(R.id.commoditynumber);
                EditText commoditySuppers = findViewById(R.id.commoditysuppers);
                EditText commodityPrice = findViewById(R.id.commodityprice);
                EditText commodityDate = findViewById(R.id.commoditycreatedate);
                Button save_commodity = findViewById(R.id.save_commodity);
                save_commodity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bundle.putString("commodityname",commodityName.getText().toString());
                        bundle.putString("commoditynumber",commodityNumber.getText().toString());
                        bundle.putString("commoditysuppers",commoditySuppers.getText().toString());
                        bundle.putString("commodityprice",commodityPrice.getText().toString());
                        bundle.putString("commoditycreatedate",commodityDate.getText().toString());
                        intent.putExtras(bundle);
                        AddActivity.this.setResult(Activity.RESULT_OK,intent);
                        AddActivity.this.finish();
                    }
                });
                break;
            case "customer":
                setContentView(R.layout.activity_addcustomer);
                this.getSupportActionBar().setTitle("新建客户");
                EditText customerName = findViewById(R.id.customername);
                EditText customerPhone = findViewById(R.id.customerphone);
                EditText customerAddress = findViewById(R.id.customeraddress);
                EditText customerLast = findViewById(R.id.customerlast);
                EditText customerDate = findViewById(R.id.customercreatedate);
                Button save_customer = findViewById(R.id.save_customer);
                save_customer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bundle.putString("customername",customerName.getText().toString());
                        bundle.putString("customerphone",customerPhone.getText().toString());
                        bundle.putString("customeraddress",customerAddress.getText().toString());
                        bundle.putString("customerlast",customerLast.getText().toString());
                        bundle.putString("customercreatedate",customerDate.getText().toString());
                        intent.putExtras(bundle);
                        AddActivity.this.setResult(Activity.RESULT_OK,intent);
                        AddActivity.this.finish();
                    }
                });
                break;
            case "order":
                setContentView(R.layout.activity_addorder);
                this.getSupportActionBar().setTitle("新建订单");
                EditText orderCustomerName = findViewById(R.id.order_customername);
                ImageButton choose_customer_name = findViewById(R.id.choose_customer_name);
                ImageButton choose_commodity = findViewById(R.id.choose_commodity);
                ListView order_list = findViewById(R.id.order_list);
                TextView order_count = findViewById(R.id.order_count);
                TextView order_money = findViewById(R.id.order_money);
                TextView order_create_date = findViewById(R.id.order_create_date);
                ImageButton choose_date = findViewById(R.id.choose_date);
                Button save_order = findViewById(R.id.save_order);
                choose_customer_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String[] item = {"张三", "李四", "王五"};
                        final AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);//得到构造器
                        builder.setTitle("请选择客户名");
                        builder.setItems(item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                orderCustomerName.setText(item[which]);
                                dialog.dismiss();
                            }
                        });
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                });
                List<Order> datalist = new ArrayList<Order>();
                adapter = new InnerListViewAdapter(datalist,getApplicationContext());
                choose_commodity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String[] item = {"京都念慈庵", "六味地黄丸", "乌鸡白凤丸"};
                        final AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);//得到构造器
                        builder.setTitle("请选择商品");
                        builder.setItems(item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Order order = new Order();
                                Toast.makeText(AddActivity.this,item[which],Toast.LENGTH_SHORT).show();
                                order.setOrder_commodity(item[which]);
                                order.setOrder_count("1");
                                adapter.add(order);
                                order_list.setAdapter(adapter);
                                dialog.dismiss();
                            }
                        });
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                });
                order_count.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = 0;
                        for(int i=0;i<adapter.getCount();i++){
                            TextView textView = order_list.getAdapter().getView(i,null,null).findViewById(R.id.number);
                            int tmp = Integer.parseInt(textView.getText().toString());
                            count+=tmp;
                        }
                        order_count.setText(""+count);
                    }
                });
                order_money.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (order_count.getText().toString()==null||order_count.getText().toString()==""){
                            Toast.makeText(AddActivity.this,"请先点击数量", Toast.LENGTH_SHORT).show();
                        }else{
                            order_money.setText(""+100*Integer.parseInt(order_count.getText().toString()));
                        }
                    }
                });
                choose_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance(Locale.CHINA);
                        DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, 0, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                order_create_date.setText(year+"-"+month+"-"+dayOfMonth);
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                        dialog.show();
                    }
                });
                save_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("ordercustomername",orderCustomerName.getText().toString());
                        String commodity = "";
                        for(int i=0;i<adapter.getCount();i++){
                            TextView textView = order_list.getAdapter().getView(i,null,null).findViewById(R.id.add_order_commodity);
                            String tmp = textView.getText().toString();
                            commodity = tmp+"|";
                        }
                        bundle.putString("ordercommodity",commodity);
                        bundle.putString("ordercount",order_count.getText().toString());
                        bundle.putString("orderprice","100元");
                        bundle.putString("ordermoney",order_money.getText().toString());
                        bundle.putString("ordercreatedate",order_create_date.getText().toString());
                        intent.putExtras(bundle);
                        AddActivity.this.setResult(Activity.RESULT_OK,intent);
                        AddActivity.this.finish();
                    }
                });

                break;
            case "suppers":
                setContentView(R.layout.activity_addsuppers);
                this.getSupportActionBar().setTitle("新建供货商");
                EditText suppersName = findViewById(R.id.suppersname);
                EditText suppersPhone = findViewById(R.id.suppersphone);
                EditText suppersAddress = findViewById(R.id.suppersaddress);
                EditText suppersLast = findViewById(R.id.supperslast);
                EditText suppersDate = findViewById(R.id.supperscreatedate);
                Button save_suppers = findViewById(R.id.save_suppers);
                save_suppers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bundle.putString("suppersname",suppersName.getText().toString());
                        bundle.putString("suppersphone",suppersPhone.getText().toString());
                        bundle.putString("suppersaddress",suppersAddress.getText().toString());
                        bundle.putString("supperslast",suppersLast.getText().toString());
                        bundle.putString("supperscreatedate",suppersDate.getText().toString());
                        intent.putExtras(bundle);
                        AddActivity.this.setResult(Activity.RESULT_OK,intent);
                        AddActivity.this.finish();
                    }
                });
                break;
        }
    }
}