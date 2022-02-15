package com.lib.base.util;

import android.text.TextUtils;

import java.util.Objects;

/**
 * ProjectName  XSCat
 * PackageName  com.bigheadhorse.xscat.utils
 * Date         2021/6/17.
 *
 * @author Administrator
 */

public class OUtil {
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    public static boolean isNotNull(Object obj) {
        return null != obj;
    }

    public static boolean isNull(String s) {
        return TextUtils.isEmpty(s);
    }

    public static boolean isNotNull(String s) {
        return !TextUtils.isEmpty(s);
    }

    public static boolean isNotNull(Object... obj) {
        boolean hasNoNull = true;
        for (Object o : obj) {
            if (isNull(o)) {
                hasNoNull = false;
                break;
            } else if (o instanceof String) {
                if (isNull((String) o)) {
                    hasNoNull = false;
                    break;
                }
            }
        }
        return hasNoNull;
    }

    public static <T> T nonNull(T obj) {
        return Objects.requireNonNull(obj);
    }
}
