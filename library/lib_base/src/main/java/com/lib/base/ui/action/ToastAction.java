package com.lib.base.ui.action;


import com.lib.base.config.App;
import com.lib.base.util.DebugUtil;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

/**
 * toast工具类
 * <p>
 *
 * @author: xwchen
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2019/12/08
 * desc   : 吐司意图
 */
public interface ToastAction /*extends ContextAction*/ {
    /**
     * debug toast CharSequence
     *
     * @param text
     */
    default void debugToast(@NonNull CharSequence text) {
        DebugUtil.debugToast(text.toString());
    }

    /**
     * toast CharSequence
     *
     * @param text
     */
    default void toast(@NonNull CharSequence text) {
        DebugUtil.toast(text.toString());
    }

    /**
     * toast res id
     *
     * @param id
     */
    default void toast(@StringRes int id) {
        DebugUtil.toast(App.getContext().getResources().getString(id));
    }

    /**
     * toast object
     *
     * @param object
     */
    default void toast(@NonNull Object object) {
        DebugUtil.toast(object.toString());
    }

}