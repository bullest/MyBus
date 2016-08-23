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
        location1.setLongitude(121.663685);
        location1.setLatitude(31.202364);
        mMap.put(MyLocation.家, location1);

        Location location2 = new Location("Tink");
        location2.setLongitude(121.587716);
        location2.setLatitude(31.201910);
        mMap.put(MyLocation.张江地铁, location2);

        Location location3 = new Location("Tink");
        location3.setLongitude(121.561892);
        location3.setLatitude(31.317050);
        mMap.put(MyLocation.码头, location3);

        Location location4 = new Location("Tink");
        location4.setLongitude(121.5826162);
        location4.setLatitude(31.2135596);
        mMap.put(MyLocation.德国中心, location4);

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
