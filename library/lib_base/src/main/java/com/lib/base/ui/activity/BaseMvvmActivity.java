package com.lib.base.ui.activity;


import com.lib.base.mvvm.BaseViewModel;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

/**
 * ProjectName  XSCat
 * PackageName  com.bigheadhorse.xscat.view.activity.common
 *@author      xwchen
 * Date         2021/6/16.
 */

public abstract class BaseMvvmActivity<T extends ViewBinding, S extends BaseViewModel> extends BaseActivity<T> {
    public static final String TAG = "BaseMvvmActivity";
    protected S mViewModel;

    /**
     * viewmodel
     *
     * @return
     */
    protected abstract Class<S> getViewModelClass();

    /**
     * 初始化默认viewmodel实例
     */
    @Override
    public void setDevelopmentMode() {
        Class<S> viewModelClass = getViewModelClass();
        if (viewModelClass == null) {
            throw new RuntimeException("getViewModelClass() return must not return null!");
        }
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(viewModelClass);
    }

    /**
     * 实例{@link com.lib.base.ui.activity.BaseMvvmActivity#mViewModel}以外的ViewModel
     *
     * @param clazz
     * @param <U>
     * @return
     */
    public <U extends BaseViewModel> U createViewModel(Class<U> clazz) {
        if (clazz == null) {
            throw new RuntimeException("clazz mustn't be null!");
        }
        return new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(clazz);
    }
}
