package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Follow_Up_Adapter;
import com.example.admin.health_app.R;

import java.util.ArrayList;

/**
 * Created by Admin on 5/23/2018.
 */

public class Emergency_Services extends AppCompatActivity {
    private EditText searchET;
    private RecyclerView pathology_OrderRV;
    private ArrayList<String>arrayList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathology_my_order);
        insertData();
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Emergency Services");
        searchET=(EditText)findViewById(R.id.searchET);
        pathology_OrderRV=(RecyclerView) findViewById(R.id.pathology_OrderRV);
        searchET.setVisibility(View.GONE);
        pathology_OrderRV.setLayoutManager(new LinearLayoutManager(this));
//        emergency_services_adapter = new Emergency_Services_Adapter(this, arrayList);
//        pathology_OrderRV.setAdapter(emergency_services_adapter);
    }
    private void insertData(){
        arrayList.add("Dr.Satsih Yadav");
        arrayList.add("Dr.Gaurav Sharma");
        arrayList.add("Dr.Neha Sharma");
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
//dialup  intent
//    @Override
//    public void oOnclickdata(String number) {
//        Log.e("number",""+number);
//
//        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null));
//        startActivity(intent);
//
//    }
}
