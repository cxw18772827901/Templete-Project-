package com.lib.base.ui.action;

import androidx.viewbinding.ViewBinding;

/**
 * ViewBinding抽象类
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.ui.action
 * @author      xwchen
 * Date         2022/2/8.
 */

public abstract class ViewBindingActivityAction<T extends ViewBinding> extends RxjavaActivityAction {
    protected T mViewBinding;

    protected abstract T viewBinding();

}
