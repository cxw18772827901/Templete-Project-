package com.module.b.view.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lib.base.aroute.ArouteConfig;
import com.lib.base.ui.activity.BaseActivity;
import com.module.b.databinding.MbActivityMainBinding;

@Route(path = ArouteConfig.ACTIVITY_MAIN_B/*, group = RouteConfig.GROUP_B*/)
public class MainActivity extends BaseActivity<MbActivityMainBinding> {

    @Override
    public MbActivityMainBinding viewBinding() {
        return MbActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void inits() {
        setTitleStr("module b");
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