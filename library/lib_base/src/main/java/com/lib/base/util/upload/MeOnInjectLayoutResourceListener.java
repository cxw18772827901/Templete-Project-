package com.lib.base.util.upload;

import android.content.Context;

import com.luck.picture.lib.interfaces.OnInjectLayoutResourceListener;

/**
 * 注入自定义布局UI，前提是布局View id 和 根目录Layout必须一致
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.util.upload
 * @author      xwchen
 * Date         2022/2/9.
 */

class MeOnInjectLayoutResourceListener implements OnInjectLayoutResourceListener {

    @Override
    public int getLayoutResourceId(Context context, int resourceSource) {
      /*switch (resourceSource) {
         case InjectResourceSource.MAIN_SELECTOR_LAYOUT_RESOURCE:
            return R.layout.ps_custom_fragment_selector;
         case InjectResourceSource.PREVIEW_LAYOUT_RESOURCE:
            return R.layout.ps_custom_fragment_preview;
         case InjectResourceSource.MAIN_ITEM_IMAGE_LAYOUT_RESOURCE:
            return R.layout.ps_custom_item_grid_image;
         case InjectResourceSource.MAIN_ITEM_VIDEO_LAYOUT_RESOURCE:
            return R.layout.ps_custom_item_grid_video;
         case InjectResourceSource.MAIN_ITEM_AUDIO_LAYOUT_RESOURCE:
            return R.layout.ps_custom_item_grid_audio;
         case InjectResourceSource.ALBUM_ITEM_LAYOUT_RESOURCE:
            return R.layout.ps_custom_album_folder_item;
         case InjectResourceSource.PREVIEW_ITEM_IMAGE_LAYOUT_RESOURCE:
            return R.layout.ps_custom_preview_image;
         case InjectResourceSource.PREVIEW_ITEM_VIDEO_LAYOUT_RESOURCE:
            return R.layout.ps_custom_preview_video;
         case InjectResourceSource.PREVIEW_GALLERY_ITEM_LAYOUT_RESOURCE:
            return R.layout.ps_custom_preview_gallery_item;
         default:
            return 0;
      }*/
        return 0;
    }
}
