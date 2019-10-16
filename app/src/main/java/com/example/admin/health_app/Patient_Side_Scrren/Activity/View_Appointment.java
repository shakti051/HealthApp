package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.health_app.Common_package.Dialog_popup;
import com.example.admin.health_app.Parse_Web_Services.Parse_json;
import com.example.admin.health_app.Patient_Side_Scrren.Adapter.View_Appointment_Adapter;
import com.example.admin.health_app.R;
import com.example.admin.health_app.VolleyApiHit.DeviceNetConnectionDetector;
import com.example.admin.health_app.VolleyApiHit.Volley_Asynclass;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Admin on 5/23/2018.
 */

public class View_Appointment extends AppCompatActivity implements Volley_Asynclass.VolleyResponse {
    private RecyclerView consult_History_RV;
    private SharedPreferences preferences;
    private String user_id;
    private LinearLayout linear_layout_recycleView;
    private TextView no_data_found_TVid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_consult_history);
        setIds();
        preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        user_id = preferences.getString("USER_ID_KEY", "");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ApiHit();
    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
         no_data_found_TVid = (TextView) findViewById(R.id.no_data_found_TVid);
        linear_layout_recycleView = (LinearLayout) findViewById(R.id.linear_layout_recycleView);
        toolbarTitleText.setText("View Appointments");
        consult_History_RV = (RecyclerView) findViewById(R.id.consult_History_RV);
        consult_History_RV.setLayoutManager(new LinearLayoutManager(this));
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
    private void ApiHit() {

        new Volley_Asynclass(this, JsonParams_View_Appointment(), "viewappointments/", "Login...");
    }

    private String JsonParams_View_Appointment() {
        String urlString = null;
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject.put("API", "RAKSHA_VIEW_APPOINTMENT");
            jsonObject.put("CURD_OPERATION", "G");
            jsonObject.put("USER_ID", user_id);
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
        Log.e("loadedString", "" + loadedString);
        if (loadedString != null && !loadedString.equals("") && !loadedString.equals("ExceptionCaught")) {

            try {
                JSONObject jsonObject = new JSONObject(loadedString);
                JSONObject jsonObject1 = jsonObject.getJSONObject("RESPONSE");
                String responseCode = jsonObject1.getString("RESPONSECODE");
                if (responseCode.equals("0")) {
                    JSONArray jsonArray = jsonObject1.getJSONArray("DETAILS");
                    Parse_json parse_json = new Parse_json();

                    consult_History_RV.setAdapter(new View_Appointment_Adapter(this, parse_json.parse_view_Appointment(jsonArray)));
                } else {
                    no_data_found_TVid.setVisibility(View.VISIBLE);
                    linear_layout_recycleView.setVisibility(View.GONE);
                }
            } catch (Exception e) {
            }
        } else {
            if (DeviceNetConnectionDetector.checkDataConnWifiMobile(this))
                Snackbar.make(linear_layout_recycleView, getResources().getString(R.string.common_ServerConnection), Snackbar.LENGTH_LONG).show();
            else
                Snackbar.make(linear_layout_recycleView, getResources().getString(R.string.common_checkNetConnection), Snackbar.LENGTH_LONG).show();

        }
    }
}
