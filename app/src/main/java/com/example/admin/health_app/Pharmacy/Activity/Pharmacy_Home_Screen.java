package com.example.admin.health_app.Pharmacy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.health_app.Common_package.LoginScreenActivity;
import com.example.admin.health_app.Doctor_Side.Activity.Doctor_Home_Screen;
import com.example.admin.health_app.R;
import com.example.admin.health_app.Pharmacy.Activity.UploadYOurDocuments;
import com.example.admin.health_app.Radiology.Activity.View_History;

/**
 * Created by Prateek on 23-May-18.
 */

public class Pharmacy_Home_Screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private LinearLayout myorder, uploadBill_LY, View_HistoryLY;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_navigation_drawer);
        setIds();
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

    private void setIds() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        View_HistoryLY = (LinearLayout) findViewById(R.id.View_HistoryLY);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Pharmacy");
        ImageView toolBarRightIcon = (ImageView) findViewById(R.id.toolBarRightIconID);
        toolBarRightIcon.setVisibility(View.VISIBLE);
        toolBarRightIcon.setImageResource(R.drawable.logout_48);
        toolBarRightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pharmacy_Home_Screen.this, LoginScreenActivity.class);


                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        myorder = (LinearLayout) findViewById(R.id.LL_myorders);
        uploadBill_LY = (LinearLayout) findViewById(R.id.uploadBill_LY);
        uploadBill_LY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Pharmacy_Home_Screen.this, UploadYOurDocuments.class);
                startActivity(i);
            }
        });
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pharmacy_Home_Screen.this, Pharmacy_Order_List.class);
                startActivity(i);
            }
        });
        View_HistoryLY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pharmacy_Home_Screen.this, View_History.class));
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

        super.onBackPressed();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
//        Intent i = new Intent(this, Patient_Home_Screen.class);
//        // set the new task and clear flags
////        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(i);
//        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
