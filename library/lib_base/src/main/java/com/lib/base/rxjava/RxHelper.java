package com.lib.base.rxjava;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 官方建议不再使用注解方式回到生命周期
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.rxjava
 *@author      xwchen
 * Date         2022/2/14.
 */

public class RxHelper implements /*LifecycleEventObserver*/DefaultLifecycleObserver {

    private CompositeDisposable compositeDisposable;

    private RxHelper() {
    }

    public RxHelper(@NonNull Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }


    public void addDisposable(Disposable subscription) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(subscription);
    }

    /**
     * 使用LifecycleEventObserver
     */
    /*@Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {

    }*/

    /**
     * 使用DefaultLifecycleObserver
     *
     * @param owner
     */
    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }
}
