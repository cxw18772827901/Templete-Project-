package com.lib.base.util.txt.span;

import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.CharacterStyle;

/**
 * 不要跟Bold一起用
 */
public class FakeBoldSpan extends CharacterStyle {
    float width = 0.2f;

    public FakeBoldSpan() {
    }

    public FakeBoldSpan(float width) {
        this.width = width;
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        //tp.setFakeBoldText(true);
        //tp.setColor(App.instance().getResources().getColor(R.color.black_2A2A2A));
        tp.setStyle(Paint.Style.FILL_AND_STROKE);
        tp.setStrokeWidth(width);
    }
}