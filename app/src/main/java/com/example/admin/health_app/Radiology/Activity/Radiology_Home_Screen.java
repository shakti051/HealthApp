package com.example.admin.health_app.Radiology.Activity;

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

/**
 * Created by Admin on 5/21/2018.
 */

public class Radiology_Home_Screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private GridView option_GridView;
    private int height = 400;
    private TextView toolbarTitleText, addressTVID, specialityTVFID, genderTVID;
    private ImageView toolBarRightIcon, iamge_id;
    private TextView doctorNmaeTVID;
    private LinearLayout mainlayout, videoCallLY;
    private int image[] = {R.drawable.myorder,R.drawable.upload_icon,R.drawable.test_tube,R.drawable.consult_history};
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_side_navigation);
        String appointname[] = {"New orders","Upload Document","Sample Collection","View History"};
        setIds();
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Pathology_Grid_Adapter general_practioner__home_screen_adapter =
                new Pathology_Grid_Adapter(this, appointname, image);
        option_GridView.setAdapter(general_practioner__home_screen_adapter);


    }

    private void setIds() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        doctorNmaeTVID = (TextView) findViewById(R.id.doctorNmaeTVID);
        specialityTVFID = (TextView) findViewById(R.id.specialityTVFID);
        addressTVID = (TextView) findViewById(R.id.addressTVID);
        specialityTVFID.setVisibility(View.GONE);
        iamge_id = (ImageView) findViewById(R.id.iamge_id);
        videoCallLY = (LinearLayout) findViewById(R.id.videoCallLY);
        videoCallLY.setVisibility(View.GONE);
        doctorNmaeTVID.setText("Arun ultraSound care Center");
        addressTVID.setText("Bhajanpura,Near main Market\n Delhi-110053");
        iamge_id.setImageResource(R.drawable.apollo_image);
        navigationView.setNavigationItemSelectedListener(this);
        option_GridView = (GridView) findViewById(R.id.gridview_id);
        toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        mainlayout = (LinearLayout) findViewById(R.id.mainLayout_id_LY);
        mainlayout.setVisibility(View.VISIBLE);
        toolbarTitleText.setVisibility(View.VISIBLE);
        toolbarTitleText.setText("Radiology");
        option_GridView.setNumColumns(2);
//        selectTypeTV = (TextView) findViewById(R.id.selectTypeTV);

        toolBarRightIcon = (ImageView) findViewById(R.id.toolBarRightIconID);
        toolBarRightIcon.setVisibility(View.VISIBLE);
        toolBarRightIcon.setImageResource(R.drawable.logout_48);
        toolBarRightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Radiology_Home_Screen.this, LoginScreenActivity.class);


                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
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

    public class Pathology_Grid_Adapter extends BaseAdapter {
        private Context context;
        private String[] list;
        private int[] imgdraw;
        private LayoutInflater inflater = null;
        private DisplayMetrics displayMetrics;

        public Pathology_Grid_Adapter(Context context, String list[], int imgdraw[]) {
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
                public void onClick(View v) {

                    Log.e("position_item_click", ":" + position);
                    if (position == 0) {
                        context.startActivity(new Intent(context, Pathology_My_Order.class));
                    } else if (position == 1) {

                        context.startActivity(new Intent(context, Pathology_Upload_Document.class));


                    }
                    else if (position == 2) {

                        context.startActivity(new Intent(context, SampleCollection.class));

                    }
                    else if (position == 3) {
                        context.startActivity(new Intent(context, View_History.class));


                    } else if (position == 4) {


                    } else if (position == 5) {

                    }
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

