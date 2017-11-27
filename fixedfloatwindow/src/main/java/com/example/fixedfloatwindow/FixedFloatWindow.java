package com.example.fixedfloatwindow;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.IntDef;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * Created by yhao on 17-11-14.
 * 固定(fixed)悬浮(float)控件
 * 可在手机任意界面显示
 * 4.4~7.0 无需权限可点击,  4.3及以下无触摸事件， 7.1及以上需申请权限
 * 不支持位置改变(因部分机型如小米无法实现)
 */

public class FixedFloatWindow implements FixedFloatView {


    /**
     * 默认类型，7.1 以下采用自定义 toast 方式跳过申请权限，7.1 及以上内部自动申请权限 ， 推荐
     */
    public static final int AUTO_REQ = 0;


    /**
     * 7.1 以下采用自定义 toast 方式跳过权限申请；7.1 及以上需用户自己申请权限，否则报错
     */
    public static final int ME_REQ = 1;


    /**
     * 所有版本都申请权限，内部自动申请
     */
    public static final int ALL_AUTO_REQ = 2;


    /**
     * 所有版本都申请权限，需要用户自己申请权限，否则报错
     */
    public static final int ALL_ME_REQ = 3;


    /**
     * 所有版本都采用自定义 toast 方式跳过权限申请，不兼容 7.1 及以上版本，不推荐
     */
    public static final int NO_REQ = 4;


    /**
     * 悬浮窗控件尺寸
     */
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;


    public FixedFloatWindow(Context applicationContext) {
        this(applicationContext, AUTO_REQ);
    }


    /**
     * @param applicationContext Application 上下文
     * @param type               申请权限类型，见注释
     */
    public FixedFloatWindow(Context applicationContext, @TYPE int type) {
        switch (type) {
            case AUTO_REQ:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                    mFixedFloatView = new FixedFloatPhone(applicationContext, true);
                } else {
                    mFixedFloatView = new FixedFloatToast(applicationContext);
                }
                break;
            case ME_REQ:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                    mFixedFloatView = new FixedFloatPhone(applicationContext, false);
                } else {
                    mFixedFloatView = new FixedFloatToast(applicationContext);
                }
                break;
            case ALL_AUTO_REQ:
                mFixedFloatView = new FixedFloatPhone(applicationContext, true);
                break;
            case ALL_ME_REQ:
                mFixedFloatView = new FixedFloatPhone(applicationContext, false);
                break;
            case NO_REQ:
                mFixedFloatView = new FixedFloatToast(applicationContext);
                break;
        }
    }


    /**
     * 设置要悬浮的视图
     *
     * @param view   要悬浮的视图
     * @param width  悬浮视图宽
     * @param height 悬浮视图高
     */
    @Override
    public void setView(View view, int width, int height) {
        mFixedFloatView.setView(view, width, height);
        mView = view;
    }

    /**
     * 设置要悬浮的视图
     *
     * @param view 要悬浮的视图
     */
    @Override
    public void setView(View view) {
        this.setView(view, WRAP_CONTENT, WRAP_CONTENT);
    }

    /**
     * 设置悬浮窗显示位置
     *
     * @param gravity Gravity.TOP 等
     * @param xOffset 偏移距离
     * @param yOffset 偏移距离
     */
    @Override
    public void setGravity(int gravity, int xOffset, int yOffset) {
        mFixedFloatView.setGravity(gravity, xOffset, yOffset);
    }

    /**
     * 显示悬浮窗
     */
    @Override
    public void show() {
        if (once) {
            mFixedFloatView.show();
            once = false;
        } else {
            if (isShow) return;
            mView.setVisibility(View.VISIBLE);
            isShow = true;
        }
    }

    /**
     * 隐藏悬浮窗
     */
    public void hide() {
        if (once || !isShow) return;
        mView.setVisibility(View.INVISIBLE);
        isShow = false;
    }

    /**
     * 销毁悬浮窗，调用后不可再通过 show 方法显示，一般情况下不需要调用
     */
    @Override
    public void dismiss() {
        mFixedFloatView.dismiss();
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


    private FixedFloatView mFixedFloatView;
    static PermissionListener mPermissionListener;
    private boolean isShow;
    private boolean once = true;
    private View mView;

    @IntDef({AUTO_REQ, ME_REQ, ALL_AUTO_REQ, ALL_ME_REQ, NO_REQ})
    @Retention(RetentionPolicy.SOURCE)
    private @interface TYPE {
    }

}
