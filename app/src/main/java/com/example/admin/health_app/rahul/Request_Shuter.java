package com.example.admin.health_app.rahul;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.health_app.R;

import java.util.ArrayList;

/**
 * Created by Admin on 5/17/2018.
 */

public class Request_Shuter extends AppCompatActivity implements    AdapterView.OnItemSelectedListener {
    private Spinner spinner_Family_details,cartypeSP;
    private ArrayAdapter arrayAdapter,arrayAdapter1;
    private ArrayList<String>carTypeList=new ArrayList<>();
    private ArrayList<String>carTypeList1=new ArrayList<>();
    private TextView carTypeTV;
    private LinearLayout spinnerLY;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_choose_pick_up_self);
        addValue_in_Array();
//        setIds();
    }

//    private void setIds() {
//        spinner_Family_details = (Spinner) findViewById(R.id.selectFamilyDetail_SP);
//        cartypeSP = (Spinner) findViewById(R.id.cartypeSP);
//        carTypeTV = (TextView) findViewById(R.id.carTypeTV);
//        spinnerLY = (LinearLayout) findViewById(R.id.spinnerLY);
//        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, carTypeList);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_Family_details.setAdapter(arrayAdapter);
//        spinner_Family_details.setOnItemSelectedListener(this);
//
//        arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, carTypeList1);
//        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        cartypeSP.setAdapter(arrayAdapter1);
//
//    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        String name=spinner_Family_details.getSelectedItem().toString();
        Log.e("name....",""+name+position);
        if (spinner_Family_details.getSelectedItem().toString().equals("Temporary")) {
            carTypeTV.setVisibility(View.GONE);
            spinnerLY.setVisibility(View.GONE);
        }
        else {
            carTypeTV.setVisibility(View.VISIBLE);
            spinnerLY.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private void addValue_in_Array(){
        carTypeList.add("shutter");
        carTypeList.add("Temporary");
        carTypeList1.add("drop");
    }
}
