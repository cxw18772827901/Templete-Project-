package com.lib.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.base.databinding.DemoLayoutBinding;
import com.lib.base.util.DebugUtil;

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
public class DemoAdapter extends AppAdapter<Integer> {
    private boolean show;

    public void setShow(boolean show) {
        this.show = show;
    }

    public DemoAdapter(@NonNull Context context) {
        super(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DemoLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    private final class ViewHolder extends AppAdapter<?>.ViewHolder {

        private final DemoLayoutBinding binding;

        private ViewHolder(@NonNull DemoLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBindView(int position) {
            binding.tv1.setOnClickListener(v -> DebugUtil.toast("影藏item点击"));
            if (show) {
                binding.tv1.setVisibility(View.VISIBLE);
            }
        }
    }
}
