package com.lack006.gps;

import android.content.Context;
import android.location.LocationManager;

import java.util.List;

import eu.chainfire.libsuperuser.Shell;

public class GPSUtil {
//    private static final String GPS = "gps";

    public static boolean getGPSStatus(Context context) {
//        List<String> list = Shell.SU.run(Commands.GET_GPS_STATUS_CMD);
//        return !list.isEmpty() && list.get(0).contains(GPS);
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return lm != null && lm.isProviderEnabled(LocationManager.GPS_PROVIDER);


    }

    public static boolean GPSOn() {
        List<String> list = Shell.SU.run(Commands.GPS_ON_CMD);
        return list.isEmpty();
    }

    public static boolean GPSOff() {
        List<String> list = Shell.SU.run(Commands.GPS_OFF_CMD);
        return list.isEmpty();
    }
}
