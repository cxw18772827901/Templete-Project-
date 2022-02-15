package com.lib.base.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hjq.shape.view.imageView.roundedimageview.RoundedImageView;
import com.lib.base.R;
import com.lib.base.bean.DataBean;
import com.lib.base.glide.GlideUtil;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;


/**
 * 自定义布局，图片
 */
public class ImageAdapter extends BannerAdapter<DataBean, ImageHolder> {

    private final OnItemClickListener onItemClickListener;

    public ImageAdapter(List<DataBean> mDatas, OnItemClickListener onItemClickListener) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
        this.onItemClickListener = onItemClickListener;
    }

    //更新数据
    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<DataBean> data) {
        //这里的代码自己发挥，比如如下的写法等等
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        RoundedImageView imageView = (RoundedImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_iv, parent, false);
        //RoundedImageView imageView =layout.findViewById(R.id.sdv);// new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new ImageHolder(imageView);
    }

    @Override
    public void onBindView(ImageHolder holder, DataBean data, int position, int size) {
        //holder.imageView.setImageResource(data.imageRes);
        GlideUtil.loadPic(holder.imageView.getContext(), data.imageUrl, holder.imageView);
        holder.imageView.setOnClickListener(v -> onItemClickListener.onItemClick(position));
    }

}
