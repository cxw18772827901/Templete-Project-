package com.templete.project;

import android.annotation.SuppressLint;
import android.content.Context;

import com.lib.base.config.App;
import com.templete.project.config.DemoAppConfig;

import androidx.multidex.MultiDex;

/**
 * ProjectName  TempleteProject
 * PackageName  com.module.a
 *@author      xwchen
 * Date         2021/10/11.
 */
public class DemoMainApp extends App {
    public static final String TAG = "MainApp";
    @SuppressLint("StaticFieldLeak")
    private static DemoMainApp mainApp;

    @Override
    public void initApplication(Context context) {
        init(context, true);
    }


    @Override
    public void initModule(Context context) {
        init(context, false);
    }

    private void init(Context context, boolean isApp) {
        try {
            mainApp = this;
            for (Class name : DemoAppConfig.APP_ARR) {
                Object o = name.newInstance();
                if (isApp) {
                    ((App) o).initApplication(context);
                } else {
                    ((App) o).initModule(context);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static DemoMainApp getMainApp() {
        return mainApp;
    }

}
