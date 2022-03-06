package com.lib.base.config;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * ProjectName  XSCat
 * PackageName  com.bigheadhorse.xscat.config
 *@author      xwchen
 * Date         2021/6/15.
 */

public class ActivityLifecycleCallback implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        createActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        resumeActivity(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        pauseActivity(activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        destroyActivity(activity);
    }

    public void createActivity(Activity activity) {

    }

    public void resumeActivity(Activity activity) {

    }

    public void pauseActivity(Activity activity) {

    }

    public void destroyActivity(Activity activity) {

    }
}
