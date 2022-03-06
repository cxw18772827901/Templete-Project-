package com.lib.base.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lib.base.R;
import com.lib.base.aroute.ArouteConfig;
import com.lib.base.config.App;
import com.lib.base.config.AppConfig;

import java.util.List;

/**
 * PackageName  com.bigheadhorse.xscat.utils
 * ProjectName  XSCat
 *@author      xwchen
 * Date         2019-12-02.
 */
public class LoginUtil {

    public static void toLogin() {
        try {
            Activity topActivity = App.getContext().getTopActivity();
            if (ContextUtil.isActivitySurvive(topActivity)) {
//                    LoginActivity.toLogin(topActivity);
                    /*ARouter
                            .getInstance()
                            .build(ArouteConfig.ACTIVITY_MAIN_A)
                            .withTransition(R.anim.trans_pre_in, R.anim.trans_pre_out)
                            .navigation(topActivity);*/
                ARouter
                        .getInstance()
                        .build(ArouteConfig.ACTIVITY_LOGIN)
                        .withTransition(R.anim.bottom_in_anim, R.anim.no_anim)
                        .withString("data", "1000")
                        .navigation(topActivity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isLogin() {
        return !TextUtils.isEmpty(SPUtil.getString(AppConfig.USER_PHONE_NUM));
    }

    public static String getUserNum() {
        return SPUtil.getString(AppConfig.USER_PHONE_NUM);
    }

    public static void clear() {
        SPUtil.clear();
    }

    /*public static boolean isOk(LoginRecord bean) {
        return bean.code == 200;
    }*/

    public static void restartApplication() {
        try {
            Activity activity = App.getContext().getTopActivity();
            Intent intent = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
            OUtil.nonNull(intent).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
            //exit
            //App.getContext().finishAllActivity();
            //注意：不能先杀掉主进程，否则逻辑代码无法继续执行，需先杀掉相关进程最后杀掉主进程
            ActivityManager mActivityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> mList = mActivityManager.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : mList) {
                if (runningAppProcessInfo.pid != android.os.Process.myPid()) {
                    android.os.Process.killProcess(runningAppProcessInfo.pid);
                }
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
