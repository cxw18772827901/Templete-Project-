package com.templete.project.ui.activity;

import com.hjq.shape.view.xtabLayout.TabItem;
import com.hjq.shape.view.xtabLayout.XTabLayout;
import com.lib.base.listener.TabListener;
import com.lib.base.listener.ViewPagerLitener;
import com.lib.base.ui.activity.AppTheme;
import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.util.OUtil;
import com.templete.project.R;
import com.templete.project.databinding.CustomTabActivityBinding;
import com.templete.project.ui.fragment.EmptyFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui
 *@author      xwchen
 * Date         2021/12/29.
 */

public class CustomXTabActivity extends BaseActivity<CustomTabActivityBinding> {
    public static final String TAG = "XTabLayout";
    String[] arr = {"条目一", "条目二", "条目三", "条目四", "条目五", "条目六", "条目七", "条目八"};
    List<EmptyFragment> fragments = new ArrayList<>();

    @Override
    protected CustomTabActivityBinding viewBinding() {
        return CustomTabActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public int getActivityTheme() {
        return AppTheme.THEME_NONE;
    }

    @Override
    public void inits() {
        setImmersionBar(R.color.common_accent_color);
    }

    @Override
    public void initView() {
        for (int i = 0; i < arr.length; i++) {
            fragments.add(new EmptyFragment());
            TabItem item = TabItem.createItem(this, "条目" + (i + 1), false);
            mViewBinding.xTablayout.addView(item);
        }
        mViewBinding.viewPager.setOffscreenPageLimit(arr.length);
        mViewBinding.viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        mViewBinding.xTablayout.setOnTabSelectedListener(tabListener);
        mViewBinding.viewPager.addOnPageChangeListener(pagerLitener);
        getData(0);
    }

    private void getData(int i) {
        fragments.get(i).getData(i);
    }

    private final TabListener tabListener = new TabListener() {
        @Override
        public void select(@NonNull XTabLayout.Tab tab) {
            mViewBinding.viewPager.removeOnPageChangeListener(pagerLitener);
            mViewBinding.viewPager.setCurrentItem(tab.getPosition(), true);
            mViewBinding.viewPager.addOnPageChangeListener(pagerLitener);

            getData(tab.getPosition());
        }
    };

    private final ViewPagerLitener pagerLitener = new ViewPagerLitener() {
        @Override
        public void select(int position) {
            mViewBinding.xTablayout.removeOnTabSelectedListener();
            OUtil.nonNull(mViewBinding.xTablayout.getTabAt(position)).select();
            mViewBinding.xTablayout.setOnTabSelectedListener(tabListener);

            getData(position);
        }
    };


    class PageAdapter extends FragmentPagerAdapter {
        public PageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        toast("我是自定义toast");
    }
}
