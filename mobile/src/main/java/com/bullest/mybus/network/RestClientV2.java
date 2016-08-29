package com.bullest.mybus.network;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by yunfezhang on 8/29/16.
 */
public class RestClientV2 {
    private static BusRealtimeService sBusApiInterface;
    private static String v2BaseUrl = "http://bst.shdzyb.com:36001";

    public static BusRealtimeService getClientV2() {
        if (sBusApiInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(v2BaseUrl)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            sBusApiInterface = retrofit.create(BusRealtimeService.class);
        }
        return sBusApiInterface;
    }
}
