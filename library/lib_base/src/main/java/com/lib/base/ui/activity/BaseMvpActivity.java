package com.lib.base.ui.activity;

import android.annotation.SuppressLint;

import com.lib.base.mvp.BaseContract;

import androidx.viewbinding.ViewBinding;

/**
 * activity基类基础上增加mvp模式。
 * mvp和mvvm{@link BaseMvvmActivity}可选择性使用。
 *
 * @author xwchen
 * Date          2019/11/28.
 */
@SuppressLint("Registered")
public abstract class BaseMvpActivity<T extends ViewBinding, S extends BaseContract.BasePresenter> extends BaseActivity<T> {
    protected S mPresenter;

    /**
     * bind presenter
     *
     * @return
     */
    protected abstract S bindPresenter();

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