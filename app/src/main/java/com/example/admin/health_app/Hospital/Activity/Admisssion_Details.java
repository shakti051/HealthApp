package com.example.admin.health_app.Hospital.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.admin.health_app.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Admin on 5/28/2018.
 */

public class Admisssion_Details extends AppCompatActivity implements View.OnClickListener {
    private TextView selectDateTV_id, selectTimeTV_id;
    private ImageView toolBarRightIconID, uploadedImageViewID;
    private DatePickerDialog datePickerDialog;
    private LinearLayout addDocID;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admission_detail);
        setIds();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIds() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        TextView toolbarTitleText = (TextView) findViewById(R.id.toolbarTitleTextID);
        toolbarTitleText.setText("Admission Details");
        selectDateTV_id = (TextView) findViewById(R.id.selectDateTV_id);
        selectTimeTV_id = (TextView) findViewById(R.id.selectTimeTV_id);
        toolBarRightIconID = (ImageView) findViewById(R.id.toolBarRightIconID);
        toolBarRightIconID.setImageResource(R.drawable.send_icon);
        uploadedImageViewID = (ImageView) findViewById(R.id.uploadedImageViewID);
        addDocID = (LinearLayout) findViewById(R.id.addDocID);
        toolBarRightIconID.setOnClickListener(this);
        selectDateTV_id.setOnClickListener(this);
        selectTimeTV_id.setOnClickListener(this);
        addDocID.setOnClickListener(this);
        uploadedImageViewID.setOnClickListener(this);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selectTimeTV_id:
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                final TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Log.e("onTimeSet", "" + selectedHour + "\n" + selectedMinute);
                        if (selectedHour > 1 && selectedHour < 12) {
//                            selectTime = selectedHour + ":" + selectedMinute + "AM";
                            selectTimeTV_id.setText(selectedHour + ":" + selectedMinute + "AM");
//                            select_startTime = selectedHour + ":" + selectedMinute + "AM";
//                            Log.e("aaaa", "" + select_startTime);

                        } else {
//                            selectTime = selectedHour + ":" + selectedMinute + "PM";
                            selectTimeTV_id.setText(selectedHour + ":" + selectedMinute + "PM");
//                            select_startTime = selectedHour + ":" + selectedMinute + "PM";
//                            Log.e("bbbbb", "" + select_startTime);
                        }
                    }

                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");

                mTimePicker.show();
                break;
            case R.id.selectDateTV_id:
                Calendar c = Calendar.getInstance();
//***********OnDateSetListener is a Interface Of DatePickerDialog************************************************************
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        Calendar c = Calendar.getInstance();


                        int month = monthOfYear + 1;
                        if (dayOfMonth < 10 && month < 10) {

                            selectDateTV_id.setText("0" + month + "/" + "0" + dayOfMonth + "/" + year);
//                            selectdate = month + "/" + dayOfMonth + "/" + year;
                            Log.e("selectDateTV", "" + selectDateTV_id.getText().toString());


                        } else if (dayOfMonth > 10 && month > 10) {

                            selectDateTV_id.setText(month + "/" + dayOfMonth + "/" + year);
                            Log.e("selectDateTV", "" + selectDateTV_id.getText().toString());

                        } else if (dayOfMonth > 10 || month < 10) {
                            selectDateTV_id.setText("0" + month + "/" + dayOfMonth + "/" + year);
                            Log.e("selectDateTV", "" + selectDateTV_id.getText().toString());
                        } else {

                            selectDateTV_id.setText(dayOfMonth + "/" + month + "/" + year);
//                            selectdate = dayOfMonth + "/" + month + "/" + year;
                            Log.e("selectdate...", "" + selectDateTV_id);
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
            case R.id.toolBarRightIconID:
                startActivity(new Intent(this, Hospital_Select_Mode_after_Patient_Select.class));
                finish();
                break;
            case R.id.addDocID:
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

        if (resultCode == Activity.RESULT_OK) {
            if (getPickImageResultUri(data) != null) {
                Uri picUri = getPickImageResultUri(data);
                Log.e("picUri", ".." + picUri);


                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);


                    uploadedImageViewID.setImageBitmap(bitmap);
//                    loadImageURI = bitmapToFile(bitmap);

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
}

