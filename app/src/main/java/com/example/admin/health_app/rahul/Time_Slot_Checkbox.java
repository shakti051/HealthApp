package com.example.admin.health_app.rahul;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.health_app.R;

import java.util.ArrayList;

/**
 * Created by Admin on 5/18/2018.
 */

public class Time_Slot_Checkbox extends AppCompatActivity implements AppointmentTimeSlotAdapter.OnTimeSlotClickListener{
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private AppointmentTimeSlotAdapter mAdapter;
    private ArrayList<String> listValue = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slot_time_checkbox);
        add_ArrayList();
        setIds();
    }

    private void setIds() {
        mRecyclerView = (RecyclerView) findViewById(R.id.doctorSpecialityRV);
        layoutManager = new LinearLayoutManager(Time_Slot_Checkbox.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AppointmentTimeSlotAdapter(listValue, Time_Slot_Checkbox.this);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void add_ArrayList() {
        listValue.add("Select Time");
        listValue.add("02-30PM");
        listValue.add("03-30PM");
        listValue.add("04-30PM");
        listValue.add("05-30PM");
        listValue.add("06-30PM");
        listValue.add("07-30PM");
        listValue.add("08-30PM");
        listValue.add("09-30PM");

    }

    @Override
    public void onClick(String time) {

    }
}
