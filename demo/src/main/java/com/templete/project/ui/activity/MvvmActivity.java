package com.templete.project.ui.activity;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lib.base.ui.activity.BaseMvvmActivity;
import com.templete.project.R;
import com.templete.project.databinding.DemoMvvmActivityBinding;
import com.templete.project.mvvm.DemoViewModel;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui
 * @author      xwchen
 * Date         2021/12/28.
 */

public class MvvmActivity extends BaseMvvmActivity<DemoMvvmActivityBinding, DemoViewModel> {
    public static final String TAG = "DemoMvvmActivity";
    private LayoutTransition mTransitioner;
    private float dimension210;

    @Override
    protected DemoMvvmActivityBinding viewBinding() {
        return DemoMvvmActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class<DemoViewModel> getViewModelClass() {
        return DemoViewModel.class;
    }

    @Override
    public void initS() {
        setTitleStr("mvvm activity");
        dimension210 = getDimen(com.lib.base.R.dimen.x210);
    }

    private int pos = 1;

    @SuppressLint("InflateParams")
    @Override
    public void initView() {
        mViewBinding.tvAdd.setOnClickListener(v -> {
            View viewChild = LayoutInflater.from(MvvmActivity.this).inflate(R.layout.add_item, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mViewBinding.ll.addView(viewChild, mViewBinding.ll.getChildCount(), params);
        });
        mViewBinding.tvDelete.setOnClickListener(v -> {
            try {
                if (mViewBinding.ll.getChildCount() == 0) {
                    return;
                }
                mViewBinding.ll.removeViewAt(pos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //??????LayoutTransition
        mTransitioner = new LayoutTransition();
        //?????????ViewGroup??????
        mViewBinding.ll.setLayoutTransition(mTransitioner);
        setTransition();
    }

    private void setTransition() {
        //??????View?????????????????????
        /*ObjectAnimator addAnimator = ObjectAnimator
                .ofFloat(null, "transitionY", mViewBinding.ll.getHeight(), mViewBinding.ll.getHeight() + dimension210)
                .setDuration(mTransitioner.getDuration(LayoutTransition.APPEARING));
        mTransitioner.setAnimator(LayoutTransition.APPEARING, addAnimator);*/

        //??????View?????????????????????
        /*ObjectAnimator removeAnimator = ObjectAnimator
                //.ofFloat(null, "transitionY", mViewBinding.ll.getHeight(), mViewBinding.ll.getHeight() - dimension210)
//                .ofFloat(null, "transitionY", dimension210 * pos, dimension210 * (pos-1))
//                .ofFloat(null, "transitionY", dimension210 * (pos+1), dimension210 * pos)
                .ofFloat(null, "transitionY", dimension210 * (pos + 2), dimension210 * (pos+1))
                .setDuration(mTransitioner.getDuration(LayoutTransition.DISAPPEARING));
        mTransitioner.setAnimator(LayoutTransition.DISAPPEARING, removeAnimator);*/

        /*//view ???????????????????????????????????????view?????????????????????
        mTransitioner.setStagger(LayoutTransition.CHANGE_APPEARING, 30);
        mTransitioner.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);*/
    }

    @Override
    public void initEvent() {
        /*getGlobalUserStateViewModel().data1.observe(this, integer -> DebugUtil.logD(TAG, "data1=" + integer));
        getGlobalUserStateViewModel().data2.observe(this, integer -> DebugUtil.logD(TAG, "data2=" + integer));*/

    }

    @Override
    public void initData() {
//        mViewModel.test(BaseData.TAG_1, "test");
        /*if (!getGlobalUserStateViewModel().init) {
            getGlobalUserStateViewModel().init = true;
            getGlobalUserStateViewModel().data1.setValue(1);
            getGlobalUserStateViewModel().data2.setValue(2);
        }*/
         /*mViewModel.getBaseData().observe(this, baseData -> {
            switch (baseData.type) {
                case BaseData.TAG_1:
                    DebugUtil.logD(TAG, "baseData=" + GsonUtil.getInstance().toJson(baseData));
                    break;
                default:
                    break;
            }
        });*/
    }

    /*???????????????????????????/?????????*/
    /*private void toggleMenu(boolean hideStatusBar) {
        initMenuAnim();
        if (mAblTopMenu.getVisibility() == View.VISIBLE) {
            //??????
            mAblTopMenu.startAnimation(mTopOutAnim);
            mLlBottomMenu.startAnimation(mBottomOutAnim);
            mAblTopMenu.setVisibility(GONE);
            mLlBottomMenu.setVisibility(GONE);
            mTvPageTip.setVisibility(GONE);

            if (hideStatusBar) {
                hideSystemBar();
            }
        } else {
            mAblTopMenu.setVisibility(View.VISIBLE);
            mLlBottomMenu.setVisibility(View.VISIBLE);
            mAblTopMenu.startAnimation(mTopInAnim);
            mLlBottomMenu.startAnimation(mBottomInAnim);

            showSystemBar();
        }
    }

    private void initMenuAnim() {
        if (mTopInAnim != null) return;

        mTopInAnim = AnimationUtils.loadAnimation(this, R.anim.slide_top_in);
        mTopOutAnim = AnimationUtils.loadAnimation(this, R.anim.slide_top_out);
        mBottomInAnim = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_in);
        mBottomOutAnim = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_out);
        //?????????????????????
        mTopOutAnim.setDuration(200);
        mBottomOutAnim.setDuration(200);
    }*/
}
