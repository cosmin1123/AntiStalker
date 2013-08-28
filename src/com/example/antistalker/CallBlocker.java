package com.example.antistalker;

import java.lang.reflect.Method;
import java.util.ArrayList;

import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsMessage;
import com.android.internal.telephony.ITelephony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
        import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.internal.telephony.ITelephony;

public class CallBlocker extends BroadcastReceiver {
    Context context = null;
    private static final String TAG = "Phone call";
    private ITelephony telephonyService;

    ArrayList<Person> bannedPersons;
    Integer currentRingMode;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String phoneNr = bundle.getString("incoming_number");

        String MSG_TYPE=intent.getAction();

      //  Log.v(TAG + "1234", phoneNr );
        if(MSG_TYPE.compareTo("android.provider.Telephony.SMS_RECEIVED") == 0){
            phoneNr = getSmsNumber(bundle);
            Log.v(TAG, phoneNr + "asdasd");
        }

        if(!isBanned(phoneNr))
            return;

        silencePhone();

        abortBroadcast();

        TelephonyManager telephony = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);

        try {
            Class c = Class.forName(telephony.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            telephonyService = (ITelephony) m.invoke(telephony);
            telephonyService.endCall();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void block(ArrayList<Person> bannedPersons) {
        this.bannedPersons = bannedPersons;
        for(Person p : bannedPersons){
            Log.v(TAG, p.telephone);
        }
    }

    public String getSmsNumber(Bundle bundle){

        Object messages[] = (Object[]) bundle.get("pdus");
        SmsMessage smsMessage[] = new SmsMessage[messages.length];
        for (int n = 0; n < messages.length; n++)
        {
            smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
        }

        return smsMessage[0].getDisplayOriginatingAddress();

    }

    public Boolean isBanned(String telephone) {

        if (telephone == null) {
            return false;
        }

        for(Person p : this.bannedPersons){
            Log.v(TAG, p.telephone);
            if(telephone.contains(p.telephone))
                return  true;
        }
        return  false;
    }

    public void silencePhone(){


    }

}