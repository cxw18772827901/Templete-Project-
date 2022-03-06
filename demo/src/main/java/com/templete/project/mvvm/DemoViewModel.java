package com.templete.project.mvvm;

import android.app.Application;

import com.lib.base.mvvm.BaseViewModel;

import androidx.annotation.NonNull;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.mvvm
 * @author      xwchen
 * Date         2021/12/28.
 */

public class DemoViewModel extends BaseViewModel {
    /**
     * 注意:ViewModel构造函数不要写任务业务逻辑,否则有报错的可能
     *
     * @param application app application
     */
    public DemoViewModel(@NonNull Application application) {
        super(application);
    }

    public void test(int tag,String data) {
        setBaseData(tag,data);
    }
}
