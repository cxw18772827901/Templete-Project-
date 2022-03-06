package com.lib.base.mvp;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

/**
 * PackageName  com.bigheadhorse.xscat.presenter
 * ProjectName  NumericalCodeProject
 *@author      xwchen
 * Date         2019-11-27.
 *
 * @author xwchen
 */
public class DemoPresenter extends RxPresenter<DemoContract.View> implements DemoContract.Presenter {

    public DemoPresenter(@NonNull Lifecycle lifecycle) {
        super(lifecycle);
    }

    @Override
    public void loadData() {
        if (isLifecycleSurvive()) {
            mView.showData();
        }
    }
    
}
