package com.lib.base.ui.action;

import android.content.Intent;

import com.lib.base.config.App;
import com.lib.base.mvvm.GlobalViewModel;
import com.lib.base.rxjava.RxHelper;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Rxjava取消
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.ui.action
 * Author       Administrator
 * Date         2022/1/27.
 */

public class RxjavaActivityAction extends DialogAction {
    protected GlobalViewModel getGlobalViewModel() {
        return ((App) getApplication()).getGlobalViewModel();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onActivityResults(requestCode, resultCode, data);
    }

    protected void onActivityResults(int requestCode, int resultCode, Intent data) {

    }

    /**
     * 自定义CompositeDisposable,结合Lifecycle,自动取消操作
     */
    private RxHelper rxHelper;

    protected void addDisposable(Disposable subscription) {
        getRxHelper().addDisposable(subscription);
    }

    protected RxHelper getRxHelper() {
        if (rxHelper == null) {
            rxHelper = new RxHelper(getLifecycle());
        }
        return rxHelper;
    }
}
