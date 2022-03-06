package com.lib.base.ui.action;


import com.lib.base.util.DebugUtil;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

/**
 * toast工具类
 * <p>
 * @author: xwchen
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2019/12/08
 * desc   : 吐司意图
 */
public interface ToastAction extends ContextAction {

    default void debugToast(@NonNull CharSequence text) {
        DebugUtil.debugToast(text.toString());
    }

    default void toast(@NonNull CharSequence text) {
        DebugUtil.toast(text.toString());
    }

    default void toast(@StringRes int id) {
        DebugUtil.toast(getContexts().getResources().getString(id));
    }

    default void toast(@NonNull Object object) {
        DebugUtil.toast(object.toString());
    }

}