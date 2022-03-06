package com.lib.base.util.upload;

import com.lib.base.glide.GlideApp;
import com.lib.base.util.DebugUtil;
import com.luck.lib.camerax.SimpleCameraX;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.interfaces.OnCameraInterceptListener;

import androidx.fragment.app.Fragment;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.util.upload
 * @author      xwchen
 * Date         2022/2/9.
 */

class MeOnCameraInterceptListener implements OnCameraInterceptListener {

    @Override
    public void openCamera(Fragment fragment, int cameraMode, int requestCode) {
        if (cameraMode == SelectMimeType.ofAudio()) {
            DebugUtil.toast("自定义录音功能，请自行扩展");
        } else {
            SimpleCameraX camera = SimpleCameraX.of();
            camera.setCameraMode(cameraMode);
            camera.setVideoFrameRate(25);
            camera.setRecordVideoMaxSecond(10);
            camera.setRecordVideoMinSecond(2);
            camera.setVideoBitRate(3 * 1024 * 1024);
            camera.isDisplayRecordChangeTime(true);
            camera.setOutputPathDir(Util.getSandboxCameraOutputPath());
            camera.setImageEngine((context, url, imageView) -> GlideApp.with(context).load(url).into(imageView));
            camera.start(fragment.getActivity(), fragment, requestCode);
        }
    }

}