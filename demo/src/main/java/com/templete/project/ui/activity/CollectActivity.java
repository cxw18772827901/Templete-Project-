package com.templete.project.ui.activity;

import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.CollectionActivityBinding;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui
 * Author       Administrator
 * Date         2021/12/29.
 */
public class CollectActivity extends BaseActivity<CollectionActivityBinding> {
    @Override
    public CollectionActivityBinding viewBinding() {
        return CollectionActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void inits() {
        setTitleStr("安卓开发中台");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

}
