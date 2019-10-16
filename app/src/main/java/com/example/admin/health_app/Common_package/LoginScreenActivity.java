package com.example.admin.health_app.Common_package;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.health_app.R;
import com.example.admin.health_app.Validation;
import com.example.admin.health_app.VolleyApiHit.DeviceNetConnectionDetector;
import com.example.admin.health_app.VolleyApiHit.Volley_Asynclass;

import org.json.JSONObject;

/**
 * Created by AshuRajput on 1/13/2018.
 */

public class LoginScreenActivity extends AppCompatActivity implements View.OnClickListener, Volley_Asynclass.VolleyResponse {

    private EditText user_EmailET, passwordET;
    private AppCompatButton submitLoginBtn;
    private LinearLayout mainLayout;
    private TextView selfRegister_TV;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_activity);
        editor = getSharedPreferences("prefs", MODE_PRIVATE).edit();
        setUpID();

    }

    private void setUpID() {

        mainLayout = (LinearLayout) findViewById(R.id.mainLayoutID);
        user_EmailET = (EditText) findViewById(R.id.usernameETID);
        passwordET = (EditText) findViewById(R.id.passwordETID);
        submitLoginBtn = (AppCompatButton) findViewById(R.id.submitLoginBtnID);
        submitLoginBtn.setOnClickListener(this);
        selfRegister_TV = (TextView) findViewById(R.id.selfRegister_TV_id);
        selfRegister_TV.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selfRegister_TV_id:
//                startActivity(new Intent(this, SelfRegistrationActivity.class));
                break;
            case R.id.submitLoginBtnID:
                login_Validate();
                break;
        }
    }

    private void login_Validate() {
//        if (!Validation.emailAddressIsValid(user_EmailET.getText().toString(), user_EmailET)) {
//        }
         if (!Validation.matchPassword(passwordET.getText().toString(), passwordET)) {
        } else {
            try {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(LoginScreenActivity.this.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                Validate();
            } catch (Exception e) {
                e.printStackTrace();
                String srror = String.valueOf(e);
                Dialog_popup dialog_popup = new Dialog_popup(this, srror);
                dialog_popup.show();
                Log.e("error", "" + srror);
            }
        }

    }
    private void Validate() {
        if(!Validation.emailAddressIsValid(user_EmailET.getText().toString(),user_EmailET)){
        }

        else if(!Validation.matchPassword(passwordET.getText().toString(),passwordET)){
        }
        else {
            try {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(LoginScreenActivity.this.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                ApiHit();
//                if(user_EmailET.getText().toString().equals("patient@gmail.com")){
//                    Intent intent=new Intent(LoginScreenActivity.this,MPIN_Activity.class);
//                    intent.putExtra("EMAIL_ID","patient@gmail.com");
//                    startActivity(intent);
//                    finish();
//                }
//                else if(user_EmailET.getText().toString().equals("doctor@gmail.com")){
//                    Intent intent=new Intent(LoginScreenActivity.this,MPIN_Activity.class);
//                    intent.putExtra("EMAIL_ID","doctor@gmail.com");
//                    startActivity(intent);
//                    finish();
//
//                }
//                else if(user_EmailET.getText().toString().equals("pathology@gmail.com")){
//                    Intent intent=new Intent(LoginScreenActivity.this,MPIN_Activity.class);
//                    intent.putExtra("EMAIL_ID","pathology@gmail.com");
//                    startActivity(intent);
//                    finish();
//                }
//                else if(user_EmailET.getText().toString().equals("pharmacy@gmail.com")){
//                    Intent intent=new Intent(LoginScreenActivity.this,MPIN_Activity.class);
//                    intent.putExtra("EMAIL_ID","pharmacy@gmail.com");
//                    startActivity(intent);
//                    finish();
//                }
//                else if(user_EmailET.getText().toString().equals("hospital@gmail.com")){
//                    Intent intent=new Intent(LoginScreenActivity.this,MPIN_Activity.class);
//                    intent.putExtra("EMAIL_ID","hospital@gmail.com");
//                    startActivity(intent);
//                    finish();
//                }
//                else if(user_EmailET.getText().toString().equals("radiology@gmail.com")){
//                    Intent intent=new Intent(LoginScreenActivity.this,MPIN_Activity.class);
//                    intent.putExtra("EMAIL_ID","radiology@gmail.com");
//                    startActivity(intent);
//                    finish();
//                }
//                else{
//                    Snackbar.make(mainLayout,"Please enter valid email id...",Snackbar.LENGTH_LONG).show();
//                }
////                ApiHit();
            } catch (Exception e) {
                e.printStackTrace();
                String srror = String.valueOf(e);
//                Dialog_popup dialog_popup=new Dialog_popup(this,srror);
//                dialog_popup.show();
                Log.e("error", "" + srror);
            }
        }

    }

    private void ApiHit() {
        new Volley_Asynclass(this, JsonParams(), "loginvalidate/", "Login...");
    }

    private String JsonParams() {
        String urlString = null;
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject.put("API", "RAKSHA_USERPROFILE_LOGIN_CHECK");
            jsonObject.put("CURD_OPERATION", "G");
            jsonObject.put("EMAIL", user_EmailET.getText().toString());
            jsonObject.put("PASSWORD", passwordET.getText().toString());
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

                    Log.e("INSIDE IF", "inide if... ");
                    editor.putString("USER_TYPE_KEY", jsonObject1.getString("USER_TYPE"));
                    editor.putString("USER_ID_KEY", jsonObject1.getString("USER_ID"));
                    editor.putString("EMPLOYEEID_KEY", jsonObject1.getString("EMPLOYEEID"));
                    editor.putString("LOGIN_SUCCESS_KEY", jsonObject1.getString("LOGIN"));
                    editor.apply();
                    Intent mpinIntent = new Intent(this, MPIN_Activity.class);
                    startActivity(mpinIntent);
                    finish();

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
            if (DeviceNetConnectionDetector.checkDataConnWifiMobile(LoginScreenActivity.this))
                Snackbar.make(mainLayout, getResources().getString(R.string.common_ServerConnection), Snackbar.LENGTH_LONG).show();
            else
                Snackbar.make(mainLayout, getResources().getString(R.string.common_checkNetConnection), Snackbar.LENGTH_LONG).show();
        }

    }

}
