package com.example.antistalker;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class SmsBlocker extends BroadcastReceiver
{
    /** Called when the activity is first created. */
    private static final String ACTION = "android.provider.Telephony.SEND_SMS";

    ArrayList<Person> bannedPersons;
    public static int MSG_TPE=0;
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String phoneNr = bundle.getString("incoming_number");

        if(!isBanned(phoneNr))
            return;

        String MSG_TYPE=intent.getAction();
        Log.v("TAGasd", MSG_TYPE);

        if(MSG_TYPE.equals("android.provider.Telephony.SMS_RECEIVED"))
        {
//          Toast toast = Toast.makeText(context,"SMS Received: "+MSG_TYPE , Toast.LENGTH_LONG);
//          toast.show();

            Object messages[] = (Object[]) bundle.get("pdus");
            SmsMessage smsMessage[] = new SmsMessage[messages.length];
            for (int n = 0; n < messages.length; n++)
            {
                smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
            }

            // show first message
            Toast toast = Toast.makeText(context,"BLOCKED Received SMS: " + smsMessage[0].getMessageBody(), Toast.LENGTH_LONG);
            toast.show();
            abortBroadcast();
            for(int i=0;i<8;i++)
            {
                Log.v("asdsad","Blocking SMS **********************");
            }

        }
        else if(MSG_TYPE.equals("android.provider.Telephony.SEND_SMS"))
        {
            Toast toast = Toast.makeText(context,"SMS SENT: "+MSG_TYPE , Toast.LENGTH_LONG);
            toast.show();
            abortBroadcast();
            for(int i=0;i<8;i++)
            {
                Log.v("asdsad","Blocking SMS **********************");
            }

        }
        else
        {

            Toast toast = Toast.makeText(context,"SIN ELSE: "+MSG_TYPE , Toast.LENGTH_LONG);
            toast.show();
            abortBroadcast();
            for(int i=0;i<8;i++)
            {
                Log.v("asdsad","Blocking SMS **********************");
            }

        }
    }

    public void block(ArrayList<Person> bannedPersons) {
        this.bannedPersons = bannedPersons;
    }

    public Boolean isBanned(String telephone) {

        if (telephone == null)
            return false;

        if("0751122566".compareTo(telephone) == 0) {
            return  true;
        }

        for(Person p : this.bannedPersons){
            if(p.telephone.compareTo(telephone) == 0)
                return  true;
        }
        return  false;
    }
}
