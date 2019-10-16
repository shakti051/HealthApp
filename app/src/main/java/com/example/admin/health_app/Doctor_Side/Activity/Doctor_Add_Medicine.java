package com.example.admin.health_app.Doctor_Side.Activity;

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
import android.widget.Toast;

import com.example.admin.health_app.Doctor_Side.Adapter.Doctor_add_Medicine_Adapter;
import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/21/2018.
 */

public class Doctor_Add_Medicine extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView add_medicineRV;
    private Button addMedicineButtonId,nextBTN;
    private Doctor_add_Medicine_Adapter doctor_add_medicine_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_add_medicine);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        add_medicineRV = (RecyclerView) findViewById(R.id.add_medicineRV);
        addMedicineButtonId = (Button) findViewById(R.id.addMedicineButtonId);
        nextBTN = (Button) findViewById(R.id.nextBTN);
        addMedicineButtonId.setOnClickListener(this);
        nextBTN.setOnClickListener(this);
        add_medicineRV.setLayoutManager(new LinearLayoutManager(this));
        doctor_add_medicine_adapter = new Doctor_add_Medicine_Adapter(this, "combiflam");
        add_medicineRV.setAdapter(doctor_add_medicine_adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Add Medicine");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
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
            case R.id.addMedicineButtonId:

                Toast.makeText(Doctor_Add_Medicine.this, "Add Medicine", Toast.LENGTH_LONG).show();
                break;
            case R.id.nextBTN:
                startActivity(new Intent(this,Doctor_add_Pathology.class));
                finish();
                break;
        }
    }
}
