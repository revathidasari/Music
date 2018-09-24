package com.example.revathid.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private String incomingNumber;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Toast.makeText(context,"Incoming Action-"+intent.getAction(),Toast.LENGTH_SHORT).show();

        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        if (action.equals("android.intent.action.PHONE_STATE")){
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){

                String incomingNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                displayToast(context,"Incoming Call from "+incomingNumber);
                sendAutoReply(incomingNumber);
            }else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){

            }else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){

            }


        }else if (action.equals("android.provider.Telephony.SMS_RECEIVED")){
            displayToast(context,"Incoming SMS");

            Object[] obj = (Object[]) extras.get("obj");
            SmsMessage[] smsMessage = new SmsMessage[obj.length];
            for(int i = 0; i < smsMessage.length; i++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String format = extras.getString("format");

                    smsMessage[i] = SmsMessage.createFromPdu((byte[]) obj[i],format);
                } else {
                    smsMessage[i] = SmsMessage.createFromPdu((byte[]) obj[i]);
                }
                incomingNumber = smsMessage[i].getOriginatingAddress();
                String incomingMessage = smsMessage[i].getMessageBody();
                displayToast(context,"Incoming Message from " +incomingNumber);
                sendAutoReply(incomingNumber);
            }
        }else if (action.equals("android.intent.action.AIRPLANE_MODE")){
            displayToast(context,"Incoming Action-"+intent.getAction());
        }
    }

    private void displayToast(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    private void sendAutoReply(String number){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number,null,"Sorry! I'm busy now..",null,null);
    }
}