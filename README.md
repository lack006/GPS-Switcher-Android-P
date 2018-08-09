# GPS Switcher Android P
This software will add a tile at Quick Setting to switch the GPS ON/OFF.
use
    settings get secure location_providers_allowed    
to get GPS status

use
    "settings put secure location_providers_allowed +gps"
to switch on GPS

use
    "settings put secure location_providers_allowed -gps"
to switch off GPS
