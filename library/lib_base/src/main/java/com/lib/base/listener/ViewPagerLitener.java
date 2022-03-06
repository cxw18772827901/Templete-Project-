package com.lib.base.listener;

import androidx.viewpager.widget.ViewPager;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.listener
 * @author      xwchen
 * Date         2022/1/6.
 */

public class ViewPagerLitener implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        select(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void select(int position) {

    }
}
