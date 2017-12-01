package com.example.fixedfloatwindow;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yhao on 17-12-1.
 * AUTO_REQ：默认类型，7.1 以下采用自定义 toast 方式跳过申请权限，7.1 及以上内部自动申请权限 ， 推荐
 * ME_REQ：7.1 以下采用自定义 toast 方式跳过权限申请；7.1 及以上需用户自己申请权限，否则报错
 * ALL_AUTO_REQ：所有版本都申请权限，内部自动申请
 * ALL_ME_REQ：所有版本都申请权限，需要用户自己申请权限，否则报错
 * NO_REQ：所有版本都采用自定义 toast 方式跳过权限申请，不兼容 7.1 及以上版本，不推荐
 */

public class ReqType {
    public static final int AUTO_REQ = 0;
    public static final int ME_REQ = 1;
    public static final int ALL_AUTO_REQ = 2;
    public static final int ALL_ME_REQ = 3;
    public static final int NO_REQ = 4;

    @IntDef({AUTO_REQ, ME_REQ, ALL_AUTO_REQ, ALL_ME_REQ, NO_REQ})
    @Retention(RetentionPolicy.SOURCE)
    public @interface REQ_TYPE {
    }
}
