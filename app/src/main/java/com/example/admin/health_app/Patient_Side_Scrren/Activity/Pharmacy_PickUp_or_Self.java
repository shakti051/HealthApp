package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.health_app.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Admin on 5/24/2018.
 */

public class Pharmacy_PickUp_or_Self extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    private Button submitBTN;
    private RecyclerView listmedicineRV;
    private RadioGroup radioGroupPick;
    private RadioButton homeDeliveryRadio;
    private LinearLayout homeDelievryLY;
    private TextView selectDateTV,selectTimeTV;
    private String selectdate,selectTime;
    private DatePickerDialog datePickerDialog;
    private Spinner TimeSlot_SP;
    private ArrayAdapter timeSlotList;
    private String[] timeSlot= {"09:30 AM","10:40 AM","04:00 PM"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_pick_up_or_self);
        setIds();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Select Option");
        submitBTN=(Button)findViewById(R.id.submitBTN);
        selectDateTV=(TextView)findViewById(R.id.selectDateTV_id);
        TimeSlot_SP=(Spinner) findViewById(R.id.TimeSlot_SP);
        selectDateTV.setOnClickListener(this);
        timeSlotList = new ArrayAdapter(this, android.R.layout.simple_spinner_item, timeSlot);
        timeSlotList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TimeSlot_SP.setAdapter(timeSlotList);
        radioGroupPick=(RadioGroup) findViewById(R.id.radioGroupPick);
        homeDelievryLY=(LinearLayout) findViewById(R.id.homeDelievryLY);
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pharmacy_PickUp_or_Self.this,Current_Conulation.class));
            }
        });
        radioGroupPick.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                homeDeliveryRadio = (RadioButton) findViewById(selectedId);
                Log.e("selectedId", "" + selectedId + homeDeliveryRadio.getText().toString());

                if (homeDeliveryRadio.getText().toString().equals("Home Delivery")) {
                    homeDeliveryRadio.setTextColor(Color.BLACK);
                    homeDelievryLY.setVisibility(View.VISIBLE);
                    Log.e("andar_aaya", "");
                } else if (homeDeliveryRadio.getText().toString().equals("Collection")) {
                    Log.e("andar_aaya", "else");
                    homeDelievryLY.setVisibility(View.GONE);
                    homeDeliveryRadio.setTextColor(Color.BLACK);
                }
            }
        });

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

            case R.id.selectDateTV_id:
                Calendar c = Calendar.getInstance();
//***********OnDateSetListener is a Interface Of DatePickerDialog************************************************************
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        Calendar c = Calendar.getInstance();


                        int month = monthOfYear + 1;
                        if (dayOfMonth < 10 && month < 10) {

                            selectDateTV.setText("0" + month + "/" + "0" + dayOfMonth + "/" + year);
                            selectdate = month + "/" + dayOfMonth + "/" + year;
                            Log.e("selectDateTV", "" + selectDateTV.getText().toString());


                        } else if (dayOfMonth > 10 && month > 10) {

                            selectDateTV.setText(month + "/" + dayOfMonth + "/" + year);
                            Log.e("selectDateTV", "" + selectDateTV.getText().toString());

                        } else if (dayOfMonth > 10 || month < 10) {
                            selectDateTV.setText("0" + month + "/" + dayOfMonth + "/" + year);
                            Log.e("selectDateTV", "" + selectDateTV.getText().toString());
                        } else {

                            selectDateTV.setText(dayOfMonth + "/" + month + "/" + year);
                            selectdate = dayOfMonth + "/" + month + "/" + year;
                            Log.e("selectdate...", "" + selectdate);
                        }
                        Log.e("month...", "" + month + dayOfMonth);
                        // TODO Auto-generated method stub
//                        c.set(Calendar.YEAR, year);
//                        c.set(Calendar.MONTH, monthOfYear);
//                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                        selectdate = month + "/" + dayOfMonth + "/" + year;


//                        selectDateTV.setText(dayOfMonth + "/" + month + "/" + year);

                    }

                };
                datePickerDialog = new DatePickerDialog(this, date, c
                        .get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
                //**************Show A Current Date******************************************
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());
                break;


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}