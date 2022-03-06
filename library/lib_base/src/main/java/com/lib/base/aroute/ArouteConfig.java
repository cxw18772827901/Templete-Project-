package com.lib.base.aroute;

/**
 * ARouter.getInstance().build(RouteConfig.ACTIVITY_MAIN_A).navigation();
 * ModuleAService moduleAService = (ModuleAService) ARouter.getInstance().build(RouteConfig.MODULE_SERVICE_A).navigation();
 * String a = moduleAService.getA();
 * DebugUtil.logD("ModuleAService", "aa=" + a);
 * 以上是使用样例demo,不同modele不能使用相同前缀
 * PackageName  com.lib.base.config
 * ProjectName  TempleteProject
 *@author      xwchen
 * Date         10/10/21.
 */
public interface ArouteConfig {
    /*app*/
    String ACTIVITY_MAIN = "/app/MainActivity";
    /*moudle_a*/
    String ACTIVITY_MAIN_A = "/module_a/MainActivity";
    String MODULE_SERVICE_A = "/module_a/ModuleAService";
    /*moudle_b*/
    String ACTIVITY_MAIN_B = "/module_b/MainActivity";
    /*moudle_login*/
    String ACTIVITY_LOGIN = "/module_l/LoginActivity";
}
