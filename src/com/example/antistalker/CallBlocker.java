package com.example.antistalker;

import java.lang.reflect.Method;
import java.util.ArrayList;

import android.os.Bundle;
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

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String phoneNr = bundle.getString("incoming_number");

        Log.v(TAG, "Receving from " + phoneNr);

        if(!isBanned(phoneNr))
            return;

        TelephonyManager telephony = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            Class c = Class.forName(telephony.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            telephonyService = (ITelephony) m.invoke(telephony);
            telephonyService.silenceRinger();
            telephonyService.endCall();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void block(ArrayList<Person> bannedPersons) {
        this.bannedPersons = bannedPersons;
    }

    public Boolean isBanned(String telephone) {

        if (telephone == null)
            return false;
        //Log.v(TAG, "Receving from " + bannedPersons);

        Log.v(TAG, "2Receving from: " + telephone);
        if("0751122566".compareTo(telephone) == 0) {
            Log.v(TAG, "INTRA IN MATA!");
            return  true;
        }

        for(Person p : this.bannedPersons){
            if(p.telephone.compareTo(telephone) == 0)
                return  true;
        }
        return  false;
    }


}