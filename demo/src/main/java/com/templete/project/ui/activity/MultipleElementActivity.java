package com.templete.project.ui.activity;

import android.annotation.SuppressLint;

import com.lib.base.bean.ShareData;
import com.lib.base.glide.GlideUtil;
import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.OneElementActivityBinding;

/**
 * PackageName  com.templete.project.ui
 * ProjectName  TempleteProject-java
 * Date         2022/2/13.
 *
 * @author xwchen
 */

public class MultipleElementActivity extends BaseActivity<OneElementActivityBinding> {
    public static final String TAG = "MultipleElementActivity";

    @Override
    protected OneElementActivityBinding viewBinding() {
        return OneElementActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void inits() {
        setTitleStr("A activity");
    }

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void initView() {
        String url1 = "http://images0.zaijiawan.com/nanjing/huazhuangdaquan/2/5-1.jpg@!16app_video_list";
        GlideUtil.loadPic(this, url1, mViewBinding.iv);
        mViewBinding.iv.setOnClickListener(v -> {
            startAty(this, ElementTargetActivity.class,
                    ShareData.createData(mViewBinding.iv, "ivTransform"),
                    ShareData.createData(mViewBinding.tv, "tvTransform"));
        });
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int finishAnimType() {
        return ANIM_NO;
    }
}
