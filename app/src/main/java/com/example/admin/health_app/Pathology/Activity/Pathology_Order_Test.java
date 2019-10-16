package com.example.admin.health_app.Pathology.Activity;

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

import com.example.admin.health_app.Pathology.Adapter.Pathology_Order_Test_Adapter;
import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/21/2018.
 */

public class Pathology_Order_Test extends AppCompatActivity {
    private RecyclerView prescribeTest_pathologyRV;
    private Pathology_Order_Test_Adapter pathology_order_test_adapter;
    private String[] array={"Acth Suppression test","Entiglobulin"};
    private Button submitBTN;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathology_order_test);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        prescribeTest_pathologyRV=(RecyclerView)findViewById(R.id.prescribeTest_pathologyRV);
        submitBTN=(Button) findViewById(R.id.submitBTN);
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pathology_Order_Test.this,Pathology_Confirm_Order.class));
                finish();

            }
        });
        prescribeTest_pathologyRV.setLayoutManager(new LinearLayoutManager(this));
        pathology_order_test_adapter = new Pathology_Order_Test_Adapter(this, array);

        prescribeTest_pathologyRV.setAdapter(pathology_order_test_adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("My Orders");
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
