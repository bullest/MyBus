package com.bullest.mybus;

import android.location.Location;

import com.bullest.mybus.model.MyLocation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gang on 8/15/16.
 */

public class LocationHelper {
    private static HashMap<MyLocation, Location> mMap = new HashMap<>();
    static {
        Location location1 = new Location("Tink");
        location1.setLongitude(121.640116);
        location1.setLatitude(31.097375);
        mMap.put(MyLocation.家, location1);

    }

    public static MyLocation getMyLocation(Location location) {

        MyLocation myLocation = MyLocation.家;
        float distance = 999999;

        for (Map.Entry<MyLocation, Location> entry : mMap.entrySet()) {
            float distanceT = location.distanceTo(entry.getValue());
            if (distanceT < distance){
                distance = distanceT;
                myLocation = entry.getKey();
            }
        }

        return myLocation;
    }
}
