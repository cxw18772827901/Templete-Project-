package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.hjq.shape.R;

import androidx.annotation.Nullable;

/**
 * 三角形和倒三角形
 * ProjectName  TempleteProject-java
 * PackageName  com.hjq.shape.view
 * Author       Administrator
 * Date         2022/2/25.
 *
 * @author chenxiaowu
 */

public class TriangleView extends View {
    private final Paint mPaint = new Paint();
    private int width;
    private int height;
    private boolean isTop;
    private int colorRes;

    public TriangleView(Context context) {
        this(context, null);
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TriangleView);
        isTop = ta.getBoolean(R.styleable.TriangleView_sj_top, true);
        colorRes = ta.getColor(R.styleable.TriangleView_sj_color, Color.BLACK);
        ta.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = /*isInEditMode() ? 36 :*/ w;
        height = /*isInEditMode() ? 15 :*/ h;
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(colorRes);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(1f);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));

        Path path = new Path();
        if (isTop) {
            //尖叫朝上
            path.moveTo(width / 2, 0f);
            path.lineTo(width, height);
            path.lineTo(0f, height);
        } else {
            //尖叫朝下
            path.moveTo(0f, 0f);
            path.lineTo(width, 0f);
            path.lineTo(width / 2, height);
        }
        path.close();
        canvas.drawPath(path, mPaint);
    }

    public void setWidthAndHeight(int width, int height) {
        setMeasuredDimension(width, height);
        invalidate();
    }

    public void setTop(boolean top) {
        isTop = top;
        invalidate();
    }

    public void setColorRes(int colorRes) {
        this.colorRes = colorRes;
        invalidate();
    }
}
