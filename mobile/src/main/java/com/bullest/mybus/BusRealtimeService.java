package com.bullest.mybus;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yunfezhang on 8/10/16.
 */
public interface BusRealtimeService {
    @GET("palmbus_serv/PalmBusJgj/carMonitor.do")
    Call<List<RealtimeBus>> groupList
            (@Query("lineid") String lineid,
             @Query("stopid") String stopid,
             @Query("direction") String direction,
             @Query("time") String time);
}