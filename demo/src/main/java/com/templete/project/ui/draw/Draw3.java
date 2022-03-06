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
 * @author      xwchen
 * Date         2022/2/25.
 */

public class Draw3 extends View {
    Paint mPaint = new Paint();

    public Draw3(Context context) {
        super(context);
        initPaint();
    }

    public Draw3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Draw3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        canvas.drawLine(15, 15, 15, 55, mPaint);
        canvas.drawLine(
                50, 50,
                700, 50,
                mPaint);
    }

}
