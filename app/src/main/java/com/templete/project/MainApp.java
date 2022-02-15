package com.templete.project;

import android.annotation.SuppressLint;
import android.content.Context;

import com.lib.base.config.App;
import com.templete.project.config.AppConfig;

import androidx.multidex.MultiDex;

/**
 * ProjectName  TempleteProject
 * PackageName  com.module.a
 * Author       Administrator
 * Date         2021/10/11.
 */
public class MainApp extends App {
    public static final String TAG = "MainApp";
    @SuppressLint("StaticFieldLeak")
    private static MainApp mainApp;

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
            for (Class name : AppConfig.APP_ARR) {
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

    public static MainApp getMainApp() {
        return mainApp;
    }

}
