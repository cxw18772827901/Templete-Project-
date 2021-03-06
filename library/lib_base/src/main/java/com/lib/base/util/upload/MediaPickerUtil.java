package com.lib.base.util.upload;

import android.content.Context;
import android.graphics.Color;

import com.lib.base.R;
import com.luck.picture.lib.animators.AnimationType;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.config.SelectModeConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnExternalPreviewEventListener;
import com.luck.picture.lib.language.LanguageConfig;
import com.luck.picture.lib.manager.PictureCacheManager;
import com.luck.picture.lib.style.BottomNavBarStyle;
import com.luck.picture.lib.style.PictureSelectorStyle;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;
import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.style.TitleBarStyle;
import com.luck.picture.lib.utils.DensityUtil;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;

/**
 * R.style.picture_default_style
 * R.style.picture_white_style
 * R.style.picture_QQ_style
 * R.style.picture_Sina_style
 * R.style.picture_WeChat_style
 */
public class MediaPickerUtil {

    public static final int STYLE_DEFAULT = 0;
    public static final int STYLE_WHITE = 1;
    public static final int STYLE_QQ = 2;
    public static final int STYLE_WECHAT = 3;
    public static int styleSelector = STYLE_WECHAT;

    public static final int ANIM_DEFAULT = 0;
    public static final int ANIM_UP = 1;
    public static int styleWindowAnimation = ANIM_DEFAULT;

    public static final int RECYCLER_ANIM_DEFAULT = 0;
    public static final int RECYCLER_ANIM_ALPHA = 1;
    public static final int RECYCLER_SLIDE_IN = 1;
    public static int animRecyclerView = RECYCLER_ANIM_ALPHA;

    /**
     * RecyclerView Anim
     *
     * @return
     */
    private static int getAnimRecyclerView() {
        if (animRecyclerView == RECYCLER_ANIM_ALPHA) {
            return AnimationType.ALPHA_IN_ANIMATION;
        } else if (animRecyclerView == RECYCLER_SLIDE_IN) {
            return AnimationType.SLIDE_IN_BOTTOM_ANIMATION;
        } else {
            return AnimationType.DEFAULT_ANIMATION;
        }
    }

    /**
     * ??????????????????
     *
     * @param context
     * @return
     */
    private static PictureSelectorStyle getStyle(Context context) {
        //????????????
        PictureSelectorStyle pictureSelectorStyle = new PictureSelectorStyle();
        //style
        if (styleSelector == STYLE_WHITE) {
            setWhiteStyle(context, pictureSelectorStyle);
        } else if (styleSelector == STYLE_QQ) {
            setQqStyle(context, pictureSelectorStyle);
        } else if (styleSelector == STYLE_WECHAT) {
            setWeChatStyle(context, pictureSelectorStyle);
        }
        //anim
        if (styleWindowAnimation == ANIM_DEFAULT) {
            setDefaultWindowAnim(pictureSelectorStyle);
        } else {
            setWUpindowAnim(pictureSelectorStyle);
        }
        return pictureSelectorStyle;
    }

    /**
     * ????????????
     *
     * @param pictureSelectorStyle
     */
    private static void setWUpindowAnim(PictureSelectorStyle pictureSelectorStyle) {
        PictureWindowAnimationStyle animationStyle = new PictureWindowAnimationStyle();
        animationStyle.setActivityEnterAnimation(R.anim.ps_anim_up_in);
        animationStyle.setActivityExitAnimation(R.anim.ps_anim_down_out);
        pictureSelectorStyle.setWindowAnimationStyle(animationStyle);
    }

    /**
     * ??????
     *
     * @param pictureSelectorStyle
     */
    private static void setDefaultWindowAnim(PictureSelectorStyle pictureSelectorStyle) {
        PictureWindowAnimationStyle defaultAnimationStyle = new PictureWindowAnimationStyle();
        defaultAnimationStyle.setActivityEnterAnimation(R.anim.ps_anim_enter);
        defaultAnimationStyle.setActivityExitAnimation(R.anim.ps_anim_exit);
        pictureSelectorStyle.setWindowAnimationStyle(defaultAnimationStyle);
    }

    /**
     * ??????????????????
     *
     * @param context
     * @param pictureSelectorStyle
     */
    private static void setWeChatStyle(Context context, PictureSelectorStyle pictureSelectorStyle) {
        SelectMainStyle numberSelectMainStyle = new SelectMainStyle();
        numberSelectMainStyle.setSelectNumberStyle(true);
        numberSelectMainStyle.setPreviewSelectNumberStyle(false);
        numberSelectMainStyle.setPreviewDisplaySelectGallery(true);
        numberSelectMainStyle.setSelectBackground(R.drawable.ps_default_num_selector);
        numberSelectMainStyle.setPreviewSelectBackground(R.drawable.ps_preview_checkbox_selector);
        numberSelectMainStyle.setSelectNormalBackgroundResources(R.drawable.ps_select_complete_normal_bg);
        numberSelectMainStyle.setSelectNormalTextColor(ContextCompat.getColor(context, R.color.ps_color_53575e));
        numberSelectMainStyle.setSelectNormalText(context.getString(R.string.ps_send));
        numberSelectMainStyle.setAdapterPreviewGalleryBackgroundResource(R.drawable.ps_preview_gallery_bg);
        numberSelectMainStyle.setAdapterPreviewGalleryItemSize(DensityUtil.dip2px(context, 52));
        numberSelectMainStyle.setPreviewSelectText(context.getString(R.string.ps_select));
        numberSelectMainStyle.setPreviewSelectTextSize(14);
        numberSelectMainStyle.setPreviewSelectTextColor(ContextCompat.getColor(context, R.color.ps_color_white));
        numberSelectMainStyle.setPreviewSelectMarginRight(DensityUtil.dip2px(context, 6));
        numberSelectMainStyle.setSelectBackgroundResources(R.drawable.ps_select_complete_bg);
        numberSelectMainStyle.setSelectText(context.getString(R.string.ps_send_num));
        numberSelectMainStyle.setSelectTextColor(ContextCompat.getColor(context, R.color.ps_color_white));
        numberSelectMainStyle.setMainListBackgroundColor(ContextCompat.getColor(context, R.color.ps_color_black));
        numberSelectMainStyle.setCompleteSelectRelativeTop(true);
        numberSelectMainStyle.setPreviewSelectRelativeBottom(true);
        numberSelectMainStyle.setAdapterItemIncludeEdge(false);

        // ??????TitleBar ??????
        TitleBarStyle numberTitleBarStyle = new TitleBarStyle();
        numberTitleBarStyle.setHideCancelButton(true);
        numberTitleBarStyle.setAlbumTitleRelativeLeft(true);
        numberTitleBarStyle.setTitleAlbumBackgroundResource(R.drawable.ps_album_bg);
        numberTitleBarStyle.setTitleDrawableRightResource(R.drawable.ps_ic_grey_arrow);
        numberTitleBarStyle.setPreviewTitleLeftBackResource(R.drawable.ps_ic_normal_back);

        // ??????NavBar ??????
        BottomNavBarStyle numberBottomNavBarStyle = new BottomNavBarStyle();
        numberBottomNavBarStyle.setBottomPreviewNarBarBackgroundColor(ContextCompat.getColor(context, R.color.ps_color_half_grey));
        numberBottomNavBarStyle.setBottomPreviewNormalText(context.getString(R.string.ps_preview));
        numberBottomNavBarStyle.setBottomPreviewNormalTextColor(ContextCompat.getColor(context, R.color.ps_color_9b));
        numberBottomNavBarStyle.setBottomPreviewNormalTextSize(16);
        numberBottomNavBarStyle.setCompleteCountTips(false);
        numberBottomNavBarStyle.setBottomPreviewSelectText(context.getString(R.string.ps_preview_num));
        numberBottomNavBarStyle.setBottomPreviewSelectTextColor(ContextCompat.getColor(context, R.color.ps_color_white));

        pictureSelectorStyle.setTitleBarStyle(numberTitleBarStyle);
        pictureSelectorStyle.setBottomBarStyle(numberBottomNavBarStyle);
        pictureSelectorStyle.setSelectMainStyle(numberSelectMainStyle);
    }

    /**
     * qq??????
     *
     * @param context
     * @param pictureSelectorStyle
     */
    private static void setQqStyle(Context context, PictureSelectorStyle pictureSelectorStyle) {
        TitleBarStyle blueTitleBarStyle = new TitleBarStyle();
        blueTitleBarStyle.setTitleBackgroundColor(ContextCompat.getColor(context, R.color.ps_color_blue));

        BottomNavBarStyle numberBlueBottomNavBarStyle = new BottomNavBarStyle();
        numberBlueBottomNavBarStyle.setBottomPreviewNormalTextColor(ContextCompat.getColor(context, R.color.ps_color_9b));
        numberBlueBottomNavBarStyle.setBottomPreviewSelectTextColor(ContextCompat.getColor(context, R.color.ps_color_blue));
        numberBlueBottomNavBarStyle.setBottomNarBarBackgroundColor(ContextCompat.getColor(context, R.color.ps_color_white));
        numberBlueBottomNavBarStyle.setBottomSelectNumResources(R.drawable.picture_num_oval_blue);
        numberBlueBottomNavBarStyle.setBottomEditorTextColor(ContextCompat.getColor(context, R.color.ps_color_53575e));
        numberBlueBottomNavBarStyle.setBottomOriginalTextColor(ContextCompat.getColor(context, R.color.ps_color_53575e));

        SelectMainStyle numberBlueSelectMainStyle = new SelectMainStyle();
        numberBlueSelectMainStyle.setStatusBarColor(ContextCompat.getColor(context, R.color.ps_color_blue));
        numberBlueSelectMainStyle.setSelectNumberStyle(true);
        numberBlueSelectMainStyle.setPreviewSelectNumberStyle(true);
        numberBlueSelectMainStyle.setSelectBackground(R.drawable.picture_checkbox_num_selector);
        numberBlueSelectMainStyle.setMainListBackgroundColor(ContextCompat.getColor(context, R.color.ps_color_white));
        numberBlueSelectMainStyle.setSelectNormalTextColor(ContextCompat.getColor(context, R.color.ps_color_9b));
        numberBlueSelectMainStyle.setSelectTextColor(ContextCompat.getColor(context, R.color.ps_color_blue));
        numberBlueSelectMainStyle.setSelectText(context.getString(R.string.ps_completed));

        pictureSelectorStyle.setTitleBarStyle(blueTitleBarStyle);
        pictureSelectorStyle.setBottomBarStyle(numberBlueBottomNavBarStyle);
        pictureSelectorStyle.setSelectMainStyle(numberBlueSelectMainStyle);
    }

    /**
     * ????????????
     *
     * @param context
     * @param pictureSelectorStyle
     */
    private static void setWhiteStyle(Context context, PictureSelectorStyle pictureSelectorStyle) {
        TitleBarStyle whiteTitleBarStyle = new TitleBarStyle();
        whiteTitleBarStyle.setTitleBackgroundColor(ContextCompat.getColor(context, R.color.ps_color_white));
        whiteTitleBarStyle.setTitleDrawableRightResource(R.drawable.ic_orange_arrow_down);
        whiteTitleBarStyle.setTitleLeftBackResource(R.drawable.ps_ic_black_back);
        whiteTitleBarStyle.setTitleTextColor(ContextCompat.getColor(context, R.color.ps_color_black));
        whiteTitleBarStyle.setTitleCancelTextColor(ContextCompat.getColor(context, R.color.ps_color_53575e));
        whiteTitleBarStyle.setDisplayTitleBarLine(true);

        BottomNavBarStyle whiteBottomNavBarStyle = new BottomNavBarStyle();
        whiteBottomNavBarStyle.setBottomNarBarBackgroundColor(Color.parseColor("#EEEEEE"));
        whiteBottomNavBarStyle.setBottomPreviewSelectTextColor(ContextCompat.getColor(context, R.color.ps_color_53575e));

        whiteBottomNavBarStyle.setBottomPreviewNormalTextColor(ContextCompat.getColor(context, R.color.ps_color_9b));
        whiteBottomNavBarStyle.setBottomPreviewSelectTextColor(ContextCompat.getColor(context, R.color.ps_color_fa632d));
        whiteBottomNavBarStyle.setCompleteCountTips(false);
        whiteBottomNavBarStyle.setBottomEditorTextColor(ContextCompat.getColor(context, R.color.ps_color_53575e));
        whiteBottomNavBarStyle.setBottomOriginalTextColor(ContextCompat.getColor(context, R.color.ps_color_53575e));

        SelectMainStyle selectMainStyle = new SelectMainStyle();
        selectMainStyle.setStatusBarColor(ContextCompat.getColor(context, R.color.ps_color_white));
        selectMainStyle.setDarkStatusBarBlack(true);
        selectMainStyle.setSelectNormalTextColor(ContextCompat.getColor(context, R.color.ps_color_9b));
        selectMainStyle.setSelectTextColor(ContextCompat.getColor(context, R.color.ps_color_fa632d));
        selectMainStyle.setSelectText(context.getString(R.string.ps_done_front_num));
        selectMainStyle.setMainListBackgroundColor(ContextCompat.getColor(context, R.color.ps_color_white));

        pictureSelectorStyle.setTitleBarStyle(whiteTitleBarStyle);
        pictureSelectorStyle.setBottomBarStyle(whiteBottomNavBarStyle);
        pictureSelectorStyle.setSelectMainStyle(selectMainStyle);
    }

    // ????????????
    public static void takeMedia(Context context, int chooseMode, boolean customCamera, boolean crop, MeOnResultCallbackListener callbackListener) {
        PictureSelector.create(context)
                .openCamera(chooseMode)
                .setCameraInterceptListener(customCamera ? new MeOnCameraInterceptListener() : null)//???????????????
                .setCropEngine(crop ? new ImageCropEngine() : null)//??????
                .setCompressEngine(new ImageCompressEngine())//??????
                .setSandboxFileEngine(new MeSandboxFileEngine())//????????????
                .isOriginalControl(false)//??????
                .setOutputAudioDir(Util.getSandboxAudioOutputPath())
                //.setSelectedData(mAdapter.getData());// ??????????????????
                .setRecordVideoMaxSecond(10)
                .setRecordVideoMinSecond(2)
                .forResult(callbackListener);
    }

    // ??????????????????????????????
    public static void preview(Context context, int position, ArrayList<LocalMedia> data) {
        PictureSelector.create(context)
                .openPreview()
                .setImageEngine(GlideEngine.createGlideEngine())
                .setSelectorUIStyle(getStyle(context))
                .setLanguage(LanguageConfig.CHINESE)
                .isPreviewFullScreenMode(true)
                .isHidePreviewDownload(true)
                .setExternalPreviewEventListener(new OnExternalPreviewEventListener() {
                    @Override
                    public void onPreviewDelete(int position) {
                        data.remove(position);
                    }

                    @Override
                    public boolean onLongPressDownload(LocalMedia media) {
                        return false;
                    }
                })
                .startActivityPreview(position, false, data);
    }

    // ????????????
    public static void getMedia(Context context, int chooseMode, boolean customCamera, boolean crop, int maxNum, MeOnResultCallbackListener callbackListener) {
        PictureSelector.create(context)
                .openGallery(chooseMode)
                .setSelectorUIStyle(getStyle(context))//UI
                .setImageEngine(GlideEngine.createGlideEngine())//glide
                .setCropEngine(crop ? new ImageCropEngine() : null)//??????
                .setCompressEngine(new ImageCompressEngine())//??????
                .setSandboxFileEngine(new MeSandboxFileEngine())//?????????????????????
                .setCameraInterceptListener(customCamera ? new MeOnCameraInterceptListener() : null)//???????????????
                .setSelectLimitTipsListener(new MeOnSelectLimitTipsListener())//????????????
                .setEditMediaInterceptListener(new MeOnMediaEditInterceptListener())//???????????????
                //.setExtendLoaderEngine(getExtendLoaderEngine())
                //.setInjectLayoutResourceListener(new MeOnInjectLayoutResourceListener())//??????view
                .setSelectionMode(maxNum > 1 ? SelectModeConfig.MULTIPLE : SelectModeConfig.SINGLE)
                .setLanguage(LanguageConfig.CHINESE)
                .setOutputCameraDir(chooseMode == SelectMimeType.ofAudio() ? Util.getSandboxAudioOutputPath() : Util.getSandboxCameraOutputPath())
                .setOutputAudioDir(chooseMode == SelectMimeType.ofAudio() ? Util.getSandboxAudioOutputPath() : Util.getSandboxCameraOutputPath())
                .setQuerySandboxDir(chooseMode == SelectMimeType.ofAudio() ? Util.getSandboxAudioOutputPath() : Util.getSandboxCameraOutputPath())
                .isDisplayTimeAxis(true)//?????????????????????
                .isOnlyObtainSandboxDir(false)//??????????????????
                .isPageStrategy(true)//????????????
                .isOriginalControl(false)//????????????????????????
                .isDisplayCamera(true)//????????????
                .isOpenClickSound(true)//true
                .isFastSlidingSelect(true)//????????????
                //.setOutputCameraImageFileName("luck.jpeg")
                //.setOutputCameraVideoFileName("luck.mp4")
                .isWithSelectVideoImage(true)//??????????????????
                .isPreviewFullScreenMode(true)//????????????(??????)
                .isPreviewZoomEffect(true)//??????????????????
                .isPreviewImage(true)//??????????????????
                .isPreviewVideo(true)//??????????????????
                .isPreviewAudio(true)//??????????????????
                //.setQueryOnlyMimeType(PictureMimeType.ofGIF())
                .isMaxSelectEnabledMask(true)//??????????????????(????????????????????????)
                .isDirectReturnSingle(true)//????????????????????????
                .setMaxSelectNum(maxNum)
                .setRecyclerAnimationMode(getAnimRecyclerView())
                .isGif(false)//??????gif
                .isWebp(false)//??????Webp
                .isBmp(false)//??????Bmp
                .isEmptyResultReturn(true)//?????????????????????
                //.setSelectedData(mAdapter.getData());
                //forSelectResult(selectionModel);
                //.setVideoQuality(1)//0??????,1??????
                .setFilterMaxFileSize(chooseMode == SelectMimeType.ofVideo() ? 100 * 1024 : 5 * 1024)//??????????????????(KB),??????100MB,??????5MB
                .forResult(callbackListener);
    }

    /**
     * ???????????????????????????,???????????????????????????.
     *
     * @param context
     */
    public static void clearCache(Context context) {
        //?????????
        //??????????????????????????????????????????????????????????????????type ???????????????or?????????????????????????????????ofImage???ofVideo ?????????????????????sd?????????
        //PictureFileUtils.deleteCacheDirFile(context,type);
        // ?????????????????? ??????????????????????????????????????????????????????????????????
        //PictureFileUtils.deleteAllCacheDirFile(context);

        //????????????
        PictureCacheManager.deleteAllCacheDirFile(context);
    }
}
