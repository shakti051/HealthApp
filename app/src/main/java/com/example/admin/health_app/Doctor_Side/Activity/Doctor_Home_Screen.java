package com.example.admin.health_app.Doctor_Side.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.health_app.Common_package.LoginScreenActivity;
import com.example.admin.health_app.R;
import com.suke.widget.SwitchButton;


/**
 * Created by Ankit on 1/11/2018.
 */

public class Doctor_Home_Screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
   private GridView option_GridView;
    private int height = 400;
    private  TextView toolbarTitleText;
    private LinearLayout mainlayout;
    private int image[] = {R.drawable.book_appointment,R.drawable.complete_request_icon};
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private  ActionBarDrawerToggle toggle;
    private SwitchButton switchButton,Sos_toggleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_side_navigation);
        String appointname[] = getResources().getStringArray(R.array.General_Practioner);
        setIds();
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        General_Practioner__home_screen_Adapter general_practioner__home_screen_adapter =
                new General_Practioner__home_screen_Adapter(this, appointname, image);
        option_GridView.setAdapter(general_practioner__home_screen_adapter);


    }

    private void setIds() {
        Sos_toggleButton=(SwitchButton)findViewById(R.id.toggleButton);
        Sos_toggleButton.setVisibility(View.VISIBLE);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        option_GridView = (GridView) findViewById(R.id.gridview_id);
         toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        mainlayout = (LinearLayout) findViewById(R.id.mainLayout_id_LY);
        mainlayout.setVisibility(View.VISIBLE);
        toolbarTitleText.setVisibility(View.VISIBLE);
        toolbarTitleText.setText("Doctor");
        option_GridView.setNumColumns(2);
//        selectTypeTV = (TextView) findViewById(R.id.selectTypeTV);

//        toolBarRightIcon = (ImageView) findViewById(R.id.toolBarRightIconID);
//        toolBarRightIcon.setVisibility(View.VISIBLE);
//        toolBarRightIcon.setImageResource(R.drawable.logout_48);
//        toolBarRightIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Doctor_Home_Screen.this, LoginScreenActivity.class);
//
//
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(i);
//            }
//        });
        switchButton = (SwitchButton) findViewById(R.id.switch_button);

        switchButton.setChecked(true);
        switchButton.isChecked();
        switchButton.toggle();     //switch state
        switchButton.toggle(true);//switch without animation
        switchButton.setShadowEffect(true);//disable shadow effect
        switchButton.setEnabled(true);//disable button
        switchButton.setEnableEffect(true);//disable the switch animation
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
                if (!isChecked) {
//                    onlineTV_id.setText("[ Offline ]");
//                    onlineTV_id.setTextColor(Color.RED);
                }
                if (isChecked) {
//                    onlineTV_id.setText("[ Online ]");
//                    onlineTV_id.setTextColor(Color.GREEN);
                }

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //*****************************Grid View Adapter Class****************************************************************

    public class General_Practioner__home_screen_Adapter extends BaseAdapter {
       private Context context;
        private  String[] list;
        private  int[] imgdraw;
        private LayoutInflater inflater = null;
        private  DisplayMetrics displayMetrics;

        public General_Practioner__home_screen_Adapter(Context context, String list[], int imgdraw[]) {
            this.context = context;
            this.list = list;
            this.imgdraw = imgdraw;
            Log.e("list", "....." + list);
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class Holder {
            TextView tv;
            ImageView img;


        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder = new Holder();
            View rowView;
            int i;

            rowView = inflater.inflate(R.layout.grid_list_item, null);
            holder.tv = (TextView) rowView.findViewById(R.id.appointTV_id);
            holder.img = (ImageView) rowView.findViewById(R.id.imageIV_id);
            displayMetrics = context.getResources().getDisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height1 = displayMetrics.heightPixels / 3;
            int width = (int) (displayMetrics.widthPixels);
            Log.e("height", "" + displayMetrics.heightPixels);
            Log.e("width", "" + displayMetrics.widthPixels);
            height = height1;
            holder.img.setImageResource(imgdraw[position]);
            holder.tv.setText(list[position]);
            Log.e("Height_width", ":" + width + height);

            option_GridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    Log.e("position_item_click", ":" + position);
                    if (position == 0) {
                        context.startActivity(new Intent(context, Doctor_View_Appointment.class));

                    } else if (position == 1) {

//                        context.startActivity(new Intent(context, Appointment_booked.class));
//                        finish();

                    }
//                    else if (position == 2) {
//
//                        context.startActivity(new Intent(context, Doc_View_History.class));
//                        finish();
//                    }
//                    else if (position == 3) {
//                        context.startActivity(new Intent(context, PendingRequest.class));
//
//
//                    } else if (position == 4) {
//
//
//                    } else if (position == 5) {
//
//                    }
                }

            });


            rowView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, height));
            return rowView;

        }


    }


    @Override
    public void onBackPressed() {


        super.onBackPressed();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
