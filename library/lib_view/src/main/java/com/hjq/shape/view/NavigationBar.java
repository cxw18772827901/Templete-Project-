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

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * ProjectName  xuanshangmao
 * PackageName  com.shapojie.five.view
 * Author       Administrator
 * Date         2022/2/11.
 */

public class NavigationBar extends FrameLayout {

    private TabLayout tabLayout;

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

    @SuppressLint("ClickableViewAccessibility")
    public void setData(List<String> tabs, OnSelectListener listener, int pos) {
        if (tabs == null || tabs.size() == 0) {
            return;
        }
        int dimension25 = (int) getContext().getResources().getDimension(R.dimen.x25);
        for (int i = 0; i < tabs.size(); i++) {
            String tabStr = tabs.get(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.navigation_bar_item, this, false);
            TextView tv = view.findViewById(R.id.tv);
            tv.setText(tabStr);
            //params
            FrameLayout.LayoutParams params = (LayoutParams) tv.getLayoutParams();
            params.setMarginStart(i == 0 ? dimension25 : 0);
            params.setMarginEnd(dimension25);
            tv.setLayoutParams(params);
            //tab
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setCustomView(view);
            tabLayout.addTab(tab, i, i == pos);
        }
        //select
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (listener != null) {
                    int position = tab.getPosition();
                    listener.selest(position, tabs.get(position));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
        void selest(int index, String str);
    }
}
