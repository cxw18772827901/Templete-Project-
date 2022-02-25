package com.templete.project.ui.activity;

import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.BasicActivityBinding;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui.activity
 * Author       Administrator
 * Date         2022/2/25.
 */

public class BasicActivity extends BaseActivity<BasicActivityBinding> {
    @Override
    public void inits() {
        setTitleStr("基础绘制");
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

    @Override
    protected BasicActivityBinding viewBinding() {
        return BasicActivityBinding.inflate(getLayoutInflater());
    }
}
