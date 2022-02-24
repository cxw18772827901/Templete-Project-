package com.lib.base.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.lib.base.glide.GlideUtil;

import androidx.annotation.Nullable;

/**
 * 将url加载封装到RoundedImageView中
 * PackageName  com.lib.base.ui.widget
 * ProjectName  TempleteProject-java
 * Date         2022/2/24.
 *
 * @author chenxiaowu
 */

public class GlideImageView extends androidx.appcompat.widget.AppCompatImageView {
    public GlideImageView(Context context) {
        super(context);
    }

    public GlideImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GlideImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void loadPic(Context context, String url) {
        GlideUtil.loadPic(context, url, this);
    }

    public void loadPicCircle(Context context, String url) {
        GlideUtil.loadPicCircle(context, url, this);
    }

    public void loadPicRound(Context context, String url, int radius) {
        GlideUtil.loadPicRound(context, url, this, radius);
    }
}
