package com.example.admin.health_app.Common_package;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.health_app.CurrentLocation;
import com.example.admin.health_app.Doctor_Side.Activity.Doctor_Home_Screen;
import com.example.admin.health_app.Pathology.Activity.Pathology_Home_Screen;
import com.example.admin.health_app.Patient_Side_Scrren.Activity.Patient_Home_Screen;

import com.example.admin.health_app.Pharmacy.Activity.Pharmacy_Home_Screen;
import com.example.admin.health_app.R;
import com.example.admin.health_app.Radiology.Activity.Radiology_Home_Screen;


/**
 * Created by AshuRajput on 1/8/2018.
 */

public class MPIN_Activity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton submitMpinBtn;
    private    String loginStatus, MPIN_ValueCombine = null, MPIN_GET_SHARE, DESCRIPTION, GENERAL_MPIN, Pathology_MPIN, pharmacyMPIN;
    private   EditText editTextMPIN_1ET, editTextMPIN_2ET, editTextMPIN_3ET, editTextMPIN_4ET;
    private  SharedPreferences.Editor editor;
    private   SharedPreferences preferences;
    private  TextView forgetMPIN_TV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mpin_activity);
        setUpID();
        editor = getSharedPreferences("prefs", MODE_PRIVATE).edit();
        preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        loginStatus = preferences.getString("USER_TYPE_KEY", "");
        DESCRIPTION = preferences.getString("DESCRIPTION_KEY", "");
        MPIN_GET_SHARE = preferences.getString("MPIN_VALUE_KEY", "");
        GENERAL_MPIN = preferences.getString("GENERAL_MPIN_VALUE_KEY", "");
        Pathology_MPIN = preferences.getString("Pathology_MPIN_VALUE_KEY", "");
        pharmacyMPIN = preferences.getString("Pharmacy_MPIN_VALUE_KEY", "");
        Log.e("loginStatus....", "" + loginStatus + "\n" + MPIN_GET_SHARE + "\n" + GENERAL_MPIN + "\n" + Pathology_MPIN);

        //*************Check After Login******************************************
        if (loginStatus.equals("6")) {
            Log.e("andar_aaya_kya", "");

            if (MPIN_GET_SHARE != null && !MPIN_GET_SHARE.equals("")) {
                submitMpinBtn.setText("PROCEED");
                forgetMPIN_TV.setVisibility(View.VISIBLE);
            } else {
                submitMpinBtn.setText("SET");
                forgetMPIN_TV.setVisibility(View.GONE);
            }
        }
//        else if (MPIN_ValueCombine != null && MPIN_ValueCombine.equals("")) {
//            submitMpinBtn.setText("PROCEED");
//
//        }

        if (loginStatus.equals("2")) {

            if (GENERAL_MPIN != null && !GENERAL_MPIN.equals("")) {

                submitMpinBtn.setText("PROCEED");
                forgetMPIN_TV.setVisibility(View.VISIBLE);
            } else {
                submitMpinBtn.setText("SET");
                forgetMPIN_TV.setVisibility(View.GONE);
            }
        }
        if (loginStatus.equals("Pathology")) {
            if (Pathology_MPIN != null && !Pathology_MPIN.equals("")) {

                submitMpinBtn.setText("PROCEED");
                forgetMPIN_TV.setVisibility(View.VISIBLE);
            } else {
                submitMpinBtn.setText("SET");
                forgetMPIN_TV.setVisibility(View.GONE);
            }
        }
//        else if (MPIN_ValueCombine != null && MPIN_ValueCombine.equals("")) {
//            submitMpinBtn.setText("PROCEED");
//
//        }


        if (loginStatus.equals("1")) {
            if (pharmacyMPIN != null && !pharmacyMPIN.equals("")) {

                submitMpinBtn.setText("PROCEED");
                forgetMPIN_TV.setVisibility(View.VISIBLE);
            } else {
                submitMpinBtn.setText("SET");
                forgetMPIN_TV.setVisibility(View.GONE);
            }
        }

    }

    private void setUpID() {
        submitMpinBtn = (AppCompatButton) findViewById(R.id.submitMpinBtnID);
        submitMpinBtn.setOnClickListener(this);
        editTextMPIN_1ET = (EditText) findViewById(R.id.editTextMPIN_1_id);
        editTextMPIN_2ET = (EditText) findViewById(R.id.editTextMPIN_2_id);
        editTextMPIN_3ET = (EditText) findViewById(R.id.editTextMPIN_3_id);
        editTextMPIN_4ET = (EditText) findViewById(R.id.editTextMPIN_4_id);
        forgetMPIN_TV = (TextView) findViewById(R.id.forgetMPIN_TV_id);
        forgetMPIN_TV.setOnClickListener(this);

        move_Edit_Text_fromOne();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitMpinBtnID:

                if (loginStatus.equals("6")) {
                    if (submitMpinBtn.getText().toString().equals("SET")) {

                        if (editTextMPIN_1ET.getText().toString().equals("")) {

                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else if (editTextMPIN_2ET.getText().toString().equals("")) {
                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else if (editTextMPIN_3ET.getText().toString().equals("")) {
                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else if (editTextMPIN_4ET.getText().toString().equals("")) {
                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else {
                            String value1 = editTextMPIN_1ET.getText().toString();
                            String value2 = editTextMPIN_2ET.getText().toString();
                            String value3 = editTextMPIN_3ET.getText().toString();
                            String value4 = editTextMPIN_4ET.getText().toString();
                            MPIN_ValueCombine = value1 + value2 + value3 + value4;
                            Log.e("MPIN_ValueCombine....", "" + MPIN_ValueCombine);
                            editor.putString("MPIN_VALUE_KEY", MPIN_ValueCombine);
                            editor.apply();
                            Log.e("isme_kab_aaya....", "");
                            InputMethodManager inputManager = (InputMethodManager)
                                    getSystemService(MPIN_Activity.this.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                            Intent intent = new Intent(this, Patient_Home_Screen.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);
                            finish();
                        }

                    } else if (submitMpinBtn.getText().toString().equals("PROCEED")) {
                        String value1 = editTextMPIN_1ET.getText().toString();
                        String value2 = editTextMPIN_2ET.getText().toString();
                        String value3 = editTextMPIN_3ET.getText().toString();
                        String value4 = editTextMPIN_4ET.getText().toString();
                        MPIN_ValueCombine = value1 + value2 + value3 + value4;
                        if (MPIN_ValueCombine.equals(MPIN_GET_SHARE)) {
                            InputMethodManager inputManager = (InputMethodManager)
                                    getSystemService(MPIN_Activity.this.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                            Intent intent = new Intent(this, Patient_Home_Screen.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);
                            finish();


                        } else {

                            Toast.makeText(this, "Please Enter valid MPIN...", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                if (loginStatus.equals("2")) {
                    if (submitMpinBtn.getText().toString().equals("SET")) {

                        if (editTextMPIN_1ET.getText().toString().equals("")) {

                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else if (editTextMPIN_2ET.getText().toString().equals("")) {
                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else if (editTextMPIN_3ET.getText().toString().equals("")) {
                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else if (editTextMPIN_4ET.getText().toString().equals("")) {
                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else {
                            String value1 = editTextMPIN_1ET.getText().toString();
                            String value2 = editTextMPIN_2ET.getText().toString();
                            String value3 = editTextMPIN_3ET.getText().toString();
                            String value4 = editTextMPIN_4ET.getText().toString();
                            MPIN_ValueCombine = value1 + value2 + value3 + value4;
                            Log.e("MPIN_ValueCombine....", "" + MPIN_ValueCombine);
                            editor.putString("GENERAL_MPIN_VALUE_KEY", MPIN_ValueCombine);
                            editor.commit();
                            Log.e("isme_kab_aaya....", "");
                            InputMethodManager inputManager = (InputMethodManager)
                                    getSystemService(MPIN_Activity.this.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
//                            Intent intent = new Intent(this, General_Practioner_Home_Screen.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
                            finish();

                        }

                    } else if (submitMpinBtn.getText().toString().equals("PROCEED")) {
                        String value1 = editTextMPIN_1ET.getText().toString();
                        String value2 = editTextMPIN_2ET.getText().toString();
                        String value3 = editTextMPIN_3ET.getText().toString();
                        String value4 = editTextMPIN_4ET.getText().toString();
                        MPIN_ValueCombine = value1 + value2 + value3 + value4;
                        if (MPIN_ValueCombine.equals(GENERAL_MPIN)) {
                            InputMethodManager inputManager = (InputMethodManager)
                                    getSystemService(MPIN_Activity.this.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
//                            Intent intent = new Intent(this, General_Practioner_Home_Screen.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                            startActivity(intent);
                            finish();
                        } else {

                            Toast.makeText(this, "Please Enter valid MPIN...", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                if (loginStatus.equals("Pathology")) {
                    if (submitMpinBtn.getText().toString().equals("SET")) {

                        if (editTextMPIN_1ET.getText().toString().equals("")) {

                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else if (editTextMPIN_2ET.getText().toString().equals("")) {
                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else if (editTextMPIN_3ET.getText().toString().equals("")) {
                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else if (editTextMPIN_4ET.getText().toString().equals("")) {
                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else {
                            String value1 = editTextMPIN_1ET.getText().toString();
                            String value2 = editTextMPIN_2ET.getText().toString();
                            String value3 = editTextMPIN_3ET.getText().toString();
                            String value4 = editTextMPIN_4ET.getText().toString();
                            MPIN_ValueCombine = value1 + value2 + value3 + value4;
                            Log.e("MPIN_ValueCombine....", "" + MPIN_ValueCombine);
                            editor.putString("Pathology_MPIN_VALUE_KEY", MPIN_ValueCombine);
                            editor.commit();
                            Log.e("isme_kab_aaya....", "");
                            InputMethodManager inputManager = (InputMethodManager)
                                    getSystemService(MPIN_Activity.this.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
//                            Intent intent = new Intent(this, Pathology_Home_Activity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                            startActivity(intent);
                            finish();

                        }

                    } else if (submitMpinBtn.getText().toString().equals("PROCEED")) {
                        String value1 = editTextMPIN_1ET.getText().toString();
                        String value2 = editTextMPIN_2ET.getText().toString();
                        String value3 = editTextMPIN_3ET.getText().toString();
                        String value4 = editTextMPIN_4ET.getText().toString();
                        MPIN_ValueCombine = value1 + value2 + value3 + value4;
                        if (MPIN_ValueCombine.equals(Pathology_MPIN)) {
                            InputMethodManager inputManager = (InputMethodManager)
                                    getSystemService(MPIN_Activity.this.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
//                            Intent intent = new Intent(this, Pathology_Home_Activity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                            startActivity(intent);
                            finish();
                        } else {

                            Toast.makeText(this, "Please Enter valid MPIN...", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                if (loginStatus.equals("1")) {
                    if (submitMpinBtn.getText().toString().equals("SET")) {

                        if (editTextMPIN_1ET.getText().toString().equals("")) {

                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else if (editTextMPIN_2ET.getText().toString().equals("")) {
                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else if (editTextMPIN_3ET.getText().toString().equals("")) {
                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else if (editTextMPIN_4ET.getText().toString().equals("")) {
                            Toast.makeText(this, "Please fill MPIN...", Toast.LENGTH_LONG).show();

                        } else {
                            String value1 = editTextMPIN_1ET.getText().toString();
                            String value2 = editTextMPIN_2ET.getText().toString();
                            String value3 = editTextMPIN_3ET.getText().toString();
                            String value4 = editTextMPIN_4ET.getText().toString();
                            MPIN_ValueCombine = value1 + value2 + value3 + value4;
                            Log.e("MPIN_ValueCombine....", "" + MPIN_ValueCombine);
                            editor.putString("Pharmacy_MPIN_VALUE_KEY", MPIN_ValueCombine);
                            editor.commit();
                            Log.e("isme_kab_aaya....", "");
                            InputMethodManager inputManager = (InputMethodManager)
                                    getSystemService(MPIN_Activity.this.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
//                            Intent intent = new Intent(this, Pathology_Home_Activity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                            startActivity(intent);
                            finish();

                        }

                    } else if (submitMpinBtn.getText().toString().equals("PROCEED")) {
                        String value1 = editTextMPIN_1ET.getText().toString();
                        String value2 = editTextMPIN_2ET.getText().toString();
                        String value3 = editTextMPIN_3ET.getText().toString();
                        String value4 = editTextMPIN_4ET.getText().toString();
                        MPIN_ValueCombine = value1 + value2 + value3 + value4;
                        if (MPIN_ValueCombine.equals(pharmacyMPIN)) {
                            InputMethodManager inputManager = (InputMethodManager)
                                    getSystemService(MPIN_Activity.this.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
//                            Intent intent = new Intent(this, Pathology_Home_Activity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                            startActivity(intent);
                            finish();
                        } else {

                            Toast.makeText(this, "Please Enter valid MPIN...", Toast.LENGTH_LONG).show();
                        }
                    }
                }


                break;
            case R.id.forgetMPIN_TV_id:
                if (loginStatus.equals("6")) {
                    Intent intent = new Intent(this, CurrentLocation.class);
                    SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.remove("MPIN_VALUE_KEY");
                    editor.remove("LOGIN_SUCCESS_KEY");
                    editor.putString("FORGETMPIN_KEY", "FORGET_MPIN");
                    editor.apply();


                    startActivity(intent);
                    finish();
                }

                if (loginStatus.equals("2")) {
                    Intent intent = new Intent(this, CurrentLocation.class);

                    Intent intent1 = new Intent(this, LoginScreenActivity.class);
                    SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.remove("GENERAL_MPIN_VALUE_KEY");
                    editor.remove("LOGIN_SUCCESS_KEY");
                    editor.putString("FORGETMPIN_KEY", "FORGET_MPIN");
                    editor.apply();
                    startActivity(intent1);
                    finish();
                }
                if (loginStatus.equals("Pathology")) {
                    Intent intent = new Intent(this, CurrentLocation.class);
                    SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.remove("Pathology_MPIN_VALUE_KEY");
                    editor.remove("LOGIN_SUCCESS_KEY");
                    editor.putString("FORGETMPIN_KEY", "FORGET_MPIN");
                    editor.apply();
                    startActivity(intent);
                    finish();
                }
                if (loginStatus.equals("1")) {
                    Intent intent = new Intent(this, CurrentLocation.class);
                    SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.remove("Pharmacy_MPIN_VALUE_KEY");
                    editor.remove("LOGIN_SUCCESS_KEY");
                    editor.putString("FORGETMPIN_KEY", "FORGET_MPIN");
                    editor.apply();


                    startActivity(intent);
                    finish();
                }
                break;
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.raksha_logo)
                // .setTitle("Future In Digital")
                .setMessage("Are you confirm to exist from application")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }

                })
//                .setNegativeButton("No", null)
                .show();


        super.onBackPressed();
    }

    public void move_Edit_Text_fromOne() {
        editTextMPIN_1ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                Log.e("onTextChanged", "" + count);
                Integer textlength1 = editTextMPIN_1ET.getText().length();

                if (textlength1 >= 1) {
                    editTextMPIN_2ET.requestFocus();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        editTextMPIN_2ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                Log.e("onTextChanged", "" + count);
                Integer textlength1 = editTextMPIN_2ET.getText().length();

                if (textlength1 >= 1) {
                    editTextMPIN_3ET.requestFocus();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextMPIN_3ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                Log.e("onTextChanged", "" + count);
                Integer textlength1 = editTextMPIN_3ET.getText().length();

                if (textlength1 >= 1) {
                    editTextMPIN_4ET.requestFocus();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextMPIN_4ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                Log.e("onTextChanged", "" + count);
                Integer textlength1 = editTextMPIN_3ET.getText().length();

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