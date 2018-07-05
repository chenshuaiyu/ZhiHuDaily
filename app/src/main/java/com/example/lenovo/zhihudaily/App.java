package com.example.lenovo.zhihudaily;

import android.app.Application;
import android.content.Context;

/**
 * Coder : chenshuaiyu
 * Time : 2018/7/5 13:04
 */
public class App extends Application{
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext=this;
    }
    public static Context getContext(){
        return sContext;
    }
}
