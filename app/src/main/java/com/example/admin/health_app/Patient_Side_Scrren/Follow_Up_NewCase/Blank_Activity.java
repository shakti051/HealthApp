package com.example.admin.health_app.Patient_Side_Scrren.Follow_Up_NewCase;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.admin.health_app.Patient_Side_Scrren.Activity.Patient_description_details;
import com.example.admin.health_app.R;

public class Blank_Activity extends Activity {
    private Button confirmBTN;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private LinearLayout linear_LY;
    private int selectedId;
    private String radioButton_Text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.follow_up_new_case);
        setIds();
        //getVlaue from model class through activity patient_Home_Screen.......

//        Intent i = getIntent();
//        Bundle bundle = i.getExtras();
//        Employe_Family_Details user = (Employe_Family_Details) bundle.getSerializable("user");
//        Log.e("Blank_Activity", "receive:" + user.getName());

    }

    private void setIds() {
        confirmBTN = (Button) findViewById(R.id.confirmBTN);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        linear_LY = (LinearLayout) findViewById(R.id.linear_LY);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                radioButton_Text = radioButton.getText().toString();
                Log.e("selectedId", "" + selectedId + radioButton.getText().toString());
                if (radioButton.getText().toString().equals("Follow/Up")) {
                    radioButton.setTextColor(Color.BLACK);
                    Log.e("andar_aaya", "");

                } else if (radioButton.getText().toString().equals("New Case")) {
                    radioButton.setTextColor(Color.BLACK);
                    Log.e("andar_aaya", "else");
                }
            }
        });
        confirmBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("value", "" + radioButton_Text);
                if (selectedId == -1) {

                    Snackbar.make(linear_LY, "Please Select option...", Snackbar.LENGTH_LONG).show();
                } else {
                    if (radioButton_Text.equals("Follow/Up")) {
                        startActivity(new Intent(Blank_Activity.this, Follow_Up_List.class));

                    } else {
                        startActivity(new Intent(Blank_Activity.this, Patient_description_details.class));

                    }
                }
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {

//        Intent i = new Intent(this, Patient_Home_Screen.class);
//        // set the new task and clear flags
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
