package com.lib.base.util.upload;

import com.lib.base.R;
import com.lib.base.config.App;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import androidx.core.content.ContextCompat;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.util.upload
 *@author      xwchen
 * Date         2022/2/9.
 */

public class Util {
    /**
     * 裁减配置
     *
     * @return
     */
    public static UCrop.Options buildOptions() {
        UCrop.Options options = new UCrop.Options();
        options.setHideBottomControls(true);//是否显示裁剪菜单栏
        options.setFreeStyleCropEnabled(false);//裁剪框or图片拖动
        options.setShowCropFrame(true);//是否显示裁剪边框
        options.setShowCropGrid(true);//是否显示裁剪框网格
        options.setCircleDimmedLayer(false);//圆形
        options.withAspectRatio(1, 1);
        options.setCropOutputPathDir(Util.getSandboxPath());
        options.isCropDragSmoothToCenter(false);
        options.isUseCustomLoaderBitmap(true);
        options.isForbidSkipMultipleCrop(false);
        options.setStatusBarColor(ContextCompat.getColor(App.getContext(), R.color.ps_color_grey));
        options.setToolbarColor(ContextCompat.getColor(App.getContext(), R.color.ps_color_grey));
        options.setToolbarWidgetColor(ContextCompat.getColor(App.getContext(), R.color.ps_color_white));
        return options;
    }

    /**
     * 自定义沙盒路径
     * @return
     */
    public static String getSandboxPath() {
        File externalFilesDir = App.getContext().getExternalFilesDir("");
        File customFile = new File(externalFilesDir.getAbsolutePath(), "Sandbox");
        if (!customFile.exists()) {
            customFile.mkdirs();
        }
        return customFile.getAbsolutePath() + File.separator;
    }


    /**
     * 创建音频自定义输出目录
     *
     * @return
     */
    public static String getSandboxAudioOutputPath() {
        File externalFilesDir = App.getContext().getExternalFilesDir("");
        File customFile = new File(externalFilesDir.getAbsolutePath(), "Sound");
        if (!customFile.exists()) {
            customFile.mkdirs();
        }
        return customFile.getAbsolutePath() + File.separator;
    }

    /**
     * 创建相机自定义输出目录
     *
     * @return
     */
    public static String getSandboxCameraOutputPath() {
        File externalFilesDir = App.getContext().getExternalFilesDir("");
        File customFile = new File(externalFilesDir.getAbsolutePath(), "Sandbox");
        if (!customFile.exists()) {
            customFile.mkdirs();
        }
        return customFile.getAbsolutePath() + File.separator;
    }
}
