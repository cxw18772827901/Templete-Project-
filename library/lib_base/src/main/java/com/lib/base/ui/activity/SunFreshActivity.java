package com.lib.base.ui.activity;

import com.lib.base.adapter.DemoAdapter;
import com.lib.base.databinding.SunFreshActivityBinding;
import com.lib.base.util.FreshUtil;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui.activity
 * Author       Administrator
 * Date         2022/2/15.
 */

public class SunFreshActivity extends BaseActivity<SunFreshActivityBinding> {

    private DemoAdapter demoAdapter;

    @Override
    public void inits() {

    }

    @Override
    public void initView() {
        mViewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        demoAdapter = new DemoAdapter(this);
        mViewBinding.recyclerView.setAdapter(demoAdapter);
        mViewBinding.refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                FreshUtil.finishSmart(mViewBinding.refreshLayout, false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                FreshUtil.finishSmart(mViewBinding.refreshLayout, true);
            }
        });
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        demoAdapter.setData(list);
    }

    @Override
    protected SunFreshActivityBinding viewBinding() {
        return SunFreshActivityBinding.inflate(getLayoutInflater());
    }
}
