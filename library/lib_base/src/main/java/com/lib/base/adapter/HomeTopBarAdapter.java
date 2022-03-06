package com.lib.base.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lib.base.R;
import com.lib.base.config.App;
import com.lib.base.databinding.HomeBarItemBinding;
import com.lib.base.rxjava.RxUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * PackageName  com.tiyu.zjwt.adapter
 * ProjectName  NumericalCodeAbilitiesPool
 *@author      xwchen
 * Date         1/7/21.
 */

public class HomeTopBarAdapter extends RecyclerView.Adapter<HomeTopBarAdapter.ViewHolder> {
    private int pos;

    public HomeTopBarAdapter(int pos) {
        this.pos = pos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new ViewHolder(HomeBarItemBinding.inflate(layoutInflater, viewGroup, false));
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        try {
            String str = "导航条目" + (i + 1);
            viewHolder.itemBinding.tv.setText(str);

            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewHolder.itemBinding.indicator.getLayoutParams();
            layoutParams.width = (int) (App.getContext().getResources().getDimension(R.dimen.x55) * (str.length() - 0.5));
            viewHolder.itemBinding.indicator.requestLayout();
            viewHolder.itemBinding.indicator.setSelected(pos == i);

            RxUtils.throwFirstClick(viewHolder.itemBinding.getRoot(), view -> {
                if (pos == i) {
                    return;
                }
                int temp = pos;
                pos = i;
                //notifyDataSetChanged();
                notifyItemChanged(temp);
                notifyItemChanged(pos);
//                notifyItemRangeChanged(Math.min(temp, pos), Math.abs(temp - pos) + 1);
                if (listener != null) {
                    listener.onItemClick(i, str);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSelect(int pos) {
        if (this.pos == pos) {
            return;
        }
        this.pos = pos;
        notifyDataSetChanged();
    }

    public int getPos() {
        return pos;
    }

    @SuppressLint("NonConstantResourceId")
    static class ViewHolder extends RecyclerView.ViewHolder {
        HomeBarItemBinding itemBinding;

        public ViewHolder(@NonNull HomeBarItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, String itemStr);
    }
}
