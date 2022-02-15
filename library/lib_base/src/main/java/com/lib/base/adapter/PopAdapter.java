package com.lib.base.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.base.bean.BtnBean;
import com.lib.base.databinding.PopItemLayoutBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
 * Author       Administrator
 * Date         2021/12/30.
 */
public class PopAdapter extends AppAdapter<BtnBean> {
    private final boolean hasSelect;
    private final boolean hasHtml;
    private int posSelect = 0;
    private boolean hasLeftRes;

    public PopAdapter(@NonNull Context context, boolean hasSelect, boolean hasHtml) {
        super(context);
        this.hasSelect = hasSelect;
        this.hasHtml = hasHtml;
    }

    @Override
    public void setData(@Nullable List<BtnBean> data) {
        checkResId(data);
        super.setData(data);
    }

    private void checkResId(List<BtnBean> data) {
        hasLeftRes = true;
        for (BtnBean titleBtnBean : data) {
            if (titleBtnBean.resId <= 0) {
                hasLeftRes = false;
                break;
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(PopItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    private final class ViewHolder extends AppAdapter.ViewHolder {

        private final PopItemLayoutBinding binding;

        private ViewHolder(@NonNull PopItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBindView(int position) {
            try {
                binding.line.setVisibility(position == getData().size() - 1 ? View.INVISIBLE : View.VISIBLE);

                BtnBean popBean = getItem(position);
                binding.tv.setText(getTxt(popBean.str));
                if (hasLeftRes) {
                    binding.ivLeft.setVisibility(View.VISIBLE);
                    binding.ivLeft.setImageResource(popBean.resId);
                }
                if (hasSelect) {
                    binding.getRoot().setSelected(posSelect == position);
                    binding.ivRight.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private CharSequence getTxt(String str) {
            return !hasHtml ? str : Html.fromHtml(str);
        }
    }

    /**
     * 只刷新两个item状态
     *
     * @param position
     */
    public void notifyData(int position) {
        if (hasSelect && posSelect != position) {
            int temp = posSelect;
            posSelect = position;
            notifyItemChanged(temp);
            notifyItemChanged(position);
            //notifyItemRangeChanged(Math.min(temp, position), Math.abs(temp - position) + 1);
        }
    }
}
