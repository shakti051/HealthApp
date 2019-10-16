package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/23/2018.
 */

public class Medical_Report_Details_Option extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout reportLY, consult_HistoryLY, current_ConsultLY, viewAppointmentLY, pharmacyOrderLY, pathologyOrderLY,
            RadiologyOrder_LY,HospitalOrderLY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_report_details);
        setIds();
    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Medical Reports");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        reportLY = (LinearLayout) findViewById(R.id.reportLY);
        viewAppointmentLY = (LinearLayout) findViewById(R.id.viewAppointmentLY);
        consult_HistoryLY = (LinearLayout) findViewById(R.id.consult_HistoryLY);
        current_ConsultLY = (LinearLayout) findViewById(R.id.current_ConsultLY);
        pharmacyOrderLY = (LinearLayout) findViewById(R.id.pharmacyOrderLY);
        pathologyOrderLY = (LinearLayout) findViewById(R.id.pathologyOrderLY);
        RadiologyOrder_LY = (LinearLayout) findViewById(R.id.RadiologyOrder_LY);
        HospitalOrderLY = (LinearLayout) findViewById(R.id.HospitalOrderLY);
        reportLY.setOnClickListener(this);
        HospitalOrderLY.setOnClickListener(this);
        current_ConsultLY.setOnClickListener(this);
        consult_HistoryLY.setOnClickListener(this);
        viewAppointmentLY.setOnClickListener(this);
        pharmacyOrderLY.setOnClickListener(this);
        pathologyOrderLY.setOnClickListener(this);
        RadiologyOrder_LY.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reportLY:
                startActivity(new Intent(this, Medical_Report_View_Upload.class));
                break;
            case R.id.consult_HistoryLY:
                startActivity(new Intent(this, Consultation_History_Prescrption.class));
                break;
            case R.id.current_ConsultLY:
                startActivity(new Intent(this, Current_Conulation.class));

                break;
            case R.id.viewAppointmentLY:
                startActivity(new Intent(this, View_Appointment.class));
                break;
            case R.id.pharmacyOrderLY:
                startActivity(new Intent(this, View_Pharmacy_Order.class));
                break;
            case R.id.pathologyOrderLY:
                startActivity(new Intent(this, Patient_View_Pathology_Order.class));
                break;
            case R.id.RadiologyOrder_LY:
                startActivity(new Intent(this, Patient_View_Radiology_order_List.class));
                break;
            case R.id.HospitalOrderLY:
                startActivity(new Intent(this, Patient_View_Hospital_Visit.class));
                break;

        }

    }
}
