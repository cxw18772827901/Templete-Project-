package com.lib.base.mvp;

/**
 * Created by chenxiaowu on 19-4-26.
 */

public interface BaseContract {

    interface BasePresenter<T> {

        void attachView(T view);

        void detachView();
    }

    interface BaseView {
        void complete();

        void error();
    }
}
