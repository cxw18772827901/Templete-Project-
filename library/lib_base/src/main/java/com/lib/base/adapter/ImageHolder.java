package com.lib.base.adapter;

import android.view.View;


import com.hjq.shape.view.imageView.roundedimageview.RoundedImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageHolder extends RecyclerView.ViewHolder {
    public RoundedImageView imageView;

    public ImageHolder(@NonNull View view) {
        super(view);
        this.imageView = (RoundedImageView) view;
    }
}