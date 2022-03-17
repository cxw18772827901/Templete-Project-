package com.templete.project.ui.activity;

import com.lib.base.databinding.SelectorActivityBinding;
import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.util.GlobalThreadPoolUtil;
import com.greendao.db.util.GsonUtil;
import com.lib.base.util.upload.MeOnResultCallbackListener;
import com.lib.base.util.upload.MediaPickerUtil;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui
 * @author      xwchen
 * Date         2022/2/7.
 */

public class SelectorActivity extends BaseActivity<SelectorActivityBinding> {

    @Override
    protected SelectorActivityBinding viewBinding() {
        return SelectorActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initS() {
        setTitleStr("图片选择器");
    }

    @Override
    public void initView() {
        setOnClickListener(v -> {
            if (v.equals(mViewBinding.tv1)) {
                MediaPickerUtil.getMedia(this, SelectMimeType.ofImage(), true, true, 1, new MeOnResultCallbackListener() {
                    @Override
                    public void onResult(ArrayList<LocalMedia> result) {
                        logD("MediaPickerUtil", "thread1=" + Thread.currentThread().getName());
                        logD("MediaPickerUtil", "result1=" + GsonUtil.getInstance().toJson(result));
                    }
                });
            } else if (v.equals(mViewBinding.tv2)) {
                MediaPickerUtil.getMedia(this, SelectMimeType.ofImage(), true, false, 6, new MeOnResultCallbackListener() {
                    @Override
                    public void onResult(ArrayList<LocalMedia> result) {
                        logD("MediaPickerUtil", "result2=" + GsonUtil.getInstance().toJson(result));
                        if (result.size() > 1) {
                            GlobalThreadPoolUtil.postOnUiThreadDelay(() -> MediaPickerUtil.preview(SelectorActivity.this, 0, result), 2000);
                        }
                    }
                });
            } else if (v.equals(mViewBinding.tv3)) {
                MediaPickerUtil.takeMedia(this, SelectMimeType.ofImage(), true, true, new MeOnResultCallbackListener() {
                    @Override
                    public void onResult(ArrayList<LocalMedia> result) {
                        logD("MediaPickerUtil", "result3=" + GsonUtil.getInstance().toJson(result));
                    }
                });
            } else if (v.equals(mViewBinding.tv4)) {
                MediaPickerUtil.takeMedia(this, SelectMimeType.ofVideo(), true, true, new MeOnResultCallbackListener() {
                    @Override
                    public void onResult(ArrayList<LocalMedia> result) {
                        logD("MediaPickerUtil", "result4=" + GsonUtil.getInstance().toJson(result));
                    }
                });
            }
        }, mViewBinding.tv1, mViewBinding.tv2, mViewBinding.tv3, mViewBinding.tv4);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        ArrayList<LocalMedia> list = new ArrayList<>();
        LocalMedia media = new LocalMedia();
//        media.setPath("http://1258757031.vod2.myqcloud.com/83e310f0vodsh1258757031/e019e5075285890812975115033/QRQy2RHMwwUA.mp4");
        media.setPath("https://omma-cdn.zhmobi.com/admin/20210521/31mR1621572617824.jpg");
        list.add(media);
        GlobalThreadPoolUtil.postOnUiThreadDelay(() -> MediaPickerUtil.preview(SelectorActivity.this, 0, list), 2000);
    }
}
