package com.lib.base.ui.fragment;

import android.annotation.SuppressLint;

import com.lib.base.mvp.BaseContract;

import androidx.viewbinding.ViewBinding;

/**
 * fragment基类增加mvp模式。
 * 出于规范性，一个项目中最好不要和{@link BaseMvvmFragment}混用。
 * @author      xwchen
 * Date          2019/11/28.
 */
@SuppressLint("Registered")
public abstract class BaseMvpFragment<T extends BaseContract.BasePresenter, S extends ViewBinding> extends BaseFragment<S> {
    protected T mPresenter;

    protected abstract T bindPresenter();

    @Override
    public void setDevelopmentMode() {
        mPresenter = bindPresenter();
        mPresenter.attachView((BaseContract.BaseView) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter = null;
    }
}
