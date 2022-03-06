package com.templete.project.ui.activity;

import com.lib.base.databinding.RatioActivityBinding;
import com.lib.base.ui.activity.BaseActivity;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui
 * @author      xwchen
 * Date         2022/1/19.
 */

public class RatioActivity extends BaseActivity<RatioActivityBinding> {
    @Override
    protected RatioActivityBinding viewBinding() {
        return RatioActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void inits() {
        setTitleStr("百分比布局");
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
