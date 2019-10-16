package com.example.admin.health_app.Pharmacy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.admin.health_app.Pharmacy.Adapter.Order_Adapter;
import com.example.admin.health_app.Pharmacy.Adapter.Upload_bill_Adapter;
import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/24/2018.
 */

public class Upload_Bill extends AppCompatActivity implements Upload_bill_Adapter.OnClickListerner_Adapter{
    private RecyclerView orderRecycleView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_bill);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        orderRecycleView=(RecyclerView)findViewById(R.id.orderRecycleView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Upload Bills");
        orderRecycleView.setLayoutManager(new LinearLayoutManager(this));
        orderRecycleView.setAdapter(new Upload_bill_Adapter(this,"1"));
    }

    @Override
    public void oOnclickdata(int position) {
        Log.e("oOnclickdata","upload_bills:"+position);
        startActivity(new Intent(this,Pharmacy_Upload_Document.class));
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
