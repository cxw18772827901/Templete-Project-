package com.templete.project.ui.widget;

import com.lib.base.ui.fragment.BaseFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui.widget
 * @author      xwchen
 * Date         2022/2/11.
 */

public interface Container {
    /**
     * 1.添加数据(BaseFragment的基类),添加顺序作为切换的索引;
     * 2.反射生成fragment对象.
     *
     * @param manager
     * @param clazz
     */
    void setFragments(@NonNull FragmentManager manager, @NonNull Class... clazz);

    /**
     * 根据添加时索引切换对应的fragment
     *
     * @param position
     * @return
     */
    BaseFragment showFragment(int position);

    /**
     * 获取当前展示的fragment
     *
     * @return
     */
    BaseFragment getCurrentFragment();

    /**
     * 获取当前展示的fragment
     *
     * @param index
     * @return
     */
    BaseFragment getFragmentByIndex(int index);
}
