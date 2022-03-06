package com.templete.project.ui.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.templete.project.R;

import androidx.annotation.Nullable;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui.draw
 *@author      xwchen
 * Date         2022/2/25.
 */

public class Draw2 extends View {
    Paint mPaint = new Paint();

    public Draw2(Context context) {
        super(context);
    }

    public Draw2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Draw2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        initPaint();
        canvas.drawColor(getContext().getResources().getColor(R.color.cl_eee));
        canvas.drawPoint(15, 15, mPaint);
        canvas.drawPoints(new float[]{
                50, 50,
                100, 50,
                150, 50,
                200, 50
        }, mPaint);
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(30f);
        mPaint.setAntiAlias(true);
    }
}
