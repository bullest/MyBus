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

    public int getStopdis() {
        return  Integer.getInteger(stopdis);
    }

    public int getDistance() {
        return Integer.getInteger(distance);
    }

    public String getTime() {
        return "到达时间" + time ;
    }
}
