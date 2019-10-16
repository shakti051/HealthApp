package com.example.admin.health_app.VolleyApiHit;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DeviceNetConnectionDetector
{
    public static boolean checkDataConnWifiMobile(Activity activityContext)
    {
        try
        {
            ConnectivityManager cm = (ConnectivityManager)activityContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected())
            {
                return true;
            }
        }catch(Exception networkException)
        {
        }
        return false;
    }

    public static boolean checkDataConnWifiMobile(Context activityContext)
    {
        try
        {
            ConnectivityManager cm = (ConnectivityManager)activityContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected())
            {
                return true;
            }
        }catch(Exception networkException)
        {
        }
        return false;
    }

    public static boolean checkWebSiteUp()
    {
        boolean flag=false;
        try
        {
            java.net.InetAddress ina = java.net.InetAddress.getByName("http://www.google.com");
            if(ina.isReachable(8000))
            {
                flag=true;
            }
            else
            {
                flag=false;
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();

        }
        return flag;
    }

}
