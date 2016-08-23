package com.bullest.mybus;

import android.app.Application;

/**
 * Created by yunfezhang on 8/22/16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        initLocation();
    }

    private void initLocation() {

    }
}
