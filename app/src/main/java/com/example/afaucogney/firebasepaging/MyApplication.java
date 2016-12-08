package com.example.afaucogney.firebasepaging;

import android.app.Application;

/**
 * Created by afaucogney on 07/12/2016.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG);
    }
}
