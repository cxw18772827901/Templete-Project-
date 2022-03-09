package com.templete.project.ui.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.ui.fragment.BaseFragment;
import com.templete.project.databinding.ForthFragmentBinding;
import com.templete.project.ui.activity.IndexActivity;
import com.templete.project.ui.activity.CanvasActivity;
import com.templete.project.ui.activity.SoftActivity;
import com.templete.project.ui.activity.TxtActivity;
import com.templete.project.ui.activity.WrapActivity;

/**
 * PackageName  com.templete.project.ui.fragment
 * ProjectName  TempleteProject
 * Date         10/10/21.
 *
 * @author xwchen
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
                    } else if (v.equals(mViewBinding.tv2)) {
                        clazz = IndexActivity.class;
                    } else if (v.equals(mViewBinding.tv3)) {
                        clazz = SoftActivity.class;
                    } else if (v.equals(mViewBinding.tv4)) {
                        clazz = CanvasActivity.class;
                    } else if (v.equals(mViewBinding.tv5)) {
                        clazz = WrapActivity.class;
                    }
                    if (clazz != null) {
                        ((BaseActivity<?>) requireActivity()).startAty(requireActivity(), clazz);
                    }
                },
                mViewBinding.tv1, mViewBinding.tv2,
                mViewBinding.tv3, mViewBinding.tv4,
                mViewBinding.tv5);
    }

    @Override
    public void initData() {

    }
}
