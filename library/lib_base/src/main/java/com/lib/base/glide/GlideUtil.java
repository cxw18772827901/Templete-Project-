package com.lib.base.glide;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.lang.ref.WeakReference;


/**
 * Package  com.dave.project.util
 * Project  Project
 * @author  xwchen
 * Date     2017/10/30.
 */

public class GlideUtil {

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadPic(Context context, String url, ImageView imageView) {
        try {
            GlideApp
                    .with(new WeakReference<>(context).get())
                    .load(url)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadPicCircle(Context context, String url, ImageView imageView) {
        try {
            GlideApp
                    .with(new WeakReference<>(context).get())
                    .load(url)
                    .transform(new MultiTransformation<>(new CircleCrop()))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param radius
     */
    public static void loadPicRound(Context context, String url, ImageView imageView, int radius) {
        try {
            GlideApp
                    .with(new WeakReference<>(context).get())
                    .load(url)
                    .transform(new MultiTransformation<>(new RoundedCorners(radius)))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载视频的某一帧
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadVideoFrame(Context context, String url, ImageView imageView) {
        try {
            GlideApp
                    .with(new WeakReference<>(context).get())
                    .load(url)
                    .apply(new RequestOptions()./*centerCropTransform().*/frame(100))//0.01秒的图片
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isPic(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        String lowerCase = path.toLowerCase();
        return lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".png");
    }

    public static boolean isNetPicNeedHeader(String url) {
        return !TextUtils.isEmpty(url) && !url.startsWith("http://") && !url.startsWith("https://");
    }
}
