package com.lib.base.ui.action;


import com.lib.base.config.App;
import com.lib.base.util.DebugUtil;

import androidx.annotation.StringRes;

/**
 * 日志
 *
 * @author: xwchen
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2019/12/08
 * desc   : 吐司意图
 */
public interface LogAction {

    default void logD(String tag, CharSequence text) {
        DebugUtil.logD(tag, text.toString());
    }

    default void logD(String tag, @StringRes int id) {
        DebugUtil.logD(tag, App.getContext().getResources().getString(id));
    }

    default void logE(String tag, CharSequence text) {
        DebugUtil.logE(tag, text.toString());
    }

    default void logE(String tag, @StringRes int id) {
        DebugUtil.logE(tag, App.getContext().getResources().getString(id));
    }

}