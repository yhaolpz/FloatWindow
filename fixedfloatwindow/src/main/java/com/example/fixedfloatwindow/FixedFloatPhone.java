package com.example.fixedfloatwindow;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by yhao on 17-11-14.
 * 7.1及以上需申请权限
 */

class FixedFloatPhone implements FixedFloatView {

    private final Context mContext;

    private int mWidth = FixedFloatWindow.WRAP_CONTENT;
    private int mHeight = FixedFloatWindow.WRAP_CONTENT;

    private final WindowManager mWindowManager;
    private final WindowManager.LayoutParams mLayoutParams;
    private View mView;

    private boolean mAutoReqPermission;


    public FixedFloatPhone(Context applicationContext, boolean autoReqPermission) {
        mContext = applicationContext;
        mAutoReqPermission = autoReqPermission;
        mWindowManager = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
    }


    @Override
    public void setView(View view, int width, int height) {
        mWidth = width;
        mHeight = height;
        setView(view);
    }

    @Override
    public void setView(View view) {
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mLayoutParams.width = mWidth;
        mLayoutParams.height = mHeight;
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        mView = view;
    }

    @Override
    public void setGravity(int gravity, int xOffset, int yOffset) {
        mLayoutParams.gravity = gravity;
        mLayoutParams.x = xOffset;
        mLayoutParams.y = yOffset;
    }

    @Override
    public void show() {
        if (FixedFloatWindow.hasPermission(mContext)) {
            mWindowManager.addView(mView, mLayoutParams);
        } else {
            if (mAutoReqPermission) {
                FixedFloatActivity.setPermissionListener(new PermissionListener() {
                    @Override
                    public void onSuccess() {
                        mWindowManager.addView(mView, mLayoutParams);
                        if (FixedFloatWindow.mPermissionListener != null) {
                            FixedFloatWindow.mPermissionListener.onSuccess();
                        }
                    }

                    @Override
                    public void onFail() {
                        if (FixedFloatWindow.mPermissionListener != null) {
                            FixedFloatWindow.mPermissionListener.onFail();
                        }
                    }
                });
                Intent intent = new Intent(mContext, FixedFloatActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }else{
                throw new IllegalArgumentException("请将 FixedFloatWindow 设置为自动申请权限，或自行申请权限！");
            }
        }
    }

    @Override
    public void hide() {
        mWindowManager.removeView(mView);
    }
}
