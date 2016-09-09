package com.bullest.mybus.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by yunfezhang on 8/11/16.
 */

@Root
public class Car {
    @Element (name = "terminal", required = false)
    private  String terminal;

    @Element(name = "stopdis", required = false)
    public String stopdis;

    @Element(name = "distance", required = false)
    public String distance;

    @Element(name = "time", required = false)
    public String time;

    @Element(name = "vehicle", required = false)
    public String vehicle;

    public String getStopdis() {
        if (stopdis != null){
            return  stopdis;
        } else {
            return null;
        }
    }

    public int getDistance() {
        return Integer.getInteger(distance);
    }

    public String getTime() {
        if (time.contains("null")) {
            return " ";
        } else if(time.contains(":")) {
            return "发车时间: " + time ;
        } else if (time.contains("分组")) {
            return time;
        } else {
            int timeValue = Integer.valueOf(time);
            return "" + timeValue/60 + "分" + timeValue%60 + "秒后到站";
        }

    }
}
