package com.example.admin.health_app.Pharmacy.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.health_app.Pharmacy.Adapter.Order_Adapter;
import com.example.admin.health_app.R;

public class Pharmacy_Order_List extends AppCompatActivity implements Order_Adapter.OnClickListerner_Adapter{
    private RecyclerView orderRecycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_order_list);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        makeorder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i= new Intent(Pharmacy_Order_List.this,Pharmacy_Order_Delivery.class);
//                startActivity(i);
//            }
//        });
    }

    private void setIds() {
        orderRecycleView=(RecyclerView)findViewById(R.id.orderRecycleView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("My Orders");
        orderRecycleView.setLayoutManager(new LinearLayoutManager(this));
        orderRecycleView.setAdapter(new Order_Adapter(this,"1"));
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
        Log.e("oOnclickdata","pharmacy_order:"+position);
        startActivity(new Intent(this,My_Order_Delievery.class));
    }
}
