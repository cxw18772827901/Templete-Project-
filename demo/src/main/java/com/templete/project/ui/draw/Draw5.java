package com.templete.project.ui.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
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

public class Draw5 extends View {
    Paint mPaint = new Paint();

    public Draw5(Context context) {
        super(context);
        initPaint();
    }

    public Draw5(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Draw5(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));

        canvas.drawColor(getContext().getResources().getColor(R.color.cl_eee));
        //圆椭圆
        RectF rect2 = new RectF(95, 35, 195, 85);
        canvas.drawOval(rect2, mPaint);

        canvas.drawOval(255, 35, 505, 155, mPaint);
    }

}
