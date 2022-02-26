package com.templete.project.ui.activity;

import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.PaintActivityBinding;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui.activity
 * Author       Administrator
 * Date         2022/2/25.
 */

public class CanvasActivity extends BaseActivity<PaintActivityBinding> {
    @Override
    public void inits() {
        setTitleStr("绘制回顾");
    }

    @Override
    public void initView() {
        setOnClickListener(v -> {
                    Class<? extends BaseActivity<?>> clazz = null;
                    if (v.equals(mViewBinding.tv1)) {
                        clazz = BasicActivity.class;
                    } else if (v.equals(mViewBinding.tv2)) {
                        clazz = SummaryGraphActivity.class;
                    } else if (v.equals(mViewBinding.tv3)) {
                        clazz = RemoteControlActivity.class;
                    }
                    if (clazz != null) {
                        startAty(this, clazz);
                    }
                },
                mViewBinding.tv1, mViewBinding.tv2,
                mViewBinding.tv3);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected PaintActivityBinding viewBinding() {
        return PaintActivityBinding.inflate(getLayoutInflater());
    }
}
