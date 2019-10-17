package com.lack006.gps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("need_boot_switch_off", false)) {
            GPSUtil.GPSOff();
        }
    }
}
