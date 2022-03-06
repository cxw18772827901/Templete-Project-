package com.hjq.shape.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.hjq.shape.R;

import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 滑动可整体偏移的RecyclerView:
 * 1.trans偏移量,transPercent便偏移百分比,默认屏幕宽度20%;
 * 2.item宽度百分比为1/(1+transPercent),偏移item宽度百分比为transPercent/(1+transPercent).
 * <p>
 * PackageName  com.lib.base.ui.widget
 * ProjectName  TempleteProject-java
 * Date         2022/1/22.
 *
 * @author xwchen
 */

public class TransRecyclerView extends RecyclerView {
    //是否已向左偏移
    private boolean hasTransLeft;
    //偏移百分比
    private float transPercent;
    //偏移量
    private float trans;
    //触摸事件
    private float pointX, pointY;
    private float x, y;
    private boolean touchDown;
    private boolean isScroll;
    //是否在执行动画
    private boolean anim;
    //可以禁止滚动的layoutManager
    private StopScrollManager layoutManager;

    public TransRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public TransRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TransRecyclerView);
        transPercent = array.getFloat(R.styleable.TransRecyclerView_transPercent, (float) 0.2);
        array.recycle();

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    isScroll = true;
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    isScroll = false;
                }
            }
        });
    }

    /**
     * 修改RecyclerView宽度为屏幕宽度的(1+transPercent)倍
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        trans = widthSpecSize * transPercent;
        widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) (widthSpecSize + trans), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 根据触摸事件开始偏移动画,若触摸事件为MotionEvent.ACTION_UP则根据动画状态来判断是否恢复可滚动状态
     * 需要注意的是平移动画时不要使用event.getX/event.getRawY,一定要使用event.getRawX/event.getRawY
     *
     * @param event
     * @return
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown = true;
                isScroll = false;
                pointX = event.getRawX();
                pointY = event.getRawY();
                reset();
                break;
            case MotionEvent.ACTION_MOVE:
                touchDown = true;
                if (isScroll) {
                    reset();
                    return super.onTouchEvent(event);
                }
                float pointX = event.getRawX();
                float pointY = event.getRawY();
                x += pointX - this.pointX;
                y += pointY - this.pointY;
                this.pointX = pointX;
                this.pointY = pointY;
                Log.d("OnTouch", "x=" + x + ",y=" + y);
                //无效滑动
                if ((hasTransLeft && x < 0) || (!hasTransLeft && x > 0)) {
                    reset();
                }
                if (x < -50 && y != 0 && Math.abs(x / y) > 2) {
                    //向左滑动
                    if (!hasTransLeft && !anim) {
                        trans();
                    }
                    reset();
                } else if (x > 50 && y != 0 && Math.abs(x / y) > 2) {
                    //向右滑动
                    if (hasTransLeft && !anim) {
                        trans();
                    }
                    reset();
                }
                break;
            case MotionEvent.ACTION_UP:
                touchDown = false;
                if (!anim) {
                    layoutManager.setCanScrollVertically(true);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void reset() {
        x = 0;
        y = 0;
    }

    /**
     * 开始偏移
     */
    public void trans() {
        if (hasTransLeft) {
            transToRight();
        } else {
            transToLeft();
        }
        hasTransLeft = !hasTransLeft;
    }

    /**
     * 向右偏移动画
     */
    private void transToRight() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationX", -trans, 0).setDuration(120);
        setAnimListener(anim);
        anim.start();
    }

    /**
     * 想左偏移动画
     */
    private void transToLeft() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationX", 0, -trans).setDuration(120);
        setAnimListener(anim);
        anim.start();
    }

    /**
     * 动画开始执行后禁止滚动,动画结束或者取消后若触摸事件为MotionEvent.ACTION_UP则恢复可滚动状态
     *
     * @param animW
     */
    private void setAnimListener(@NonNull ObjectAnimator animW) {
        animW.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                anim = true;
                stopFling();
                layoutManager.setCanScrollVertically(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                anim = false;
                if (!touchDown) {
                    layoutManager.setCanScrollVertically(true);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                anim = false;
                if (!touchDown) {
                    layoutManager.setCanScrollVertically(true);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //anim = true;
            }
        });
    }

    /**
     * RecyclerView调用fling(0,0)停止惯性滚动无效,暂时使用反射停止滚动方法代替,需要再研究一下.
     */
    public void stopFling() {
        try {
            Method field = RecyclerView.class.getDeclaredMethod("cancelScroll");
            field.setAccessible(true);
            field.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 偏移量(屏幕宽度的百分比)
     *
     * @param transPercent
     */
    public void setTransPercent(float transPercent) {
        this.transPercent = transPercent;
        requestLayout();
    }

    /**
     * 设置一个可以禁止滚动的LinearLayoutManager
     */
    public void setLayoutManager() {
        layoutManager = new StopScrollManager(getContext());
        super.setLayoutManager(layoutManager);
    }

    @Override
    public void setLayoutManager(@Nullable LayoutManager layout) {
        throw new RuntimeException("do you need a StopScrollManager?");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearAnimation();
    }
}
