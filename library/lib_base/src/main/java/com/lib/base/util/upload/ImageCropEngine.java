package com.lib.base.util.upload;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lib.base.glide.GlideApp;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.engine.CropEngine;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.utils.DateUtils;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropImageEngine;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.util.upload
 * @author      xwchen
 * Date         2022/2/9.
 */

class ImageCropEngine implements CropEngine {

    @Override
    public void onStartCrop(Fragment fragment, LocalMedia currentLocalMedia,
                            ArrayList<LocalMedia> dataSource, int requestCode) {
        String currentCropPath = currentLocalMedia.getAvailablePath();
        Uri inputUri;
        if (PictureMimeType.isContent(currentCropPath) || PictureMimeType.isHasHttp(currentCropPath)) {
            inputUri = Uri.parse(currentCropPath);
        } else {
            inputUri = Uri.fromFile(new File(currentCropPath));
        }
        String fileName = DateUtils.getCreateFileName("CROP_") + ".jpg";
        Uri destinationUri = Uri.fromFile(new File(Util.getSandboxPath(), fileName));
        UCrop.Options options = Util.buildOptions();
        ArrayList<String> dataCropSource = new ArrayList<>();
        for (int i = 0; i < dataSource.size(); i++) {
            LocalMedia media = dataSource.get(i);
            dataCropSource.add(media.getAvailablePath());
        }
        UCrop uCrop = UCrop.of(inputUri, destinationUri, dataCropSource);
        //options.setMultipleCropAspectRatio(buildAspectRatios(dataSource.size()));
        uCrop.withOptions(options);
        uCrop.setImageEngine(new UCropImageEngine() {
            @Override
            public void loadImage(Context context, String url, ImageView imageView) {
                if (!ImageLoaderUtils.assertValidRequest(context)) {
                    return;
                }
                GlideApp.with(context).load(url).into(imageView);
            }

            @Override
            public void loadImage(Context context, Uri url, int maxWidth, int maxHeight, OnCallbackListener<Bitmap> call) {
                if (!ImageLoaderUtils.assertValidRequest(context)) {
                    return;
                }
                GlideApp.with(context).asBitmap().override(maxWidth, maxHeight).load(url).into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if (call != null) {
                            call.onCall(resource);
                        }
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        if (call != null) {
                            call.onCall(null);
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
            }
        });
        uCrop.start(fragment.getActivity(), fragment, requestCode);
    }
}