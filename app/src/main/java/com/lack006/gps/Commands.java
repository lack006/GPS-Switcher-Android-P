package com.lack006.gps;

public class Commands {
    public static final String GET_GPS_STATUS_CMD = "settings get secure location_providers_allowed";

    public static final String GPS_ON_CMD = "settings put secure location_providers_allowed +gps";

    public static final String GPS_OFF_CMD = "settings put secure location_providers_allowed -gps";
}
