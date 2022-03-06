package com.module.login.mvvm;

import android.app.Application;

import com.lib.base.mvvm.BaseViewModel;

import androidx.annotation.NonNull;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.module.login.mvvm
 * @author      xwchen
 * Date         2021/12/29.
 */

public class LoginViewModel extends BaseViewModel {
    /**
     * 注意:ViewModel构造函数不要写任务业务逻辑,否则有报错的可能
     *
     * @param application app application
     */
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }
}
