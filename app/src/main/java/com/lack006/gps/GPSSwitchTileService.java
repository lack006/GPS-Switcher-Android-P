package com.lack006.gps;

import android.annotation.TargetApi;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.Toast;

public class GPSSwitchTileService extends TileService {
    public GPSSwitchTileService() {
    }

    @Override
    public void onTileAdded() {
        Tile tile = getQsTile();
        if (GPSUtil.getGPSStatus(this)) {
            tile.setLabel(getString(R.string.gps_on));
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            tile.setLabel(getString(R.string.gps_off));
            tile.setState(Tile.STATE_INACTIVE);
        }

        tile.updateTile();
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onStartListening() {
        Tile tile = getQsTile();
        if (GPSUtil.getGPSStatus(this)) {
            tile.setLabel(getString(R.string.gps_on));
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            tile.setLabel(getString(R.string.gps_off));
            tile.setState(Tile.STATE_INACTIVE);
        }

        tile.updateTile();
    }


    @Override
    public void onClick() {
        boolean isSucceed;

        Tile tile = getQsTile();
        if (tile.getState() == Tile.STATE_ACTIVE) {
            isSucceed = GPSUtil.GPSOff();
            if (isSucceed) {
                tile.setState(Tile.STATE_INACTIVE);
                tile.setLabel(getString(R.string.gps_off));
            } else {
                Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
            }

        } else {

            isSucceed = GPSUtil.GPSOn();
            if (isSucceed) {
                tile.setState(Tile.STATE_ACTIVE);
                tile.setLabel(getString(R.string.gps_on));
            } else {
                Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
            }

        }

        tile.updateTile();
    }

}
