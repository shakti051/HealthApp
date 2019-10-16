package com.example.admin.health_app.Pharmacy.Activity;

import android.app.Activity;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.health_app.R;
import com.example.admin.health_app.Radiology.Activity.Radiology_Home_Screen;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by AshuRajput on 1/9/2018.
 */

public class UploadYOurDocuments extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton submitUploadBtn;
    private ImageView addDocuments, uploadedImageView;
    private Bitmap bitmap;
    private File loadImageURI = null;
    private TextView toolbarTitleText,current_dateTV;
    private SharedPreferences preferences;
    private String aadhaar_number,loginStatus;
    private EditText reportET;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_your_documents);
        setUpID();

        //*******GET SYS DATE********
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, 1);
        int day = c.get(Calendar.DATE);
        int year = c.get(Calendar.YEAR);
        String formattedDate = month + 1 + "/" + day + "/" + year;
        String month1= String.valueOf(month+1);
        Log.e("formattedDate....", "" + formattedDate);
        current_dateTV.setText(day+"-"+month1+"-"+year);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        preferences=getSharedPreferences("prefs",MODE_PRIVATE);
        aadhaar_number=preferences.getString("AADHAAR_VALUE_KEY","");

        loginStatus = preferences.getString("USER_TYPE_KEY", "");
        Log.e("loginStatus.....",""+loginStatus);// ans 1
        Log.e("aadhaar_number....",""+aadhaar_number);
        if(loginStatus.equals("1")){
            submitUploadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        addDocuments.setOnClickListener(this);
        submitUploadBtn.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }


    private void setUpID() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setVisibility(View.VISIBLE);
        toolbarTitleText.setText("Upload Documents");

        submitUploadBtn = (AppCompatButton) findViewById(R.id.submitUploadBtnID);
        addDocuments = (ImageView) findViewById(R.id.addDocumentsID);
        uploadedImageView = (ImageView) findViewById(R.id.uploadedImageViewID);
        current_dateTV=(TextView)findViewById(R.id.current_dateTV_id);
        reportET=(EditText)findViewById(R.id.reportET_id);
        mainLayout=(LinearLayout)findViewById(R.id.mainLayout_id);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addDocumentsID: {
                startActivityForResult(getPickImageChooserIntent(), 200);
                break;
            }
            case R.id.submitUploadBtnID: {
                if(loginStatus.equals("4")){
                    if (bitmap == null) {
                        Toast.makeText(this,"Please add file", Toast.LENGTH_LONG).show();
                    }

                }

              else if (reportET.getText().toString().equals("") ) {
                    Toast.makeText(this,"Please fill report name", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(this, Pharmacy_Home_Screen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
//                else{
//                    checkNetwork = isNetworkAvailable();
//                    if (checkNetwork) {
//                        try {
//                            new WebServiceFileUploadViaPost(UploadYOurDocuments.this, UploadYOurDocuments.this, "patientdocumentupload/", forManagePostMethos(), loadImageURI).execute();
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//
//                        Toast.makeText(this, "Please check network connection....", Toast.LENGTH_LONG).show();
//                    }
//
//                }

                break;
            }
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


                    uploadedImageView.setImageBitmap(bitmap);
                    loadImageURI = bitmapToFile(bitmap);

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
//
//    private void compressWithLs(File file) {
//
//        final ProgressDialog mProgressDialog = new ProgressDialog(this);
//        mProgressDialog.setMessage(getResources().getString(R.string.common_reg_buildShop_proceed_text));
//        mProgressDialog.setCancelable(false);
//
//        if (mProgressDialog != null)
//            mProgressDialog.show();
//
//        ImageCompresssion.get(this)
//                .load(file)
//                .putGear(ImageCompresssion.THIRD_GEAR)
//                .setCompressListener(new OnCompressListener() {
//                    @Override
//                    public void onStart() {
//                        Log.e("START", "STARAT");
//                    }
//
//                    @Override
//                    public void onSuccess(File file) {
////                        Glide.with(context).load(file);
//
//                        if (mProgressDialog != null && mProgressDialog.isShowing())
//                            mProgressDialog.cancel();
//
//                        Log.e("SUCCESS", "SUCCESS");
////                        returnFile = file;
//
//
//                        if (file != null) {
//                            shopLogoImageFile = file;
//
//                            Log.e("SIZE COMPRESS FILE", "SIZE COMPRESS " + shopLogoImageFile.length() / 1024 + "k");
//                            Log.e("SIZE COMPRESS IMG ", "SIZE COMPRESS " + ImageCompresssion.get(Seller_Add_product.this).getImageSize(shopLogoImageFile.getPath())[0] + " * " + ImageCompresssion.get(Seller_Add_product.this).getImageSize(shopLogoImageFile.getPath())[1]);
//
//                        } else {
//                            Log.e("SIZE COMPRESS FILE 22", "SIZE COMPRESS " + shopLogoImageFile.length() / 1024 + "k");
//                            Log.e("SIZE COMPRESS IMG 22 ", "SIZE COMPRESS " + ImageCompresssion.get(Seller_Add_product.this).getImageSize(shopLogoImageFile.getPath())[0] + " * " + ImageCompresssion.get(Seller_Add_product.this).getImageSize(shopLogoImageFile.getPath())[1]);
//
//                        }
//
//                        ApiHit();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                        if (mProgressDialog != null && mProgressDialog.isShowing())
//                            mProgressDialog.cancel();
//                        returnFile = null;
//                        Log.e("SUCCESS", "FAIL");
//                        e.printStackTrace();
//
//
//                        Log.e("SIZE COMPRESS FILE 22", "SIZE COMPRESS " + shopLogoImageFile.length() / 1024 + "k");
//                        Log.e("SIZE COMPRESS IMG 22 ", "SIZE COMPRESS " + ImageCompresssion.get(Seller_Add_product.this).getImageSize(shopLogoImageFile.getPath())[0] + " * " + ImageCompresssion.get(Seller_Add_product.this).getImageSize(shopLogoImageFile.getPath())[1]);
//
//
//                        ApiHit();
//
//
//                    }
//                }).launch();
//
////        return returnFile;
//    }




}
