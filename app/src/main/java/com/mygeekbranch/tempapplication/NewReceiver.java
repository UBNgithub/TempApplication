package com.mygeekbranch.tempapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NewReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Питание устройства отключено", Toast.LENGTH_SHORT).show();
        if (intent.getAction().equalsIgnoreCase("android.intent.action.AIRPLANE_MODE_CHANGED")) {
            Toast.makeText(context, "Питание устройства отключено", Toast.LENGTH_SHORT).show();
        }
    }
}
