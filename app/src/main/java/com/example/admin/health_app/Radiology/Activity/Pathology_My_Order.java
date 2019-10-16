package com.example.admin.health_app.Radiology.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.admin.health_app.Pathology.Adapter.Pathology_Order_Adapter;
import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/21/2018.
 */

public class Pathology_My_Order extends AppCompatActivity implements  Pathology_Order_Adapter.OnClickListerner_Adapter {
    private RecyclerView pathology_OrderRV;
    private Pathology_Order_Adapter pathology_order_adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathology_my_order);
        setId();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setId() {
        pathology_OrderRV=(RecyclerView)findViewById(R.id.pathology_OrderRV);

        pathology_OrderRV.setLayoutManager(new LinearLayoutManager(this));
        pathology_order_adapter = new Pathology_Order_Adapter(this, "combiflam");
        pathology_OrderRV.setAdapter(pathology_order_adapter);
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

    @Override
    public void oOnclickdata(int position) {
        Log.e("oOnclickdata",""+position);
//        startActivity(new Intent(this,Pathology_Order_Test.class));

    }
}
