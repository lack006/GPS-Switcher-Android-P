package com.lack006.gps;

import android.content.Context;
import android.location.LocationManager;
import com.topjohnwu.superuser.Shell;
import java.util.List;


public class GPSUtil {


    static boolean getGPSStatus(Context context) {

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return lm != null && lm.isProviderEnabled(LocationManager.GPS_PROVIDER);


    }

    static List GPSOn() {

        return Shell.su(Commands.GPS_ON_CMD).exec().getOut();
    }

    static List GPSOff() {

        return Shell.su(Commands.GPS_OFF_CMD).exec().getOut();
    }
}
