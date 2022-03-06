package com.lib.base.mvp;

/**
 * PackageName  com.bigheadhorse.xscat.presenter.contract
 * ProjectName  NumericalCodeProject
 * Date         2019-11-27.
 *
 * @author xwchen
 */
public interface DemoContract {
    interface View extends BaseContract.BaseView {
        /**
         * showData
         */
        void showData();

        /**
         * loadError
         */
        void loadError();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        /**
         * loadData
         */
        void loadData();
    }
}
