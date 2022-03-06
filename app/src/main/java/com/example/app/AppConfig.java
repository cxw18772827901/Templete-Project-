package com.example.app;

import com.module.a.base.ModuleAApp;
import com.module.b.config.ModuleBApp;
import com.module.login.config.ModuleLoginApp;

/**
 * ProjectName  TempleteProject
 * PackageName  com.templete.project.config
 *@author      xwchen
 * Date         2021/11/17.
 */

public interface AppConfig {
    //新增module需要在此处添加application
    Class[] APP_ARR = {ModuleAApp.class, ModuleBApp.class, ModuleLoginApp.class};
}
