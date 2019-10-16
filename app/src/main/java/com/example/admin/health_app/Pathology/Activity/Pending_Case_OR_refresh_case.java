package com.example.admin.health_app.Pathology.Activity;

import android.content.Intent;
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

import com.example.admin.health_app.Pathology.Adapter.Pending_Case_OR_RefreshCase_Adapter;
import com.example.admin.health_app.Patient_Side_Scrren.Activity.Patient_Home_Screen;
import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Follow_Up_Adapter;
import com.example.admin.health_app.R;

import java.util.ArrayList;

public class Pending_Case_OR_refresh_case extends AppCompatActivity {
    private EditText searchET;
    private RecyclerView refresh_Case_RV;

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
        toolbarTitleText.setText("Refresh Case");
        searchET = (EditText) findViewById(R.id.searchET);
        refresh_Case_RV = (RecyclerView) findViewById(R.id.pathology_OrderRV);
        searchET.setVisibility(View.GONE);
        refresh_Case_RV.setLayoutManager(new LinearLayoutManager(this));
        refresh_Case_RV.setAdapter(new Pending_Case_OR_RefreshCase_Adapter(this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        finish();

        super.onBackPressed();
    }


}
