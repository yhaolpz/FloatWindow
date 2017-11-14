package com.example.fixedfloatwindow;

import android.view.View;

/**
 * Created by yhao on 17-11-14.
 */

interface FixedFloatView {


    /**
     * 设置悬浮控件视图
     */
    void setView(View view, int width, int height);


    /**
     * 设置悬浮控件视图
     */
    void setView(View view);


    /**
     * 设置悬浮控件位置
     */
    void setGravity(int gravity, int xOffset, int yOffset);


    /**
     * 显示悬浮控件
     */
    void show();


    /**
     * 隐藏悬浮控件
     */
    void hide();
}
