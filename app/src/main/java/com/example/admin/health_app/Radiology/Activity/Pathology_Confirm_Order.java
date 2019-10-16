package com.example.admin.health_app.Radiology.Activity;

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

import com.example.admin.health_app.Pathology.Adapter.Pathology_Test_Receipt_Adapter;
import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/21/2018.
 */

public class Pathology_Confirm_Order extends AppCompatActivity {
    private RecyclerView pathology_order_receipt_testRV;
    private Pathology_Test_Receipt_Adapter pathology_test_receipt_adapter;
    private String[] array = {"Acth Suppression test", "Entiglobulin"};
    private Button SubmitBTN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathology_order_receipt);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        pathology_order_receipt_testRV = (RecyclerView) findViewById(R.id.pathology_order_receipt_testRV);
        pathology_order_receipt_testRV.setLayoutManager(new LinearLayoutManager(this));
        pathology_test_receipt_adapter = new Pathology_Test_Receipt_Adapter(this, array);
        pathology_order_receipt_testRV.setAdapter(pathology_test_receipt_adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Confirm Order");
        SubmitBTN = (Button) findViewById(R.id.SubmitBTN);
        SubmitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pathology_Confirm_Order.this, Radiology_Home_Screen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
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
}
