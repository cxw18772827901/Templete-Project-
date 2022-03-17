package com.templete.project.ui.activity;

import android.content.Intent;

import com.lib.base.ui.activity.AppTheme;
import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.util.LifecycleImp;
import com.templete.project.R;
import com.templete.project.databinding.XTabActivityBinding;
import com.templete.project.ui.fragment.EmptyFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui
 * @author      xwchen
 * Date         2021/12/29.
 */

public class XTabActivity extends BaseActivity<XTabActivityBinding> {
    String[] arr = {"条目一", "条目二", "条目三", "条目四", "条目五", "条目六", "条目七", "条目八"};
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected XTabActivityBinding viewBinding() {
        return XTabActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public int getActivityTheme() {
        return AppTheme.THEME_NONE;
    }

    @Override
    public void initS() {
        setImmersionBar(R.color.common_accent_color);
        getLifecycle().addObserver(new LifecycleImp());
    }

    @Override
    public void initView() {
        for (int i = 0; i < arr.length; i++) {
            fragments.add(new EmptyFragment());
        }
        //fragments.add(HeroDetailFour.get(dataBean));
        mViewBinding.viewPager.setOffscreenPageLimit(12);
        mViewBinding.viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        mViewBinding.xTablayout.setupWithViewPager(mViewBinding.viewPager);
        mViewBinding.viewPager.addOnPageChangeListener(changeListener);
//        fragments.get(0).getData();
    }

    private final ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
//            fragments.get(position).getData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    class PageAdapter extends FragmentPagerAdapter {
        public PageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arr[position];
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

    }
}
