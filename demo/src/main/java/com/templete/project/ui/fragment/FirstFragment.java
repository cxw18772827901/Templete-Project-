package com.templete.project.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.base.bean.BtnBean;
import com.lib.base.ui.activity.DialogActivity;
import com.lib.base.ui.fragment.BaseFragment;
import com.templete.project.databinding.FirstFragmentBinding;
import com.templete.project.ui.activity.CustomXTabActivity;
import com.templete.project.ui.activity.GlideActivity;
import com.templete.project.ui.activity.ImmersionActivity;
import com.templete.project.ui.activity.PopActivity;
import com.templete.project.ui.activity.RatioActivity;
import com.templete.project.ui.activity.ShapeActivity;
import com.templete.project.ui.activity.ViewActivity;
import com.templete.project.ui.activity.XTabActivity;

/**
 * PackageName  com.templete.project.ui.fragment
 * ProjectName  TempleteProject
 *@author      xwchen
 * Date         10/10/21.
 *
 * @author xwchen
 */

public class FirstFragment extends BaseFragment<FirstFragmentBinding> {
    public static final String TAG = "FirstFragment";

    @Override
    public FirstFragmentBinding viewBinding(LayoutInflater inflater, ViewGroup container) {
        return FirstFragmentBinding.inflate(getLayoutInflater(), container, false);
    }

    @Override
    public void inits() {
        //mViewBinding.titleBar.setRightClickListener((position, view) -> DebugUtil.toast("right click"));
        mViewBinding.titleBar.setRightClickViews((position, view) -> {
            //
            toast("right click");
        }, false, new BtnBean("按钮1"), new BtnBean("按钮1"), new BtnBean("按钮1"), new BtnBean("按钮1"));
    }

    @Override
    public void initView() {
    }

    @Override
    public void initEvent() {
        setOnClickListener(v -> {
                    Class clazz = null;
                    if (v.equals(mViewBinding.tv1)) {
                        clazz = PopActivity.class;
                    } else if (v.equals(mViewBinding.tv2)) {
                        clazz = ShapeActivity.class;
                    } else if (v.equals(mViewBinding.tv3)) {
                        clazz = DialogActivity.class;
                    } else if (v.equals(mViewBinding.tv4)) {
                        clazz = XTabActivity.class;
                    } else if (v.equals(mViewBinding.tv5)) {
                        clazz = CustomXTabActivity.class;
                    } else if (v.equals(mViewBinding.tv6)) {
                        clazz = ViewActivity.class;
                    } else if (v.equals(mViewBinding.tv7)) {
                        clazz = RatioActivity.class;
                    } else if (v.equals(mViewBinding.tv8)) {
                        clazz = GlideActivity.class;
                    } else if (v.equals(mViewBinding.tv9)) {
                        clazz = ImmersionActivity.class;
                    }
                    if (clazz != null) {
                        getBaseActivity().startAty(requireActivity(), clazz);
                    }
                },
                mViewBinding.tv1, mViewBinding.tv2, mViewBinding.tv3,
                mViewBinding.tv4, mViewBinding.tv5, mViewBinding.tv6,
                mViewBinding.tv7, mViewBinding.tv8, mViewBinding.tv9);
    }

    @Override
    public void initData() {
        //getBaseActivity().showDialogView(GlobalActivity.DIALOG_OK, "app启动", false, true);
    }

    @Override
    public void onClick(View v) {
    }
}
