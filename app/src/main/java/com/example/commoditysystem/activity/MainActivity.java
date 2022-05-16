package com.example.commoditysystem.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.commoditysystem.R;
import com.example.commoditysystem.ui.commodity.CommodityFragment;
import com.example.commoditysystem.ui.customer.CustomerFragment;
import com.example.commoditysystem.ui.order.OrderFragment;
import com.example.commoditysystem.ui.suppers.SuppersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.commoditysystem.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setLabelVisibilityMode(1);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_commodity, R.id.navigation_customer, R.id.navigation_order,R.id.navigation_suppers)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(MainActivity.this,AddActivity.class);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Bundle bundle = new Bundle();
        switch (navView.getSelectedItemId()){
            case R.id.navigation_commodity:
                bundle.putString("tag","commodity");
                intent.putExtras(bundle);
                startActivityForResult(intent,1,bundle);
                break;
            case R.id.navigation_customer:
                bundle.putString("tag","customer");
                intent.putExtras(bundle);
                startActivityForResult(intent,2,bundle);
                break;
            case R.id.navigation_order:
                bundle.putString("tag","order");
                intent.putExtras(bundle);
                startActivityForResult(intent,3,bundle);
                break;
            case R.id.navigation_suppers:
                bundle.putString("tag","suppers");
                intent.putExtras(bundle);
                startActivityForResult(intent,4,bundle);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode== Activity.RESULT_OK) {
                    CommodityFragment commodityFragment = new CommodityFragment();
                    commodityFragment.onActivityResult(requestCode,resultCode,data);
                }
                break;
            case 2:
                if (resultCode== Activity.RESULT_OK) {
                    CustomerFragment customerFragment = new CustomerFragment();
                    customerFragment.onActivityResult(requestCode,resultCode,data);
                }
                break;
            case 3:
                if (resultCode== Activity.RESULT_OK) {
                    OrderFragment orderFragment = new OrderFragment();
                    orderFragment.onActivityResult(requestCode,resultCode,data);
                }
                break;
            case 4:
                if (resultCode== Activity.RESULT_OK) {
                    SuppersFragment suppersFragment = new SuppersFragment();
                    suppersFragment.onActivityResult(requestCode,resultCode,data);
                }
                break;
        }
    }
}