package com.example.yhao.fixedfloatwindow;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.example.fixedfloatwindow.FixedFloatWindow;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yhao on 17-11-16.
 * 管理应用内悬浮窗
 * 实现 ActivityLifecycleCallbacks 是为了更方便的获取 activity 界面状态，从而更方便的管理
 */

public class MyFloatWindow implements Application.ActivityLifecycleCallbacks {


    private Context mAppContext;
    private View mView;
    private FixedFloatWindow mFixedFloatWindow;

    private int mActivityNum; //用于判断应用是否处于前台，若处于后台需要隐藏悬浮窗，否则就会在桌面显示

    private int mRotation; //当前角度

    private boolean playing = true; //用于控制定时器中是否需要改变悬浮控件角度


    public MyFloatWindow(Context mAppContext) {
        this.mAppContext = mAppContext;
        initView();
        initTimer();
    }


    /**
     * 初始化定时器
     */
    private void initTimer() {

        Timer timer = new Timer();
        TimerTask timeTask = new TimerTask() {
            @Override
            public void run() {
                if (playing) {
                    updateRotation();
                    mView.post(new Runnable() {
                        @Override
                        public void run() {
                            mView.setRotation(mRotation);
                        }
                    });
                }
            }
        };
        timer.schedule(timeTask, 0, 50);
    }


    /**
     * 初始化悬浮窗
     */
    private void initView() {
        ImageView imageView = new ImageView(mAppContext);
        imageView.setBackgroundResource(R.drawable.cd);
        mView = imageView;
        mView.setVisibility(View.INVISIBLE);

        mFixedFloatWindow = new FixedFloatWindow(mAppContext);
        mFixedFloatWindow.setView(mView,120,120);
        mFixedFloatWindow.setGravity(Gravity.RIGHT | Gravity.TOP, 60, 60);
    }


    /**
     * 显示悬浮窗
     */
    public void show() {
        mFixedFloatWindow.show();

    }


    /**
     * 隐藏悬浮窗
     */
    public void hide() {
        mFixedFloatWindow.hide();
    }


    /**
     * 调用后不可再 show 显示出来
     */
    public void dismiss() {
        mFixedFloatWindow.dismiss();
    }



    /**
     * 更新角度，实现旋转
     */
    private void updateRotation() {
        mRotation++;
        if (mRotation == 360) {
            mRotation = 0;
        }
    }


    /**
     * 此方法用于指定哪些界面需要展示悬浮窗
     */
    private boolean isNeedShow(Activity activity) {
        return (activity instanceof MainActivity || activity instanceof Main2Activity);
    }


    @Override
    public void onActivityStarted(Activity activity) {
        mActivityNum++;
        if (isNeedShow(activity)) {
            show();
        }else{
            hide();
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        mActivityNum--;
        if (mActivityNum == 0) {
            hide();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

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
