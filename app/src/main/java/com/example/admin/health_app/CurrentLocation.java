package com.example.admin.health_app;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.admin.health_app.Common_package.LoginScreenActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CurrentLocation extends RuntimePermissionsActivity implements GoogleApiClient.OnConnectionFailedListener
        , GoogleApiClient.ConnectionCallbacks, ResultCallback<LocationSettingsResult> {

    private static final int REQUEST_PERMISSIONS = 20;
    private SharedPreferences.Editor editor;
    GoogleApiClient mGoogleApiClient;
    double latitude, longitude;
    String lat = null, longii, curr_locality;

    ProgressDialog progressDialog;
    LocationRequest locationRequest;

    protected int REQUEST_CHECK_SETTINGS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank);
        editor = getSharedPreferences("prefs", MODE_PRIVATE).edit();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CurrentLocation.super.requestAppPermissions(new
                            String[]{Manifest.permission.READ_CONTACTS,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION
                            , Manifest.permission.RECORD_AUDIO}, R.string
                            .runtime_permissions_txt
                    , REQUEST_PERMISSIONS);
        }
        else{
            getCurrentLocation();
        }
//        else {
//            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//            String imei = telephonyManager.getDeviceId();
//            Log.e("onClick_imei...", "" + imei);
//            if (imei != null) {
//                editor.putString("IMEI_KEY", imei);
//                editor.commit();
//                startActivity(new Intent(this, LoginScreenActivity.class));
//                finish();
//            }
        }



    private void getCurrentLocation() {

        initializeSettingApi();


    }

    private void initializeSettingApi() {
        Log.e("Toggle", "Area 000000");

        progressDialog = new ProgressDialog(CurrentLocation.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Searching Location...");

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();


        mGoogleApiClient.connect();
    }

    //GET CURRENT LOCTION
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        progressDialog.show();

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(2 * 1000);
        locationRequest.setFastestInterval(0);

        Log.e("Toggle", "Area 11111");

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Log.e("Toggle", "Area 2222");

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    Log.e("Toggle", "BroadCast Area...Location Found " + location.getLatitude() + " : " + location.getLongitude());
                    // locationController = false;

                    //getPinCode(location.getLatitude(), location.getLongitude());
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    getPinCode(location.getLatitude(), location.getLongitude());

                    lat = String.valueOf(latitude);
                    longii = String.valueOf(longitude);
                    Log.e("onLocationChanged", "" + longitude + latitude);
//                    editor.putString("Current_latitude_KEY", lat);
//                    editor.putString("Current_longitude_KEY", longii);
                    editor.putString("curr_locality_KEY", curr_locality);
                    editor.commit();
                    startActivity(new Intent(CurrentLocation.this, LoginScreenActivity.class));
                    finish();
                }
                Log.e("Toggle", "BroadCast Area...Location Found2222 " + location.getLatitude() + " : " + location.getLongitude());

                // Toast.makeText(LoginScreenActivity.this, "lat:" + location.getLatitude() + "longi:" + location.getLongitude(), Toast.LENGTH_LONG).show();
                if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.disconnect();
                }

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.cancel();

              /*  if (location != null ) {
                    Log.e("Toggle", "BroadCast Area...Location Found " + location.getLatitude() + " : " + location.getLongitude());
//                    locationController = false;

                    getPinCode(location.getLatitude(), location.getLongitude());
                }*/
            }
        });

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }


    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        Log.e("status....", "" + status);
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:


                Log.e("Toggle", "BroadCast Area...Location Found Successfully");


                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    Log.e("Toggle", "BroadCast Area...Location Found Required Permission");
                    status.startResolutionForResult(CurrentLocation.this, REQUEST_CHECK_SETTINGS);

                    // getCurrentLocation();


                } catch (IntentSender.SendIntentException e) {
                }
                break;

            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                Log.e("Toggle", "BroadCast Area...Location ERROR unsuccessful");
                break;
        }
    }


    private void getPinCode(final double latitude, final double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses != null && addresses.size() > 0) {
//                String address = addresses.get(0).getAddressLine(0);
//                String city = addresses.get(0).getAddressLine(1);
//                String country = addresses.get(0).getAddressLine(2);
                String postalCode = addresses.get(0).getPostalCode();
                String address = addresses.get(0).getAddressLine(0);
                String array[] = address.split(",");
                curr_locality = array[3];
                Log.e("Toggle", "address.... " + curr_locality);


//                Toast.makeText(CurrentLocation.this, "Pincode " + addresses.get(0).getPostalCode(), Toast.LENGTH_SHORT).show();


                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.cancel();

              /*  searchedQuery = postalCode + "~" + callingModule;
                if (searchedQuery != null)
                    new LoadResponseFromOnlineSearch(CD_K_OnlineSearch.this, CD_K_OnlineSearch.this, searchedQuery.toUpperCase()).execute();
*/
                //pincodeACTV.setText(postalCode);

            } else {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.cancel();

//                searchedUserRecycleVW.setVisibility(View.GONE);
//                noRecordTV.setVisibility(View.VISIBLE);

            }
        } catch (IOException e) {                                                 //
            e.printStackTrace();

            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.cancel();

//            searchedUserRecycleVW.setVisibility(View.GONE);
//            noRecordTV.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onPermissionsGranted(final int requestCode) {
        Log.e("requestCode", "" + requestCode);
//        Toast.makeText(this, "Permissions Received.", Toast.LENGTH_LONG).show();
        if (requestCode == 20) {
            getCurrentLocation();

        }


    }

}
