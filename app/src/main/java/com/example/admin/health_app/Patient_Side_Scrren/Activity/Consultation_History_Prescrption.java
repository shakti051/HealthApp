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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.health_app.Common_package.Dialog_popup;
import com.example.admin.health_app.Model.Employe_Family_Details_Model;
import com.example.admin.health_app.Parse_Web_Services.Parse_json;
import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Consultation_History_Adapter;
import com.example.admin.health_app.R;
import com.example.admin.health_app.VolleyApiHit.DeviceNetConnectionDetector;
import com.example.admin.health_app.VolleyApiHit.Volley_Asynclass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Consultation_History_Prescrption extends AppCompatActivity
        implements Consultation_History_Adapter.OnClickListerner_Adapter, AdapterView.OnItemSelectedListener, Volley_Asynclass.VolleyResponse {
    private RecyclerView historyList_RV;
    private LinearLayout mainLayout_id,linear_layout_recycleView;
    private ArrayAdapter arrayAdapter_family_Details;
    private Spinner spinner_Family_details;
    private String employeId, tag, user_id;
    private SharedPreferences preferences;
    private ArrayList<String> familyDetails = new ArrayList<>();
    private List<Employe_Family_Details_Model> employe_family_detailsList;
    private TextView notdataFoundTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conultation_history_prescription);
        setIds();
        preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        employeId = preferences.getString("EMPLOYEEID_KEY", "");
        user_id = preferences.getString("USER_ID_KEY", "");
        Log.e("employeId", "" + employeId);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ApiHit();
    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        mainLayout_id = (LinearLayout) findViewById(R.id.mainLayout_id);
        linear_layout_recycleView = (LinearLayout) findViewById(R.id.linear_layout_recycleView);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        spinner_Family_details = (Spinner) findViewById(R.id.selectFamilyDetail_SP);
        spinner_Family_details.setOnItemSelectedListener(this);
        toolbarTitleText.setText("Consultation History");
        historyList_RV = (RecyclerView) findViewById(R.id.prescriptionList_RV);
        notdataFoundTV = (TextView) findViewById(R.id.notdataFoundTV);
        historyList_RV.setLayoutManager(new LinearLayoutManager(this));


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
    public void oOnclickdata(String position) {
        Log.e("oOnclickdata", "." + position);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (spinner_Family_details.getSelectedItem().toString().equals("Please select type")) {

        } else {
            Log.e("position", "" + position + "\n" + spinner_Family_details + "\n" +
                    employe_family_detailsList.get(position - 1).getMember_key());
//            relation_key = Integer.parseInt(arrayList.get(position - 1).get("RELATION_KEY"));
//            member_key = Integer.parseInt(arrayList.get(position - 1).get("MEMBER_KEY"));
            ApiHit_view_history(employe_family_detailsList.get(position - 1).getMember_key());
//
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void ApiHit() {
        tag = "family_details";
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

        if (tag.equals("family_details")) {
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
                            familyDetails.add("Please select type");
                            for (int i = 0; i < employe_family_detailsList.size(); i++) {
                                mainLayout_id.setVisibility(View.VISIBLE);
                                familyDetails.add(employe_family_detailsList.get(i).getName() + "(" + employe_family_detailsList.get(i).getRelation_name() + ")");
                                arrayAdapter_family_Details = new ArrayAdapter(this, android.R.layout.simple_spinner_item, familyDetails);
                                arrayAdapter_family_Details.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner_Family_details.setAdapter(arrayAdapter_family_Details);

                            }


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
        } else if (tag.equals("View_History")) {
            Log.e("loadedString....", "" + loadedString);
            if (loadedString != null && !loadedString.equals("") && !loadedString.equals("ExceptionCaught")) {

                try {
                    JSONObject jsonObject = new JSONObject(loadedString);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("RESPONSE");
                    String responseCode = jsonObject1.getString("RESPONSECODE");
                    if (responseCode.equals("0")) {
                        JSONArray jsonArray = jsonObject1.getJSONArray("DETAILS");
                        Parse_json parse_json = new Parse_json();

                        notdataFoundTV.setVisibility(View.GONE);
                            linear_layout_recycleView.setVisibility(View.VISIBLE);
                            historyList_RV.setAdapter(new Consultation_History_Adapter(this, parse_json.parse_patient_view_History(jsonArray)));

                    }
                    else{
                        notdataFoundTV.setVisibility(View.VISIBLE);
                        linear_layout_recycleView.setVisibility(View.GONE);

                    }

                } catch (Exception e) {
                }
            }
            else {
                if (DeviceNetConnectionDetector.checkDataConnWifiMobile(this))
                    Snackbar.make(mainLayout_id, getResources().getString(R.string.common_ServerConnection), Snackbar.LENGTH_LONG).show();
                else
                    Snackbar.make(mainLayout_id, getResources().getString(R.string.common_checkNetConnection), Snackbar.LENGTH_LONG).show();

            }

        }

    }

    private void ApiHit_view_history(String member_key) {
        tag = "View_History";
        new Volley_Asynclass(this, JsonParams_view_history(member_key), "viewhistory/", "View History...");
    }

    private String JsonParams_view_history(String member_key) {
        String urlString = null;
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject.put("API", "RAKSHA_VIEW_HISTORY");
            jsonObject.put("CURD_OPERATION", "G");
            jsonObject.put("USER_ID", user_id);
            jsonObject.put("MEMBER_KEY", member_key);
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

}
