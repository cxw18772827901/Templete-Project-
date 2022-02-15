package com.lib.base.ui.action;

/**
 * 初始化工具类
 * <p>
 * PackageName  com.test.novel.ui.base
 * ProjectName  Project
 * Author       chenxiaowu
 * Date         2018/3/5.
 */

public interface InitAction {
    /**
     * 初始化开发模式
     */
    default void setDevelopmentMode() {
    }

    /**
     * setContentView之前
     */
    default void beforeSetContent() {

    }

    /**
     * inits,注意不能叫init,会跟FragmentActivity中的init方法冲突,这个问题标记一下
     */
    void inits();

    /**
     * initView
     */
    void initView();

    /**
     * initEvent
     */
    void initEvent();

    /**
     * initData
     */
    void initData();

    /**
     * fresh
     */
    default void freshData() {
    }

    /**
     * loadData
     */
    default void loadData() {
    }

    /**
     * requestData
     *
     * @param isFresh
     */
    default void requestData(boolean isFresh) {
    }
}
