package com.example.fixedfloatwindow;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by yhao on 17-12-1.
 * 用于控制悬浮窗显示周期
 */

class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private FFWindow ffWindow;
    private boolean showFlag;
    private Class[] activities;


    LifecycleCallbacks(FFWindow ffWindow, boolean showFlag, Class[] activities) {
        this.ffWindow = ffWindow;
        this.showFlag = showFlag;
        this.activities = activities;
    }


    private boolean needShow(Activity activity) {
        for (Class a : activities) {
            if (a.isInstance(activity)) {
                return showFlag;
            }
        }
        return !showFlag;
    }


    @Override
    public void onActivityResumed(Activity activity) {
        if (needShow(activity)) {
            ffWindow.show();
        } else {
            ffWindow.hide();
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ffWindow.postHide();
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
