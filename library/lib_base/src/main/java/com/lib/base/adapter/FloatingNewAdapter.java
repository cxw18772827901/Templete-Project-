package com.lib.base.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.hjq.shape.view.NavigationBar;
import com.lib.base.databinding.DemoLayoutBinding;
import com.lib.base.databinding.HomeHeaderLayoutNewBinding;
import com.lib.base.util.ViewUtil;

import androidx.annotation.NonNull;

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
 *@author      xwchen
 * Date         2021/12/30.
 */
public class FloatingNewAdapter extends AppAdapter<String> {
    public static final String TAG = "FloatingAdapter";
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_TOP = 0;
    public static final int TYPE_MIDDLE = 1;

    private final Context context;
    private final OnItemClickListener onItemClickListener;
    private FrameLayout container;

    public FloatingNewAdapter(@NonNull Context context, OnItemClickListener onItemClickListener) {
        super(context);
        this.context = context;
        this.onItemClickListener = onItemClickListener;
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
                new HeaderHolder(HomeHeaderLayoutNewBinding.inflate(layoutInflater, parent, false)) :
                new ViewHolder(DemoLayoutBinding.inflate(layoutInflater, parent, false));
    }

    public void addView(NavigationBar bar) {
        ViewUtil.addView(container, bar);
    }

    private final class HeaderHolder extends AppAdapter<?>.ViewHolder {

        private final HomeHeaderLayoutNewBinding binding;

        private HeaderHolder(@NonNull HomeHeaderLayoutNewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBindView(int position) {
            container = binding.container;
        }
    }

    public int getheight() {
        final int[] locations = new int[2];
        container.getLocationOnScreen(locations);
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
        void itemClick(int position);
    }
}
