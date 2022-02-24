package com.templete.project.ui.activity;

import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.ui.dialog.base.BaseDialog;
import com.lib.base.util.GlobalThreadPoolUtil;
import com.templete.project.databinding.LoadingActivityBinding;

/**
 * PackageName  com.templete.project.ui
 * ProjectName  TempleteProject-java
 * Date         2022/1/1.
 *
 * @author chenxiaowu
 */

public class LoadingActivity extends BaseActivity<LoadingActivityBinding> {
    @Override
    protected LoadingActivityBinding viewBinding() {
        return LoadingActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void inits() {
        setTitleStr("activity dialog");
    }

    @Override
    public void initView() {
        mViewBinding.tv1.setOnClickListener(v -> showDialogView(DIALOG_LOADING, "加载中...", false, false));
        mViewBinding.tv2.setOnClickListener(v -> showDialogView(DIALOG_OK, "提交成功!", false, true));
        mViewBinding.tv3.setOnClickListener(v -> showDialogView(DIALOG_WARNING, "信息不全!", false, true));
        mViewBinding.tv4.setOnClickListener(v -> {
            showDialogView(DIALOG_ERROR, "提交失败!", false, true);
            //GlobalThreadPoolUtil.postOnUiThreadDelay(this::finish, BaseDialog.SHOW_TIME*3);
        });
        mViewBinding.tv5.setOnClickListener(v -> {
            showLoadingDialog("我是dialog");
            GlobalThreadPoolUtil.postOnUiThreadDelay(this::closeDialog, BaseDialog.SHOW_TIME);
        });
        mViewBinding.tv6.setOnClickListener(v -> {
            showConfirmDialog("加载中");
            GlobalThreadPoolUtil.postOnUiThreadDelay(this::closeDialog, BaseDialog.SHOW_TIME);
        });
    }

    @Override
    public void initEvent() {
    }

    @Override
    public void initData() {
    }
}
