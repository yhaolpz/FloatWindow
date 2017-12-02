package com.example.fixedfloatwindow;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by yhao on 17-12-1.
 * 用于控制悬浮窗显示周期
 * 使用了三种方法针对返回桌面时隐藏悬浮按钮
 * 1.startCount计数，针对back到桌面可以及时隐藏
 * 2.监听home键，从而及时隐藏
 * 3.resumeCount计时，针对一些只执行onPause不执行onStop的奇葩情况
 */

class FFLifecycle extends BroadcastReceiver implements Application.ActivityLifecycleCallbacks {

    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final long delay = 300;
    private FFWindow mFFWindow;
    private Handler mHandler;
    private Class[] activities;
    private boolean showFlag;
    private int startCount;
    private int resumeCount;
    private boolean appBackground;


    FFLifecycle(Context applicationContext, FFWindow ffWindow, boolean showFlag, Class[] activities) {
        this.mFFWindow = ffWindow;
        this.showFlag = showFlag;
        this.activities = activities;
        mHandler = new Handler();
        ((Application) applicationContext).registerActivityLifecycleCallbacks(this);
        applicationContext.registerReceiver(this,new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
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
        resumeCount++;
        if (needShow(activity)) {
            mFFWindow.afterOnceShow();
        } else {
            mFFWindow.hide();
        }
        if (appBackground) {
            appBackground = false;
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        resumeCount--;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (resumeCount == 0) {
                    appBackground = true;
                    mFFWindow.postHide();
                }
            }
        }, delay);

    }


    @Override
    public void onActivityStarted(Activity activity) {
        startCount++;
    }


    @Override
    public void onActivityStopped(Activity activity) {
        startCount--;
        if (startCount == 0) {
            mFFWindow.hide();
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
            if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)) {
                mFFWindow.hide();
            }
        }
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }


    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }





}
