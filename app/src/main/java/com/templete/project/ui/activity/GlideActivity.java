package com.templete.project.ui.activity;

import android.graphics.Color;
import android.graphics.PointF;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.lib.base.glide.GlideApp;
import com.lib.base.glide.transformations.BlurTransformation;
import com.lib.base.glide.transformations.ColorFilterTransformation;
import com.lib.base.glide.transformations.CropCircleTransformation;
import com.lib.base.glide.transformations.CropCircleWithBorderTransformation;
import com.lib.base.glide.transformations.CropSquareTransformation;
import com.lib.base.glide.transformations.CropTransformation;
import com.lib.base.glide.transformations.GrayscaleTransformation;
import com.lib.base.glide.transformations.MaskTransformation;
import com.lib.base.glide.transformations.RoundedCornersTransformation;
import com.lib.base.glide.transformations.gpu.BrightnessFilterTransformation;
import com.lib.base.glide.transformations.gpu.ContrastFilterTransformation;
import com.lib.base.glide.transformations.gpu.InvertFilterTransformation;
import com.lib.base.glide.transformations.gpu.KuwaharaFilterTransformation;
import com.lib.base.glide.transformations.gpu.PixelationFilterTransformation;
import com.lib.base.glide.transformations.gpu.SepiaFilterTransformation;
import com.lib.base.glide.transformations.gpu.SketchFilterTransformation;
import com.lib.base.glide.transformations.gpu.SwirlFilterTransformation;
import com.lib.base.glide.transformations.gpu.ToonFilterTransformation;
import com.lib.base.glide.transformations.gpu.VignetteFilterTransformation;
import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.R;
import com.templete.project.databinding.GlideActivityBinding;

/**
 * PackageName  com.templete.project.ui
 * ProjectName  TempleteProject-java
 * Date         2022/1/9.
 *
 * @author chenxiaowu
 */

public class GlideActivity extends BaseActivity<GlideActivityBinding> {
    @Override
    protected GlideActivityBinding viewBinding() {
        return GlideActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void inits() {
        setTitleStr("glide transform 大全");
    }

    @Override
    public void initView() {
        //Mask 只展示面具
        GlideApp.with(this)
                .load(R.drawable.check)
//                .apply(RequestOptions.overrideOf(300, 200))
                .apply(RequestOptions.bitmapTransform(new MultiTransformation<>(new CenterCrop(), new MaskTransformation(R.drawable.mask_starfish))))
                .into(mViewBinding.iv1);
        //NinePatchMask 点9图 右
        GlideApp.with(this)
                .load(R.drawable.check)
//                .apply(RequestOptions.overrideOf(300, 200))
                .apply(RequestOptions.bitmapTransform(new MultiTransformation<>(new CenterCrop(), new MaskTransformation(R.drawable.mask_chat_right))))
                .into(mViewBinding.iv2);
        //CropTop
        GlideApp.with(this)
                .load(R.drawable.demo)
                .apply(RequestOptions.bitmapTransform(new CropTransformation(/*300*/0, /*100*/0, CropTransformation.CropType.TOP)))
                .into(mViewBinding.iv3);

        //CropCenter 
        GlideApp.with(this)
                .load(R.drawable.demo)
                .apply(RequestOptions.bitmapTransform(new CropTransformation(/*300*/0, /*100*/0, CropTransformation.CropType.CENTER)))
                .into(mViewBinding.iv4);

        //CropBottom
        GlideApp.with(this)
                .load(R.drawable.demo)
                .apply(RequestOptions.bitmapTransform(new CropTransformation(/*300*/0, /*100*/0, CropTransformation.CropType.BOTTOM)))
                .into(mViewBinding.iv5);

        //CropSquare 方形
        GlideApp.with(this)
                .load(R.drawable.demo)
                .apply(RequestOptions.bitmapTransform(new CropSquareTransformation()))
                .into(mViewBinding.iv6);

        //CropCircle
        GlideApp.with(this)
                .load(R.drawable.demo)
                .apply(RequestOptions.bitmapTransform(new CropCircleTransformation()))
                .into(mViewBinding.iv7);

        //CropCircleWithBorder
        GlideApp.with(this)
                .load(R.drawable.demo)
                .apply(RequestOptions.bitmapTransform(
                        new CropCircleWithBorderTransformation((int) getDimen(com.lib.base.R.dimen.x10), Color.rgb(0, 145, 86))))
                .into(mViewBinding.iv8);

        //ColorFilter 滤色镜
        GlideApp.with(this)
                .load(R.drawable.demo)
                .apply(RequestOptions.bitmapTransform(new ColorFilterTransformation(Color.argb(80, 255, 0, 0))))
                .into(mViewBinding.iv9);

        //Grayscale 黑白
        GlideApp.with(this)
                .load(R.drawable.demo)
                .apply(RequestOptions.bitmapTransform(new GrayscaleTransformation()))
                .into(mViewBinding.iv10);

        //RoundedCorners
        GlideApp.with(this)
                .load(R.drawable.demo)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(120, 0, RoundedCornersTransformation.CornerType.DIAGONAL_FROM_TOP_LEFT)))
                .into(mViewBinding.iv11);

        //BlurLight 毛玻璃-浅色
        GlideApp.with(this)
                .load(R.drawable.check)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25)))
                .into(mViewBinding.iv12);

        //BlurDeep 毛玻璃-深色
        GlideApp.with(this)
                .load(R.drawable.check)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 8)))
                .into(mViewBinding.iv13);

        //Toon 绘画
        GlideApp.with(this)
                .load(R.drawable.demo)
                .apply(RequestOptions.bitmapTransform(new ToonFilterTransformation()))
                .into(mViewBinding.iv14);

        //Sepia 深褐色
        GlideApp.with(this)
                .load(R.drawable.check)
                .apply(RequestOptions.bitmapTransform(new SepiaFilterTransformation()))
                .into(mViewBinding.iv15);

        //Contrast 对比度
        GlideApp.with(this)
                .load(R.drawable.check)
                .apply(RequestOptions.bitmapTransform(new ContrastFilterTransformation(2.0f)))
                .into(mViewBinding.iv16);

        //Invert 反转
        GlideApp.with(this)
                .load(R.drawable.check)
                .apply(RequestOptions.bitmapTransform(new InvertFilterTransformation()))
                .into(mViewBinding.iv17);

        //Pixel 像素
        GlideApp.with(this)
                .load(R.drawable.check)
                .apply(RequestOptions.bitmapTransform(new PixelationFilterTransformation(20f)))
                .into(mViewBinding.iv18);

        //Sketch 素描
        GlideApp.with(this)
                .load(R.drawable.check)
                .apply(RequestOptions.bitmapTransform(new SketchFilterTransformation()))
                .into(mViewBinding.iv19);

        //Swirl 旋涡状态
        GlideApp.with(this)
                .load(R.drawable.check)
                .apply(RequestOptions.bitmapTransform(new SwirlFilterTransformation(0.5f, 1.0f, new PointF(0.5f, 0.5f))).dontAnimate())
                .into(mViewBinding.iv20);

        //Brightness 增加曝光
        GlideApp.with(this)
                .load(R.drawable.check)
                .apply(RequestOptions.bitmapTransform(new BrightnessFilterTransformation(0.5f)).dontAnimate())
                .into(mViewBinding.iv21);

        //Kuawahara 沙漠化
        GlideApp.with(this)
                .load(R.drawable.check)
                .apply(RequestOptions.bitmapTransform(new KuwaharaFilterTransformation(25)).dontAnimate())
                .into(mViewBinding.iv22);

        //Vignette 类似电影的晕映效果
        float[] arr = {0.0f, 0.0f, 0.0f};
        GlideApp.with(this)
                .load(R.drawable.check)
                .apply(RequestOptions.bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f), arr, 0f, 0.75f)).dontAnimate())
                .into(mViewBinding.iv23);
        //NinePatchMask 点9图 左
        GlideApp.with(this)
                .load(R.drawable.check)
//                .apply(RequestOptions.overrideOf(300, 200))
                .apply(RequestOptions.bitmapTransform(new MultiTransformation<>(new CenterCrop(), new MaskTransformation(R.drawable.mask_chat_left))))
                .into(mViewBinding.iv24);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }
}
