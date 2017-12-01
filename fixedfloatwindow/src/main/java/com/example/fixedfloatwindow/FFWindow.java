package com.example.fixedfloatwindow;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.StyleableRes;
import android.support.v4.media.RatingCompat;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Method;

/**
 * Created by yhao on 17-11-14.
 * 应用内 固定(fixed) 悬浮(float) 窗
 * 4.X~7.0 版本采用自定义 toast 方式，可绕过权限申请，
 * 7.1 及以上采用权限申请 TYPE_PHONE 方式，需要申请权限
 * 以上根据 Android 版本选择不同处理方式为最佳方案
 * 你也可以轻松的选择其他方案测试、使用，见 ReqType.java
 * 局限：不支持位置改变；4.3 版本及以下无法接收触摸事件
 */

public class FFWindow {


    /**
     * 悬浮窗控件尺寸
     */
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;


    public FFWindow(Context applicationContext) {
        this(applicationContext, ReqType.AUTO_REQ);
    }


    /**
     * @param applicationContext Application 上下文
     * @param reqType            申请权限类型
     */
    public FFWindow(Context applicationContext, @ReqType.REQ_TYPE int reqType) {
        mApplicationContext = applicationContext;
        switch (reqType) {
            case ReqType.AUTO_REQ:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                    mFixedFloatView = new FFPhone(applicationContext, true);
                } else {
                    mFixedFloatView = new FFToast(applicationContext);
                }
                break;
            case ReqType.ME_REQ:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                    mFixedFloatView = new FFPhone(applicationContext, false);
                } else {
                    mFixedFloatView = new FFToast(applicationContext);
                }
                break;
            case ReqType.ALL_AUTO_REQ:
                mFixedFloatView = new FFPhone(applicationContext, true);
                break;
            case ReqType.ALL_ME_REQ:
                mFixedFloatView = new FFPhone(applicationContext, false);
                break;
            case ReqType.NO_REQ:
                mFixedFloatView = new FFToast(applicationContext);
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
    public FFWindow setView(View view, int width, int height) {
        mFixedFloatView.setView(view, width, height);
        mView = view;
        return this;
    }

    /**
     * 设置要悬浮的视图
     *
     * @param view 要悬浮的视图
     */
    public FFWindow setView(View view) {
        this.setView(view, WRAP_CONTENT, WRAP_CONTENT);
        return this;
    }

    /**
     * 设置悬浮窗显示位置
     *
     * @param gravity Gravity.TOP 等
     * @param xOffset 偏移距离
     * @param yOffset 偏移距离
     */
    public FFWindow setGravity(int gravity, int xOffset, int yOffset) {
        mFixedFloatView.setGravity(gravity, xOffset, yOffset);
        return this;
    }

    /**
     * 设置 Activity 过滤器，用于指定在哪些界面显示悬浮窗
     *
     * @param filterType 　过滤类型,子类类型也会生效
     * @param activities 　过滤界面
     */
    public FFWindow setFilter(@FilterType.FILTER_TYPE int filterType, Class... activities) {
        switch (filterType) {
            case FilterType.SHOW:
                new FFLifecycle(mApplicationContext, this, true, activities);
                break;
            case FilterType.NOT_SHOW:
                new FFLifecycle(mApplicationContext, this, false, activities);
                break;
        }
        return this;
    }


    /**
     * 显示悬浮窗
     */
    public FFWindow show() {
        if (once) {
            mFixedFloatView.show();
            once = false;
        } else {
            if (isShow) return this;
            mView.setVisibility(View.VISIBLE);
            isShow = true;
        }
        return this;
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
    public void dismiss() {
        mFixedFloatView.dismiss();
        isShow = false;
    }

    /**
     * 申请权限回调，注意只有设置内部自动申请权限才需要设置此监听，否则也不会回调
     * 一般情况下不需要设置监听，因为权限申请成功后显示悬浮窗的操作都在内部封装好了
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
            result = false;
        }
        return result;
    }


    void postHide() {
        if (once || !isShow) return;
        mView.post(new Runnable() {
            @Override
            public void run() {
                mView.setVisibility(View.INVISIBLE);
            }
        });
        isShow = false;
    }


    private Context mApplicationContext;
    private FixedFloatView mFixedFloatView;
    static PermissionListener mPermissionListener;
    private boolean isShow;
    private boolean once = true;
    private View mView;


}
