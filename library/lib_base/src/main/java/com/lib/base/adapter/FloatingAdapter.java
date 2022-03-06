package com.lib.base.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.base.databinding.DemoLayoutBinding;
import com.lib.base.databinding.HomeHeaderLayoutBinding;
import com.lib.base.util.ViewUtil;
import com.hjq.shape.view.recyclerList.CenterLayoutManager;
import com.lib.base.util.OUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*      mAdapter = new ImagePreviewAdapter(this);//AppAdapter
        mAdapter.setData(images);
        mAdapter.setOnItemClickListener(this);
        mViewPager.setAdapter(new RecyclerPagerAdapter(mAdapter));
        if (images.size() != 1) {
            if (images.size() < 10) {
                // 如果是 10 张以内的图片，那么就显示圆圈指示器
                mCircleIndicatorView.setVisibility(View.VISIBLE);
                mCircleIndicatorView.setViewPager(mViewPager);
            } else {
                // 如果超过 10 张图片，那么就显示文字指示器
                mTextIndicatorView.setVisibility(View.VISIBLE);
                mViewPager.addOnPageChangeListener(this);
            }

            int index = getInt(INTENT_KEY_IN_IMAGE_INDEX);
            if (index < images.size()) {
                mViewPager.setCurrentItem(index);
                onPageSelected(index);
            }
        }*/

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.adapter
 * @author      xwchen
 * Date         2021/12/30.
 */
public class FloatingAdapter extends AppAdapter<String> {
    public static final String TAG = "FloatingAdapter";
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_TOP = 0;
    public static final int TYPE_MIDDLE = 1;

    private final Context context;
    private final OnItemClickListener onItemClickListener;
    private RecyclerView recyclerView1, recyclerView2;
    private CenterLayoutManager layoutManager1, layoutManager2;
    private HomeTopBarAdapter adapter1, adapter2;
    //记录滚动参数
    private int scrollType, firstPosition, positionOffset;
    //记录点击的position
    private boolean hasClick;

    public FloatingAdapter(@NonNull Context context, RecyclerView recyclerView1, OnItemClickListener onItemClickListener) {
        super(context);
        this.context = context;
        this.recyclerView1 = recyclerView1;
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 初始化顶部recyclerview
     */
    public void initRecyclerView1() {
        ViewUtil.stopFlingWhenTouchUp(recyclerView1);
        layoutManager1 = new CenterLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        adapter1 = new HomeTopBarAdapter(0);
        recyclerView1.setAdapter(adapter1);
        adapter1.setOnItemClickListener((pos, itemStr) -> {
            adapter2.setSelect(adapter1.getPos());
            scrollType = TYPE_TOP;
            recyclerView1.smoothScrollToPosition(pos);//滚动到中间位置
            //recyclerView2.smoothScrollToPosition(pos);
            //点击事件
            hasClick = true;
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_ITEM;
    }

    @NonNull
    @Override
    public AppAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return TYPE_HEADER == viewType ?
                new HeaderHolder(HomeHeaderLayoutBinding.inflate(layoutInflater, parent, false)) :
                new ViewHolder(DemoLayoutBinding.inflate(layoutInflater, parent, false));
    }

    private final class HeaderHolder extends AppAdapter<?>.ViewHolder {

        private final HomeHeaderLayoutBinding binding;

        private HeaderHolder(@NonNull HomeHeaderLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBindView(int position) {
            //中间的recyclerView
            recyclerView2 = binding.recyclerView2;
            ViewUtil.stopFlingWhenTouchUp(recyclerView2);
            layoutManager2 = new CenterLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            recyclerView2.setLayoutManager(layoutManager2);
            adapter2 = new HomeTopBarAdapter(adapter1.getPos());
            recyclerView2.setAdapter(adapter2);
            adapter2.setOnItemClickListener((pos, itemStr) -> {
                adapter1.setSelect(adapter2.getPos());
                scrollType = TYPE_MIDDLE;
                //recyclerView1.smoothScrollToPosition(pos);
                recyclerView2.smoothScrollToPosition(pos);//滚动到中间位置
                //点击事件
                hasClick = true;
            });
            //滚动到之前的记录的位置
            recyclerView1.clearOnScrollListeners();
            recyclerView2.clearOnScrollListeners();
            layoutManager1.scrollToPositionWithOffset(firstPosition, positionOffset);
            layoutManager2.scrollToPositionWithOffset(firstPosition, positionOffset);
            //滚动监听同步操作
            recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    endScroll(newState, true, position);
                }
            });
            recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    endScroll(newState, false, position);
                }
            });
        }
    }

    private void endScroll(int newState, boolean isTop, int position) {
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            scrollType = isTop ? TYPE_TOP : TYPE_MIDDLE;
        } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            boolean endScroll = (isTop && scrollType == TYPE_TOP) || (!isTop && scrollType == TYPE_MIDDLE);
            if (endScroll) {
                CenterLayoutManager layoutManager = isTop ? layoutManager1 : layoutManager2;
                int firstPosition = layoutManager.findFirstVisibleItemPosition();
                if (firstPosition < 0) {
                    return;
                }
                View view = layoutManager.findViewByPosition(firstPosition);
                if (OUtil.isNull(view)) {
                    return;
                }
                int positionOffset = view.getLeft();
                //联动另外一个recyclerview滚动
                CenterLayoutManager scrollLayoutManager = isTop ? layoutManager2 : layoutManager1;
                scrollLayoutManager.scrollToPositionWithOffset(firstPosition, positionOffset);
                //记录滚动位置
                rememberPosition(firstPosition, positionOffset);
                //点击事件
                if (hasClick) {
                    hasClick = false;
                    if (isTop) {
                        onItemClickListener.topBarClick(position);
                    } else {
                        onItemClickListener.middleBarClick(position);
                    }
                }
            }
        }
    }

    private void rememberPosition(int firstPosition, int positionOffset) {
        this.firstPosition = firstPosition;
        this.positionOffset = positionOffset;
    }

    public int getheight() {
        final int[] locations = new int[2];
        recyclerView2.getLocationOnScreen(locations);
        return locations[1];
    }

    private final class ViewHolder extends AppAdapter<?>.ViewHolder {

        private final DemoLayoutBinding binding;

        private ViewHolder(@NonNull DemoLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindView(int position) {
            binding.tv.setText(getItem(position));
        }

    }

    public interface OnItemClickListener {
        void topBarClick(int position);

        void middleBarClick(int position);

        void itemClick(int position);
    }
}
