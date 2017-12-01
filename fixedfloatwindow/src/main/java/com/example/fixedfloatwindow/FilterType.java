package com.example.fixedfloatwindow;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yhao on 17-12-1.
 * Activity　过滤类型，两种方式
 * NOT_SHOW : 设置项不显示，其他全部显示
 * SHOW ：　设置项显示，其他全部不显示
 */

public class FilterType {

    public static final int NOT_SHOW = 1;
    public static final int SHOW = 2;

    @IntDef({SHOW, NOT_SHOW})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FILTER_TYPE {
    }
}
