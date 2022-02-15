package com.lib.base.ui.widget.banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjq.shape.view.imageView.roundedimageview.RoundedImageView;
import com.lib.base.R;
import com.lib.base.adapter.OnItemClickListener;
import com.lib.base.glide.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * 自定义轮播图:
 * 1.轮播间隔3s;
 * 2.手动滑动后取消自动轮播,松开后重新开始轮播;
 * 3.已处理与父view滚动事件冲突.
 */
public class BannerView extends FrameLayout {
    public static final String TAG = "AutoRollLayout";
    protected static final long ROLL_DELAY = 3000;//每3s轮播一次
    protected static final long LOOP_DELAY = 2000;//每2s轮询一次

    boolean allowAutoRoll;//是否自动轮播
    private List<? extends IShowItem> items;//data
    private ViewPager viewpager;
    private TextView titleTv;
    private LinearLayout dotContainer;
    private OnItemClickListener onItemClickListener;
    private int positionOffsetPixels;

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context) {
        this(context, null);
    }

    public void setOnItemClickListener(OnItemClickListener oicl) {
        this.onItemClickListener = oicl;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(Context context) {
        View view = View.inflate(context, R.layout.layout_auto_roll, this);
        viewpager = view.findViewById(R.id.arl_view_pager);
        titleTv = view.findViewById(R.id.arl_title);
        dotContainer = view.findViewById(R.id.arl_dot_container);

        viewpager.setAdapter(pagerAdapter);
        viewpager.setOnTouchListener(onTouchListener);
        viewpager.addOnPageChangeListener(onPageChangeListener);

        gestureDetector = new GestureDetector(context, onGestureListener);
    }

    private long lastTime;
    //当parent拦截时间后可能会出现轮播图停止的情况，再开一个定时任务处理
    private final Runnable loopRunnable = new Runnable() {
        @Override
        public void run() {
            removeCallbacks(this);
            long timeNow = System.currentTimeMillis();
            if (items != null && items.size() > 0 && lastTime != 0 && timeNow - lastTime > ROLL_DELAY && positionOffsetPixels == 0) {
                postDelayed(rollRunnable, 10);
            }
            postDelayed(this, LOOP_DELAY);
        }
    };

    public void setItems(List<? extends IShowItem> items) {
        //每次刷新时取消自动轮播
        removeCallbacks(rollRunnable);
        //取消定时轮训,重新开始定时轮训
        removeCallbacks(loopRunnable);
        postDelayed(loopRunnable, LOOP_DELAY);
        int dotWithd = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 7, getResources().getDisplayMetrics());
        // 添加点
        dotContainer.removeAllViews();
        for (IShowItem ignored : items) {
            View dot = new View(getContext());
            dot.setBackgroundResource(R.drawable.dot);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dotWithd, dotWithd);
            lp.leftMargin = (int) (dotWithd * 0.75);
            lp.rightMargin = (int) (dotWithd * 0.75);
            dotContainer.addView(dot, lp);
        }
        dotContainer.invalidate();
        if (dotContainer.getChildCount() >= 1) {
            dotContainer.getChildAt(0).setVisibility(View.INVISIBLE);
            dotContainer.getChildAt(dotContainer.getChildCount() - 1).setVisibility(View.INVISIBLE);
        }
        this.items = items;
        pagerAdapter.notifyDataSetChanged();
    }

    public void setAllowAutoRoll(boolean allowAutoRoll) {
        this.allowAutoRoll = allowAutoRoll;
        lastTime = System.currentTimeMillis();
        postDelayed(rollRunnable, ROLL_DELAY);
    }

    Runnable rollRunnable = new Runnable() {
        @Override
        public void run() {
            removeCallbacks(this);
            // 如果发现不允许自动滚动了就直接返回
            if (!allowAutoRoll) {
                return;
            }
            int currentIndex = viewpager.getCurrentItem();
            int next;
            if (currentIndex == pagerAdapter.getCount() - 1) {
                next = 0;
            } else {
                next = currentIndex + 1;
            }
            viewpager.setCurrentItem(next);
            postDelayed(this, ROLL_DELAY);
        }
    };

    private final PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            @SuppressLint("InflateParams") FrameLayout layout = (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.auto_roll_iv, null);
            RoundedImageView iv = layout.findViewById(R.id.sdv);
            GlideUtil.loadPic(container.getContext(), items.get(position).getImageUrl(), iv);
            container.addView(layout);
            return layout;
        }
    };

    private final ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            titleTv.setText(items.get(position).getTitle());
            for (int i = 0; i < pagerAdapter.getCount(); i++) {
                dotContainer.getChildAt(i).setEnabled(i != position);
            }
            lastTime = System.currentTimeMillis();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            BannerView.this.positionOffsetPixels = positionOffsetPixels;
            getParent().requestDisallowInterceptTouchEvent(positionOffsetPixels > 0);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (ViewPager.SCROLL_STATE_IDLE == state) {
                if (viewpager.getCurrentItem() == items.size() - 1) {
                    viewpager.setCurrentItem(1, false);
                } else if (viewpager.getCurrentItem() == 0) {
                    viewpager.setCurrentItem(items.size() - 2, false);
                }
            }
        }
    };

    private final OnTouchListener onTouchListener = new OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            gestureDetector.onTouchEvent(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: // 用户开始触摸了 ，，应该停止自动滚动
                    removeCallbacks(rollRunnable);
                    break;
                case MotionEvent.ACTION_MOVE://开始移动了
                    break;
                case MotionEvent.ACTION_UP: // 用户手指离开屏幕,, 应该恢复自动滚动（原来是停的，你摸完之后，）
                    postDelayed(rollRunnable, ROLL_DELAY);
                    break;
            }
            // 原来触摸事件怎么走就怎么走
            // onTouchEvent(event);
            //  return true;
            // 上面的代码和下面的意思是一样的
            return false;
        }
    };

    private GestureDetector gestureDetector;

    private final OnGestureListener onGestureListener = new OnGestureListener() {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // 通知外界点击事件
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(viewpager.getCurrentItem());
            }
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }
    };

    /**
     * 防内存泄漏处理
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(loopRunnable);
        removeCallbacks(rollRunnable);
    }
}
