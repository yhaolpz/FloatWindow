package com.example.yhao.fixedfloatwindow;

import android.app.Application;

/**
 * Created by yhao on 17-11-16.
 */

public class BaseApplication extends Application {

    private MyFloatWindow myFloatWindow;


    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(myFloatWindow = new MyFloatWindow(this));
    }


    public MyFloatWindow getMyFloatWindow() {
        return myFloatWindow;
    }
}
