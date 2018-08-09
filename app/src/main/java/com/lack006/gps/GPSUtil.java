package com.lack006.gps;

import java.util.List;

import eu.chainfire.libsuperuser.Shell;

public class GPSUtil {
    private static final String GPS = "gps";

    public static boolean getGPSStatus() {
        List<String> list = Shell.SU.run(Commands.GET_GPS_STATUS_CMD);
        return !list.isEmpty() && list.get(0).contains(GPS);
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
