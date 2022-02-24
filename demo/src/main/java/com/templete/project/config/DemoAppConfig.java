package com.templete.project.config;

import com.module.a.base.ModuleAApp;
import com.module.b.config.ModuleBApp;
import com.module.login.config.ModuleLoginApp;

/**
 * ProjectName  TempleteProject
 * PackageName  com.templete.project.config
 * Author       Administrator
 * Date         2021/11/17.
 */

public interface DemoAppConfig {
    //新增module需要在此处添加application
    Class[] APP_ARR = {ModuleAApp.class, ModuleBApp.class, ModuleLoginApp.class};
}
