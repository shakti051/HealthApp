package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.health_app.Common_package.Dialog_popup;
import com.example.admin.health_app.Model.Doctor_Detail_List_Model;
import com.example.admin.health_app.R;
import com.example.admin.health_app.VolleyApiHit.DeviceNetConnectionDetector;
import com.example.admin.health_app.VolleyApiHit.Volley_Asynclass;
import com.example.admin.health_app.rahul.AppointmentTimeSlotAdapter;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ankit on 1/10/2018.
 */

public class Patient_Book_Now extends AppCompatActivity implements View.OnClickListener,
        AppointmentTimeSlotAdapter.OnTimeSlotClickListener, Volley_Asynclass.VolleyResponse {
    private TextView toolbarTitleText, selectDateTV, selectTimeTV, doctorTV, qualificationSpecTV, phoneTV, addressTV, relationTV;
    private Button submitBTN;
    private DatePickerDialog datePickerDialog;
    private String employeId, relation_aadhaarNO, relation_member_key, selectdate, select_Slot_Time,
            relation_relation_key, user_id, organ_issue;
    private RatingBar ratingbarRATINGB;
    private SharedPreferences preferences;
    private LinearLayout mainLayout, selectDateLY;
    private TextView patientname, DOBTV, patientEMPTV, patient_aadhaarTV;
    private HashMap<String, String> doctorList;
    private EditText syntomsET, primary_ET, secondary_ET, dayET;
    private Spinner TimeSlot_SP;
    private ArrayAdapter timeSlotAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private AppointmentTimeSlotAdapter mAdapter;
    private ArrayList<String> listValue = new ArrayList<>();
    private ImageView addDocumentsID;
    private Bitmap bitmap;
    private File loadImageURI;
    private Uri picUri;
    private EditText fileNameEt;
    private Doctor_Detail_List_Model doctor_detail_listModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_now_layout);

        //get details from sharePrefrences
        getDate_from_DB_and_INtent();
        add_ArrayList();
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    //getVlaue from model class through activity patient_Home_Screen.......

    private void getDate_from_DB_and_INtent() {
        preferences = getSharedPreferences("prefs", MODE_PRIVATE);

        employeId = preferences.getString("EMPLOYEEID_KEY", "");
        relation_aadhaarNO = preferences.getString("RELATION_AADHAAR_KEY", "");
        relation_member_key = preferences.getString("RELATION_MEMBER_KEY", "");
        relation_relation_key = preferences.getString("RELATION_RELATION_KEY", "");
        user_id = preferences.getString("USER_ID_KEY", "");
        Log.e("relation_member_key", "" + relation_member_key);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){

            doctor_detail_listModel = (Doctor_Detail_List_Model) extras.getSerializable("Doctor_details");
            Log.e("book_Appointment", "doctor_details....:" + doctor_detail_listModel.getDocName());
            organ_issue= extras.getString("Organ_Issue");
            Log.e("Organ_Issue", "book_appointment..." + organ_issue);
        }








    }

    private void setIds() {
        mRecyclerView = (RecyclerView) findViewById(R.id.doctorSpecialityRV);
        fileNameEt = (EditText) findViewById(R.id.fileNameEt);
        layoutManager = new LinearLayoutManager(Patient_Book_Now.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AppointmentTimeSlotAdapter(listValue, Patient_Book_Now.this);
        mRecyclerView.setAdapter(mAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        syntomsET = (EditText) findViewById(R.id.syntomsET);
        primary_ET = (EditText) findViewById(R.id.primary_ET);
        secondary_ET = (EditText) findViewById(R.id.secondary_ET);
        dayET = (EditText) findViewById(R.id.dayET);
        selectDateLY = (LinearLayout) findViewById(R.id.selectDateLY);
        selectDateLY.setOnClickListener(this);
        selectDateTV = (TextView) findViewById(R.id.selectDateTV_id);
        toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setVisibility(View.VISIBLE);
        toolbarTitleText.setText("Book Appointment");
        submitBTN = (Button) findViewById(R.id.submitBTN_id);
        addDocumentsID = (ImageView) findViewById(R.id.addDocumentsID);
        submitBTN.setOnClickListener(this);
        addDocumentsID.setOnClickListener(this);

        doctorTV = (TextView) findViewById(R.id.doctorTVID);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout_id);
        qualificationSpecTV = (TextView) findViewById(R.id.qualificationSpecTVID);

        phoneTV = (TextView) findViewById(R.id.phoneTVID);
        addressTV = (TextView) findViewById(R.id.addressTVID);
        ratingbarRATINGB = (RatingBar) findViewById(R.id.ratingbarRATINGB_id);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitBTN_id:
                ApiHit();
//                startActivity(new Intent(Patient_Book_Now.this, Appointment_booked.class));
//                if (!Validation.normalValidation(syntomsET.getText().toString(), syntomsET)) {
//                    syntomsET.setError("Please enter Symptom");
//                } else if (!Validation.normalValidation(dayET.getText().toString(), dayET)) {
//                    dayET.setError("Please enter no. of days");
//                } else if (selectDateTV.getText().equals("dd/mm/yyyy")) {
//                    Snackbar.make(mainLayout, "Please select date", Snackbar.LENGTH_LONG).show();
//                } else if (TimeSlot_SP.getSelectedItem().toString().equals("Please select time")) {
//                    Snackbar.make(mainLayout, "Please select time", Snackbar.LENGTH_LONG).show();
//                } else {
//startActivity(new Intent(Patient_Book_Now.this,Appointment_booked.class));
//                }
                break;
            case R.id.selectDateLY:
                Calendar c = Calendar.getInstance();
//***********OnDateSetListener is a Interface Of DatePickerDialog************************************************************
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        Calendar c = Calendar.getInstance();


                        int month = monthOfYear + 1;
                        if (dayOfMonth < 10 && month < 10) {

                            selectDateTV.setText("0" + month + "/" + "0" + dayOfMonth + "/" + year);
                            selectdate = month + "/" + dayOfMonth + "/" + year;
                            Log.e("selectDateTV", "" + selectDateTV.getText().toString());


                        } else if (dayOfMonth > 10 && month > 10) {

                            selectDateTV.setText(month + "/" + dayOfMonth + "/" + year);
                            Log.e("selectDateTV", "" + selectDateTV.getText().toString());

                        } else if (dayOfMonth > 10 || month < 10) {
                            selectDateTV.setText("0" + month + "/" + dayOfMonth + "/" + year);
                            Log.e("selectDateTV", "" + selectDateTV.getText().toString());
                        } else {

                            selectDateTV.setText(dayOfMonth + "/" + month + "/" + year);
                            selectdate = dayOfMonth + "/" + month + "/" + year;
                            Log.e("selectdate...", "" + selectdate);
                        }
                        Log.e("month...", "" + month + dayOfMonth);
                        // TODO Auto-generated method stub
//                        c.set(Calendar.YEAR, year);
//                        c.set(Calendar.MONTH, monthOfYear);
//                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                        selectdate = month + "/" + dayOfMonth + "/" + year;


//                        selectDateTV.setText(dayOfMonth + "/" + month + "/" + year);

                    }

                };
                datePickerDialog = new DatePickerDialog(this, date, c
                        .get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
                //**************Show A Current Date******************************************
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());
                break;
            case R.id.addDocumentsID:
                startActivityForResult(getPickImageChooserIntent(), 200);
                break;

        }
    }

    private Intent getPickImageChooserIntent() {
        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK && data != null) {
            Log.e("resultCode", ".." + data + "\n" + requestCode);
            if (getPickImageResultUri(data) != null) {
                picUri = getPickImageResultUri(data);
                Log.e("picUri", ".." + picUri);
                Uri selectedImage = data.getData();

                String[] filePath = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePath, null, null, null);
                cursor.moveToFirst();
                String mImagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
                String filename = mImagePath.substring(mImagePath.lastIndexOf("/") + 1);
                Log.e("mImagePath", ".." + filename);
                fileNameEt.setText(filename);
                fileNameEt.setEnabled(false);
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
                    Log.e("bitmap_gallery", String.valueOf(bitmap));
                    loadImageURI = bitmapToFile(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } else {
            try {
                fileNameEt.setEnabled(true);
                fileNameEt.getText().clear();
                fileNameEt.setHint("Add File");
                picUri = getPickImageResultUri(data);
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);

                Log.e("bitmap", "camera:" + bitmap);
//                    uploadedImageView.setImageBitmap(bitmap);
                loadImageURI = bitmapToFile(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File bitmapToFile(Bitmap bmp) {

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 80, bos);
            byte[] bArr = bos.toByteArray();
            bos.flush();
            bos.close();

            FileOutputStream fos = openFileOutput("mdroid.png", Context.MODE_WORLD_WRITEABLE);
            fos.write(bArr);
            fos.flush();
            fos.close();

            File mFile = new File(getFilesDir().getAbsolutePath(), "mdroid.png");
            //imagename=mFile.getName();
            if (!mFile.isFile()) {
                Log.e("check..", "...false");
            } else {

                Log.e("check..", "...true");

            }
            return mFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private Uri getPickImageResultUri(Intent data) {

        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }


    private void add_ArrayList() {
        listValue.add("Select Time");
        listValue.add("02:30");
        listValue.add("03:30");
        listValue.add("04:30");
        listValue.add("05:30");
        listValue.add("06:30");
        listValue.add("07:30");
        listValue.add("08:30");
        listValue.add("09:30");

    }

    private void ApiHit() {
        new Volley_Asynclass(this, JsonParams(), "bookappointments/", "Book Appointment...");
    }

    private String JsonParams() {
        String urlString = null;
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject.put("API", "RAKSHA_BOOK_APPOINTMENT");
            jsonObject.put("CURD_OPERATION", "I");
            jsonObject.put("CURRENT_PROBLEM", syntomsET.getText().toString());
            jsonObject.put("NO_OF_DAYS", dayET.getText().toString());
            jsonObject.put("CLINIC_DETAILS_KEY", doctor_detail_listModel.getDoc_clinic_detail_id());
            jsonObject.put("EMPLOYEE_ID", employeId);
            jsonObject.put("BOOKING_DATE", selectDateTV.getText().toString());
            jsonObject.put("TIME_SLOT", select_Slot_Time);
            jsonObject.put("USER_ID", user_id);
            jsonObject.put("DOC_USER_ID", doctor_detail_listModel.getDoc_Id());
            jsonObject.put("RELATION", relation_member_key);
            jsonObject.put("MEMBER_KEY", relation_member_key);
            jsonObject.put("ADHAR_NO", relation_aadhaarNO);
            jsonObject.put("ORGAN", organ_issue);
            jsonObject.put("PRIMARY_SYMPTOMS", primary_ET.getText().toString());
            jsonObject.put("SECONDARY_SYMPTOMS", secondary_ET.getText().toString());
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

                    Intent mpinIntent = new Intent(this, Patient_Home_Screen.class);
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
            if (DeviceNetConnectionDetector.checkDataConnWifiMobile(this))
                Snackbar.make(mainLayout, getResources().getString(R.string.common_ServerConnection), Snackbar.LENGTH_LONG).show();
            else
                Snackbar.make(mainLayout, getResources().getString(R.string.common_checkNetConnection), Snackbar.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(String time) {
        select_Slot_Time = time;
        Log.e("onClick", "time_selected" + time);
    }
}
