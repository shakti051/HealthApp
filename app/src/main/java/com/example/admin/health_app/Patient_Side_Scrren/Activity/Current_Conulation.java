package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Patient_choose_PickUp_Adapter;
import com.example.admin.health_app.Patient_Side_Scrren.Adapter.View_Radiology_Test_Adapter;
import com.example.admin.health_app.Patient_Side_Scrren.Adapter.View_pathology_Test_Adapter;
import com.example.admin.health_app.R;


/**
 * Created by Admin on 5/23/2018.
 */

public class Current_Conulation extends AppCompatActivity implements View.OnClickListener {
    private TextView locate_pathology_TV, locate_Pharmacy_TV,locate_Specialist_TV,locate_Hospitalisation_TV;
    private ImageView toolbarRight_icon;
private RecyclerView listmedicineRV,Pathology_Test_RV,radiology_Test_RV;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_e_prescription);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Current Consulation");
        locate_pathology_TV = (TextView) findViewById(R.id.locate_pathology_TV);
        locate_Pharmacy_TV = (TextView) findViewById(R.id.locate_Pharmacy_TV);
        locate_pathology_TV.setOnClickListener(this);
        locate_Pharmacy_TV.setOnClickListener(this);
        toolbarRight_icon = (ImageView) findViewById(R.id.toolBarRightIconID);
        locate_Specialist_TV = (TextView) findViewById(R.id.locate_Specialist_TV);
        locate_Hospitalisation_TV = (TextView) findViewById(R.id.locate_Hospitalisation_TV);
        toolbarRight_icon.setImageResource(R.drawable.home);
        toolbarRight_icon.setOnClickListener(this);
        locate_Specialist_TV.setOnClickListener(this);
        locate_Hospitalisation_TV.setOnClickListener(this);
        listmedicineRV=(RecyclerView)findViewById(R.id.listmedicineRV);
        Pathology_Test_RV=(RecyclerView)findViewById(R.id.Pathology_Test_RV);
        radiology_Test_RV=(RecyclerView)findViewById(R.id.radiology_Test_RV);
        listmedicineRV.setLayoutManager(new LinearLayoutManager(this));
        radiology_Test_RV.setLayoutManager(new LinearLayoutManager(this));
        Pathology_Test_RV.setLayoutManager(new LinearLayoutManager(this));
        listmedicineRV.setAdapter(new Patient_choose_PickUp_Adapter(this,"1"));
        Pathology_Test_RV.setAdapter(new View_pathology_Test_Adapter(this,"1"));
        radiology_Test_RV.setAdapter(new View_Radiology_Test_Adapter(this,"1"));
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
            case R.id.locate_pathology_TV:
                startActivity(new Intent(this, Patient_Locate_Pathology.class));
                break;
            case R.id.locate_Pharmacy_TV:
                startActivity(new Intent(this, Locate_Pharmacy.class));
                break;
            case R.id.toolBarRightIconID:
                Intent intent = new Intent(this, Patient_Home_Screen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.locate_Specialist_TV:
                intent = new Intent(this, Locate_Specialist.class);
                startActivity(intent);
                break;
            case R.id.locate_Hospitalisation_TV:
                intent = new Intent(this, Locate_Hospitalisation.class);
                startActivity(intent);
                break;
        }
    }
}
