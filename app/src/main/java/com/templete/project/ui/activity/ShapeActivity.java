package com.templete.project.ui.activity;

import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.ShapeDemoActivityBinding;

public class ShapeActivity extends BaseActivity<ShapeDemoActivityBinding> {

    @Override
    public void inits() {
        setTitleStr("最强shape");
    }

    @Override
    public void initView() {
        mViewBinding.btnMainTest.setOnClickListener(v -> {
            mViewBinding.btnMainTest.getShapeDrawableBuilder()
                    .setSolidColor(0xFF000000)
                    .setStrokeColor(0xFF5A8DDF)
                    .intoBackground();
            mViewBinding.btnMainTest.getTextColorBuilder()
                    .setTextColor(0xFFFFFFFF)
                    .intoTextColor();
            mViewBinding.btnMainTest.setText("颜色已经改变啦");
        });
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected ShapeDemoActivityBinding viewBinding() {
        return ShapeDemoActivityBinding.inflate(getLayoutInflater());
    }

}