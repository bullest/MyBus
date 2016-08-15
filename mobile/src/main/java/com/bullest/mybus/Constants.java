package com.bullest.mybus;

import android.location.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yunfezhang on 8/10/16.
 */
public class Constants {
    public static class BUS {
        public static final String ZJ1 = "1000";
        public static final String PD2 = "2000";
        public static final String SCZX = "3000";
        public static final String K989 = "989";
        public static final String PD11 = "11";
    }

    public static class TERMINAL {
        public static final String HOME = "1";
        public static final String ZJMETRO = "2";
        public static final String CHANGTAI = "3";
        public static final String TZMETRO = "4";
        public static final String GCCENTER = "5";
    }

    public static class DIRECTION {
        public static final String ZJ1_TO_HOME = "0";
        public static final String ZJ1_TO_WORK = "1";
        public static final String PD2_TO_HOME = "0";
        public static final String PD2_TO_WORK = "1";
        public static final String SCZX_TO_WORK = "0";
        public static final String K989_TO_WORK = "1";
        public static final String PD11_TO_WORK = "0";
        public static final String PD11_TO_HOME = "1";
    }

    public static class LOCATION {
        public static HashMap<String, Location> map;
        public static final int SHIP_NEAR = 2000;
        public static Location HOME = new Location("Tink");
        public static Location GC  = new Location("Tink");



        LOCATION(){
            Location Ship = new Location("Tink");
            Ship.setLatitude(31.31963917);
            Ship.setLongitude(121.55542447);
            map.put("ship", Ship);


        }
    }

}
