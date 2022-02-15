package com.templete.project.ui.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.ui.fragment.BaseFragment;
import com.templete.project.databinding.ForthFragmentBinding;
import com.templete.project.ui.activity.TxtActivity;

/**
 * PackageName  com.templete.project.ui.fragment
 * ProjectName  TempleteProject
 * Date         10/10/21.
 *
 * @author chenxiaowu
 */

public class ForthFragment extends BaseFragment<ForthFragmentBinding> {
    @Override
    public ForthFragmentBinding viewBinding(LayoutInflater inflater, ViewGroup container) {
        return ForthFragmentBinding.inflate(getLayoutInflater(), container, false);
    }

    @Override
    public void inits() {

    }

    @Override
    public void initView() {
    }

    @Override
    public void initEvent() {
        setOnClickListener(v -> {
                    Class<? extends BaseActivity<?>> clazz = null;
                    if (v.equals(mViewBinding.tv1)) {
                        clazz = TxtActivity.class;
                    }
                    if (clazz != null) {
                        ((BaseActivity<?>) requireActivity()).startAty(requireActivity(), clazz);
                    }
                },
                mViewBinding.tv1);
    }

    @Override
    public void initData() {

    }
}
