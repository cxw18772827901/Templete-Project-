package com.hjq.shape.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * 可以禁用滚动的LinearLayoutManager
 * PackageName  com.hjq.shape.view.recyclerList
 * ProjectName  TempleteProject-java
 * Date         2022/1/22.
 *
 * @author xwchen
 */

public class StopScrollManager extends LinearLayoutManager {
    //默认可以滚动
    private boolean canScrollVertically = true;

    public StopScrollManager(Context context) {
        super(context);
    }

    public StopScrollManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public StopScrollManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean canScrollVertically() {
        return canScrollVertically && super.canScrollVertically();
    }

    public void setCanScrollVertically(boolean canScrollVertically) {
        this.canScrollVertically = canScrollVertically;
    }
}
