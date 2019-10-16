package com.example.admin.health_app.Doctor_Side.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.health_app.Doctor_Side.Adapter.Doctor_view_appointment_adapter;
import com.example.admin.health_app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Ankit on 1/29/2018.
 */

public class Doctor_View_Appointment extends AppCompatActivity implements Doctor_view_appointment_adapter.ClickListener {
    private SharedPreferences preferences;
    private String user_id, memeber_key;
    private LinearLayout mainLayout, no_data_found_LY_id;
    private RecyclerView patientsatatus_recycle_view;
    private Doctor_view_appointment_adapter doctor_view_appointment_adapter;
    private ArrayList<String> patientstatusList = new ArrayList<>();
    private ArrayList<String> patientTimeList = new ArrayList<>();
    private ImageView calenderIV;
    private TextView date_Show_TV;
    private DatePickerDialog datePickerDialog;
    private String selectdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_my_appointment);
        add_value_patientstatusList();
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setData_From_SharePreferences() {

        preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        user_id = preferences.getString("USER_ID_KEY", "");
        int member_key1 = preferences.getInt("MEMBER_KEY", 0);
        memeber_key = String.valueOf(member_key1);
        Log.e("memeber_key", "" + memeber_key + user_id);
    }

    private void setIds() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        calenderIV = (ImageView) findViewById(R.id.calenderIV);
        date_Show_TV = (TextView) findViewById(R.id.date_Show_TV);
        toolbarTitleText.setText("My Appointments");
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout_id);
        no_data_found_LY_id = (LinearLayout) findViewById(R.id.no_data_found_LY_id);
        patientsatatus_recycle_view = (RecyclerView) findViewById(R.id.patientsatatus_recycle_view_id);
        patientsatatus_recycle_view.setLayoutManager(new LinearLayoutManager(this));

        calenderIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                privateDate_Selected();
            }
        });


        patientsatatus_recycle_view.setLayoutManager(new LinearLayoutManager(this));
        doctor_view_appointment_adapter = new Doctor_view_appointment_adapter(this, patientstatusList, patientTimeList);
        patientsatatus_recycle_view.setAdapter(doctor_view_appointment_adapter);


    }

    private void privateDate_Selected() {
        Calendar c = Calendar.getInstance();
//***********OnDateSetListener is a Interface Of DatePickerDialog************************************************************
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                Calendar c = Calendar.getInstance();


                int month = monthOfYear + 1;
                if (dayOfMonth < 10 && month < 10) {

                    date_Show_TV.setText("0" + month + "/" + "0" + dayOfMonth + "/" + year);
                    selectdate = month + "/" + dayOfMonth + "/" + year;
                    Log.e("selectDateTV", "" + date_Show_TV.getText().toString());


                } else if (dayOfMonth > 10 && month > 10) {

                    date_Show_TV.setText(month + "/" + dayOfMonth + "/" + year);
                    Log.e("selectDateTV", "" + date_Show_TV.getText().toString());

                } else if (dayOfMonth > 10 || month < 10) {
                    date_Show_TV.setText("0" + month + "/" + dayOfMonth + "/" + year);
                    Log.e("selectDateTV", "" + date_Show_TV.getText().toString());
                } else {

                    date_Show_TV.setText(dayOfMonth + "/" + month + "/" + year);
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

        super.onBackPressed();
    }


    private void add_value_patientstatusList() {

        patientstatusList.add("Ankit Saxena");
        patientstatusList.add("Satish Yadav");
        patientstatusList.add("Pooja Yadav");
        patientstatusList.add("Vikas Sharma");

        patientTimeList.add("10:15 AM");
        patientTimeList.add("11:25 AM");
        patientTimeList.add("01:00 PM");
        patientTimeList.add("03:35 PM");

    }


    @Override
    public void getDataClick(int position) {
        Log.e("dataClick", "" + position);
//        startActivity(new Intent(this, Doctor_Generate_Prescription.class));


    }

}
