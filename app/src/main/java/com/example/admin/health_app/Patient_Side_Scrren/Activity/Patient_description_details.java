package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.admin.health_app.Patient_Side_Scrren.Follow_Up_NewCase.New_Case;
import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/17/2018.
 */

public class Patient_description_details extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout Eyes_LY, hearingLY, meternity_LY, heartLY, other_LY, lungLY, linearLayout, LL_Home_main;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_description);
        setIds();
    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Health Issue");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hearingLY = (LinearLayout) findViewById(R.id.hearingLY);
        Eyes_LY = (LinearLayout) findViewById(R.id.Eyes_LY);
        LL_Home_main = (LinearLayout) findViewById(R.id.LL_Home_main);
        meternity_LY = (LinearLayout) findViewById(R.id.meternity_LY);
        heartLY = (LinearLayout) findViewById(R.id.heartLY);
        other_LY = (LinearLayout) findViewById(R.id.other_LY);
        lungLY = (LinearLayout) findViewById(R.id.lungLY);
        Eyes_LY.setOnClickListener(this);
        hearingLY.setOnClickListener(this);
        heartLY.setOnClickListener(this);
        meternity_LY.setOnClickListener(this);
        other_LY.setOnClickListener(this);
        lungLY.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, Patient_Home_Screen.class);
        // set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hearingLY:
                Intent intent = new Intent(Patient_description_details.this, New_Case.class);
                intent.putExtra("Organ_Issue", "ENT");
                startActivity(intent);

                break;
            case R.id.Eyes_LY:
                Intent intent1 = new Intent(Patient_description_details.this, New_Case.class);
                intent1.putExtra("Organ_Issue", "Eye care");
                startActivity(intent1);
                break;
            case R.id.other_LY:
                Intent intent2 = new Intent(Patient_description_details.this, New_Case.class);
                intent2.putExtra("Organ_Issue", "Others");
                startActivity(intent2);
                break;
            case R.id.heartLY:
                Intent intent3 = new Intent(Patient_description_details.this, New_Case.class);
                intent3.putExtra("Organ_Issue", "Heart");
                startActivity(intent3);
                break;
            case R.id.meternity_LY:
                Intent intent4 = new Intent(Patient_description_details.this, New_Case.class);
                intent4.putExtra("Organ_Issue", "Maternity");
                startActivity(intent4);
                break;
            case R.id.lungLY:
                Intent intent5 = new Intent(Patient_description_details.this, New_Case.class);
                intent5.putExtra("Organ_Issue", "Lungs");
                startActivity(intent5);
                break;
        }
    }


    private void setIds1() {


    }
}
