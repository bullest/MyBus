package com.bullest.mybus.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by gang on 8/21/16.
 */
@Root(name = "result")
public class DispatchBus {
    @Element(name = "terminal", required = false)
    private  String terminal;
}
