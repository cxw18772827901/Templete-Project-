package com.templete.project.ui.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lib.base.ui.fragment.BaseFragment;
import com.templete.project.databinding.EmptyLayoutBinding;

/**
 * PackageName  com.templete.project.ui.fragment
 * ProjectName  TempleteProject-java
 * Date         2021/12/29.
 *
 * @author xwchen
 */

public class EmptyFragment extends BaseFragment<EmptyLayoutBinding> {
    public static final String TAG = "EmptyFragment_LOG";

    @Override
    public EmptyLayoutBinding viewBinding(LayoutInflater inflater, ViewGroup container) {
        return EmptyLayoutBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initS() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    public void getData(int i) {
        logD(TAG, "i=" + i);
    }
}
