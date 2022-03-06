package com.lib.base.aroute;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * PackageName  com.lib.base.aroute
 * ProjectName  TempleteProject
 * @author      xwchen
 * Date         10/11/21.
 */
public interface ModuleAService extends IProvider {
    String getA();

    void getA(Result result);
}
