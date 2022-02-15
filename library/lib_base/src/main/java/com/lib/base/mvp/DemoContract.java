package com.lib.base.mvp;

/**
 * PackageName  com.bigheadhorse.xscat.presenter.contract
 * ProjectName  NumericalCodeProject
 * Author       chenxiaowu
 * Date         2019-11-27.
 */
public interface DemoContract {
    interface View extends BaseContract.BaseView {
        void showData1();

        void showData2();

        void showData3();

        void loadError();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void loadData1();

        void loadData2();

        void loadData3();
    }
}
