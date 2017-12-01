package com.example.fixedfloatwindow;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 自定义 toast 方式，无需申请权限
 */

class FFToast implements FixedFloatView {


    private Toast toast;

    private Object mTN;
    private Method show;
    private Method hide;

    private int mWidth;
    private int mHeight;

    private int mAnim;

    FFToast(Context applicationContext) {
        toast = new Toast(applicationContext);
    }


    public void setView(View view, int width, int height) {
        mWidth = width;
        mHeight = height;
        setView(view);
    }


    public void setView(View view) {
        toast.setView(view);
        initTN();
    }


    public void setGravity(int gravity, int xOffset, int yOffset) {
        toast.setGravity(gravity, xOffset, yOffset);
    }


    public void show() {
        try {
            show.invoke(mTN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void dismiss() {
        try {
            hide.invoke(mTN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 利用反射设置 toast 参数
     */
    private void initTN() {
        try {
            Field tnField = toast.getClass().getDeclaredField("mTN");
            tnField.setAccessible(true);
            mTN = tnField.get(toast);
            show = mTN.getClass().getMethod("show");
            hide = mTN.getClass().getMethod("hide");

            Field tnParamsField = mTN.getClass().getDeclaredField("mParams");
            tnParamsField.setAccessible(true);
            WindowManager.LayoutParams params = (WindowManager.LayoutParams) tnParamsField.get(mTN);
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            params.width = mWidth;
            params.height = mHeight;
            params.windowAnimations = mAnim;
            Field tnNextViewField = mTN.getClass().getDeclaredField("mNextView");
            tnNextViewField.setAccessible(true);
            tnNextViewField.set(mTN, toast.getView());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
