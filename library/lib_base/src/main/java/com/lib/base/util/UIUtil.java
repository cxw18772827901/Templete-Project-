package com.lib.base.util;


import android.app.Activity;
import android.util.DisplayMetrics;

import com.lib.base.config.App;


/**
 * 提供UI操作 / 布局 工具类
 * <p>
 * Created by Sky on 2015/09/16
 */
public class UIUtil {
    private static float getDensity() {
        return App.getContext().getResources().getDisplayMetrics().density;
    }

    /**
     * dip 转 px
     */
    public static int dip2px(double dip) {
        return (int) (dip * getDensity() + 0.5f);
    }

    /**
     * px 转 dip
     */
    public static int px2dip(int px) {
        return (int) (px / getDensity() + 0.5f);
    }


    public static int getDpi(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return (int) (dm.density * 160);
    }

    //width*160/dpi
    public static int getSW(Activity activity) {
        return ScreenUtil.getScreenWid() * 160 / getDpi(activity);
    }
}

