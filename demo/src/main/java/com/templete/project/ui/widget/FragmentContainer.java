package com.templete.project.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.lib.base.ui.fragment.BaseFragment;
import com.lib.base.util.DebugUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

/**
 * 自定义无按钮的Fragment导航容器,按照添加数据顺序索引切换Fragment
 * ProjectName  xuanshangmao
 * PackageName  com.shapojie.five.view
 *@author      xwchen
 * Date         2022/2/11.
 */

public class FragmentContainer extends FrameLayout implements Container {
    public List<BaseFragment> fragmentList = new ArrayList<>();
    private BaseFragment currentFragment;
    private FragmentManager manager;
    private int lastIndex = -1;
    private int currentIndex;

    public FragmentContainer(@NonNull Context context) {
        super(context);
    }

    public FragmentContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentContainer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 添加数据,反射生成fragment对象
     *
     * @param manager
     * @param clazz
     */
    @Override
    public void setFragments(@NonNull FragmentManager manager, @NonNull Class... clazz) {
        if (clazz.length == 0) {
            return;
        }
        this.manager = manager;
        fragmentList.clear();
        for (int i = 0; i < clazz.length; i++) {
            Class aClass = clazz[i];
            BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(aClass.getSimpleName());
            if (fragment != null) {
                //记录之前选中的position
                if (!fragment.isHidden()) {
                    lastIndex = i;
                }
            } else {
                //直接new 一个Fragment
                Object o = null;
                try {
                    o = aClass.newInstance();
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
                if (!(o instanceof BaseFragment)) {
                    throw new RuntimeException("clazz must be BaseFragment!");
                } else {
                    fragment = (BaseFragment) o;
                }
            }
            fragmentList.add(fragment);
        }
        //展示第一个fragment
        showFragment();
    }

    /**
     * 展示默认fragment
     */
    private void showFragment() {
        if (lastIndex != -1) {
            currentFragment = fragmentList.get(lastIndex);
            currentIndex = lastIndex;
        } else {
            BaseFragment fragment = fragmentList.get(0);
            manager
                    .beginTransaction()
                    .add(getId(), fragment, fragment.getClass().getSimpleName())
                    .commit();
            currentFragment = fragment;
            currentIndex = 0;
        }
    }

    /**
     * 切换fragment
     *
     * @param index
     */
    @Override
    public BaseFragment showFragment(int index) {
        BaseFragment fragment = fragmentList.get(index);
        if (currentFragment.equals(fragment)) {//是当前的tab,再次点击的话无效
            return fragment;
        }
        if (fragment.isAdded()) {
            DebugUtil.logD("sss", "isAdded");
            manager
                    .beginTransaction()
                    .hide(currentFragment)
                    .show(fragment)
                    .commit();
        } else {
            DebugUtil.logD("sss", "notAdded");
            manager
                    .beginTransaction()
                    .hide(currentFragment)
                    .add(getId(), fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
        //状态
        currentFragment = fragment;
        currentIndex = index;
        return fragment;
    }

    //获取当前展示的fragment
    @Override
    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    @Override
    public BaseFragment getFragmentByIndex(int index) {
        return fragmentList.get(index);
    }
}
