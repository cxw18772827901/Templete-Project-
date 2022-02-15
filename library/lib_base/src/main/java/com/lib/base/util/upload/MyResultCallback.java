package com.lib.base.util.upload;

import android.content.Context;
import android.text.TextUtils;

import com.lib.base.bean.ResBean;
import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.util.ContextUtil;
import com.lib.base.util.GsonUtil;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import java.util.ArrayList;

/*callback = new MyResultCallback(token,context);
        callback.setListener(new DialogImgListener() {
            @Override
            public void sucess(String url) {

                try {
                    mList.get(position).setContent(url);
                    mList.get(position).setEditContent(url);
                    holder.iv_sel_img.setImageData(url);
                } catch (Exception e) {
                    BaseApp.show("上传失败..请稍后再试");
                    e.printStackTrace();
                }
            }
        });
        PictureSelectorUtils.picSelector(context, PictureMimeType.ofImage(), PictureConfig.SINGLE,1,true,callback);*/
public class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
    private String token;
    private Context context;
    private UploadManager uploadManager;

    public MyResultCallback(String token, Context context) {
        this.token = token;
        this.context = context;
    }

    @Override
    public void onResult(ArrayList<LocalMedia> result) {
        String url = result.get(0).getCompressPath();
        if (TextUtils.isEmpty(url)) {
            return;
        }
        uploadImg(url);
    }


    private DialogImgListener dialogImgListener;

    public void setListener(DialogImgListener dialogImgListener) {
        this.dialogImgListener = dialogImgListener;
    }

    @Override
    public void onCancel() {

    }

    private void initQiniu() {
        if (uploadManager == null) {
            Configuration config = new Configuration.Builder().build();
            uploadManager = new UploadManager(config);
        }
    }


    /**
     * 上传多图到服务器
     */
    private void uploadImg(String url) {
        initQiniu();
        BaseActivity activity = (BaseActivity) ContextUtil.getActivityByContext(context);
        if (!ContextUtil.isActivitySurvive(activity)) {
            return;
        }
        activity.showLoadingDialog("上传中...");
        activity.cancelUpload = false;
        uploadManager.put(url, "xsm_" + System.currentTimeMillis(), token,
                (key, info, res) -> {
                    activity.closeDialog();
                    //res包含hash、key等信息，具体字段取决于上传策略的设置
                    if (info.isOK()) {
                        activity.toast("上传图片成功");
                        ResBean resBean = GsonUtil.getInstance().fromJson(res.toString(), ResBean.class);
                        dialogImgListener.sucess(QINIU + resBean.getKey());
                    } else {
                        activity.toast("上传失败");
                    }
                    activity.logD("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                }, new UploadOptions(null, null, false,
                        (key, percent) -> activity.logD("login", "percent" + percent),
                        () -> activity.cancelUpload));
    }

    public static final String QINIU = "http://img.tuippp.com/";
}
