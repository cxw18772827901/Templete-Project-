package com.lib.base.rxjava;

import android.view.View;

import com.jakewharton.rxbinding4.view.RxView;
import com.lib.base.util.DebugUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * rxjava和rxview工具类
 * Created by chenxiaowu on 19-4-29.
 */

public class RxUtils {
    public static final String TAG = "RxUtils";

    public static <T> SingleSource<T> toSimpleSingle(Single<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> SingleSource<T> toSimpleSingleIo(Single<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    public static <T> ObservableSource<T> toSimpleSingle(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Flowable<T> toSimpleSingle(Flowable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 连续点击次数大于atLeastCount次才触发
     *
     * @param view
     * @param atLeastCount
     * @param listener
     */
    public static void setClickCountAtLeast(View view, int atLeastCount, RxClickListener listener) {
//        Observable observable = RxView.clicks(view).share();
//        observable
//                .buffer(observable.debounce(300, TimeUnit.MILLISECONDS))
//                .map((Function<List, Integer>) List::size)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext((Consumer<Integer>) size -> {
//                    Log.d(TAG, "size=" + size);
//                    if (size >= atLeastCount && listener != null) {
//                        listener.click(view);
//                    }
//                })
//                .subscribe();

        //另一种写法
        RxView
                .clicks(view)
                .share()
                .compose(RxUtils::buffer)
                .map(List::size)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(size -> {
                    DebugUtil.logD(TAG, "size=" + size);
                    if (size >= atLeastCount && listener != null) {
                        listener.click(view);
                    }
                })
                .subscribe();
    }

    private static Observable<List> buffer(Observable upstream) {
        return upstream.buffer(upstream.debounce(300, TimeUnit.MILLISECONDS));
    }

    /**
     * 防止view连续点击，2.5s内仅第一次会触发,安卓连击的解决方案之一
     *
     * @param view
     * @param listener
     */
    public static void throwFirstClick(View view, RxClickListener listener) {
        RxView
                .clicks(view)
                .throttleFirst(2500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(o -> {
                    if (listener != null) {
                        listener.click(view);
                    }
                })
                .subscribe();
    }

    /**
     * 连续点击结束后才触发,安卓连击的解决方案之一
     *
     * @param view
     * @param listener
     */
    public static void throwEndClick(View view, RxClickListener listener) {
        RxView
                .clicks(view)
                .share()
                .compose(RxUtils::buffer)
                .map(List::size)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(size -> {
                    DebugUtil.logD(TAG, "size=" + size);
                    if (listener != null) {
                        listener.click(view);
                    }
                })
                .subscribe();
    }

    public interface RxClickListener {
        void click(View view);
    }

}
