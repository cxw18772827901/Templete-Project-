package com.module.a.base;

import android.annotation.SuppressLint;
import android.content.Context;

import com.lib.base.config.App;

/**
 * ProjectName  TempleteProject
 * PackageName  com.module.a
 * Author       Administrator
 * Date         2021/10/11.
 */
public class ModuleAApp extends App {
    @SuppressLint("StaticFieldLeak")
    private static ModuleAApp moduleAApp;

    @Override
    public void initApplication(Context context) {
        moduleAApp = this;
    }

    @Override
    public void initModule(Context context) {
        moduleAApp = this;
    }

    /**
     * 1.作为moudle时,此方法仅返回一个ModuleAApp对象实例,不要当context使用,因为当前ModuleAApp对象为反射方式创建,并非由系统创建.需要获取context直接调用getContext方法即可.
     * 2.独立app运行时没有影响,可以直接当context使用.
     *
     * @return ModuleAApp
     */
    public static ModuleAApp getModuleAApp() {
        return moduleAApp;
    }

}
