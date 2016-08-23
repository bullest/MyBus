package com.bullest.mybus.network;

import com.bullest.mybus.model.RealtimeBus;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yunfezhang on 8/10/16.
 */
public interface BusRealtimeService {
    @GET("palmbus_serv/PalmBusJgj/carMonitor.do")
    Call<RealtimeBus> realBus
            (@Query("lineid") String lineid,
             @Query("stopid") String stopid,
             @Query("direction") String direction);

    @GET("Project/Ver2/carMonitor.ashx")
    Call<RealtimeBus> realBusV2
            (@Query("lineid") String lineid,
             @Query("stopid") String stopid,
             @Query("direction") String direction);

    @GET("palmbus_serv/PalmBusJgj/getdispatchScreen.do")
    Call<RealtimeBus> dispatchBus
            (@Query("lineid") String lineid,
             @Query("stopid") String stopid,
             @Query("direction") String direction);
}
