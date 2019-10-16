package com.example.admin.health_app.VolleyApiHit;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ankit on 10/2/2017.
 */

public class Volley_Asynclass {

    String hostURL = "http://www.mymediapp.in/ords/telemedicine/insert/";
    Context context;
    String appendTag = null;
    VolleyResponse volleyResponse;
    String param = null;
    private ProgressDialog mProgressDialog;

    public Volley_Asynclass(Context context, String params, String tag) {
        this.context = context;
        volleyResponse = (VolleyResponse) context;
        this.param = params;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
//        mProgressDialog.show();
        Log.e("volleyAsyn", "params..." + param);
        api(tag);


    }

    public Volley_Asynclass(Context context, String params, String tag, String message) {
        this.context = context;
        this.param = params;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(message);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        api(tag);
        volleyResponse = (VolleyResponse) context;
    }

    public void api(String Tag) {

        hostURL = hostURL + Tag;
        Log.e("hosturl", "" + hostURL);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, hostURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mProgressDialog.dismiss();
                Log.e("response", "" + response.toString());
                volleyResponse.getDataFromVolleyInterFace(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mProgressDialog.dismiss();
                String message = null;
                if (volleyError instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (volleyError instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (volleyError instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (volleyError instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (volleyError instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (volleyError instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                }
                Log.e("onErrorResponse", "" + message);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("a", param);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }

    public interface VolleyResponse {
        public void getDataFromVolleyInterFace(String loadedString);
    }
}
