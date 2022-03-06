package com.lib.base.util;


import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/**
 * PackageName  com.hycg.ge.utils
 * ProjectName  HYCGGrvorenmentProject
 * @author      xwchen
 * Date         2019-07-17.
 */
public class FreshUtil {
    public static void autoRefresh(SmartRefreshLayout refreshLayout) {
        refreshLayout.autoRefresh(200, 100, 1, false);
    }

    public static void finishFresh(SmartRefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(200);
    }

    public static void finishFreshWithNoMoreData(SmartRefreshLayout refreshLayout) {
        refreshLayout.finishRefreshWithNoMoreData();
    }

    public static void finishLoad(SmartRefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore(200);
    }

    public static void finishLoadWithNoMoreData(SmartRefreshLayout refreshLayout) {
        refreshLayout.finishLoadMoreWithNoMoreData();
    }

    public static void finishSmart(SmartRefreshLayout refreshLayout, boolean isFresh) {
        if (isFresh) {
            refreshLayout.finishRefresh(200);
        } else {
            refreshLayout.finishLoadMore(200);
        }
    }

    public static void finishSmartWithNoMoreData(SmartRefreshLayout refreshLayout, boolean isFresh) {
        if (isFresh) {
            refreshLayout.finishRefreshWithNoMoreData();
        } else {
            refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    public static void enableLoadMore(SmartRefreshLayout refreshLayout, boolean enable) {
        refreshLayout.setEnableLoadMore(enable);
    }
}
