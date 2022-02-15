package com.lib.base.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.lib.base.R;
import com.lib.base.config.App;

import java.security.MessageDigest;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;


/**
 * Copyright (C) 2020 Wasabeef
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class CropCircleWithBorderTransformation extends BitmapTransformation {


    private static final int VERSION = 1;
    private static final String ID = "jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation." + VERSION;

    private final int borderSize;
    private final int borderColor;


    public CropCircleWithBorderTransformation() {
        this.borderSize = (int) App.getContext().getResources().getDimension(R.dimen.x10);
        this.borderColor = Color.BLACK;
    }

    public CropCircleWithBorderTransformation(int borderSize, @ColorInt int borderColor) {
        this.borderSize = borderSize;
        this.borderColor = borderColor;
    }

    @Override
    protected Bitmap transform(@NonNull Context context, @NonNull BitmapPool pool,
                               @NonNull Bitmap toTransform, int outWidth, int outHeight) {

        Bitmap bitmap = TransformationUtils.circleCrop(pool, toTransform, outWidth, outHeight);

        setCanvasBitmapDensity(toTransform, bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderSize);
        paint.setAntiAlias(true);
        paint.setDither(true);

        Canvas canvas = new Canvas(bitmap);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        //注意此处使用bitmap的宽高,否则会有异常
        canvas.drawCircle(
                /*outWidth*/bitmap.getWidth() / 2f,
                /*outHeight*/bitmap.getHeight() / 2f,
                Math.min(/*outWidth*/bitmap.getWidth(), /*outHeight*/bitmap.getHeight()) / 2f - borderSize / 2f,
                paint
        );

        return bitmap;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((ID + borderSize + borderColor).getBytes(CHARSET));
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CropCircleWithBorderTransformation &&
                ((CropCircleWithBorderTransformation) o).borderSize == borderSize &&
                ((CropCircleWithBorderTransformation) o).borderColor == borderColor;
    }

    @Override
    public int hashCode() {
        return ID.hashCode() + borderSize * 100 + borderColor + 10;
    }
}
