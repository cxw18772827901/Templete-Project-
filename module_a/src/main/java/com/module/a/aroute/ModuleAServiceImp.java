package com.module.a.aroute;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lib.base.aroute.ModuleAService;
import com.lib.base.aroute.ArouteConfig;
import com.lib.base.util.DebugUtil;

/**
 * PackageName  com.module.a
 * ProjectName  TempleteProject
 * Author       chenxiaowu
 * Date         10/11/21.
 */
@Route(path = ArouteConfig.MODULE_SERVICE_A)
public class ModuleAServiceImp implements ModuleAService {
    public static final String TAG = "ModuleAService";

    @Override
    public String getA() {
        return "aaaaa";
    }

    @Override
    public void init(Context context) {
        DebugUtil.logD(TAG, "ModuleAServiceImp init");
    }
}
