package com.templete.project.bean;

import androidx.annotation.NonNull;

/**
 * http请求基类,包含code和message和success判断
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.bean
 *@author      xwchen
 * Date         2022/2/22.
 */

public class BaseBean {
    public static final int SUCCESS = 200;
    public int code;
    public String message;

    public boolean isSuccess() {
        return code == SUCCESS;
    }

    @NonNull
    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
