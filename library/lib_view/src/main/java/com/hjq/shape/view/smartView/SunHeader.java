package com.hjq.shape.view.smartView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.shape.R;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * 经典下拉头部
 * Created by SCWANG on 2017/5/28.
 */
@SuppressWarnings("unused")
public class SunHeader extends RelativeLayout implements RefreshHeader {

    /* public static String REFRESH_HEADER_PULL_DOWN = "下拉刷新";
     public static String REFRESH_HEADER_REFRESHING = "刷新中...";
     public static String REFRESH_HEADER_LOADING = "加载中...";
     public static String REFRESH_HEADER_RELEASE = "释放刷新";
     public static String REFRESH_HEADER_FINISH = "刷新结束";
     public static String REFRESH_HEADER_FAILED = "刷新失败";*/
    public static String REFRESH_HEADER_LAST_TIME = "yyyy-MM-dd HH:mm:ss";
    protected String KEY_LAST_UPDATE_TIME = "上次更新";

    protected Date mLastTime;
    protected TextView mLastUpdateText;
    protected ImageView mArrowView;
    protected SharedPreferences mShared;
    protected RefreshKernel mRefreshKernel;
    protected SpinnerStyle mSpinnerStyle = SpinnerStyle.Translate;
    protected DateFormat mFormat = new SimpleDateFormat(REFRESH_HEADER_LAST_TIME, Locale.CHINA);
    protected int mFinishDuration = 500;
    protected int mBackgroundColor;
    protected boolean mEnableLastTime = true;
    private Animation animationFresh;
    //    private Animation animationEnd;
    private Matrix mHeaderImageMatrix;
    private float mRotationPivotX, mRotationPivotY;

    //<editor-fold desc="RelativeLayout">

    public SunHeader(Context context) {
        this(context, null);
    }

    public SunHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SunHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @SuppressLint("CustomViewStyleable")
    private void initView(Context context, AttributeSet attrs) {
//        DensityUtil density = new DensityUtil();

        LayoutInflater.from(context).inflate(R.layout.sun_header_layout, this);
        mLastUpdateText = findViewById(R.id.mLastUpdateText);
        mArrowView = findViewById(R.id.mArrowView);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ClassicsHeader);

        mFinishDuration = ta.getInt(R.styleable.ClassicsHeader_srlFinishDuration, mFinishDuration);
        mEnableLastTime = ta.getBoolean(R.styleable.ClassicsHeader_srlEnableLastTime, mEnableLastTime);
        mSpinnerStyle = SpinnerStyle.values[ta.getInt(R.styleable.ClassicsHeader_srlClassicsSpinnerStyle, mSpinnerStyle.ordinal)];

        mLastUpdateText.setVisibility(mEnableLastTime ? VISIBLE : GONE);

//        if (ta.hasValue(R.styleable.ClassicsHeader_srlDrawableArrow)) {
//            mArrowView.setImageDrawable(ta.getDrawable(R.styleable.ClassicsHeader_srlDrawableArrow));
//        } else {
//            mArrowView.setImageDrawable(getResources().getDrawable(R.mipmap.loading0));
//        }
//        mArrowView.setImageDrawable(getResources().getDrawable(R.mipmap.loading0));
        mHeaderImageMatrix = new Matrix();
        mArrowView.setImageMatrix(mHeaderImageMatrix);
        mRotationPivotX = Math.round(mArrowView.getDrawable().getIntrinsicWidth() / 2f);
        mRotationPivotY = Math.round(mArrowView.getDrawable().getIntrinsicHeight() / 2f);

       /* int tvSize = AppUtil.isTabletDevice(context) ? UIUtil.dip2px(5) : UIUtil.dip2px(4.2);
        if (ta.hasValue(R.styleable.ClassicsHeader_srlTextSizeTime)) {
            mLastUpdateText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(R.styleable.ClassicsHeader_srlTextSizeTime,*//* DensityUtil.dip2px(12)*//*tvSize));
        } else {
            mLastUpdateText.setTextSize(*//*14*//*tvSize);
        }*/

        int primaryColor = ta.getColor(R.styleable.ClassicsHeader_srlPrimaryColor, 0);
        int accentColor = ta.getColor(R.styleable.ClassicsHeader_srlAccentColor, 0);
        if (primaryColor != 0) {
            if (accentColor != 0) {
                setPrimaryColors(primaryColor, accentColor);
            } else {
                setPrimaryColors(primaryColor);
            }
        } else if (accentColor != 0) {
            setPrimaryColors(0, accentColor);
        }

        ta.recycle();

        /*try {//try 不能删除-否则会出现兼容性问题
            if (context instanceof FragmentActivity) {
                FragmentManager manager = ((FragmentActivity) context).getSupportFragmentManager();
                if (manager != null) {
                    List<Fragment> fragments = manager.getFragments();
                    if (fragments != null && fragments.size() > 0) {
                        setLastUpdateTime(new Date());
                        return;
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }*/

        KEY_LAST_UPDATE_TIME += context.getClass().getName();
        mShared = context.getSharedPreferences("ClassicsHeader", Context.MODE_PRIVATE);
        setLastUpdateTime(new Date(mShared.getLong(KEY_LAST_UPDATE_TIME, System.currentTimeMillis())));

//        animationFresh = AnimationUtils.loadAnimation(context, R.anim.sun_rotate);
//        animationFresh.setInterpolator(new LinearInterpolator());
//        animationEnd = AnimationUtils.loadAnimation(context, R.anim.sun_scale);

        //刷新动画
        RotateAnimation rotateFresh = new RotateAnimation(0, 36000, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateFresh.setInterpolator(new LinearInterpolator());
        rotateFresh.setDuration(100000);
//        rotateFresh.setRepeatMode(Animation.RESTART);
        rotateFresh.setRepeatCount(Animation.INFINITE);
        rotateFresh.setFillAfter(false);
        animationFresh = rotateFresh;
         /*//属性动画
//        animationFresh = AnimationUtils.loadAnimation(context, R.anim.anim_rotate);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(mArrowView, "rotation", 0, 359);
        rotation.setRepeatCount(ValueAnimator.INFINITE);
        rotation.setDuration(700);
        rotation.setInterpolator(new LinearInterpolator());*/
        //结束动画
        /*RotateAnimation rotateEnd = new RotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
         *//*
//        ScaleAnimation scaleEnd = new ScaleAnimation(1.0f, 0f, 1.0f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(rotateEnd);
//        set.addAnimation(scaleEnd);
        set.setFillAfter(false);
        set.setDuration(600);
*//*
        rotateEnd.setFillAfter(false);
        rotateEnd.setDuration(600);
        animationEnd = *//*set*//*rotateEnd;*/

    }
    //</editor-fold>

    //<editor-fold desc="RefreshHeader">
    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {
        mRefreshKernel = kernel;
        mRefreshKernel.requestDrawBackgroundFor(this, mBackgroundColor);
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
//        if (isDragging)
//            if (percent < 0.076) {
//                mArrowView.setImageResource(R.mipmap.loading0);
//            } else if (percent < 0.076 * 2) {
//                mArrowView.setImageResource(R.mipmap.loading1);
//            } else if (percent < 0.076 * 3) {
//                mArrowView.setImageResource(R.mipmap.loading2);
//            } else if (percent < 0.076 * 4) {
//                mArrowView.setImageResource(R.mipmap.loading3);
//            } else if (percent < 0.076 * 5) {
//                mArrowView.setImageResource(R.mipmap.loading4);
//            } else if (percent < 0.076 * 6) {
//                mArrowView.setImageResource(R.mipmap.loading5);
//            } else if (percent < 0.076 * 7) {
//                mArrowView.setImageResource(R.mipmap.loading6);
//            } else if (percent < 0.076 * 8) {
//                mArrowView.setImageResource(R.mipmap.loading7);
//            } else if (percent < 0.076 * 9) {
//                mArrowView.setImageResource(R.mipmap.loading8);
//            } else if (percent < 0.076 * 10) {
//                mArrowView.setImageResource(R.mipmap.loading9);
//            } else if (percent < 0.076 * 11) {
//                mArrowView.setImageResource(R.mipmap.loading10);
//            } else if (percent < 0.076 * 12) {
//                mArrowView.setImageResource(R.mipmap.loading11);
//            } else if (percent < 0.076 * 13) {
//                mArrowView.setImageResource(R.mipmap.loading12);
//            }
        if (isDragging && !isFreshing) {
//            mArrowView.clearAnimation();
            float angle = percent * 180f;
            mHeaderImageMatrix.setRotate(angle, mRotationPivotX, mRotationPivotY);
            mArrowView.setImageMatrix(mHeaderImageMatrix);
        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int headHeight, int extendHeight) {
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        if (success) {
            setLastUpdateTime(new Date());
        }
        return mFinishDuration;//延迟500毫秒之后再弹回
    }

    @Override
    @Deprecated
    public void setPrimaryColors(@ColorInt int... colors) {
        if (colors.length > 0) {
            if (!(getBackground() instanceof BitmapDrawable)) {
                setPrimaryColor(colors[0]);
            }
            if (colors.length > 1) {
                setAccentColor(colors[1]);
            } else {
                setAccentColor(colors[0] == 0xffffffff ? 0xff666666 : 0xffffffff);
            }
        }
    }

    @NonNull
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return mSpinnerStyle;
    }

    private boolean isFreshing = false;

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

        switch (newState) {
            case None:
                mLastUpdateText.setVisibility(mEnableLastTime ? VISIBLE : GONE);
                mArrowView.clearAnimation();
            case PullDownToRefresh:
                mArrowView.setVisibility(VISIBLE);
                break;
//            case Refreshing://此处开始刷新动画会有僵直，直接在释放时开始动画会很流畅
            case RefreshReleased:
                //需要一出matrix动画，不然刷新动画无效
                isFreshing = true;
                resetMatrixAnim();
                mArrowView.startAnimation(animationFresh);
//                startFresh();
                break;
            case RefreshFinish:
                isFreshing = false;
//                mArrowView.startAnimation(animationEnd);
                break;
            case ReleaseToRefresh:
                break;
            case Loading:
                mArrowView.setVisibility(GONE);
                mLastUpdateText.setVisibility(GONE);
                break;
        }
    }

    private void startFresh() {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(mArrowView, "rotation", 0, 359);
        rotation.setRepeatCount(ValueAnimator.INFINITE);
        rotation.setDuration(700);
        rotation.setRepeatMode(ValueAnimator.RESTART);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.start();
    }

    protected void resetMatrixAnim() {
        mArrowView.clearAnimation();
        if (null != mHeaderImageMatrix) {
            mHeaderImageMatrix.reset();
            mArrowView.setImageMatrix(mHeaderImageMatrix);
        }
    }
    //</editor-fold>

    //<editor-fold desc="API">
    public SunHeader setProgressBitmap(Bitmap bitmap) {
        return this;
    }

    public SunHeader setProgressDrawable(Drawable drawable) {
        return this;
    }

    public SunHeader setProgressResource(@DrawableRes int resId) {
        return this;
    }

    public SunHeader setArrowBitmap(Bitmap bitmap) {
        mArrowView.setImageBitmap(bitmap);
        return this;
    }

    public SunHeader setArrowDrawable(Drawable drawable) {
        mArrowView.setImageDrawable(drawable);
        return this;
    }

    public SunHeader setArrowResource(@DrawableRes int resId) {
        mArrowView.setImageResource(resId);
        return this;
    }

    @SuppressLint("SetTextI18n")
    public SunHeader setLastUpdateTime(Date time) {
        mLastTime = time;
        mLastUpdateText.setText("上次更新 " + mFormat.format(mLastTime));
        if (mShared != null && !isInEditMode()) {
            mShared.edit().putLong(KEY_LAST_UPDATE_TIME, time.getTime()).apply();
        }
        return this;
    }

    public SunHeader setTimeFormat(DateFormat format) {
        mFormat = format;
        mLastUpdateText.setText(mFormat.format(mLastTime));
        return this;
    }

    public SunHeader setSpinnerStyle(SpinnerStyle style) {
        this.mSpinnerStyle = style;
        return this;
    }

    public SunHeader setPrimaryColor(@ColorInt int primaryColor) {
        setBackgroundColor(mBackgroundColor = primaryColor);
        if (mRefreshKernel != null) {
            mRefreshKernel.requestDrawBackgroundFor(this, mBackgroundColor);
        }
        return this;
    }

    public SunHeader setAccentColor(@ColorInt int accentColor) {
        return this;
    }

    public SunHeader setPrimaryColorId(@ColorRes int colorId) {
        setPrimaryColor(ContextCompat.getColor(getContext(), colorId));
        return this;
    }

    public SunHeader setAccentColorId(@ColorRes int colorId) {
        setAccentColor(ContextCompat.getColor(getContext(), colorId));
        return this;
    }

    public SunHeader setFinishDuration(int delay) {
        mFinishDuration = delay;
        return this;
    }

    public SunHeader setEnableLastTime(boolean enable) {
        mEnableLastTime = enable;
        mLastUpdateText.setVisibility(enable ? VISIBLE : GONE);
        if (mRefreshKernel != null) {
            mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public SunHeader setTextSizeTitle(float size) {
        if (mRefreshKernel != null) {
            mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public SunHeader setTextSizeTitle(int unit, float size) {
        if (mRefreshKernel != null) {
            mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public SunHeader setTextSizeTime(float size) {
        mLastUpdateText.setTextSize(size);
        if (mRefreshKernel != null) {
            mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public SunHeader setTextSizeTime(int unit, float size) {
        mLastUpdateText.setTextSize(unit, size);
        if (mRefreshKernel != null) {
            mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public SunHeader setTextTimeMarginTop(float dp) {
        return setTextTimeMarginTopPx(DensityUtil.dip2px(getContext(),dp));
    }

    public SunHeader setTextTimeMarginTopPx(int px) {
        MarginLayoutParams lp = (MarginLayoutParams) mLastUpdateText.getLayoutParams();
        lp.topMargin = px;
        mLastUpdateText.setLayoutParams(lp);
        return this;
    }

    public SunHeader setDrawableMarginRight(float dp) {
        return setDrawableMarginRightPx(DensityUtil.dip2px(getContext(),dp));
    }

    public SunHeader setDrawableMarginRightPx(int px) {
        MarginLayoutParams lpArrow = (MarginLayoutParams) mArrowView.getLayoutParams();
        mArrowView.setLayoutParams(lpArrow);
        return this;
    }

    public SunHeader setDrawableSize(float dp) {
        return setDrawableSizePx(DensityUtil.dip2px(getContext(),dp));
    }

    public SunHeader setDrawableSizePx(int px) {
        ViewGroup.LayoutParams lpArrow = mArrowView.getLayoutParams();
        mArrowView.setLayoutParams(lpArrow);
        return this;
    }

    public SunHeader setDrawableArrowSize(float dp) {
        return setDrawableArrowSizePx(DensityUtil.dip2px(getContext(),dp));
    }

    public SunHeader setDrawableArrowSizePx(int px) {
        ViewGroup.LayoutParams lpArrow = mArrowView.getLayoutParams();
        lpArrow.width = px;
        lpArrow.height = px;
        mArrowView.setLayoutParams(lpArrow);
        return this;
    }

    public SunHeader setDrawableProgressSize(float dp) {
        return setDrawableProgressSizePx(DensityUtil.dip2px(getContext(),dp));
    }

    public SunHeader setDrawableProgressSizePx(int px) {
        return this;
    }

    public ImageView getArrowView() {
        return mArrowView;
    }

    public TextView getLastUpdateText() {
        return mLastUpdateText;
    }

    //</editor-fold>

}
