package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
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

import com.example.admin.health_app.Common_package.Dialog_popup;
import com.example.admin.health_app.Doctor_Side.Activity.Doctor_View_Appointment;
import com.example.admin.health_app.Common_package.LoginScreenActivity;
import com.example.admin.health_app.Model.Employe_Family_Details_Model;
import com.example.admin.health_app.Patient_Side_Scrren.Follow_Up_NewCase.Blank_Activity;
import com.example.admin.health_app.R;
import com.example.admin.health_app.View_Pager.Employee_Family_Details_Pager;
import com.example.admin.health_app.VolleyApiHit.DeviceNetConnectionDetector;
import com.example.admin.health_app.VolleyApiHit.Volley_Asynclass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 5/17/2018.
 */

public class Patient_Home_Screen extends AppCompatActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener, Volley_Asynclass.VolleyResponse {
    private ViewPager mViewPager;
    private ImageView left_arrow, right_arrow;
    private LinearLayout pcp_LY, medical_card_LY, mainLayout_id;
    private TextView currentLocation_TV;
    private String grid_name[] = {"Emergency Services", "Consult PCP", "Insurance Details", "Medical Report Details"};
    private int[] grid_image = {R.drawable.ambulance, R.drawable.book_appointment, R.drawable.insurance_icon, R.drawable.medical_reprt_icon};
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private SharedPreferences preferences;
    private String currentLocation, employeId;
    private GridView gridView;
    private NavigationView navigationView;
    private int height = 400;
    private List<Employe_Family_Details_Model> employe_family_detailsList;
    private Integer viewPager_Position = 0;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editor = getSharedPreferences("prefs", MODE_PRIVATE).edit();
        preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        currentLocation = preferences.getString("curr_locality_KEY", "");
        employeId = preferences.getString("EMPLOYEEID_KEY", "");
        Log.e("currentLocation_TV", "" + currentLocation);
        setIds();
        ApiHit();
        //For Navigation
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //navigation end

    }


    private void setIds() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view); //start three line for navigation drawer
        navigationView.setNavigationItemSelectedListener(this);
        gridView = (GridView) findViewById(R.id.gridview_id);
        mainLayout_id = (LinearLayout) findViewById(R.id.mainLayout_id);
        toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Patient");
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        left_arrow = (ImageView) findViewById(R.id.left_nav);
        right_arrow = (ImageView) findViewById(R.id.right_nav);
        currentLocation_TV = (TextView) findViewById(R.id.currentLocation_TV);
        currentLocation_TV.setText(currentLocation);
        right_arrow.setOnClickListener(this);
        left_arrow.setOnClickListener(this);

        getPosition_of_viewPager();
        ImageView toolBarRightIcon = (ImageView) findViewById(R.id.toolBarRightIconID);
        toolBarRightIcon.setVisibility(View.VISIBLE);
        toolBarRightIcon.setImageResource(R.drawable.logout_48);
        toolBarRightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Patient_Home_Screen.this, LoginScreenActivity.class));
                finish();
            }
        });

        gridView.setAdapter(new Patient_Grid_View(this, grid_name, grid_image));
    }


    @Override
    public void onClick(View view) {
        int tab = mViewPager.getCurrentItem();
        switch (view.getId()) {
            case R.id.left_nav:
                Log.e("andar aaya", "kya");
                if (tab > 0) {
                    tab--;
                    mViewPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    mViewPager.setCurrentItem(tab);
                }
                break;
            case R.id.right_nav:
                Log.e("andar aaya right", "kya");

                tab++;
                mViewPager.setCurrentItem(tab);
                break;

        }
    }

    private void getPosition_of_viewPager() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager_Position = position;
                Log.e("view_Pager", "" + position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Log.e("onNavigationItem", "" + id);
        if (id == R.id.videoCalling) {
            startActivity(new Intent(this, Doctor_View_Appointment.class));
            finish();
        }
//        if (id == R.id.nav_camera) {
////            Intent i = new Intent(this, Service_recycleView.class);
////            startActivity(i);
//            // Handle the camera action
//        }
//        if (id == R.id.service_request_id) {
//            Intent i = new Intent(this, MainActivity.class);
//            startActivity(i);
//            // Handle the camera action
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class Patient_Grid_View extends BaseAdapter {
        private Context context;
        private String[] list;
        private int[] imgdraw;
        private LayoutInflater inflater = null;
        private DisplayMetrics displayMetrics;

        public Patient_Grid_View(Context context, String list[], int imgdraw[]) {
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
            int height1 = (int) (displayMetrics.heightPixels / 3.5);
            int width = (int) (displayMetrics.widthPixels);
            Log.e("height", "" + displayMetrics.heightPixels);
            Log.e("width", "" + displayMetrics.widthPixels);
            height = height1;
            holder.img.setImageResource(imgdraw[position]);
            holder.tv.setText(list[position]);
            Log.e("Height_width", ":" + width + height);

            gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "101", null));
                        startActivity(intent);
//                        context.startActivity(new Intent(context, Emergency_Services.class));
//                        if (viewPager_Position == null) {
//                            Snackbar.make(mainLayout_id, "Please Select Type", Snackbar.LENGTH_LONG).show();
//
//                        } else {
//
//                            context.startActivity(new Intent(context, Emergency_Services.class));
//                        }

                    } else if (position == 1) {
                        if (viewPager_Position == 0) {
                            Log.e("if_condition", "" + viewPager_Position);
                            StoreFamily_Details_DB(viewPager_Position);
                            startActivity(new Intent(context, Blank_Activity.class));
//                            Employe_Family_Details userItem = employe_family_detailsList.get(0);
//
//                            Intent yourIntent = new Intent(context, Blank_Activity.class);
//                            Bundle b = new Bundle();
//                            b.putSerializable("user", userItem);
//                            yourIntent.putExtras(b); //pass bundle to your intent
//                            startActivity(yourIntent);
                        } else {
                            Log.e("else_condtion", "" + viewPager_Position);
                            StoreFamily_Details_DB(viewPager_Position);
                            startActivity(new Intent(context, Blank_Activity.class));
                            //Pass Vlaue from model class through activity patient_Home_Screen.......

//                            Employe_Family_Details userItem = employe_family_detailsList.get(viewPager_Position);
//
//                            Intent yourIntent = new Intent(context, Blank_Activity.class);
//                            Bundle b = new Bundle();
//                            b.putSerializable("user", userItem);
//                            yourIntent.putExtras(b); //pass bundle to your intent
//                            startActivity(yourIntent);
                        }

//                        if (viewPager_Position == null) {
//                            Snackbar.make(mainLayout_id, "Please Select Type", Snackbar.LENGTH_LONG).show();
//
//                        } else {
//
//                            context.startActivity(new Intent(context, Blank_Activity.class));
//                        }


                    } else if (position == 2) {

                        context.startActivity(new Intent(context, Insurance_Details.class));
//                        if (viewPager_Position == null) {
//                            Snackbar.make(mainLayout_id, "Please Select Type", Snackbar.LENGTH_LONG).show();
//
//                        } else {
//
//                            context.startActivity(new Intent(context, Insurance_Details.class));
//                        }
                    } else if (position == 3) {
                        context.startActivity(new Intent(context, Medical_Report_Details_Option.class));

//                        if (viewPager_Position == null) {
//                            Snackbar.make(mainLayout_id, "Please Select Type", Snackbar.LENGTH_LONG).show();
//
//                        } else {
//
//                            context.startActivity(new Intent(context, Medical_Report_Details.class));
//                        }


                    }
// else if (position == 4) {
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

    private void ApiHit() {
        new Volley_Asynclass(this, JsonParams_Patient_Details(), "patientreldetails/", "Login...");
    }

    private String JsonParams_Patient_Details() {
        String urlString = null;
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject.put("API", "RAKSHA_PATIENT_REL_DETAILS");
            jsonObject.put("CURD_OPERATION", "G");
            jsonObject.put("EMPLOYEEID", employeId);
            jsonObject1.put("ITEMS", jsonObject);
            urlString = jsonObject1.toString();

            Log.e("urlString", "" + urlString);

        } catch (Exception e) {
            String error = String.valueOf(e);
            Dialog_popup dialog_popup = new Dialog_popup(this, error);
            dialog_popup.show();
        }
        return urlString;
    }


    @Override
    public void getDataFromVolleyInterFace(String loadedString) {
        Log.e("loadedString....", "" + loadedString);
        if (loadedString != null && !loadedString.equals("") && !loadedString.equals("ExceptionCaught")) {

            try {
                JSONObject jsonObject = new JSONObject(loadedString);
                JSONObject jsonObject1 = jsonObject.getJSONObject("RESPONSE");
                String responseCode = jsonObject1.getString("RESPONSECODE");
                if (responseCode.equals("0")) {
                    JSONArray jsonArray = jsonObject1.getJSONArray("DETAILS");
                    employe_family_detailsList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.e("INSIDE IF", "inide if... " + jsonArray);
                        Employe_Family_Details_Model employe_family_details = new Employe_Family_Details_Model();
                        employe_family_details.setRelation_key(jsonArray.getJSONObject(i).getString("RELATION_KEY"));
                        employe_family_details.setRelation_name(jsonArray.getJSONObject(i).getString("RELATION_NAME"));
                        employe_family_details.setName(jsonArray.getJSONObject(i).getString("NAME"));
                        employe_family_details.setMember_key(jsonArray.getJSONObject(i).getString("MEMBER_KEY"));
                        employe_family_details.setDate_of_Birth(jsonArray.getJSONObject(i).getString("DATEOFBIRTH"));
                        employe_family_details.setGender(jsonArray.getJSONObject(i).getString("GENDER"));
                        employe_family_details.setAadhaarNo(jsonArray.getJSONObject(i).getString("AADHAR_NUMBER"));
                        employe_family_details.setPan_Number(jsonArray.getJSONObject(i).getString("PAN_NUMBER"));
                        employe_family_detailsList.add(employe_family_details);
                        Log.e("employe_family_List", "" + employe_family_detailsList.get(0).getAadhaarNo());

                    }
                    if (!employe_family_detailsList.isEmpty()) {
                        mainLayout_id.setVisibility(View.VISIBLE);
                        mViewPager.setAdapter(new Employee_Family_Details_Pager(this, employe_family_detailsList));
                    }


                } else {
                    Log.e("INSIDE IF", "inide if... ");
                    String DESCRIPTION = jsonObject1.getString("DESCRIPTION");
                    Dialog_popup dialog_popup = new Dialog_popup(this, "You are not register with us");
                    dialog_popup.show();

                }
            } catch (Exception e) {
                String error = String.valueOf(e);
                Dialog_popup dialog_popup = new Dialog_popup(this, error);
                dialog_popup.show();
            }
        } else {
            if (DeviceNetConnectionDetector.checkDataConnWifiMobile(this))
                Snackbar.make(mainLayout_id, getResources().getString(R.string.common_ServerConnection), Snackbar.LENGTH_LONG).show();
            else
                Snackbar.make(mainLayout_id, getResources().getString(R.string.common_checkNetConnection), Snackbar.LENGTH_LONG).show();
        }

    }

    private void StoreFamily_Details_DB(Integer viewPager_Position) {
        editor.putString("RELATION_AADHAAR_KEY", employe_family_detailsList.get(viewPager_Position).getAadhaarNo());
        editor.putString("RELATION_MEMBER_KEY", employe_family_detailsList.get(viewPager_Position).getMember_key());
        editor.putString("RELATION_RELATION_KEY", employe_family_detailsList.get(viewPager_Position).getRelation_key());
        editor.commit();
    }
}
