package com.hjq.shape.view.recyclerList;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 使recyclerview某个Item滚动到中间位置的LinearLayoutManager:
 * recyclerView.smoothScrollToPosition(pos);
 * <p>
 * PackageName  com.datouma.newproject.view.widget
 * ProjectName  xscat-android
 *@author      xwchen
 * Date         6/20/21.
 */

public class CenterLayoutManager extends LinearLayoutManager {
    public CenterLayoutManager(Context context) {
        super(context);
    }

    public CenterLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CenterLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        RecyclerView.SmoothScroller smoothScroller = new CenterSmoothScroller(recyclerView.getContext());
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

    private static class CenterSmoothScroller extends LinearSmoothScroller {

        CenterSmoothScroller(Context context) {
            super(context);
        }

        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
        }

//        /**
//         * 根据距离计算滚动时间，提升体验效果，不复写此方法会出现闪动的情况
//         *
//         * @param dx
//         * @return
//         */
//        @Override
//        protected int calculateTimeForScrolling(int dx) {
//            return super.calculateTimeForScrolling(dx) + 40;
//        }

        /**
         * 滚动速度
         *
         * @param displayMetrics
         * @return
         */
        @Override
        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 120f / displayMetrics.densityDpi;
        }
    }
}