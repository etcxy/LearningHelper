package com.lh.learninghelper.Actitivy;

import android.app.Application;

/**
 * Created by etcxy@live.cn on 12/8/2017.
 */

public class App extends Application {

    private static App instance = null;

    public static App getInstance(){
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
