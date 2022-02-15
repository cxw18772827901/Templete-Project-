package com.hjq.shape.view.smartView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;
import java.util.Locale;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 * 经典下拉头部
 * Created by SCWANG on 2017/5/28.
 */
@SuppressWarnings("unused")
public class GrassHeader extends RelativeLayout implements RefreshHeader {

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
    protected AnimationDrawable frameAnim = (AnimationDrawable) getResources().getDrawable(R.drawable.refresh);
    protected AnimationDrawable frameAnim2 = (AnimationDrawable) getResources().getDrawable(R.drawable.refresh_end);

    //<editor-fold desc="RelativeLayout">
    public GrassHeader(Context context) {
        super(context);
        this.initView(context, null);
    }

    public GrassHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, attrs);
    }

    public GrassHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context, attrs);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public GrassHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
//        DensityUtil density = new DensityUtil();

        LayoutInflater.from(context).inflate(R.layout.grass_header_layout, this);
        mLastUpdateText = findViewById(R.id.mLastUpdateText);
        mArrowView = findViewById(R.id.mArrowView);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GrassHeader);

        mFinishDuration = ta.getInt(R.styleable.ClassicsHeader_srlFinishDuration, mFinishDuration);
        mEnableLastTime = ta.getBoolean(R.styleable.ClassicsHeader_srlEnableLastTime, mEnableLastTime);
        mSpinnerStyle = SpinnerStyle.values[ta.getInt(R.styleable.ClassicsHeader_srlClassicsSpinnerStyle, mSpinnerStyle.ordinal)];

        mLastUpdateText.setVisibility(mEnableLastTime ? VISIBLE : GONE);

        if (ta.hasValue(R.styleable.ClassicsHeader_srlDrawableArrow)) {
            mArrowView.setImageDrawable(ta.getDrawable(R.styleable.ClassicsHeader_srlDrawableArrow));
        } else {
            mArrowView.setImageDrawable(getResources().getDrawable(R.mipmap.loading0));
        }
        mArrowView.setImageDrawable(getResources().getDrawable(R.mipmap.loading0));

       /* int tvSize = AppUtil.isTabletDevice(context) ? UIUtil.dip2px(9) : UIUtil.dip2px(5);
        if (ta.hasValue(R.styleable.ClassicsHeader_srlTextSizeTime)) {
            mLastUpdateText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(R.styleable.ClassicsHeader_srlTextSizeTime, *//*DensityUtil.dp2px(12)*//*tvSize));
        } else {
            mLastUpdateText.setTextSize(*//*14*//*tvSize);
//            mLastUpdateText.setTextSize(tvSize);
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

        try {//try 不能删除-否则会出现兼容性问题
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
        }

        KEY_LAST_UPDATE_TIME += context.getClass().getName();
        mShared = context.getSharedPreferences("banyu", Context.MODE_PRIVATE);
        setLastUpdateTime(new Date(mShared.getLong(KEY_LAST_UPDATE_TIME, System.currentTimeMillis())));

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
        double scale = 1.1;
        if (isDragging && !isFreshing)
            if (percent < 0.076 * scale) {
                mArrowView.setImageResource(R.mipmap.loading0);
            } else if (percent < 0.076 * 2 * scale) {
                mArrowView.setImageResource(R.mipmap.loading1);
            } else if (percent < 0.076 * 3 * scale) {
                mArrowView.setImageResource(R.mipmap.loading2);
            } else if (percent < 0.076 * 4 * scale) {
                mArrowView.setImageResource(R.mipmap.loading3);
            } else if (percent < 0.076 * 5 * scale) {
                mArrowView.setImageResource(R.mipmap.loading4);
            } else if (percent < 0.076 * 6 * scale) {
                mArrowView.setImageResource(R.mipmap.loading5);
            } else if (percent < 0.076 * 7 * scale) {
                mArrowView.setImageResource(R.mipmap.loading6);
            } else if (percent < 0.076 * 8 * scale) {
                mArrowView.setImageResource(R.mipmap.loading7);
            } else if (percent < 0.076 * 9 * scale) {
                mArrowView.setImageResource(R.mipmap.loading8);
            } else if (percent < 0.076 * 10 * scale) {
                mArrowView.setImageResource(R.mipmap.loading9);
            } else if (percent < 0.076 * 11 * scale) {
                mArrowView.setImageResource(R.mipmap.loading10);
            } else if (percent < 0.076 * 12 * scale) {
                mArrowView.setImageResource(R.mipmap.loading11);
            } else if (percent < 0.076 * 13 * scale) {
                mArrowView.setImageResource(R.mipmap.loading12);
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
                frameAnim.stop();
                frameAnim2.stop();
            case PullDownToRefresh:
                mArrowView.setVisibility(VISIBLE);
                break;
//            case Refreshing://此处开始刷新动画会有僵直，直接在释放时开始动画会很流畅
            case RefreshReleased:
                isFreshing = true;
                mArrowView.setImageDrawable(frameAnim);
                frameAnim.start();
                break;
            case RefreshFinish:
                isFreshing = false;
                mArrowView.setImageDrawable(frameAnim2);
                frameAnim2.start();
                break;
            case ReleaseToRefresh:
                break;
            case Loading:
                mArrowView.setVisibility(GONE);
                mLastUpdateText.setVisibility(GONE);
                break;
        }
    }
    //</editor-fold>

    //<editor-fold desc="API">
    public GrassHeader setProgressBitmap(Bitmap bitmap) {
        return this;
    }

    public GrassHeader setProgressDrawable(Drawable drawable) {
        return this;
    }

    public GrassHeader setProgressResource(@DrawableRes int resId) {
        return this;
    }

    public GrassHeader setArrowBitmap(Bitmap bitmap) {
        mArrowView.setImageBitmap(bitmap);
        return this;
    }

    public GrassHeader setArrowDrawable(Drawable drawable) {
        mArrowView.setImageDrawable(drawable);
        return this;
    }

    public GrassHeader setArrowResource(@DrawableRes int resId) {
        mArrowView.setImageResource(resId);
        return this;
    }

    @SuppressLint("SetTextI18n")
    public GrassHeader setLastUpdateTime(Date time) {
        mLastTime = time;
        mLastUpdateText.setText("上次更新 " + mFormat.format(mLastTime));
        if (mShared != null && !isInEditMode()) {
            mShared.edit().putLong(KEY_LAST_UPDATE_TIME, time.getTime()).apply();
        }
        return this;
    }

    public GrassHeader setTimeFormat(DateFormat format) {
        mFormat = format;
        mLastUpdateText.setText(mFormat.format(mLastTime));
        return this;
    }

    public GrassHeader setSpinnerStyle(SpinnerStyle style) {
        this.mSpinnerStyle = style;
        return this;
    }

    public GrassHeader setPrimaryColor(@ColorInt int primaryColor) {
        setBackgroundColor(mBackgroundColor = primaryColor);
        if (mRefreshKernel != null) {
            mRefreshKernel.requestDrawBackgroundFor(this, mBackgroundColor);
        }
        return this;
    }

    public GrassHeader setAccentColor(@ColorInt int accentColor) {
        return this;
    }

    public GrassHeader setPrimaryColorId(@ColorRes int colorId) {
        setPrimaryColor(ContextCompat.getColor(getContext(), colorId));
        return this;
    }

    public GrassHeader setAccentColorId(@ColorRes int colorId) {
        setAccentColor(ContextCompat.getColor(getContext(), colorId));
        return this;
    }

    public GrassHeader setFinishDuration(int delay) {
        mFinishDuration = delay;
        return this;
    }

    public GrassHeader setEnableLastTime(boolean enable) {
        mEnableLastTime = enable;
        mLastUpdateText.setVisibility(enable ? VISIBLE : GONE);
        if (mRefreshKernel != null) {
            mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public GrassHeader setTextSizeTitle(float size) {
        if (mRefreshKernel != null) {
            mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public GrassHeader setTextSizeTitle(int unit, float size) {
        if (mRefreshKernel != null) {
            mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public GrassHeader setTextSizeTime(float size) {
        mLastUpdateText.setTextSize(size);
        if (mRefreshKernel != null) {
            mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public GrassHeader setTextSizeTime(int unit, float size) {
        mLastUpdateText.setTextSize(unit, size);
        if (mRefreshKernel != null) {
            mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public GrassHeader setTextTimeMarginTop(float dp) {
        return setTextTimeMarginTopPx(DensityUtil.dip2px(getContext(), dp));
    }

    public GrassHeader setTextTimeMarginTopPx(int px) {
        MarginLayoutParams lp = (MarginLayoutParams) mLastUpdateText.getLayoutParams();
        lp.topMargin = px;
        mLastUpdateText.setLayoutParams(lp);
        return this;
    }

    public GrassHeader setDrawableMarginRight(float dp) {
        return setDrawableMarginRightPx(DensityUtil.dip2px(getContext(), dp));
    }

    public GrassHeader setDrawableMarginRightPx(int px) {
        MarginLayoutParams lpArrow = (MarginLayoutParams) mArrowView.getLayoutParams();
        mArrowView.setLayoutParams(lpArrow);
        return this;
    }

    public GrassHeader setDrawableSize(float dp) {
        return setDrawableSizePx(DensityUtil.dip2px(getContext(), dp));
    }

    public GrassHeader setDrawableSizePx(int px) {
        ViewGroup.LayoutParams lpArrow = mArrowView.getLayoutParams();
        mArrowView.setLayoutParams(lpArrow);
        return this;
    }

    public GrassHeader setDrawableArrowSize(float dp) {
        return setDrawableArrowSizePx(DensityUtil.dip2px(getContext(), dp));
    }

    public GrassHeader setDrawableArrowSizePx(int px) {
        ViewGroup.LayoutParams lpArrow = mArrowView.getLayoutParams();
        lpArrow.width = px;
        lpArrow.height = px;
        mArrowView.setLayoutParams(lpArrow);
        return this;
    }

    public GrassHeader setDrawableProgressSize(float dp) {
        return setDrawableProgressSizePx(DensityUtil.dip2px(getContext(), dp));
    }

    public GrassHeader setDrawableProgressSizePx(int px) {
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
