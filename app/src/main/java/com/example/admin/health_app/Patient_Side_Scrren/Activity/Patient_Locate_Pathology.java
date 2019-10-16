package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Patient_Choose_Pathology_adapter;
import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/23/2018.
 */

public class Patient_Locate_Pathology extends AppCompatActivity implements Patient_Choose_Pathology_adapter.OnclickListner_Adapter {
    private RecyclerView recycleviewRV;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_pathology);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Choose Pathology");
        recycleviewRV=(RecyclerView)findViewById(R.id.recycleviewRV);
        recycleviewRV.setLayoutManager(new LinearLayoutManager(this));
        recycleviewRV.setAdapter(new Patient_Choose_Pathology_adapter(this,"1"));
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
    public void onClick(int position) {
        Log.e("onClick.....",""+position);
        startActivity(new Intent(this,Patient_choose_PickUP_Self.class));
    }
}
