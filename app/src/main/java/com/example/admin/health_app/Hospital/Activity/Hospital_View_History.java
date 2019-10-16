package com.example.admin.health_app.Hospital.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.admin.health_app.Doctor_Side.Adapter.Doctor_add_Medicine_Adapter;
import com.example.admin.health_app.Hospital.Adapter.Hospital_View_History_Adapter;
import com.example.admin.health_app.R;

/**
 * Created by Admin on 5/28/2018.
 */

public class Hospital_View_History extends AppCompatActivity {
    private RecyclerView view_history_RV;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_view_history);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        view_history_RV=(RecyclerView)findViewById(R.id.view_history_RV);
        view_history_RV.setLayoutManager(new LinearLayoutManager(this));

        view_history_RV.setAdapter(new Hospital_View_History_Adapter(this,""));
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
            setSupportActionBar(toolbar);
            TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
            toolbarTitleText.setText("View History");
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
