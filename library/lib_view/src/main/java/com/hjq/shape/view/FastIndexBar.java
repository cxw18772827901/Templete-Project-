package com.hjq.shape.view;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hjq.shape.R;

import androidx.annotation.RequiresApi;

import static android.os.VibrationEffect.DEFAULT_AMPLITUDE;


/**
 * 索引条控件,默认是"#ABCDEFGHIJKLMNOPQRSTUVWXYZ",可以调用updateLettersData方法来跟新索引表内容
 * 注意的是要等视图显示之后才能调用这个方法
 * by Dave
 */
public class FastIndexBar extends View {
    public static final String TAG = "FastIndexBar";
    public static final String ALL_LETTERS = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String letters;
    private char[] CHARS_ARR;
    private final Paint normalPaint;
    private final Paint pressPaint;
    private final Paint strokePaint;
    private final Rect textRect;
    private float CELL_WIDTH;
    private final float CELL_HEIGHT;
    private int TOTAL_HEIGHT;
    private float bgCorner;
    private final float rectTrans;
    private final boolean touchChangeColor;
    private final Vibrator vibrator;

    public FastIndexBar(Context context) {
        this(context, null);
    }

    public FastIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FastIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FastIndexBar);
        int tvColorNormal = a.getInt(R.styleable.FastIndexBar_tvColorNormal, Color.GRAY);
        int tvColorPress = a.getInt(R.styleable.FastIndexBar_tvColorPress, Color.GRAY);
        float tvSize = a.getDimension(R.styleable.FastIndexBar_tvSize, dip2px(context, 14));
        int bgStrokeColor = a.getInt(R.styleable.FastIndexBar_bgStrokeColor, Color.GRAY);
        float bgStrokeWidth = a.getDimension(R.styleable.FastIndexBar_bgStrokeWidth, dip2px(context, 0.6));
        touchChangeColor = a.getBoolean(R.styleable.FastIndexBar_touchChangeColor, false);
        a.recycle();

        rectTrans = bgStrokeWidth / 2;
        CELL_HEIGHT = tvSize + dip2px(context, 2);
        textRect = new Rect();
        normalPaint = getTextPaint(tvColorNormal, tvSize);//默認顏色
        pressPaint = getTextPaint(tvColorPress, tvSize);//選中顏色
        strokePaint = getStrokePaint(bgStrokeColor, bgStrokeWidth);
        letters = isInEditMode() ? ALL_LETTERS : null;
        vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
    }

    private Paint getStrokePaint(int bgStrokeColor, float bgStrokeWidth) {
        Paint result = new Paint(Paint.ANTI_ALIAS_FLAG);
        result.setColor(bgStrokeColor);
        result.setStrokeWidth(bgStrokeWidth);//设置线宽
        result.setStyle(Paint.Style.STROKE);//设置样式：FILL表示颜色填充整个；STROKE表示空心
        return result;
    }

    private Paint getTextPaint(int color, float tvSize) {
        Paint result = new Paint(Paint.ANTI_ALIAS_FLAG);
        result.setColor(color);
        result.setTextSize(tvSize);
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        initParams();
        int specW = MeasureSpec.makeMeasureSpec((int) CELL_WIDTH, MeasureSpec.EXACTLY);
        float h = TOTAL_HEIGHT + CELL_HEIGHT * 2;
        int specH = MeasureSpec.makeMeasureSpec((int) h, MeasureSpec.EXACTLY);
        super.onMeasure(specW, specH);
        //setMeasuredDimension(widthSize, MeasureSpec.getSize(heightMeasureSpec));
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float textX;
        float textY;
        Paint textPaint;
        for (int i = 0; i < CHARS_ARR.length; i++) {
            textPaint = getPaint(i);
            textPaint.getTextBounds(CHARS_ARR, i, 1, textRect);
            //x的值是控件的宽度的一半减去字体边界的一半
            textX = CELL_WIDTH / 2 - (float) textRect.width() / 2;
            //y值是CELL_HEIGHT的n倍加上CELL_HEIGHT一半和字体边界的一半,再加上距离顶部的间隔
            textY = CELL_HEIGHT / 2 + (float) textRect.height() / 2 + (i + 1) * CELL_HEIGHT /*+ TOP_MARGIN*/;
            canvas.drawText(CHARS_ARR, i, 1, textX, textY, textPaint);
        }
        //画边框
        //注意canvas.drawRoundRect画圆角矩形时候，四个圆角可以画出指定宽度，四条边线只会画出一半的宽度,故RectF需要做线宽一半的偏移量
        if (strokePaint == null) return;
        canvas.drawRoundRect(new RectF(rectTrans, rectTrans, CELL_WIDTH - rectTrans,
                        TOTAL_HEIGHT + CELL_HEIGHT * 2 - rectTrans),
                bgCorner, bgCorner / 1.5f, strokePaint);
    }

    private Paint getPaint(int i) {
        Paint textPaint;
        if (touchChangeColor) {
            if (i == touchIndex) {//按住的是gray色
                textPaint = pressPaint;
            } else {//--------------默認的是53
                textPaint = normalPaint;
            }
        } else {
            textPaint = normalPaint;
        }
        return textPaint;
    }

    //得到控件的宽度和每一个cell的高度
    private void initParams() {
        CELL_WIDTH = isInEditMode() ? getResources().getDimension(R.dimen.x50) :
                getLayoutParams().width;
        bgCorner = CELL_WIDTH / 2;
        CHARS_ARR = letters.toCharArray();
        TOTAL_HEIGHT = (int) (CHARS_ARR.length * CELL_HEIGHT);
    }

    // 初始值应该是<0
    private int touchIndex = -1;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int touchY = (int) event.getY();
                //针对触摸区域进行处理
                updateTouchIndex((int) ((touchY - CELL_HEIGHT) / CELL_HEIGHT));
                break;
            case MotionEvent.ACTION_UP:
                updateTouchIndex(-1);
                break;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updateTouchIndex(int index) {
        if (touchIndex == index) {
            return;
        }
        Log.d("index", "index=" + index);
        touchIndex = index;
        //如果需要改变触摸字母颜色，重绘即可
        if (touchChangeColor) {
            invalidate();
        }
        if (listener != null) {
            if (touchIndex >= 0 && touchIndex < CHARS_ARR.length) {
                if (index != -1) {
                    vibrator();
                }
                listener.onCharSelected(touchIndex, CHARS_ARR[touchIndex]);
            }
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void vibrator() {
        try {
            vibrator.cancel();
            vibrator.vibrate(VibrationEffect.createOneShot(15, DEFAULT_AMPLITUDE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int dip2px(Context context, double dip) {
        return (int) (dip * (context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    //对外改变数据借口
    public void setLetters(String letters) {
        if (letters == null || letters.length() == 0) {
            return;
        }
        this.letters = letters;
        requestLayout();
        invalidate();
    }

    public interface OnCharSelectedListener {
        void onCharSelected(int touchIndex, char c);
    }

    private OnCharSelectedListener listener;

    public void setOnCharSelectedListener(OnCharSelectedListener listener) {
        this.listener = listener;
    }

}