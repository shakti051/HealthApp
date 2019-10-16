package com.example.admin.health_app.Common_package;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.health_app.R;
import com.example.admin.health_app.interface_folder.SmsListener;
import com.example.admin.health_app.interface_folder.SmsReceiver;

import org.json.JSONObject;

import java.util.regex.Pattern;

/**
 * Created by AshuRajput on 1/8/2018.
 */

public class OTPAutoVerificationActivity extends AppCompatActivity implements View.OnClickListener{

  /*  this is for read otp*/
    /*final Pattern p = Pattern.compile( "(\\d{4})" );
    final Matcher m = p.matcher(messages[0].getMessageBody());
                    if ( m.find() ) {
        Log.e("SMSotp", String.valueOf(m.group()));
    }
    String[] singleCHAR= m.group().split("(?!^)");*/

    public Pattern p = Pattern.compile("(|^)\\d{6}");
    EditText foureET, threeET, secondET, firstET;
    String otpString, number1, number2,
            number3, number4, combineNumber;
    char OTP1, OTP2, OTP3, OTP4;
    private ProgressDialog progressDialog;
    SharedPreferences preferences;
    Button submitOtpBtn;
    TextView resend_code;
    String aadhaarString, OTPCOMBINE, mobileNumber;
    SharedPreferences.Editor editor;
    String tag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_auto_verification);
        setUpID();
        editor = getSharedPreferences("prefs", MODE_PRIVATE).edit();
        preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        aadhaarString = preferences.getString("AADHAAR_VALUE_KEY", "");
        mobileNumber = preferences.getString("MOBILE_NUMBER_KEY", "");
        Log.e("mobileNumber...", "" + mobileNumber + "\n" + aadhaarString);


        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText, String senderNum) {
                Log.d("Text", messageText);
                Log.e("TEXTMESSAGE", "messagetext... " + messageText);
                Log.e("TEXTNUMBER", "messagenumber... " + senderNum);

                try {
                    if (senderNum.equals("MD-RAKSHA")) {
                        Log.e("AAYYYA", "KABUTER.... ");
                        Log.e("TEXTMESSAGE", "messagetext... " + messageText);
                        for(int i=0;i<4;i++){
                            if(i==0){

                                OTP1=messageText.charAt(i);
                            }
                            else if(i==1){

                                OTP2=messageText.charAt(i);
                            }
                            else if(i==2){

                                OTP3=messageText.charAt(i);
                            }
                            else if(i==3){

                                OTP4=messageText.charAt(i);
                            }

                            Log.e("OTP1", "number... " + OTP1+"\n"+OTP2+"\n"+OTP3+"\n"+OTP4);
//                            firstET.setText(OTP1);
//                            secondET.setText(OTP2);
//                            threeET.setText(OTP3);
//                            foureET.setText(OTP4);
                        }
//
                        Toast.makeText(OTPAutoVerificationActivity.this, "Message: " + messageText, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(OTPAutoVerificationActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
//            startActivity(new Intent(this, LoginScreenActivity.class));
//            finish();
        }
    }


    private void setUpID() {

        submitOtpBtn = (AppCompatButton) findViewById(R.id.submitOtpBtnID);
        foureET = (EditText) findViewById(R.id.foureETID);
        threeET = (EditText) findViewById(R.id.ThreeETID);
        secondET = (EditText) findViewById(R.id.secondETID);
        firstET = (EditText) findViewById(R.id.firstETID);
        submitOtpBtn = (Button) findViewById(R.id.submitOtpBtnID);
        submitOtpBtn.setOnClickListener(this);
        resend_code = (TextView) findViewById(R.id.resend_code_id);
        resend_code.setOnClickListener(this);
        move_Edit_Text_fromOne();//focus to next EDIT TEXT
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitOtpBtnID:
                combineNumber = firstET.getText().toString() + secondET.getText().toString() + threeET.getText().toString() + foureET.getText().toString();
                Log.e("combineNumber", "" + combineNumber);
                boolean networkCheck = isNetworkAvailable();
                if (networkCheck) {
//
//                    API_HIT();
                } else {

                    Toast.makeText(this, "Please check network connection....", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.resend_code_id:
                boolean networkCheck1 = isNetworkAvailable();
                if (networkCheck1) {
//
//                    Resend_API();
                } else {

                    Toast.makeText(this, "Please check network connection....", Toast.LENGTH_LONG).show();
                }
                break;
        }

    }

    //*************Api JSON CREATE*********************
    public String JSON_Params() {
        String urlString = null;
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject.put("API", "RKA_OTP_STATUS");
            jsonObject.put("CURD_OPERATION", "U");
            jsonObject.put("LANG_TYPE", "ENG");
            jsonObject.put("ADHAAR_NO", aadhaarString);
            jsonObject.put("OTP", combineNumber);
            jsonObject1.put("ITEMS", jsonObject);
            urlString = jsonObject1.toString();
            Log.e("urlString.....", "" + urlString);

        } catch (Exception e) {
            String error = String.valueOf(e);

            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
        return urlString;
    }
//
//    public void API_HIT() {
//        tag = "otp_Verified";
//        try {
//            volley_asynclass = new Volley_Asynclass(this, JSON_Params(), "otpverified/", "Please Wait OTP verify...");
//
//        } catch (Exception e) {
//
//            String error = String.valueOf(e);
//
//            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
//        }
//    }


    //*******Network Connection Check*******************************************
    public boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public String JsonParams_resend_OTP() {
        String urlString = null;
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject.put("API", "RKA_OTP_RESEND");
            jsonObject.put("CURD_OPERATION", "G");

            jsonObject.put("ADHAAR_NO", aadhaarString);
            jsonObject.put("LANG_TYPE", "ENG");
            jsonObject.put("MOBILE_NUMBER", mobileNumber);
            jsonObject1.put("ITEMS", jsonObject);
            urlString = jsonObject1.toString();
            Log.e("urlString", "" + urlString);


        } catch (Exception e) {
            String error = String.valueOf(e);
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
        return urlString;
    }

//    public void Resend_API() {
//        tag = "resend_OTP";
//        try {
//            volley_asynclass = new Volley_Asynclass(this, JsonParams_resend_OTP(), "resendotp/", "Resend OTP...");
//
//        } catch (Exception e) {
//
//            String error = String.valueOf(e);
//
//            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
//        }
//    }

    public void otp_Seperate_no(int num) {
        int num4 = (num / 1) % 10;
        int num3 = (num / 10) % 10;
        int num2 = (num / 100) % 10;
        int num1 = (num / 1000) % 10;
        number1 = String.valueOf(num1);
        number2 = String.valueOf(num2);
        number3 = String.valueOf(num3);
        number4 = String.valueOf(num4);
        OTPCOMBINE = number1 + number2 + number3 + number4;



        Log.e("registration_screen...", "" + number4 + "\n" + number3 + "\n" + number2 + "\n" + number1);
    }

    public void popup(String message) {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.raksha_logo)
                // .setTitle("Future In Digital")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                })
//                .setNegativeButton("No", null)
                .show();

    }

    public void move_Edit_Text_fromOne() {
        firstET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                Log.e("onTextChanged", "" + count);
                Integer textlength1 = firstET.getText().length();

                if (textlength1 >= 1) {
                    secondET.requestFocus();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        secondET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                Log.e("onTextChanged", "" + count);
                Integer textlength1 = secondET.getText().length();

                if (textlength1 >= 1) {
                    threeET.requestFocus();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        threeET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                Log.e("onTextChanged", "" + count);
                Integer textlength1 = threeET.getText().length();

                if (textlength1 >= 1) {
                    foureET.requestFocus();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        foureET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                Log.e("onTextChanged", "" + count);
                Integer textlength1 = foureET.getText().length();

                if (textlength1 <= 0) {

//                    editTextMPIN_3ET.requestFocus();
                } else {
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
