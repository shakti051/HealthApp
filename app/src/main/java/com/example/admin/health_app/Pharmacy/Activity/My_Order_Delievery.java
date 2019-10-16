package com.example.admin.health_app.Pharmacy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Patient_Choose_Pathology_adapter;
import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Patient_choose_PickUp_Adapter;
import com.example.admin.health_app.Pharmacy.Adapter.My_Order_Delievery_Adapter;
import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/24/2018.
 */

public class My_Order_Delievery extends AppCompatActivity {
    private RecyclerView listmedicineRV;
    private Button deliver_Customer_BTN;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_my_order_delievery);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setIds() {
        deliver_Customer_BTN=(Button)findViewById(R.id.deliver_Customer_BTN);
        deliver_Customer_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startActivity(new Intent(My_Order_Delievery.this,Rating_To_Delivery.class));
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("My Orders");
        listmedicineRV=(RecyclerView)findViewById(R.id.listmedicineRV);
        listmedicineRV.setLayoutManager(new LinearLayoutManager(this));
        listmedicineRV.setAdapter(new My_Order_Delievery_Adapter(this,"1"));



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        finish();
//        Intent i = new Intent(this, Patient_Home_Screen.class);
//        // set the new task and clear flags
////        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(i);
//        finish();
        super.onBackPressed();
    }

}
