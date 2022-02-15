package com.lib.base.ui.action;


import com.lib.base.config.App;
import com.lib.base.util.DebugUtil;

import androidx.annotation.StringRes;

/**
 * 日志工具类
 * author : chenxiaowu
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2019/12/08
 * desc   : 吐司意图
 */
public interface LogAction {

    default void logD(String tag, CharSequence text) {
        DebugUtil.logD(tag, text.toString());
    }

    default void logE(String tag, @StringRes int id) {
        DebugUtil.logE(tag, App.getContext().getResources().getString(id));
    }

}