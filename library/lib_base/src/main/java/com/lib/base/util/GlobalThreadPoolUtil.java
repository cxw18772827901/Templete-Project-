package com.lib.base.util;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @class 全局公用线程池
 * @anthor xianggao6
 * @time 2020/6/11
 */
public class GlobalThreadPoolUtil {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 线程池核心线程数
     */
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    /**
     * 线程池最大线程数为核心线程数+2
     */
    private static final int MAX_POOL_SIZE = CPU_COUNT + 2;
    /**
     * 额外线程空状态生存时间,不允许
     */
    private static final int KEEP_ALIVE_TIME = 0;
    /**
     * 阻塞队列。当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程。
     */
    private static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(20);

    /**
     * 线程工厂
     */
    private static final ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "Dispatcher thread:" + integer.getAndIncrement());
        }
    };

    private static final ThreadPoolExecutor sExecutor;

    private static final Handler mHandler = new Handler(Looper.getMainLooper());

    /*
      拒绝策略
      AbortPolicy:抛出RejectedExecutionException;
      DiscardPolicy:什么也不做，直接忽略;
      DiscardOldestPolicy:丢弃执行队列中最老的任务，尝试为当前提交的任务腾出位置;
      CallerRunsPolicy:直接由提交任务者执行这个任务
     */
    static {
        sExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                workQueue, threadFactory,
                new ThreadPoolExecutor.DiscardPolicy());
        sExecutor.allowCoreThreadTimeOut(true);//不设置,即使核心线程闲置也会一直保持
    }

    public static void postOnUiThread(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static void postOnUiThreadDelay(Runnable runnable, long delay) {
        mHandler.postDelayed(runnable, delay);
    }

    public static void postOnSubThread(Runnable runnable) {
        sExecutor.execute(runnable);
    }

}
