package com.templete.project.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.base.ui.fragment.BaseFragment;
import com.templete.project.databinding.CollectionFragmentBinding;

/**
 * PackageName  com.templete.project.ui.fragment
 * ProjectName  TempleteProject
 * Author       chenxiaowu
 * Date         10/10/21.
 *
 * @author chenxiaowu
 */

public class CollectionFragment extends BaseFragment<CollectionFragmentBinding> {
    public static final String TAG = "FirstFragment";

    @Override
    public CollectionFragmentBinding viewBinding(LayoutInflater inflater, ViewGroup container) {
        return CollectionFragmentBinding.inflate(getLayoutInflater(), container, false);
    }

    @Override
    public void inits() {

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

    @Override
    public void onClick(View v) {
    }
}
