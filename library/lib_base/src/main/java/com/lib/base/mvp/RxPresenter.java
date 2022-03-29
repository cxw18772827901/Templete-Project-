package com.lib.base.mvp;

import com.lib.base.ui.action.LogAction;
import com.lib.base.ui.action.ToastAction;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * RxPresenter
 * 1、绑定、解绑view；
 * 2、可以将RxJava任务添加到Disposable{@link CompositeDisposable}中，以便后续进行取消操作；
 * 3、感知生命周期.
 *
 * @author xwchen
 * * Date on 2019/11/28.
 */

public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T>, DefaultLifecycleObserver, LogAction, ToastAction {

    /**
     * mvp回调接口view,
     * 使用前必须判断{@link RxPresenter#isLifecycleSurvive()}生命周期是否存活.
     * 注意:使用WeakReference,进一步降低内存泄漏的可能性.
     */
    //protected T mView;
    protected Reference<T> mView;
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
        return mView != null && mView.get() != null && currentState != null && currentState == Lifecycle.Event.ON_RESUME;
    }

    /**
     * 感知生命周期,Lifecycle存活
     *
     * @return
     */
    protected boolean isLifecycleSurvive() {
        return mView != null && mView.get() != null && currentState != null && currentState != Lifecycle.Event.ON_DESTROY;
    }

    @Override
    public void attachView(T view) {
        //this.mView = view;
        this.mView = new WeakReference<>(view);
    }

    protected CompositeDisposable mDisposable;

    protected void addDisposable(Disposable subscription) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(subscription);
    }

    /**
     * 解绑时取消rxjava
     */
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
