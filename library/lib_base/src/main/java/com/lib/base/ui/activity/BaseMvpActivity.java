package com.lib.base.ui.activity;

import android.annotation.SuppressLint;

import com.lib.base.mvp.BaseContract;

import androidx.viewbinding.ViewBinding;

/**
 * activity基类基础上增加mvp模式。
 * 出于规范性，一个项目中最好不要和{@link BaseMvvmActivity}混用。
 * Author       chenxiaowu
 * Date          2019/11/28.
 */
@SuppressLint("Registered")
public abstract class BaseMvpActivity<T extends BaseContract.BasePresenter, S extends ViewBinding> extends BaseActivity<S> {
    protected T mPresenter;

    /**
     * bind presenter
     *
     * @return
     */
    protected abstract T bindPresenter();

    @Override
    public void setDevelopmentMode() {
        mPresenter = bindPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter = null;
    }
}