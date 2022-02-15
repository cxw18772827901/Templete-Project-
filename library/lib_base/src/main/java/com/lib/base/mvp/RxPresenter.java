package com.lib.base.mvp;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * RxPresenter
 * 1、绑定、解绑view；
 * 2、可以将single{@link Single}添加到Disposable{@link CompositeDisposable}中，以便后续进行取消操作；
 * <p>
 * Author       chenxiaowu
 * * Date on 2019/11/28.
 */

public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    protected CompositeDisposable mDisposable;

    protected void addDisposable(Disposable subscription) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(subscription);
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }

    protected void unSubscribe() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
