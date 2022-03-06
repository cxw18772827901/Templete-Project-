package com.templete.project.ui.activity;

import android.annotation.SuppressLint;

import com.lib.base.adapter.DemoAdapter;
import com.lib.base.ui.activity.BaseActivity;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.simple.SimpleMultiListener;
import com.templete.project.databinding.ScrollActivityBBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * 官方嵌套布局,适用于无刷新悬浮UI,刷新的话存在问题
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui
 *@author      xwchen
 * Date         2022/1/12.
 */

public class ScrollActivity extends BaseActivity<ScrollActivityBBinding> {

    private DemoAdapter demoAdapter;

    @Override
    protected ScrollActivityBBinding viewBinding() {
        return ScrollActivityBBinding.inflate(getLayoutInflater());
    }

    @Override
    public void inits() {
        setTitleStr("嵌套滚动");
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {
        mViewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        demoAdapter = new DemoAdapter(this);
        mViewBinding.recyclerView.setAdapter(demoAdapter);

        mViewBinding.refreshLayout.setOnMultiListener(new SimpleMultiListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mViewBinding.refreshLayout.finishRefresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mViewBinding.refreshLayout.finishLoadMore();
            }
        });

        mViewBinding.appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            //todo
            logD("onScrolled", "verticalOffset=" + verticalOffset);
        });
    }

    @Override
    public void initEvent() {
    }

    @Override
    public void initData() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        demoAdapter.setData(list);
    }
}
