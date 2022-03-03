package com.lib.base.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/**
 * PackageName  com.lib.base.util
 * ProjectName  TempleteProject-java
 * Date         2022/2/15.
 *
 * @author chenxiaowu
 */

public class ContextUtil {
    /**
     * 获取LifecycleOwner,以作生命周期感知,可能为空
     *
     * @param context
     * @return
     */
    public static LifecycleOwner getLifecycleOwnerByContext(Context context) {
        do {
            if (context instanceof LifecycleOwner) {
                return (LifecycleOwner) context;
            } else if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            } else {
                return null;
            }
        } while (context != null);
        return null;
    }

    /**
     * 根据context获取Activity,可能为空
     *
     * @param context
     * @return
     */
    public static Activity getActivityByContext(Context context) {
        do {
            if (context instanceof Activity) {
                return (Activity) context;
            } else if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            } else {
                return null;
            }
        } while (context != null);
        return null;
    }

    /**
     * Activity 是否resume
     *
     * @param activity
     * @return
     */
    public static boolean isActivityResume(Activity activity) {
        if (!(activity instanceof LifecycleOwner)) {
            return false;
        }
        return isLifecycleResume((LifecycleOwner) activity);
    }

    public static boolean isLifecycleResume(@NonNull LifecycleOwner lifecycleOwner) {
        return lifecycleOwner.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED;
    }

    /**
     * activity 没有destroy
     *
     * @param activity
     * @return
     */
    public static boolean isActivitySurvive(Activity activity) {
        if (!(activity instanceof LifecycleOwner)) {
            return false;
        }
        return isLifecycleSurvive((LifecycleOwner) activity);
    }

    public static boolean isLifecycleSurvive(@NonNull LifecycleOwner lifecycleOwner) {
        return lifecycleOwner.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED;
    }
}
