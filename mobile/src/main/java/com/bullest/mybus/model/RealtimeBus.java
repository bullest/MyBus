package com.bullest.mybus.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by yunfezhang on 8/10/16.
 */
@Root(name = "result")
public class RealtimeBus {
    @Element(name = "cars", required = false)
    public Cars cars;

    public static class Cars {
        @Attribute(name = "lineid")
        public String lineid;

        @ElementList(entry = "car", inline = true)
        public List<Car> mCarList;
    }
}
