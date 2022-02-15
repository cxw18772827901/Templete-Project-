package com.hjq.shape.view.recyclerList;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.hjq.shape.R;

/**
 * 仿ios弹性可以过拉的RecyclerView
 * PackageName  com.common.library.widget.listView
 * ProjectName  Project
 * Author       chenxiaowu
 * Date         2018/4/12.
 */
public class OverScrollListView extends ListView {

    private int overScrollY;

    public OverScrollListView(Context context) {
        this(context, null);
    }

    public OverScrollListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        overScrollValue();
    }

    private void overScrollValue() {
        overScrollY = (int) getContext().getResources().getDimension(R.dimen.x150);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, overScrollY, isTouchEvent);
    }
}
