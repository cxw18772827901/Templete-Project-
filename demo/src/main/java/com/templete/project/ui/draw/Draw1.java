package com.templete.project.ui.draw;

import android.content.Context;
import android.graphics.Canvas;
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

public class Draw1 extends View {
    public Draw1(Context context) {
        super(context);
    }

    public Draw1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Draw1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(getContext().getResources().getColor(R.color.common_confirm_text_color));
    }
}
