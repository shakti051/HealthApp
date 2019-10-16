package com.example.admin.health_app.Doctor_Side.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/21/2018.
 */

public class Doctor_add_Pathology extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{
    private Spinner add_pathology_SP;
    private String [] temporaryList={"Blood Test","Antinuclear antibody","Liver function tests"};
    private ArrayAdapter arrayAdapter_pathology;
    private Button addtestBTN,nextBTN;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_add_pathology);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        add_pathology_SP=(Spinner)findViewById(R.id.add_pathology_SP);
        addtestBTN=(Button) findViewById(R.id.addtestBTN);
        nextBTN=(Button) findViewById(R.id.nextBTN);
        addtestBTN.setOnClickListener(this);
        nextBTN.setOnClickListener(this);
        add_pathology_SP.setOnItemSelectedListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        arrayAdapter_pathology = new ArrayAdapter(this, android.R.layout.simple_spinner_item, temporaryList);
        arrayAdapter_pathology.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_pathology_SP.setAdapter(arrayAdapter_pathology);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Add Pathology");
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addtestBTN:
                Toast.makeText(Doctor_add_Pathology.this,"Add Test",Toast.LENGTH_LONG).show();
                break;
            case R.id.nextBTN:
                startActivity(new Intent(this,Doctor_add_Radiology.class));
                finish();
                break;
        }
    }
}
