package com.templete.project.ui.activity;

import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.LottieActivityBinding;

/**
 * PackageName  com.templete.project.ui
 * ProjectName  TempleteProject-java
 * Date         2021/12/31.
 *
 * @author xwchen
 */

public class LottieActivity extends BaseActivity<LottieActivityBinding> {
    @Override
    protected LottieActivityBinding viewBinding() {
        return LottieActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void inits() {
        setTitleStr("Lottie动画");
    }

    @Override
    public void initView() {
//        mViewBinding.iv1.setAnimation(com.lib.base.R.raw.loading4);
//        if (!mViewBinding.iv1.isAnimating()) {
//            mViewBinding.iv1.playAnimation();
//        }

        /*mViewBinding.iv1.setAnimation(com.lib.base.R.raw.loading);
        mViewBinding.iv2.setAnimation(com.lib.base.R.raw.loading1);
        mViewBinding.iv3.setAnimation(com.lib.base.R.raw.loading2);
        mViewBinding.iv4.setAnimation(com.lib.base.R.raw.loading3);
        mViewBinding.iv5.setAnimation(com.lib.base.R.raw.loading4);
        mViewBinding.iv6.setAnimation(com.lib.base.R.raw.loading5);
        mViewBinding.iv7.setAnimation(com.lib.base.R.raw.loading6);
        mViewBinding.iv8.setAnimation(com.lib.base.R.raw.loading7);
        mViewBinding.iv9.setAnimation(com.lib.base.R.raw.loading8);
        mViewBinding.iv10.setAnimation(com.lib.base.R.raw.loading9);
        mViewBinding.iv11.setAnimation(com.lib.base.R.raw.loading10);
        mViewBinding.iv12.setAnimation(com.lib.base.R.raw.loading11);
        mViewBinding.iv13.setAnimation(com.lib.base.R.raw.loading12);
        mViewBinding.iv14.setAnimation(com.lib.base.R.raw.loading13);
        mViewBinding.iv15.setAnimation(com.lib.base.R.raw.loading14);
        mViewBinding.iv16.setAnimation(com.lib.base.R.raw.loading15);
        mViewBinding.iv17.setAnimation(com.lib.base.R.raw.loading16);
        mViewBinding.iv18.setAnimation(com.lib.base.R.raw.loading17);
//        mViewBinding.iv18.postDelayed(() -> mViewBinding.iv18.cancelAnimation(), 2000);
        mViewBinding.iv19.setAnimation(com.lib.base.R.raw.loading18);
        mViewBinding.iv20.setAnimation(com.lib.base.R.raw.loading19);
        mViewBinding.iv21.setAnimation(com.lib.base.R.raw.loading20);*/
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }
}
