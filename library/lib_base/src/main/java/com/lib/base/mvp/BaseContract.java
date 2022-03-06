package com.lib.base.mvp;

/**
 * @author xwchen
 * @date 19-4-26
 */

public interface BaseContract {

    interface BasePresenter<T extends BaseView> {

        /**
         * Presenter绑定View
         *
         * @param view
         */
        void attachView(T view);

        /**
         * 解绑
         */
        void detachView();
    }

    interface BaseView {
        /**
         * 默认完成回调
         */
        void complete();

        /**
         * 默认错误回调
         */
        void error();
    }
}
