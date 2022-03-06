package com.templete.project.ui.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.templete.project.R;

import androidx.annotation.Nullable;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui.draw
 * @author      xwchen
 * Date         2022/2/25.
 */

public class Draw4 extends View {
    Paint mPaint = new Paint();

    public Draw4(Context context) {
        super(context);
        initPaint();
    }

    public Draw4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Draw4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(20f);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(getContext().getResources().getColor(R.color.cl_eee));
        canvas.drawRect(15, 15, 85, 65, mPaint);
        Rect rect = new Rect(95, 15, 205, 65);
        canvas.drawRect(rect, mPaint);
        RectF rect1 = new RectF(225, 15, 305, 65);
        canvas.drawRect(rect1, mPaint);

        //圆角矩形
        RectF rect2 = new RectF(95, 85, 305, 155);
        canvas.drawRoundRect(rect2, 20, 20, mPaint);

        canvas.drawRoundRect(355, 85, 555, 155, 20, 20, mPaint);
    }

}
