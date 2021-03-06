package com.module.b.config;

import android.annotation.SuppressLint;
import android.content.Context;

import com.lib.base.config.App;

/**
 * ProjectName  TempleteProject
 * PackageName  com.module.a
 * @author      xwchen
 * Date         2021/10/11.
 */

@SuppressLint("StaticFieldLeak")
public class ModuleBApp extends App {
    private static ModuleBApp moduleBApp;

    @Override
    public void initApplication(Context context) {
        moduleBApp = this;
    }

    @Override
    public void initModule(Context context) {
        moduleBApp = this;
    }

    /**
     * 1.作为moudle时,此方法仅返回一个ModuleAApp对象实例,不要当context使用,因为当前ModuleAApp对象为反射方式创建,并非由系统创建.需要获取context直接调用getContext方法即可.
     * 2.独立app运行时没有影响,可以直接当context使用.
     *
     * @return ModuleAApp
     */
    public static ModuleBApp getModuleBApp() {
        return moduleBApp;
    }

}
