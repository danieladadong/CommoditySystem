package com.example.commoditysystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.commoditysystem.R;

public class InfoActivity extends AppCompatActivity {
    private TextView text_1;
    private TextView text_2;
    private TextView text_3;
    private TextView text_4;
    private TextView text_5;
    private EditText info_1;
    private EditText info_2;
    private EditText info_3;
    private EditText info_4;
    private EditText info_5;
    private Button update_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        initView();
        switch (bundle.getString("tag")){
            case "commodity":
                this.getSupportActionBar().setTitle("商品详情");
                text_1.setText(bundle.getString("name"));
                text_2.setText(bundle.getString("count"));
                text_3.setText(bundle.getString("suppers"));
                text_4.setText(bundle.getString("price"));
                text_5.setText(bundle.getString("date"));
                info_1.setText(bundle.getString("c_name"));
                info_2.setText(bundle.getString("c_count"));
                info_3.setText(bundle.getString("c_suppers"));
                info_4.setText(bundle.getString("c_price"));
                info_5.setText(bundle.getString("c_date"));
                update_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent();
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("commodityname",info_1.getText().toString());
                        bundle1.putString("commoditynumber",info_2.getText().toString());
                        bundle1.putString("commodityprice",info_3.getText().toString());
                        bundle1.putString("commoditycreatedate",info_4.getText().toString());
                        bundle1.putString("commoditysuppers",info_5.getText().toString());
                        intent1.putExtras(bundle1);
                        InfoActivity.this.setResult(Activity.RESULT_OK,intent1);
                        InfoActivity.this.finish();
                    }
                });
                break;
            case "customer":
                this.getSupportActionBar().setTitle("客户详情");
                text_1.setText(bundle.getString("name"));
                text_2.setText(bundle.getString("phone"));
                text_3.setText(bundle.getString("address"));
                text_4.setText(bundle.getString("time"));
                text_5.setText(bundle.getString("date"));
                info_1.setText(bundle.getString("c_name"));
                info_2.setText(bundle.getString("c_phone"));
                info_3.setText(bundle.getString("c_address"));
                info_4.setText(bundle.getString("c_time"));
                info_5.setText(bundle.getString("c_date"));
                update_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent();
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("customername",info_1.getText().toString());
                        bundle1.putString("customerphone",info_2.getText().toString());
                        bundle1.putString("customeraddress",info_3.getText().toString());
                        bundle1.putString("customerlast",info_4.getText().toString());
                        bundle1.putString("customercreatedate",info_5.getText().toString());
                        intent1.putExtras(bundle1);
                        InfoActivity.this.setResult(Activity.RESULT_OK,intent1);
                        InfoActivity.this.finish();
                    }
                });
                break;
            case "suppers":
                this.getSupportActionBar().setTitle("供货商详情");
                text_1.setText(bundle.getString("name"));
                text_2.setText(bundle.getString("phone"));
                text_3.setText(bundle.getString("address"));
                text_4.setText(bundle.getString("time"));
                text_5.setText(bundle.getString("date"));
                info_1.setText(bundle.getString("s_name"));
                info_2.setText(bundle.getString("s_phone"));
                info_3.setText(bundle.getString("s_address"));
                info_4.setText(bundle.getString("s_time"));
                info_5.setText(bundle.getString("s_date"));
                update_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent();
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("suppersname",info_1.getText().toString());
                        bundle1.putString("suppersphone",info_2.getText().toString());
                        bundle1.putString("suppersaddress",info_3.getText().toString());
                        bundle1.putString("supperslast",info_4.getText().toString());
                        bundle1.putString("supperscreatedate",info_5.getText().toString());
                        intent1.putExtras(bundle1);
                        InfoActivity.this.setResult(Activity.RESULT_OK,intent1);
                        InfoActivity.this.finish();
                    }
                });
                break;

        }
    }
    public void initView(){
        text_1 = findViewById(R.id.text_1);
        text_2 = findViewById(R.id.text_2);
        text_3 = findViewById(R.id.text_3);
        text_4 = findViewById(R.id.text_4);
        text_5 = findViewById(R.id.text_5);
        info_1 = findViewById(R.id.info_1);
        info_2 = findViewById(R.id.info_2);
        info_3 = findViewById(R.id.info_3);
        info_4 = findViewById(R.id.info_4);
        info_5 = findViewById(R.id.info_5);
        update_info = findViewById(R.id.update_info);
    }
}