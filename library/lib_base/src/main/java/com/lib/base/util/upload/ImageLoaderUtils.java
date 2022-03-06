package com.lib.base.util.upload;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.util.upload
 *@author      xwchen
 * Date         2022/2/9.
 */

class ImageLoaderUtils {
    public static boolean assertValidRequest(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            return !isDestroy(activity);
        } else if (context instanceof ContextWrapper) {
            ContextWrapper contextWrapper = (ContextWrapper) context;
            if (contextWrapper.getBaseContext() instanceof Activity) {
                Activity activity = (Activity) contextWrapper.getBaseContext();
                return !isDestroy(activity);
            }
        }
        return true;
    }

    private static boolean isDestroy(Activity activity) {
        if (activity == null) {
            return true;
        }
        return activity.isFinishing() || activity.isDestroyed();
    }

}
