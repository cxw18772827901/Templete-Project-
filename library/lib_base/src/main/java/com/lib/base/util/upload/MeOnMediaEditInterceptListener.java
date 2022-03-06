package com.lib.base.util.upload;

import android.net.Uri;

import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnMediaEditInterceptListener;
import com.luck.picture.lib.utils.DateUtils;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import androidx.fragment.app.Fragment;

/**
 * 自定义编辑
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.util.upload
 *@author      xwchen
 * Date         2022/2/9.
 */

class MeOnMediaEditInterceptListener implements OnMediaEditInterceptListener {

    @Override
    public void onStartMediaEdit(Fragment fragment, LocalMedia currentLocalMedia, int requestCode) {
        String currentEditPath = currentLocalMedia.getAvailablePath();
        Uri inputUri = PictureMimeType.isContent(currentEditPath)
                ? Uri.parse(currentEditPath) : Uri.fromFile(new File(currentEditPath));
        Uri destinationUri = Uri.fromFile(
                new File(Util.getSandboxPath(), DateUtils.getCreateFileName("CROP_") + ".jpeg"));
        UCrop uCrop = UCrop.of(inputUri, destinationUri);
        UCrop.Options options = Util.buildOptions();
        options.setHideBottomControls(false);
        uCrop.withOptions(options);
        uCrop.startEdit(fragment.getActivity(), fragment, requestCode);
    }
}
