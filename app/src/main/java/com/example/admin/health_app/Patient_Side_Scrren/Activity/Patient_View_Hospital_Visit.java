package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Follow_Up_Adapter;
import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Patient_View_hospital_Visit_Adapter;
import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Patient_View_radiology_Adapter;
import com.example.admin.health_app.R;

import java.util.ArrayList;

public class Patient_View_Hospital_Visit extends AppCompatActivity {
    private EditText searchET;
    private RecyclerView view_pathologyOrder_TV;
    private Follow_Up_Adapter followUp_adapter;
    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathology_my_order);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Hospital Visits");
        searchET = (EditText) findViewById(R.id.searchET);
        view_pathologyOrder_TV = (RecyclerView) findViewById(R.id.pathology_OrderRV);
        searchET.setVisibility(View.GONE);
        view_pathologyOrder_TV.setLayoutManager(new LinearLayoutManager(this));
        view_pathologyOrder_TV.setAdapter(new Patient_View_hospital_Visit_Adapter(this,"Arun  UltraSound Centre"));

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
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(i);
//        finish();
        super.onBackPressed();
    }


}
