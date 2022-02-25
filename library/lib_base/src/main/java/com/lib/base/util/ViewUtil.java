package com.lib.base.util;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.hjq.shape.view.nestedScrollView.NestedScrollWebView;

import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 禁用RecyclerView的惯性滚动
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.ui
 * Author       Administrator
 * Date         2022/1/14.
 */

public class ViewUtil {

    @SuppressLint("ClickableViewAccessibility")
    public static void stopFlingWhenTouchUp(@NonNull RecyclerView recyclerView) {
        recyclerView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
//                recyclerView.fling(0, 0);
                stopFling(recyclerView);
            }
            return false;
        });
    }

    public static void stopFling(RecyclerView recyclerView) {
        try {
            Method field = recyclerView.getClass().getDeclaredMethod("cancelScroll");
            field.setAccessible(true);
            field.invoke(recyclerView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addView(ViewGroup container, View child) {
        //暂停惯性滚动
        if (child instanceof ListView) {
            ((ListView) child).fling(0);
        } else if (child instanceof RecyclerView) {
            //((RecyclerView) child).fling(0, 0);
            stopFling((RecyclerView) child);
        } else if (child instanceof ScrollView) {
            ((ScrollView) child).fling(0);
        } else if (child instanceof NestedScrollWebView) {
            //((NestedScrollWebView) child).flingScroll(0, 0);
        } else if (child instanceof HorizontalScrollView) {
            ((HorizontalScrollView) child).fling(0);
        }
        //从父布局移除
        ViewGroup parent = (ViewGroup) child.getParent();
        if (parent != null) {
            if (!parent.equals(container)) {
                parent.removeAllViews();
            } else {
                return;
            }
        }
        container.addView(child, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        container.requestLayout();
    }
}
