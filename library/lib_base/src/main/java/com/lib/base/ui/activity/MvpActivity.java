package com.lib.base.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;

import com.lib.base.R;
import com.lib.base.bean.BtnBean;
import com.lib.base.databinding.ActivityDemoBinding;
import com.lib.base.mvp.DemoContract;
import com.lib.base.mvp.DemoPresenter;
import com.lib.base.ui.widget.HolderView;

/**
 * mvp示例:
 * 1、申明contract（p，v）,参照{@link DemoContract}；
 * 2、申明Presenter继承RxPresenter{@link com.lib.base.mvp.RxPresenter}，实现1中的p，参照{@link com.lib.base.mvp.DemoPresenter}；
 * 3、申明activity继承{@link BaseMvpActivity}，实现1中v，复写bindPresenter()，参照{@link MvpActivity}。
 * <p>
 * 注：如果不需要使用mvp模式直接继承BaseActivity{@link BaseActivity}即可。
 * <p>
 * Author  chenxiaowu
 * Date on 2019/11/28.
 */
@SuppressLint("NonConstantResourceId")
public class MvpActivity extends BaseMvpActivity<DemoContract.Presenter, ActivityDemoBinding> implements DemoContract.View, View.OnClickListener {
//    @ViewInject(id = R.id.tv_login, needClick = true)
//    TextView tv_login;

    @Override
    protected DemoContract.Presenter bindPresenter() {
        return new DemoPresenter(getLifecycle());
    }

    @Override
    public ActivityDemoBinding viewBinding() {
        return ActivityDemoBinding.inflate(getLayoutInflater());
    }

    @Override
    public void inits() {
        setTitleStr("mvp activity");
        setRightClickViews((position, view) -> {
                    if (position == 0) {
                        showLoadingView();
                    } else if (position == 1) {
                        showNoDataView();
                    } else if (position == 2) {
                        showErrorView();
                    } else {
                        hideView();
                    }
                }, true,
                new BtnBean("加载\u3000", R.drawable.ic_pop1),
                new BtnBean("无数据", R.drawable.ic_pop1),
                new BtnBean("错误\u3000", R.drawable.ic_pop1),
                new BtnBean("正常\u3000", R.drawable.ic_pop1));
    }

    @Override
    public void initView() {
//        mViewBinding.refreshLayout.getRoot().setFresh(true);//允许刷新
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        mPresenter.loadData1();
        mPresenter.loadData2();
        mPresenter.loadData3();
    }

    @Override
    public HolderView getHolderView() {
        return mViewBinding.refreshLayout.getRoot();
    }

    @Override
    public void showData1() {

    }

    @Override
    public void showData2() {

    }

    @Override
    public void showData3() {

    }

    @Override
    public void loadError() {

    }

    @Override
    public void error() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onClick(View v) {

    }
}
