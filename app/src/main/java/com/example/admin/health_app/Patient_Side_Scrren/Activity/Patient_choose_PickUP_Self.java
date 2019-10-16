package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/23/2018.
 */

public class Patient_choose_PickUP_Self extends AppCompatActivity {
    private Button submitBTN;
    private RecyclerView listmedicineRV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_choose_pick_up_self);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Select Option");
        submitBTN = (Button) findViewById(R.id.submitBTN);
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Patient_choose_PickUP_Self.this, Current_Conulation.class));
            }
        });
//        listmedicineRV = (RecyclerView) findViewById(R.id.listmedicineRV);
//        listmedicineRV.setLayoutManager(new LinearLayoutManager(this));
//        listmedicineRV.setAdapter(new Patient_choose_PickUp_Adapter(this, "1"));

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


}


