package com.lib.base.bean;

/**
 * mvvm回调基础数据类型:
 * 1.定义此基础回调数据类型目的是防止每个viewmodel中一直频繁书写基础回调数据类型和大量的各种int变量.
 * 2.但是此种定义方式背离了代码可阅读性,使用时注意避免增加代码的阅读难度,比如发起操作时传入int变量值
 * xxxViewModel.request(TAG_1,xxx),不要直接xxxViewModel.request(xxx).
 * 3.这样发起操作时TAG_1便可以和LiveData回调的TAG_1对应,小伙伴不至于看到只有回调的TAG_1一脸懵逼,
 * 然后到处找哪里发起的操作.
 * mViewModel.getBaseDataLiveData().observe(this, baseData -> {
 * switch (baseData.type) {
 * case BaseData.TAG_1:
 * break;
 * default:
 * break;
 * }
 * });
 *
 * <p>
 * ProjectName  XSMNewProject
 * PackageName  com.datouma.newproject.model.bean
 *@author      xwchen
 * Date         2021/9/2.
 */

public class BaseData {
    public static final int TAG_1 = 1;
    public static final int TAG_2 = 2;
    public static final int TAG_3 = 3;
    public static final int TAG_4 = 4;
    public static final int TAG_5 = 5;
    public static final int TAG_6 = 6;
    public static final int TAG_7 = 7;
    public static final int TAG_8 = 8;
    public static final int TAG_9 = 9;
    public static final int TAG_10 = 10;
    public static final int TAG_11 = 11;
    public static final int TAG_12 = 12;
    public static final int TAG_13 = 13;
    public static final int TAG_14 = 14;
    public static final int TAG_15 = 15;
    public static final int TAG_16 = 16;
    public static final int TAG_17 = 17;
    public static final int TAG_18 = 18;
    public static final int TAG_19 = 19;
    public static final int TAG_20 = 20;
    public static final int TAG_21 = 21;
    public static final int TAG_22 = 22;
    public static final int TAG_23 = 23;
    public static final int TAG_24 = 24;
    public static final int TAG_25 = 25;
    public static final int TAG_26 = 26;
    public static final int TAG_27 = 27;
    public static final int TAG_28 = 28;
    public static final int TAG_29 = 29;
    public static final int TAG_30 = 30;

    public int type;
    public String message;
    public Object obj;

    public BaseData() {
    }

    public BaseData(int type) {
        this.type = type;
    }

    public BaseData(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public BaseData(int type, Object obj) {
        this.type = type;
        this.obj = obj;
    }

    public BaseData(int type, String message, Object obj) {
        this.type = type;
        this.message = message;
        this.obj = obj;
    }
}
