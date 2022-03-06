package com.templete.project.ui.activity;

import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.SummaryGraphActivityBinding;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui.activity
 *@author      xwchen
 * Date         2022/2/25.
 */

public class SummaryGraphActivity extends BaseActivity<SummaryGraphActivityBinding> {
    @Override
    public void inits() {
        setTitleStr("统计图");
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
    protected SummaryGraphActivityBinding viewBinding() {
        return SummaryGraphActivityBinding.inflate(getLayoutInflater());
    }
}
