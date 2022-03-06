package com.lib.base.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * 1.getLifecycle().addObserver(new LifecycleImp());
 * 2.添加生命周期注释@OnLifecycleEvent方法随着组件生命周期触发而自动触发
 * <p>
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.util
 *@author      xwchen
 * Date         2021/12/1.
 */

public class LifecycleImp implements /*LifecycleObserver*/DefaultLifecycleObserver {
    public static final String TAG = "LifecycleImp";

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        DebugUtil.logD(TAG, "onCreate");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        DebugUtil.logD(TAG, "onResume");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        DebugUtil.logD(TAG, "onStart");
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        DebugUtil.logD(TAG, "onStop");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        DebugUtil.logD(TAG, "onPause");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        DebugUtil.logD(TAG, "onDestroy");
    }
}
