package com.example.admin.health_app.Patient_Side_Scrren.Follow_Up_NewCase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.health_app.Common_package.Dialog_popup;
import com.example.admin.health_app.Model.Doctor_Detail_List_Model;
import com.example.admin.health_app.Model.Employe_Family_Details_Model;
import com.example.admin.health_app.Parse_Web_Services.Parse_json;
import com.example.admin.health_app.Patient_Side_Scrren.Activity.Patient_Book_Now;
import com.example.admin.health_app.Patient_Side_Scrren.Activity.Patient_Home_Screen;
import com.example.admin.health_app.R;
import com.example.admin.health_app.View_Pager.Doctor_View_pager_adapter;
import com.example.admin.health_app.VolleyApiHit.DeviceNetConnectionDetector;
import com.example.admin.health_app.VolleyApiHit.Volley_Asynclass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 5/17/2018.
 */

public class New_Case extends AppCompatActivity implements View.OnClickListener, Volley_Asynclass.VolleyResponse {
    private ViewPager mViewPager;
    private ImageView left_arrow, right_arrow, toolbar_Righr_icon;
    private LinearLayout mainLayout_id;
    private TextView doctorNameTVID, qualificationTV, addressTV;
    private Button bookingAppointmentBTN;
    private Integer position_pager = 0;
private String organ_issue;
    private List<Doctor_Detail_List_Model> doctor_detail_listListModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggested_pcp);
        setIds();
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            organ_issue= extras.getString("Organ_Issue");
            Log.e("Organ_Issue",""+organ_issue);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ApiHit();


    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Doctor Details");
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mainLayout_id = (LinearLayout) findViewById(R.id.mainLayout_id);

        left_arrow = (ImageView) findViewById(R.id.left_nav);
        qualificationTV = (TextView) findViewById(R.id.qualificationTV);
        addressTV = (TextView) findViewById(R.id.addressTV);
        right_arrow = (ImageView) findViewById(R.id.right_nav);
        bookingAppointmentBTN = (Button) findViewById(R.id.bookingAppointmentBTN);
        toolbar_Righr_icon = (ImageView) findViewById(R.id.toolBarRightIconID);
        toolbar_Righr_icon.setImageResource(R.drawable.home);
        toolbar_Righr_icon.setOnClickListener(this);
        right_arrow.setOnClickListener(this);
        left_arrow.setOnClickListener(this);
        bookingAppointmentBTN.setOnClickListener(this);

        getPosition_of_viewPager();
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

    private void getPosition_of_viewPager() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position_pager = position;
                if (position_pager == 0) {

                    Toast.makeText(New_Case.this, ".0 position.....", Toast.LENGTH_LONG).show();
                    set_Doc_Details(position_pager);
                } else {
                    Toast.makeText(New_Case.this, "after 0 position......", Toast.LENGTH_LONG).show();
                    set_Doc_Details(position_pager);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void set_Doc_Details(int position) {
        qualificationTV.setText(doctor_detail_listListModel.get(position).getDoc_Qualification());
        addressTV.setText(doctor_detail_listListModel.get(position).getDocAddress());
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

            case R.id.bookingAppointmentBTN:
                if (position_pager == 0) {
                    Log.e("if_condition", "" + position_pager);
                            Doctor_Detail_List_Model userItem = doctor_detail_listListModel.get(0);

                            Intent yourIntent = new Intent(this, Patient_Book_Now.class);
                            Bundle b = new Bundle();
                            b.putSerializable("Doctor_details", userItem);
                    yourIntent.putExtra("Organ_Issue",organ_issue);
                            yourIntent.putExtras(b); //pass bundle to your intent
                            startActivity(yourIntent);
                } else {
                    Log.e("else_condtion", "" + position_pager);
                    //Pass Vlaue from model class through activity patient_Home_Screen.......

                            Doctor_Detail_List_Model userItem = doctor_detail_listListModel.get(position_pager);

                            Intent yourIntent = new Intent(this, Patient_Book_Now.class);
                            Bundle b = new Bundle();
                            b.putSerializable("Doctor_details", userItem);
                    yourIntent.putExtra("Organ_Issue",organ_issue);
                            yourIntent.putExtras(b); //pass bundle to your intent
                            startActivity(yourIntent);
                }

                break;
            case R.id.toolBarRightIconID:
                Intent intent = new Intent(this, Patient_Home_Screen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
    }

    private void ApiHit() {
        new Volley_Asynclass(this, JsonParams_DoctorList(), "patientnewcase/", "Please Wait...");
    }

    private String JsonParams_DoctorList() {
        String urlString = null;
        Employe_Family_Details_Model employe_family_details = new Employe_Family_Details_Model();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject.put("API", "RAKSHA_DOCTOR_NEW_CASE");
            jsonObject.put("CURD_OPERATION", "G");
            jsonObject.put("PIN_CODE", "201009");
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
                    doctor_detail_listListModel = new ArrayList<>();
                    JSONArray jsonArray = jsonObject1.getJSONArray("DETAILS");
                    Parse_json parse_json = new Parse_json();

                    doctor_detail_listListModel = parse_json.parse_Doc_Details(jsonArray);
                    Log.e("doctor_detail_listList", "" + doctor_detail_listListModel.get(0).getDocName());
                    if (!doctor_detail_listListModel.isEmpty()) {
                        mViewPager.setAdapter(new Doctor_View_pager_adapter(this, doctor_detail_listListModel));
                        Log.e("doctor_detail_listList", "" + doctor_detail_listListModel.get(0).getDocName());
                    }
                } else {
                    Log.e("INSIDE IF", "inide if... ");
//                    String DESCRIPTION = jsonObject1.getString("DESCRIPTION");
//                    Dialog_popup dialog_popup = new Dialog_popup(this, "You are not register with us");
//                    dialog_popup.show();

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

}
