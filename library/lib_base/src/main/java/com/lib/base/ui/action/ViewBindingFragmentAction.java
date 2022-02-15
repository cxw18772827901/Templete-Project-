package com.lib.base.ui.action;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

/**
 * ViewBinding抽象类
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.ui.action
 * Author       Administrator
 * Date         2022/2/8.
 */

public abstract class ViewBindingFragmentAction<T extends ViewBinding> extends RxjavaFragmentAction {
    protected T mViewBinding;

    protected abstract T viewBinding(LayoutInflater inflater, ViewGroup container);

}