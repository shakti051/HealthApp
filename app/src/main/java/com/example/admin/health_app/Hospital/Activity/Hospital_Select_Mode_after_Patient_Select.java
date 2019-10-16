package com.example.admin.health_app.Hospital.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.health_app.Patient_Side_Scrren.Activity.Insurance_Details;
import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/28/2018.
 */

public class Hospital_Select_Mode_after_Patient_Select extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout prescriptionLY, InsuranceLY, Admission_LY, dischargeLY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_select_mode_after_patient_select);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Select Mode");
        dischargeLY = (LinearLayout) findViewById(R.id.dischargeLY);
        prescriptionLY = (LinearLayout) findViewById(R.id.prescriptionLY);
        InsuranceLY = (LinearLayout) findViewById(R.id.InsuranceLY);
        Admission_LY = (LinearLayout) findViewById(R.id.Admission_LY);

        Admission_LY.setOnClickListener(this);
        dischargeLY.setOnClickListener(this);
        prescriptionLY.setOnClickListener(this);
        InsuranceLY.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.InsuranceLY:
                startActivity(new Intent(this, Insurance_Details.class));
                break;
            case R.id.dischargeLY:
                startActivity(new Intent(this, Discharge_Details.class));
                break;
            case R.id.Admission_LY:
                startActivity(new Intent(this, Admisssion_Details.class));
                break;
            case R.id.prescriptionLY:
                startActivity(new Intent(this, Hospital_View_prescription.class));


                break;
        }
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
