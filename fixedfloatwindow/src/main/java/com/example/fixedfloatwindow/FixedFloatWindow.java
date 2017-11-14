package com.example.fixedfloatwindow;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Method;

/**
 * Created by yhao on 17-11-14.
 * 固定(fixed)悬浮(float)控件
 * 可在手机任意界面显示
 * 4.4~7.0 无需权限可点击,  4.3及以下无触摸事件， 7.1及以上需申请权限
 * 不支持位置改变(因部分机型如小米无法实现)
 */

public class FixedFloatWindow implements FixedFloatView {

    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;

    private FixedFloatView mFixedFloatView;
    private boolean isShow = false;
    static PermissionListener mPermissionListener;


    public FixedFloatWindow(Context applicationContext) {
        this(applicationContext, true);
    }

    /**
     * @param applicationContext Application 上下文
     * @param autoReqPermission  true将在内部自动请求权限；false需要用户自己申请权限，默认true
     */
    public FixedFloatWindow(Context applicationContext, boolean autoReqPermission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            mFixedFloatView = new FixedFloatPhone(applicationContext, autoReqPermission);
        } else {
            mFixedFloatView = new FixedFloatToast(applicationContext);
        }
    }


    @Override
    public void setView(View view, int width, int height) {
        mFixedFloatView.setView(view, width, height);
    }

    @Override
    public void setView(View view) {
        mFixedFloatView.setView(view);
    }

    @Override
    public void setGravity(int gravity, int xOffset, int yOffset) {
        mFixedFloatView.setGravity(gravity, xOffset, yOffset);

    }

    @Override
    public void show() {
        if (isShow) return;
        mFixedFloatView.show();
        isShow = true;
    }

    @Override
    public void hide() {
        if (!isShow) return;
        mFixedFloatView.hide();
        isShow = false;
    }

    /**
     * 申请权限回调，注意只有在 7.1 及以上版本且设置 autoReqPermission=true 时才需要设置此监听，否则也不会回调
     */
    public void setPermissionListener(PermissionListener permissionListener) {
        mPermissionListener = permissionListener;
    }


    /**
     * 方便用户 inflate 视图
     */
    public static View inflate(Context applicationContext, int layoutId) {
        LayoutInflater inflate = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflate.inflate(layoutId, null);
    }

    /**
     * 方便用户判断是否已授予应用悬浮窗权限
     */
    public static boolean hasPermission(Context context) {
        Boolean result;
        try {
            Class clazz = Settings.class;
            Method canDrawOverlays = clazz.getDeclaredMethod("canDrawOverlays", Context.class);
            result = (Boolean) canDrawOverlays.invoke(null, context);
        } catch (Exception e) {
            result = true;
        }
        return result;
    }
}
