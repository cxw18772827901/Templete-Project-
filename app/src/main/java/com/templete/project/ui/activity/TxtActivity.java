package com.templete.project.ui.activity;

import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.util.txt.txtflag.TxtUtil;
import com.templete.project.R;
import com.templete.project.databinding.TxtActivityBinding;

/**
 * PackageName  com.templete.project.ui.activity
 * ProjectName  TempleteProject-java
 * Date         2022/2/15.
 *
 * @author chenxiaowu
 */

public class TxtActivity extends BaseActivity<TxtActivityBinding> {
    @Override
    public void inits() {
        setTitleStr("繁琐的文本标签");
    }

    @Override
    public void initView() {
        /**
         * 只能打单标签,同时上色+粗体+字号调整,多标签请手动处理
         */
        TxtUtil.setText(mViewBinding.tv1, "#FF0000", (int) getDimen(R.dimen.x75), true, mViewBinding.tv1.getText().toString(), "标签");
        /**
         * 只能打单标签,同时上色+粗体+字号调整,多标签请手动处理
         */
        TxtUtil.setText(mViewBinding.tv2, "#FF0000", (int) getDimen(R.dimen.x75), false, mViewBinding.tv2.getText().toString(), "标签");
        /**
         * 多标签上色
         */
        getaVoid();
        /**
         * 行首增加单标签
         */
        TxtUtil.setTxtLable(this, mViewBinding.tv4, mViewBinding.tv4.getText().toString(), (int) getDimen(R.dimen.x45), "标签1");
        /**
         * 行首增加双标签
         */
        TxtUtil.setTxtLables(this, mViewBinding.tv5, mViewBinding.tv5.getText().toString(), (int) getDimen(R.dimen.x45), "标签1", "标签2");
        /**
         * 修改字体粗细,默认值0.3,注意不要跟粗体一起使用
         */
        TxtUtil.setTextBold(mViewBinding.tv6, "默认0.3粗细的字体");
        /**
         * 修改字体粗细,修改为2.0,注意不要跟粗体一起使用
         */
        TxtUtil.setTextBold(mViewBinding.tv7, "2.0粗细的字体", (float) 2.0);
    }

    private void getaVoid() {
        TxtUtil.setText(mViewBinding.tv3, "#FF0000", mViewBinding.tv3.getText().toString(), "abc", "标签");
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected TxtActivityBinding viewBinding() {
        return TxtActivityBinding.inflate(getLayoutInflater());
    }
}
