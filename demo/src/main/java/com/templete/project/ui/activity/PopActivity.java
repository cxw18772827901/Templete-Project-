package com.templete.project.ui.activity;

import android.view.View;

import com.lib.base.bean.BtnBean;
import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.ui.pop.PopMenuView;
import com.lib.base.ui.pop.PopView;
import com.templete.project.R;
import com.templete.project.databinding.MenuLayoutBinding;
import com.templete.project.databinding.PopActivityBinding;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui
 *@author      xwchen
 * Date         2021/12/27.
 */

public class PopActivity extends BaseActivity<PopActivityBinding> {

    private PopMenuView popMenuView;

    @Override
    public void inits() {
        setTitleStr("智能popView");
        setRightClickViews((position, view) -> togoMenu(), false, new BtnBean("菜单"));
    }

    private void togoMenu() {
        if (popMenuView == null) {
            MenuLayoutBinding binding = MenuLayoutBinding.inflate(getLayoutInflater());
            binding.holder.setOnClickListener(v -> popMenuView.dismiss());
            popMenuView = new PopMenuView(binding.getRoot(), getTitleBar());
            popMenuView.show();
        } else {
            if (popMenuView.isShowing()) {
                popMenuView.dismiss();
            } else {
                popMenuView.show();
            }
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {
        setOnClickListener(this, R.id.tv_zuoshang, R.id.tv_youshang, R.id.tv_zuoxia, R.id.tv_youxia, R.id.tv_shape);
    }

    @Override
    public void initData() {

    }

    @Override
    protected PopActivityBinding viewBinding() {
        return PopActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onClick(View v) {
        View view = null;
        if (v.getId() == R.id.tv_zuoshang) {
            view = mViewBinding.tvZuoshang;
        } else if (v.getId() == R.id.tv_youshang) {
            view = mViewBinding.tvYoushang;
        } else if (v.getId() == R.id.tv_zuoxia) {
            view = mViewBinding.tvZuoxia;
        } else if (v.getId() == R.id.tv_youxia) {
            view = mViewBinding.tvYouxia;
        } else if (v.getId() == R.id.tv_shape) {
            view = mViewBinding.tvShape;
        }
        if (view != null) {
            new PopView(this, null).show(view);
        }
    }

}
