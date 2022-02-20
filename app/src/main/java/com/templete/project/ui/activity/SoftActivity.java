package com.templete.project.ui.activity;

import com.lib.base.ui.activity.AppTheme;
import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.SoftActivityBinding;

/**
 * PackageName  com.templete.project.ui.activity
 * ProjectName  TempleteProject-java
 * Date         2022/2/20.
 *
 * @author chenxiaowu
 */

public class SoftActivity extends BaseActivity<SoftActivityBinding> {

    @Override
    public int getActivityTheme() {
        return AppTheme.THEME_BLUE;
    }

    @Override
    public void inits() {
        setTitleStr("输入法");
    }

    @Override
    public void softKeyboard(boolean open, int keyboardHeight) {
        logD("softKeyboard", "open=" + open + ",keyboardHeight=" + keyboardHeight);
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
    protected SoftActivityBinding viewBinding() {
        return SoftActivityBinding.inflate(getLayoutInflater());
    }
}
