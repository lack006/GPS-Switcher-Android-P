package com.lack006.gps;

import android.annotation.TargetApi;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.Toast;

import java.util.List;

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
            tile.setIcon(Icon.createWithResource(getApplicationContext(), R.drawable.gps_on));
        } else {
            tile.setLabel(getString(R.string.gps_off));
            tile.setState(Tile.STATE_INACTIVE);
            tile.setIcon(Icon.createWithResource(getApplicationContext(), R.drawable.gps_off));
        }

        tile.updateTile();
    }


    @Override
    public void onClick() {
        List list;

        Tile tile = getQsTile();
        if (tile.getState() == Tile.STATE_ACTIVE) {
            list = GPSUtil.GPSOff();
            if (list.isEmpty()) {
                tile.setState(Tile.STATE_INACTIVE);
                tile.setLabel(getString(R.string.gps_off));
                tile.setIcon(Icon.createWithResource(getApplicationContext(), R.drawable.gps_off));
            } else {
                Toast.makeText(this, getResources().getString(R.string.error) + "\n" + list.get(0), Toast.LENGTH_LONG).show();
            }

        } else {

            list = GPSUtil.GPSOn();
            if (list.isEmpty()) {
                tile.setState(Tile.STATE_ACTIVE);
                tile.setLabel(getString(R.string.gps_on));
                tile.setIcon(Icon.createWithResource(getApplicationContext(), R.drawable.gps_on));
            } else {
                Toast.makeText(this, getResources().getString(R.string.error) + "\n" + list.get(0), Toast.LENGTH_LONG).show();
            }

        }

        tile.updateTile();
    }

}
