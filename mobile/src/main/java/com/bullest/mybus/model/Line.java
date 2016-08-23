package com.bullest.mybus.model;

/**
 * Created by gang on 8/21/16.
 */

public class Line {
    public String lineName;
    public String terminalId;
    public String lineId;
    public String direction;
    public Boolean v2;
    public Boolean dispatch;

    public Line(String mLineName, String mTerminalId, String mLineId, String mDirection, Boolean mV2, Boolean mDispatch){
        this.lineName = mLineName;
        this.terminalId = mTerminalId;
        this.lineId = mLineId;
        this.direction = mDirection;
        this.v2 = mV2;
        this.dispatch = mDispatch;
    }
}
