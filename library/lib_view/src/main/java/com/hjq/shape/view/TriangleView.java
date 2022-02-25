package com.hjq.shape.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.hjq.shape.view
 * Author       Administrator
 * Date         2022/2/25.
 */

public class TriangleView extends View {
    private Paint mPaint = new Paint();
    private int width;
    private int height;

    public TriangleView(Context context) {
        this(context, null);
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = isInEditMode() ? 150 : w;
        height = isInEditMode() ? 100 : h;
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(1f);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));

        //画三角形
        Path path = new Path();
        path.moveTo(width / 2, 0f);
        path.lineTo(width, height);
        path.lineTo(0f, height);
        path.close();
        canvas.drawPath(path, mPaint);
        //画顶部圆角
        canvas.drawRoundRect(width / 2-5, 85, 555, 155, 20, 20, mPaint);
    }
}
