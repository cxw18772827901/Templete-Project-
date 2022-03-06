package com.lib.base.bean;

import android.view.View;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.bean
 *@author      xwchen
 * Date         2021/12/27.
 */

public class BtnBean {
    public String str;
    public View view;
    public int resId;

    public BtnBean(String str) {
        this.str = str.trim();
    }

    public BtnBean(View view) {
        this.view = view;
    }

    public BtnBean(int resId) {
        this.resId = resId;
    }

    public BtnBean(String str, int resId) {
        this.str = str;
        this.resId = resId;
    }
}
