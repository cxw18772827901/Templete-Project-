package com.lib.base.util.upload;

import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;

import java.util.ArrayList;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.util.upload
 *@author      xwchen
 * Date         2022/2/9.
 */

public class MeOnResultCallbackListener implements OnResultCallbackListener<LocalMedia> {
    public static final String TAG="MeOnResultCallbackListener";
    @Override
    public void onResult(ArrayList<LocalMedia> result) {
        //analyticalSelectResults(result);
    }

    @Override
    public void onCancel() {
        //Log.i(TAG, "PictureSelector Cancel");
    }
}
