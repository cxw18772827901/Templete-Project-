package com.lib.base.mvp;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * RxPresenter
 * 1、绑定、解绑view；
 * 2、可以将single{@link Single}添加到Disposable{@link CompositeDisposable}中，以便后续进行取消操作；
 * 3、感知生命周期.
 * <p>
 * Author       chenxiaowu
 * * Date on 2019/11/28.
 */

public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T>, DefaultLifecycleObserver {

    protected T mView;
    private Lifecycle.Event currentState = Lifecycle.Event.ON_CREATE;

    private RxPresenter() {
    }

    public RxPresenter(@NonNull Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    /**
     * 感知生命周期,Lifecycle可见
     *
     * @return
     */
    protected boolean isLifecycleResume() {
        return mView != null && currentState != null && currentState == Lifecycle.Event.ON_RESUME;
    }

    /**
     * 感知生命周期,Lifecycle存活
     *
     * @return
     */
    protected boolean isLifecycleSurvive() {
        return mView != null && currentState != null && currentState != Lifecycle.Event.ON_DESTROY;
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    protected CompositeDisposable mDisposable;

    protected void addDisposable(Disposable subscription) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(subscription);
    }

    @Override
    public void detachView() {
        unSubscribe();
        this.mView = null;
    }

    protected void unSubscribe() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    /**
     * 使用DefaultLifecycleObserver
     *
     * @param owner
     */
    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        currentState = Lifecycle.Event.ON_CREATE;
    }

    /**
     * 使用DefaultLifecycleObserver
     *
     * @param owner
     */
    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        currentState = Lifecycle.Event.ON_START;
    }

    /**
     * 使用DefaultLifecycleObserver
     *
     * @param owner
     */
    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        currentState = Lifecycle.Event.ON_RESUME;
    }

    /**
     * 使用DefaultLifecycleObserver
     *
     * @param owner
     */
    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        currentState = Lifecycle.Event.ON_PAUSE;
    }

    /**
     * 使用DefaultLifecycleObserver
     *
     * @param owner
     */
    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        currentState = Lifecycle.Event.ON_STOP;
    }

    /**
     * 使用DefaultLifecycleObserver
     *
     * @param owner
     */
    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        currentState = Lifecycle.Event.ON_DESTROY;
    }
}
