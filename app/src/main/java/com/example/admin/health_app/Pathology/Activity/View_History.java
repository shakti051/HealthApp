package com.example.admin.health_app.Pathology.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.health_app.Pathology.Adapter.View_History_Adapter;
import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Consultation_History_Adapter;
import com.example.admin.health_app.R;

import java.util.ArrayList;

public class View_History extends AppCompatActivity implements View_History_Adapter.OnClickListerner_Adapter {
    private RecyclerView view_historyRV;
    private EditText searchET;
    private ArrayList<String> staticvalue = new ArrayList<>();
    private ArrayList<String> staticAppointmentId = new ArrayList<>();
    private ArrayList<String> filterList = new ArrayList<>();
    private LinearLayout searchLY_id;
    private ImageView cancelIV;
    private View_History_Adapter view_history_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathology_my_order);
        addvalue();
        setIds();
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        searchET = (EditText) findViewById(R.id.searchET);
        searchLY_id = (LinearLayout) findViewById(R.id.searchLY_id);
        cancelIV = (ImageView) findViewById(R.id.cancelIV);
        searchLY_id.setVisibility(View.VISIBLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);

        toolbarTitleText.setText("View History");
        view_historyRV = (RecyclerView) findViewById(R.id.pathology_OrderRV);
        view_historyRV.setLayoutManager(new LinearLayoutManager(this));
        view_history_adapter = new View_History_Adapter(this, staticAppointmentId,staticvalue);
        view_historyRV.setAdapter(view_history_adapter);
        SearchText(searchET);
        cancelIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = searchET.getText().toString().length();
                Log.e("length", "" + length);
                if (length > 0) {
                    searchET.getText().delete(length - 1, length);
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
    public void oOnclickdata(String position) {
        Log.e("oOnclickdata", "." + position);
    }

    private void SearchText(EditText appCompatEditText) {
        appCompatEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String textChange = charSequence.toString();
                Log.e("text_change", ":" + textChange.length());
                ArrayList<String> carModelPosition = new ArrayList<>();
                cancelIV.setVisibility(View.VISIBLE);

                Boolean isdata = false;
                filterList.clear();
                for (i = 0; i < staticvalue.size(); i++) {
                    if (staticvalue.get(i).toLowerCase().contains(textChange.toLowerCase())) {

                        carModelPosition.add(staticvalue.get(i));
                        Log.e("carmodel", "" + carModelPosition);

                        view_history_adapter.updateList(carModelPosition, "name");

                    } else if (staticAppointmentId.get(i).contains(textChange.toLowerCase())) {
                        carModelPosition.add(staticAppointmentId.get(i));
                        Log.e("carmodel", "" + carModelPosition);

                        view_history_adapter.updateList(carModelPosition, "Id");
                    }


                    Log.e("aaaa", "" + carModelPosition);
                }
                if (textChange.isEmpty()) {
                    cancelIV.setVisibility(View.GONE);
                }


//                if (!isdata) {
////                    patient_report_RV.setVisibility(View.GONE);
////                    no_data_found_LY.setVisibility(View.VISIBLE);
//                    cancelIV.setVisibility(View.GONE);
////                    serachIV.setVisibility(View.VISIBLE);
//
//                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void addvalue() {
        staticvalue.add("Ankit Saxena");
        staticvalue.add("Rohan Sharma");
        staticvalue.add("Abhishek Kumar");
        staticvalue.add("Nitesh Rana");
        staticAppointmentId.add("101");
        staticAppointmentId.add("201");
        staticAppointmentId.add("103");
        staticAppointmentId.add("301");
    }
}

