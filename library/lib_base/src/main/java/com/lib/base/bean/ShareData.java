package com.lib.base.bean;

import android.view.View;

import androidx.annotation.NonNull;

/**
 * PackageName  com.templete.project.bean
 * ProjectName  TempleteProject-java
 * Date         2022/2/13.
 *
 * @author chenxiaowu
 */

public class ShareData {
    private View view;
    private String transitionName;

    private ShareData() {
    }

    @NonNull
    public static ShareData createData(@NonNull View view, @NonNull String transitionName) {
        ShareData shareData = new ShareData();
        shareData.view = view;
        shareData.transitionName = transitionName;
        return shareData;
    }

    public View getView() {
        return view;
    }

    public String getTransitionName() {
        return transitionName;
    }
}
