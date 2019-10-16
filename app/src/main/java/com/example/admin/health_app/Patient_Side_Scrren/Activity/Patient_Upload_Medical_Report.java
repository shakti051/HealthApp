package com.example.admin.health_app.Patient_Side_Scrren.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.health_app.Common_package.Dialog_popup;
import com.example.admin.health_app.Model.Employe_Family_Details_Model;
import com.example.admin.health_app.Parse_Web_Services.Parse_json;
import com.example.admin.health_app.Patient_Side_Scrren.Adapter.Consultation_History_Adapter;
import com.example.admin.health_app.R;
import com.example.admin.health_app.Utils.ImageCompresssion;
import com.example.admin.health_app.Utils.OnCompressListener;
import com.example.admin.health_app.VolleyApiHit.DeviceNetConnectionDetector;
import com.example.admin.health_app.VolleyApiHit.Volley_Asynclass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Patient_Upload_Medical_Report extends AppCompatActivity
        implements Consultation_History_Adapter.OnClickListerner_Adapter, AdapterView.OnItemSelectedListener, Volley_Asynclass.VolleyResponse {

    private ArrayAdapter arrayAdapter_family_Details;
    private Spinner spinner_Family_details;
    private String employeId, tag, user_id;
    private SharedPreferences preferences;
    private ArrayList<String> familyDetails = new ArrayList<>();
    private List<Employe_Family_Details_Model> employe_family_detailsList;
    private int spinnerposition;
    private ImageView addDocuments, toolBarRightIcon;
    private Bitmap bitmap;
    private File loadImageURI, shopLogoImageFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_upload_medical_report);
        setIds();
        spinner_Family_details=(Spinner)findViewById(R.id.selectFamilyDetail_SP);
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
        addDocuments = (ImageView) findViewById(R.id.addDocumentsID);
        toolBarRightIcon = (ImageView) findViewById(R.id.toolBarRightIconID);
        toolBarRightIcon.setVisibility(View.VISIBLE);
        toolBarRightIcon.setImageResource(R.drawable.send_icon);
        addDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(getPickImageChooserIntent(), 200);
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
        spinnerposition = position;
        if (spinner_Family_details.getSelectedItem().toString().equals("Please select type")) {

        } else {
            Log.e("position", "" + position + "\n" + spinner_Family_details + "\n" +
                    employe_family_detailsList.get(position - 1).getMember_key());
//            relation_key = Integer.parseInt(arrayList.get(position - 1).get("RELATION_KEY"));
//            member_key = Integer.parseInt(arrayList.get(position - 1).get("MEMBER_KEY"));
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
            }
//            else {
//                if (DeviceNetConnectionDetector.checkDataConnWifiMobile(this))
//                    Snackbar.make(mainLayout_id, getResources().getString(R.string.common_ServerConnection), Snackbar.LENGTH_LONG).show();
//                else
//                    Snackbar.make(mainLayout_id, getResources().getString(R.string.common_checkNetConnection), Snackbar.LENGTH_LONG).show();
//            }
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

        if (resultCode == Activity.RESULT_OK) {
            if (getPickImageResultUri(data) != null) {
                Uri picUri = getPickImageResultUri(data);
                Log.e("picUri", ".." + picUri);


                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);


                    loadImageURI = bitmapToFile(bitmap);
                    compressWithLs(loadImageURI);
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

    private void compressWithLs(File file) {

        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getResources().getString(R.string.common_reg_buildShop_proceed_text));
        mProgressDialog.setCancelable(false);

        if (mProgressDialog != null)
            mProgressDialog.show();

        ImageCompresssion.get(this)
                .load(file)
                .putGear(ImageCompresssion.THIRD_GEAR)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Log.e("START", "STARAT");
                    }

                    @Override
                    public void onSuccess(File file) {
//                        Glide.with(context).load(file);

                        if (mProgressDialog != null && mProgressDialog.isShowing())
                            mProgressDialog.cancel();

                        Log.e("SUCCESS", "SUCCESS");
//                        returnFile = file;


                        if (file != null) {
                            shopLogoImageFile = file;

                            Log.e("SIZE COMPRESS FILE", "SIZE COMPRESS " + shopLogoImageFile.length() / 1024 + "k");
                            Log.e("SIZE COMPRESS IMG ", "SIZE COMPRESS " + ImageCompresssion.get(Patient_Upload_Medical_Report.this).getImageSize(shopLogoImageFile.getPath())[0] + " * " + ImageCompresssion.get(Patient_Upload_Medical_Report.this).getImageSize(shopLogoImageFile.getPath())[1]);

                        } else {
                            Log.e("SIZE COMPRESS FILE 22", "SIZE COMPRESS " + shopLogoImageFile.length() / 1024 + "k");
                            Log.e("SIZE COMPRESS IMG 22 ", "SIZE COMPRESS " + ImageCompresssion.get(Patient_Upload_Medical_Report.this).getImageSize(shopLogoImageFile.getPath())[0] + " * " + ImageCompresssion.get(Patient_Upload_Medical_Report.this).getImageSize(shopLogoImageFile.getPath())[1]);

                        }

                        ApiHit();
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (mProgressDialog != null && mProgressDialog.isShowing())
                            mProgressDialog.cancel();
//                        returnFile = null;
                        Log.e("SUCCESS", "FAIL");
                        e.printStackTrace();


                        Log.e("SIZE COMPRESS FILE 22", "SIZE COMPRESS " + shopLogoImageFile.length() / 1024 + "k");
                        Log.e("SIZE COMPRESS IMG 22 ", "SIZE COMPRESS " + ImageCompresssion.get(Patient_Upload_Medical_Report.this).getImageSize(shopLogoImageFile.getPath())[0] + " * " + ImageCompresssion.get(Patient_Upload_Medical_Report.this).getImageSize(shopLogoImageFile.getPath())[1]);


                        ApiHit();


                    }
                }).launch();

//        return returnFile;
    }
}

