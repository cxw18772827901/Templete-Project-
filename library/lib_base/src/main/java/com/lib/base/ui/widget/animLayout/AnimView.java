package com.lib.base.ui.widget.animLayout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lib.base.R;
import com.lib.base.databinding.DynamicLayoutBinding;

/**
 * 动态添加/删除View(添加View时候以动画方式调整高度,使用场景很多)
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.ui.widget.dynamicLayout
 * Author       Administrator
 * Date         2021/12/31.
 */

public class AnimView extends FrameLayout {

    private static final int DURATION_ADD = 300;
    private static final int ANIM_IDEL = 0;
    private static final int ANIM_ING = 1;

    public int animType = ANIM_IDEL;
    private final DynamicLayoutBinding binding;
    private final float dimension210;

    public AnimView(Context context) {
        this(context, null);
    }

    public AnimView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        binding = DynamicLayoutBinding.inflate(LayoutInflater.from(context), this, true);
        dimension210 = context.getResources().getDimension(R.dimen.x210);
        init(context);
    }

    private void init(Context context) {
        binding.tvAddItem.setOnClickListener(v -> addView(context));
    }

    @SuppressLint("InflateParams")
    private void addView(Context context) {
        if (animType == ANIM_ING) {
            return;
        }
        animType = ANIM_ING;
        View viewChild = LayoutInflater.from(context).inflate(R.layout.dynamic_item, null);
        int width = getMeasuredWidth(viewChild);
        int height = getMeasuredHeight(viewChild);
        viewChild.setOnClickListener(v -> {
            if (animType == ANIM_ING) {
                return;
            }
            animType = ANIM_ING;
            binding.llContainer.removeViewAt(binding.llContainer.getChildCount() - 1);
            anim(binding.llContainer, viewChild, width, height, false);
        });
        anim(binding.llContainer, viewChild, width, height, true);
    }

    public int getMeasuredWidth(View viewChild) {
        viewChild.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return viewChild.getMeasuredWidth();
    }

    public int getMeasuredHeight(View viewChild) {
        viewChild.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return viewChild.getMeasuredHeight();
    }

    @SuppressLint("ObjectAnimatorBinding")
    private void anim(LinearLayout llContainer, View viewChild, int scaleWidth, int scaleHeight, boolean isAdd) {
        ObjectAnimator animW = ObjectAnimator.ofInt(llContainer, "scaleX", llContainer.getWidth(), llContainer.getWidth() + (isAdd ? scaleWidth : -scaleWidth)).setDuration(DURATION_ADD);
        ObjectAnimator animH = ObjectAnimator.ofInt(llContainer, "scaleY", llContainer.getHeight(), llContainer.getHeight() + (isAdd ? scaleHeight : -scaleHeight)).setDuration(DURATION_ADD);
        animH.addUpdateListener(valueAnimator -> {
            int animatedValue = (int) valueAnimator.getAnimatedValue();
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) llContainer.getLayoutParams();
            lp.height = animatedValue;
            llContainer.setLayoutParams(lp);
        });
        animW.addUpdateListener(valueAnimator -> {
            int animatedValue = (int) valueAnimator.getAnimatedValue();
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) llContainer.getLayoutParams();
            lp.width = animatedValue;
            llContainer.setLayoutParams(lp);
        });
        AnimatorSet set = new AnimatorSet();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (isAdd) {
                    binding.llContainer.addView(viewChild);
                    binding.getRoot().fullScroll(ScrollView.FOCUS_DOWN);
                }
                binding.llContainer.postDelayed(() -> {
                    notifyData();
                    animType = ANIM_IDEL;
                }, 200);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.playTogether(animH, animW);
        set.setDuration(DURATION_ADD);
        set.start();

//        ObjectAnimator animH = ObjectAnimator.ofFloat(binding.llContainer, "alpha", 1, 0, 1).setDuration(DURATION_ADD);
        /*if (pos == 0) {
            animH = ObjectAnimator.ofFloat(binding.tvAddItem, "rotation", 0, 180).setDuration(DURATION_ADD);
            animH.start();
            pos = 1;
        } else if (pos == 1) {
            animH.reverse();
            pos = 0;
        }*/
    }

    private int pos = 0;
    private ObjectAnimator anim = null;

    @SuppressLint("SetTextI18n")
    private void notifyData() {
        for (int i = 0; i < binding.llContainer.getChildCount(); i++) {
            View view = binding.llContainer.getChildAt(i);
            TextView tv = view.findViewById(R.id.tv);
            tv.setText("条目" + (i + 1) + "(点击删除条目)");
        }
    }
}
