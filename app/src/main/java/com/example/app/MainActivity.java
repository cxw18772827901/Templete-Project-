package com.example.app;

import com.example.app.databinding.ActivityMainBinding;
import com.lib.base.ui.activity.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    @Override
    public void inits() {
        setTitleStr("首页");
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
    protected ActivityMainBinding viewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}