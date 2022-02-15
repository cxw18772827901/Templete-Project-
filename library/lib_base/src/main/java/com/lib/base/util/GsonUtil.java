package com.lib.base.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gson.factory.GsonFactory;

/**
 * Created by Dave on 2020/6/8.
 */
public class GsonUtil {
    private static volatile Gson sInstance;

    /**
     * 常规gson
     *
     * @return
     */
    public static Gson getCommonInstance() {
        if (sInstance == null) {
            synchronized (GsonUtil.class.getName()) {
                if (sInstance == null) {
                    sInstance = new GsonBuilder().serializeNulls().create();
                }
            }
        }
        return sInstance;
    }

    /**
     * 解析容错gson
     *
     * @return
     */
    public static Gson getNoCrashInstance() {
        return GsonFactory.getSingletonGson();
    }

    /**
     * 默认解析容错gson
     *
     * @return
     */
    public static Gson getInstance() {
        return getNoCrashInstance();
    }
}
