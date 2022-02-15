package com.lib.base.mvvm;

import android.os.Looper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * 去粘性事件,同时做线程判断.
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.mvvm
 * Author       Administrator
 * Date         2022/1/26.
 */

public class LiveData<T> extends MutableLiveData<T> {

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);
        hook(observer);
    }

    private void hook(Observer<? super T> observer) {
        try {
            //解决粘性的关键就是mLastVersion = mVersion
            Class<?> liveDataClass = androidx.lifecycle.LiveData.class;
            //先找到保存LifecycleBoundObserver的map
            Field mObserversField = liveDataClass.getDeclaredField("mObservers");
            mObserversField.setAccessible(true);
            //Field.get(Field所在的类的实例)方法获取map的实例 反射基础
            Object mObserversObj = mObserversField.get(LiveData.this);

            assert mObserversObj != null;
            Class<?> mObserversClass = mObserversObj.getClass();
            //获取get方法信息
            Method mapMethodGet = mObserversClass.getDeclaredMethod("get", Object.class);
            mapMethodGet.setAccessible(true);
            //mObservers就是以 observer为key以  Map.Entry<Observer<? super T>, ObserverWrapper>为value的
            //通过mObserver.get(observer)获取Map.Entry<Observer<? super T>, ObserverWrapper>的实例
            Object obj = mapMethodGet.invoke(mObserversObj, observer);
            //获取Entry的class
            assert obj != null;
            Class<?> observerWrapperClass = obj.getClass();
            //获取Entry的getValue方法的信息
            Method getValue = observerWrapperClass.getDeclaredMethod("getValue");
            getValue.setAccessible(true);

            //执行Entry实例的getValue方法获取LifecycleBoundObserver实例
            Object wrapper = getValue.invoke(obj);
            assert wrapper != null;
            Class<?> lifecycleBoundObserverClass = wrapper.getClass();
            //因为mLastVersion在父类中，子类的class不包含父类的信息 泛型基础,所以需要得到父类的class信息
            Class<?> wrapperClass = lifecycleBoundObserverClass.getSuperclass();
            assert wrapperClass != null;
            Field mLastVersionField = wrapperClass.getDeclaredField("mLastVersion");
            mLastVersionField.setAccessible(true);
            //下面是取LiveData中维护的mVersion的值
            Field mVersionField = liveDataClass.getDeclaredField("mVersion");
            mVersionField.setAccessible(true);
            int mVersion = mVersionField.getInt(LiveData.this);
            //修改mLastVersion中的值
            mLastVersionField.set(wrapper, mVersion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.注意不要使用Looper.myLooper() == Looper.getMainLooper()判断主线程,
     * 因为Looper.myLooper()前提是当前线程关联了looper(),即Looper.prepare(),否则会报错;
     * 2.setValue()只能用在主线程;
     * 3.postValue()可以用到任何线程;
     * 4.一个liveData先postValue(),再调用setValue(),setValue()会被postValue()覆盖;
     * 5.多个postValue(),只有最后一个会触发.
     *
     * @param value
     */
    @Override
    public void setValue(T value) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            //主线程
            super.setValue(value);
        } else {
            //子线程
            super.postValue(value);
        }
    }
}