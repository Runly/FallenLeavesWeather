package com.ranli.fallenleavesweather.activity;

import android.app.Application;
import android.content.Context;

import im.fir.sdk.FIR;

/**
 * Created by Administrator on 2016/9/10.
 */
public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FIR.init(this);
        context = getApplicationContext();
    }
}
