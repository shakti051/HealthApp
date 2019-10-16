package com.example.admin.health_app.Radiology.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.admin.health_app.Pathology.Adapter.Pathology_Order_Test_Adapter;
import com.example.admin.health_app.R;

import java.util.Calendar;

public class Pathology_Collect_Test extends AppCompatActivity implements View.OnClickListener {
    private TextView selectDateTV_id, selectTimeTV_id;
    private DatePickerDialog datePickerDialog;
    private Button confirmBTN;
    private String[] array = {"Acth Suppression test", "Entiglobulin"};
    private RecyclerView testName_RV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathology_collect_test);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        selectDateTV_id = (TextView) findViewById(R.id.selectDateTV_id);
        selectTimeTV_id = (TextView) findViewById(R.id.selectTimeTV_id);
        confirmBTN = (Button) findViewById(R.id.confirmBTN);
        testName_RV = (RecyclerView) findViewById(R.id.testName_RV);
        testName_RV.setLayoutManager(new LinearLayoutManager(this));
        testName_RV.setAdapter(new Pathology_Order_Test_Adapter(this, array));
        confirmBTN.setOnClickListener(this);
        selectDateTV_id.setOnClickListener(this);
        selectTimeTV_id.setOnClickListener(this);
        toolbarTitleText.setText("Collection");
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

                            selectDateTV_id.setText("0" + month + "/" + "0" + dayOfMonth + "/" + year);
//                            selectdate = month + "/" + dayOfMonth + "/" + year;
                            Log.e("selectDateTV", "" + selectDateTV_id.getText().toString());


                        } else if (dayOfMonth > 10 && month > 10) {

                            selectDateTV_id.setText(month + "/" + dayOfMonth + "/" + year);
                            Log.e("selectDateTV", "" + selectDateTV_id.getText().toString());

                        } else if (dayOfMonth > 10 || month < 10) {
                            selectDateTV_id.setText("0" + month + "/" + dayOfMonth + "/" + year);
                            Log.e("selectDateTV", "" + selectDateTV_id.getText().toString());
                        } else {

                            selectDateTV_id.setText(dayOfMonth + "/" + month + "/" + year);
//                            selectdate = dayOfMonth + "/" + month + "/" + year;
                            Log.e("selectdate...", "" + selectDateTV_id);
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
                break;
            case R.id.confirmBTN:
                startActivity(new Intent(this, Radiology_Home_Screen.class));
                finish();
                break;
            case R.id.selectTimeTV_id:
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                final TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Log.e("onTimeSet", "" + selectedHour + "\n" + selectedMinute);
                        if (selectedHour > 1 && selectedHour < 12) {
//                            selectTime = selectedHour + ":" + selectedMinute + "AM";
                            selectTimeTV_id.setText(selectedHour + ":" + selectedMinute + "AM");
//                            select_startTime = selectedHour + ":" + selectedMinute + "AM";
//                            Log.e("aaaa", "" + select_startTime);

                        } else {
//                            selectTime = selectedHour + ":" + selectedMinute + "PM";
                            selectTimeTV_id.setText(selectedHour + ":" + selectedMinute + "PM");
//                            select_startTime = selectedHour + ":" + selectedMinute + "PM";
//                            Log.e("bbbbb", "" + select_startTime);
                        }
                    }

                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");

                mTimePicker.show();
                break;
        }
    }

}
