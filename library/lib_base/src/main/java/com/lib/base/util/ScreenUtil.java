package com.lib.base.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.lib.base.config.App;


/**
 * 获取屏幕尺寸工具类
 * Created by Dave on 2016/12/15.
 */

public class ScreenUtil {
    public static final String TAG = "ScreenUtil";

    private static DisplayMetrics getDisplayMetrics() {
        WindowManager wm = (WindowManager) App.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics;
    }

    public static int getScreenWid() {
        return getDisplayMetrics().widthPixels;
    }

    public static int getScreenHei() {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * 包含虚拟导航栏高度,部分机型存在奇怪问题,关闭底部虚拟导航栏后使用上面方法仍然不能获取真个屏幕高度,需要使用方法来获取整个屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeightWidthBottomBar(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Point outPoint = new Point();
        defaultDisplay.getRealSize(outPoint);
        return outPoint.y;
    }

    /**
     * 1.横屏可通过 widthPixels - widthPixels2 > 0 来判断底部导航栏是否存在
     * 2.华为部分手机上无用,通用解决办法是根据实际布局来获取屏幕的实际高度即可,例如屏幕实际高度=底部view.locationY+view.height
     *
     * @param windowManager
     * @return true表示有虚拟导航栏 false没有虚拟导航栏
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public boolean isNavigationBarShow(WindowManager windowManager) {
        Display defaultDisplay = windowManager.getDefaultDisplay();
        //获取屏幕高度
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(outMetrics);
        int heightPixels = outMetrics.heightPixels;
        //宽度
//        int widthPixels = outMetrics.widthPixels;


        //获取内容高度
        DisplayMetrics outMetrics2 = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics2);
        int heightPixels2 = outMetrics2.heightPixels;
        //宽度
//        int widthPixels2 = outMetrics2.widthPixels;

        return heightPixels - heightPixels2 > 0 /*|| widthPixels - widthPixels2 > 0*/;
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarSize() {
        int statusBarHeight = -1;
        Resources resources = App.getContext().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        DebugUtil.logD(TAG, "statusBarHeight=" + statusBarHeight);
        return statusBarHeight;
    }

    /**
     * 获取导航栏高度
     *
     * @param context
     * @return
     */
    public static int getNavHeight(Context context) {
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid != 0) {
            int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        } else
            return 0;
    }

}
