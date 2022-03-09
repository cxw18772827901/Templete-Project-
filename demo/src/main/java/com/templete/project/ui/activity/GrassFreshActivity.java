package com.templete.project.ui.activity;

import com.lib.base.adapter.DemoAdapter;
import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.util.FreshUtil;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.templete.project.databinding.GrassFreshActivityBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui.activity
 *
 * @author xwchen
 * Date         2022/2/15.
 */

public class GrassFreshActivity extends BaseActivity<GrassFreshActivityBinding> {
    public static final String TAG = "GrassFreshActivity";
    private DemoAdapter demoAdapter;

    @Override
    public void inits() {
        setTitleStr("青青草原");
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
    protected GrassFreshActivityBinding viewBinding() {
        return GrassFreshActivityBinding.inflate(getLayoutInflater());
    }
}
