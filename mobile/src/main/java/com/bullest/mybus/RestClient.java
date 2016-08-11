package com.bullest.mybus;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by yunfezhang on 8/11/16.
 */
public class RestClient {
    private static BusRealtimeService sBusApiInterface;
    private static String baseUrl = "http://180.166.5.82:8000";

    public static BusRealtimeService getClient() {
        if (sBusApiInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            sBusApiInterface = retrofit.create(BusRealtimeService.class);
        }
        return sBusApiInterface;
    }
}
