package com.example.admin.health_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.admin.health_app.Common_package.LoginScreenActivity;
import com.example.admin.health_app.Common_package.MPIN_Activity;


/**
 * Created by AshuRajput on 12/15/2017.
 */

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    SharedPreferences preferences;
    String register, otpverify, loginSuccess, language_select, FORGETMPIN, USER_TYPE_MPIN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        register = preferences.getString("SELF_REGISTRATION_KEY", "");
        language_select = preferences.getString("LANGUAGE_SELECT_KEY", "");
        otpverify = preferences.getString("OTP_SUCCESSFUL_KEY", "");
        loginSuccess = preferences.getString("LOGIN_SUCCESS_KEY", "");
        FORGETMPIN = preferences.getString("FORGETMPIN_KEY", "");
        USER_TYPE_MPIN = preferences.getString("USER_TYPE_MPIN_KEY", "");
        Log.e("Language...getShare", "" + register + "\n" + otpverify + "\n" + loginSuccess + "\n" +
                USER_TYPE_MPIN + "\n" + FORGETMPIN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (loginSuccess.equals("SUCCESS")) {
                    Log.e("login_mein", "0");
                    Intent intent1 = new Intent(SplashScreen.this, MPIN_Activity.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent1);
                }
//            if (register.equals("SUCCESSFUL_SELF_REGISTER")) {
//                Log.e("register", "1");
//                Intent intent = new Intent(this, OTPAutoVerificationActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                startActivity(intent);
//
//            }
//            if (otpverify.equals("OTP_SUCCESSFUL_VERIFY")) {
//                Log.e("otpverify", "2");
//                startActivity(new Intent(SplashScreen.this, LoginScreenActivity.class));
//                finish();
//            }
//            if (loginSuccess.equals("LOGIN_SUCCESFUL")) {
//                Log.e("login_mein", "aaya");
//                Intent intent = new Intent(this, MPIN_Activity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                startActivity(intent);
//            }
                else if (FORGETMPIN.equals("FORGET_MPIN")) {
                    Log.e("login_mein", "3");
                    Intent intent = new Intent(SplashScreen.this, LoginScreenActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);

                } else {
                    Intent intent = new Intent(SplashScreen.this, CurrentLocation.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);
                }
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

}
