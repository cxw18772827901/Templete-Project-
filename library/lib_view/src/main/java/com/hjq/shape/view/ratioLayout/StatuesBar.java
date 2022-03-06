package com.hjq.shape.view.ratioLayout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;


/**
 * <p>
 * @author: Android xwchen
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2019/08/23
 * desc   : 顶部状态栏占位图,高度在测量时{@link StatuesBar#onMeasure(int, int)}直接定死为系统状态栏的高度
 */
public final class StatuesBar extends View {

    public StatuesBar(Context context) {
        this(context, null);
    }

    public StatuesBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatuesBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(getStatusBarSize(), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 状态栏高度
     *
     * @return
     */
    public int getStatusBarSize() {
        int statusBarHeight = 0;
        Resources resources = getContext().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}