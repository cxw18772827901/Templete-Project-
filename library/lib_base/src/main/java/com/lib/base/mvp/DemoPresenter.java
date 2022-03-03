package com.lib.base.mvp;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

/**
 * PackageName  com.bigheadhorse.xscat.presenter
 * ProjectName  NumericalCodeProject
 * Author       chenxiaowu
 * Date         2019-11-27.
 */
public class DemoPresenter extends RxPresenter<DemoContract.View> implements DemoContract.Presenter {

    public DemoPresenter(@NonNull Lifecycle lifecycle) {
        super(lifecycle);
    }

    @Override
    public void loadData1() {
        /*HttpUtil.getInstance()
                .login("16621654324", "888888")
                .compose(RxUtils::toSimpleSingle)
                .subscribe(new SingleObserver<LoginRecord>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(LoginRecord loginRecord) {
                        mView.showData1();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        mView.error();
                    }
                });*/

    }

    @Override
    public void loadData2() {
        if (isLifecycleSurvive()) {
            mView.showData2();
        }
    }

    @Override
    public void loadData3() {
        if (isLifecycleSurvive()) {
            mView.showData3();
            mView.error();
        }
    }
}
