package com.hjq.shape.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.hjq.shape.R;
import com.hjq.shape.view.listener.BarContent;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 自定义NavigationBar,可以选择关联ViewPager
 * ProjectName  xuanshangmao
 * PackageName  com.shapojie.five.view
 *@author      xwchen
 * Date         2022/2/11.
 */

public class NavigationBar extends FrameLayout {

    private final TabLayout tabLayout;
    private ViewPager viewPager;
    private List<? extends BarContent> tabs;
    private OnSelectListener listener;

    public NavigationBar(@NonNull Context context) {
        this(context, null);
    }

    public NavigationBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.navigation_bar, this, true);
        tabLayout = view.findViewById(R.id.tablayout);
    }

    /**
     * 不关联viewpager
     *
     * @param tabs
     * @param listener
     * @param pos
     */
    @SuppressLint("ClickableViewAccessibility")
    public void setData(List<? extends BarContent> tabs, int pos, OnSelectListener listener) {
        if (tabs == null || tabs.size() == 0) {
            return;
        }
        this.tabs = tabs;
        this.listener = listener;
        //int dimension25 = (int) getContext().getResources().getDimension(R.dimen.x25);
        for (int i = 0; i < tabs.size(); i++) {
            String tabStr = tabs.get(i).getStr();
            TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.navigation_bar_item, this, false);
            //TextView tv = view.findViewById(R.id.tv);
            tv.setText(tabStr);
            //params
            /*FrameLayout.LayoutParams params = (LayoutParams) tv.getLayoutParams();
            params.setMarginStart(i == 0 ? dimension25 : 0);
            params.setMarginEnd(dimension25);
            tv.setLayoutParams(params);*/
            //tab
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setCustomView(tv);
            tabLayout.addTab(tab, i, i == pos);
        }
        //select
        tabLayout.addOnTabSelectedListener(tabListener);
    }

    public int getIndex() {
        return tabLayout.getSelectedTabPosition();
    }

    public void selectTab(int pos) {
        tabLayout.getTabAt(pos).select();
    }

    public void scrollTos(int x, int index) {
        tabLayout.scrollTo(x, 0);
        TabLayout.Tab tabAt = tabLayout.getTabAt(index);
        boolean selected = tabAt.isSelected();
        if (!selected) {
            tabAt.select();
        }
    }

    public void stopScroll() {
        tabLayout.fling(0);
    }

    public void smoothScrollTos(int x, int index) {
        tabLayout.smoothScrollTo(x, 0);
        TabLayout.Tab tabAt = tabLayout.getTabAt(index);
        boolean selected = tabAt.isSelected();
        if (!selected) {
            tabAt.select();
        }
    }

    public interface OnSelectListener {
        void selest(int index, BarContent content);
    }

    public static class Data implements BarContent {
        String content;

        public Data(String content) {
            this.content = content;
        }

        @Override
        public String getStr() {
            return content;
        }
    }

    /**
     * 关联viewpager,注意在viewPager调用setAdapter后调用
     *
     * @param viewPager
     * @param tabs
     * @param listener
     * @param pos
     */
    public void setData(ViewPager viewPager, List<? extends BarContent> tabs, int pos, OnSelectListener listener) {
        if (tabs == null || tabs.size() == 0) {
            return;
        }
        this.viewPager = viewPager;
        this.tabs = tabs;
        this.listener = listener;
        //int dimension25 = (int) getContext().getResources().getDimension(R.dimen.x25);
        for (int i = 0; i < tabs.size(); i++) {
            String tabStr = tabs.get(i).getStr();
            TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.navigation_bar_item, this, false);
            //TextView tv = view.findViewById(R.id.tv);
            tv.setText(tabStr);
            //params
            /*FrameLayout.LayoutParams params = (LayoutParams) tv.getLayoutParams();
            params.setMarginStart(i == 0 ? dimension25 : 0);
            params.setMarginEnd(dimension25);
            tv.setLayoutParams(params);*/
            //tab
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setCustomView(tv);
            tabLayout.addTab(tab, i, i == pos);
        }
        tabLayout.addOnTabSelectedListener(tabListener);
        viewPager.addOnPageChangeListener(pagerLitener);
        selest(0);
    }

    private void selest(int index) {
        if (listener != null && tabs != null) {
            listener.selest(index, tabs.get(index));
        }
    }

    private final TabLayout.OnTabSelectedListener tabListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(@NonNull TabLayout.Tab tab) {
            if (viewPager != null) {
                viewPager.removeOnPageChangeListener(pagerLitener);
                viewPager.setCurrentItem(tab.getPosition(), true);
                viewPager.addOnPageChangeListener(pagerLitener);
            }

            selest(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    private final ViewPager.OnPageChangeListener pagerLitener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tabLayout.removeOnTabSelectedListener(tabListener);
            tabLayout.getTabAt(position).select();
            tabLayout.addOnTabSelectedListener(tabListener);

            selest(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };
}
