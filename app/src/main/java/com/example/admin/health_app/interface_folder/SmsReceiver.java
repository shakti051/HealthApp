package com.example.admin.health_app.interface_folder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by swarajpal on 19-04-2016.
 */
public class SmsReceiver extends BroadcastReceiver {

    private static SmsListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();

        Object[] pdus = (Object[]) data.get("pdus");

        for(int i=0;i<pdus.length;i++){
           /* SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            String phoneNumber = smsMessage.getDisplayOriginatingAddress();
            //You must check here if the sender is your provider and not another one with same text.

            String messageBody = smsMessage.getMessageBody();
            String senderNum = phoneNumber ;
            //Pass on the text to our listener.
            mListener.messageReceived(messageBody,senderNum);*/
        }

    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}
