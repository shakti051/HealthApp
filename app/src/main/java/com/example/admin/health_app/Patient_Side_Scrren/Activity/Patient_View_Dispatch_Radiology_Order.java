package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Patient_View_Dispatch_Pathology_order;
import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Patient_View_Dispatch_Radiology_Adapter;
import com.example.admin.health_app.R;

public class Patient_View_Dispatch_Radiology_Order extends AppCompatActivity {
    private RecyclerView dispatch_pharmacy_orderRV;
    private ImageView patientIV;
    private TextView patientNameTV_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_patient_pharmacy_dispatch);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Dispatch Order");
        patientIV=(ImageView)findViewById(R.id.patientIV);
        patientNameTV_id=(TextView) findViewById(R.id.patientNameTV_id);
        patientNameTV_id.setText("Arun UltraSound Center");
        patientIV.setImageResource(R.drawable.apollo_image);
        dispatch_pharmacy_orderRV=(RecyclerView)findViewById(R.id.dispatch_pharmacy_orderRV);
        dispatch_pharmacy_orderRV.setLayoutManager(new LinearLayoutManager(this));
        dispatch_pharmacy_orderRV.setAdapter(new Patient_View_Dispatch_Radiology_Adapter(this,"1"));
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

