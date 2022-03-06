package com.lib.base.ui.action;

import com.lib.base.ui.widget.HolderView;

/**
 * 基于{@link HolderView}工具类
 * <p>
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.ui.action
 * @author       xwchen
 * Date         2022/1/27.
 */

public interface StatusAction {
    /**
     * 绑定HolderView
     *
     * @return
     */
    default HolderView getHolderView() {
        return null;
    }

    /**
     * loading
     */
    default void showLoadingView() {
        HolderView holderView = getHolderView();
        if (holderView != null) {
            holderView.showLoadingView();
        }
    }

    /**
     * error view
     */
    default void showErrorView() {
        HolderView holderView = getHolderView();
        if (holderView != null) {
            holderView.showErrorView();
        }
    }

    /**
     * no data
     */
    default void showNoDataView() {
        HolderView holderView = getHolderView();
        if (holderView != null) {
            holderView.showNoDataView();
        }
    }

    /**
     * cancelFresh
     *
     * @param cancelFresh
     */
    default void cancelFresh(boolean cancelFresh) {
        HolderView holderView = getHolderView();
        if (holderView != null) {
            holderView.cancelFresh(cancelFresh);
        }
    }

    /**
     * hide loading View
     */
    default void hideView() {
        HolderView holderView = getHolderView();
        if (holderView != null) {
            holderView.hideView();
        }
    }
}
