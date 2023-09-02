package com.example.loginapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class AirPlaneModeChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(isAirPlaneModeOn(context.getApplicationContext())){
            Toast.makeText(context, "Airplane mode is on", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Airplane mode is off", Toast.LENGTH_SHORT).show();
        }
    }
    private static boolean isAirPlaneModeOn(Context context)
    {
        return Settings.System.getInt(context.getContentResolver(),Settings.Global.AIRPLANE_MODE_ON,0) != 0;
    }
}
