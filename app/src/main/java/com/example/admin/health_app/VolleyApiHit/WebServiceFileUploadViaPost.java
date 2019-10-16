package com.example.admin.health_app.VolleyApiHit;

/**
 * Created by Consultit on 25/05/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Consultit on 20/09/2016.
 */
public class WebServiceFileUploadViaPost extends AsyncTask<String, Void, String>
{
    private String webResponse = null;
    private HashMap<String, String> apiParams;
    private AsyncRequestListenerViaPost asyncTaskListener;
    private HttpURLConnection httpURLConnection = null;
    private InputStream is = null;
    private OutputStream os = null;
    private static String urlParams = null,tag=null,imageName="abcd", urlAppend=null;
    private File imageFileURI;
    private ProgressDialog mProgressDialog;
    private Context activityContext;
    private String jsonParam =null;
    private boolean check = false;
    private SharedPreferences preferences;
    int tagnumber;
    String inputTag,inputParameter;
    Bitmap inputImage;

//    String baseUrlBuildShop = "http://cyberdukaan.in/apex/cyberduaan/"; // CYBER DUKAAN BASE URL FOR BUILD SHOP
    String baseUrlBuildShop = "http://cyberdukaan.in/apex/citsensor/Insert/"; // CYBER DUKAAN BASE URL FOR BUILD SHOP
//    String baseUrlBuildShop = "http://"+ ProjectConstant.chatserver+"/apex/cyberduaan/"; // CYBER DUKAAN BASE URL FOR BUILD SHOP

    public WebServiceFileUploadViaPost(AsyncRequestListenerViaPost listener, Context activityContext
            , String apiParams, File imageFileURI, String imageName, String jsonParam, String message) {
//        this.activityContext = activityContext;
        this.urlParams = apiParams;
        this.imageFileURI = imageFileURI;
        this.imageName = imageName;
        this.jsonParam = jsonParam;
        this.activityContext = activityContext;
        asyncTaskListener=listener;
        mProgressDialog = new ProgressDialog(activityContext);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    public WebServiceFileUploadViaPost(AsyncRequestListenerViaPost listener, Context activityContext
            , String apiParams, File imageFileURI, String imageName, String jsonParam, String message, int tagnumber) {
//        this.activityContext = activityContext;
        this.urlParams = apiParams;
        this.imageFileURI = imageFileURI;
        this.imageName = imageName;
        this.tagnumber = tagnumber;
        this.jsonParam = jsonParam;
        this.activityContext = activityContext;
        asyncTaskListener=listener;
        mProgressDialog = new ProgressDialog(activityContext);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    public WebServiceFileUploadViaPost(AsyncRequestListenerViaPost listener, Context activityContext
            , String apiParams, File imageFileURI, String imageName, String jsonParam, String message, boolean check) {
//        this.activityContext = activityContext;
        this.urlParams = apiParams;
        this.check = check;
        this.imageFileURI = imageFileURI;
        this.imageName = imageName;
        this.jsonParam = jsonParam;
        this.activityContext = activityContext;
        asyncTaskListener=listener;
        mProgressDialog = new ProgressDialog(activityContext);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    public WebServiceFileUploadViaPost(AsyncRequestListenerViaPost listener, Context context,
                                       String apiParams, String inputParameter, File image) {
        activityContext = context;
        asyncTaskListener = listener;
        urlParams = apiParams;
        jsonParam = inputParameter;
        imageFileURI = image;
        mProgressDialog = new ProgressDialog(activityContext);
        mProgressDialog.setMessage("Loading... ");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);

        Log.e("IMAGE","image....  "+image);
    }


    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        if(mProgressDialog!=null)
            mProgressDialog.show();
    }
    @Override
    protected String doInBackground(String... url) {
        // TODO Auto-generated method stub
        try {


            preferences= activityContext.getSharedPreferences("EapPrefFile", MODE_PRIVATE);

//            baseUrlBuildShop = "http://"+preferences.getString("APP_DOMAIN","cyberdukaan.in")+"/apex/cyberduaan/";

//            baseUrlBuildShop = baseUrlBuildShop + urlParams;

            Log.e("URLFORMAT","urlformat.... "+baseUrlBuildShop);

            URL baseURL = new URL(baseUrlBuildShop+ urlParams);

            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundrey = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            DataOutputStream dos = null;
            int maxBufferSize = 1 * 1024 * 1024;


            Log.e("", "JUDO URL formed  " + baseURL);
            Log.e("JSON", "JSON " + urlParams);
            Log.e("JSON REQUEST", "JSON REQUEST " + "a=" + urlParams);


//            String encryptedData = Uri.encode(CD_Encryption.encryptMyData(urlParams));
//            String encryptedData = CD_Encryption.encryptMyData(urlParams);
//            Log.e("JSON", "JSON ENCRYPTED " + CD_Encryption.encryptMyData(urlParams));
//            Log.e("JSON", "JSON DECRYPTED " + CD_Encryption.decryptMydata(encryptedData));


            if (!imageFileURI.isFile()) {
                Log.e("SOURECE FILE NOT EXIST", "Source file does not exist : " + imageFileURI);

            }
            else
            {
                FileInputStream fileInputStream = new FileInputStream(imageFileURI);

                httpURLConnection = (HttpURLConnection) baseURL.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setConnectTimeout(7000);
                httpURLConnection.setReadTimeout(50000);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
//                httpURLConnection.setRequestProperty("ENCTYPE", "multipart/form-data");
//                httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundrey);
                httpURLConnection.setRequestProperty("Content-Type", "image/jpg");
                httpURLConnection.setRequestProperty("uploaded_file", imageName);
                httpURLConnection.setRequestProperty("a", jsonParam);


                os = httpURLConnection.getOutputStream();

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    os.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                os.close();
                fileInputStream.close();
                httpURLConnection.connect();


                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    is = httpURLConnection.getInputStream();// is is inputstream
                } else {
                    is = httpURLConnection.getErrorStream();
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                    webResponse = sb.toString();
//                is.close();
                }
                is.close();
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            webResponse = "Exception";
        } catch (IOException ioe) {
            ioe.printStackTrace();
            webResponse = "Exception";
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            webResponse = "Exception";
        }

        return webResponse;
    }

    @Override
    protected void onPostExecute(String receivedString)
    {
        if(mProgressDialog!=null && mProgressDialog.isShowing())
            mProgressDialog.cancel();
        Log.e("JSON", "JSON RESPONSE ENCRYPTED " + receivedString);
//        Log.e("JSON", "JSON RESPONSE ENCRYPTED " + CD_Encryption.decryptDataByLine(receivedString));

       /* if(check)
        {
            Log.e("JSON", "JSON RESPONSE ENCRYPTED2222222 " + CD_Encryption.decryptDataByLine(receivedString));
            asyncTaskListener.onRequestComplete(CD_Encryption.decryptDataByLine(receivedString));
        }
        else
        {
            if(tagnumber==1){

                asyncTaskListener.onRequestComplete(receivedString);

            }else{

                asyncTaskListener.onRequestComplete(CD_Encryption.decryptMydata(receivedString));

            }

        }*/

        asyncTaskListener.onRequestComplete(receivedString);

    }
    // Introducing inner Interface to avoid "Principle of least privilege":

    public interface AsyncRequestListenerViaPost {

        public void onRequestComplete(String loadedString);
    }
}
