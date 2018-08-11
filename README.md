# GPS Switcher Android P
This software will add a tile at Quick Setting to switch the GPS ON/OFF.

<a href="https://play.google.com/store/apps/details?id=com.lack006.gps"><img src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" height="48"></a>


use

    settings get secure location_providers_allowed    
    
to get GPS status

use

    "settings put secure location_providers_allowed +gps"
    
to switch on GPS

use

    "settings put secure location_providers_allowed -gps"
    
to switch off GPS
