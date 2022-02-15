package com.lib.base.util.intent;

import android.os.Parcelable;

import java.util.ArrayList;

/**
 * PackageName  com.lib.base.util.intent
 * ProjectName  TempleteProject-java
 * Date         2022/2/11.
 *
 * @author chenxiaowu
 */

public class IntentData {
    public static final String TYPE_INT = "int";
    public static final String TYPE_LONG = "long";
    public static final String TYPE_FLOAT = "float";
    public static final String TYPE_DOUBLE = "double";
    public static final String TYPE_STRING = "string";
    public static final String TYPE_LIST_INT = "list_int";
    public static final String TYPE_LIST_STRING = "list_string";
    public static final String TYPE_PARCELABLE = "parcelable";
    public static final String TYPE_LIST_PARCELABLE = "list_parcelable";
    public static final String TYPE_ARR_INT = "arr_int";
    public static final String TYPE_ARR_LONG = "arr_long";
    public static final String TYPE_ARR_FLOAT = "arr_float";
    public static final String TYPE_ARR_DOUBLE = "arr_double";

    public String key;
    public String valueType;
    public int value1;
    public long value2;
    public float value3;
    public double value4;
    public String value5;
    public ArrayList<Integer> value6;
    public ArrayList<String> value7;
    public Parcelable value8;
    public ArrayList<? extends Parcelable> value9;
    public int[] value10;
    public long[] value11;
    public float[] value12;
    public double[] value13;

    private IntentData() {
    }

    public static IntentData put(String key, String value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value5 = value;
        intentData.valueType = TYPE_STRING;
        return intentData;
    }

    public static IntentData put(String key, int value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value1 = value;
        intentData.valueType = TYPE_INT;
        return intentData;
    }

    public static IntentData put(String key, long value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value2 = value;
        intentData.valueType = TYPE_LONG;
        return intentData;
    }

    public static IntentData put(String key, float value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value3 = value;
        intentData.valueType = TYPE_FLOAT;
        return intentData;
    }

    public static IntentData put(String key, double value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value4 = value;
        intentData.valueType = TYPE_DOUBLE;
        return intentData;
    }

    public static IntentData putListInt(String key, ArrayList<Integer> value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value6 = value;
        intentData.valueType = TYPE_LIST_INT;
        return intentData;
    }

    public static IntentData putListStr(String key, ArrayList<String> value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value7 = value;
        intentData.valueType = TYPE_LIST_STRING;
        return intentData;
    }

    public static IntentData put(String key, Parcelable value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value8 = value;
        intentData.valueType = TYPE_PARCELABLE;
        return intentData;
    }

    public static IntentData putListPar(String key, ArrayList<? extends Parcelable> value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value9 = value;
        intentData.valueType = TYPE_LIST_PARCELABLE;
        return intentData;
    }

    public static IntentData put(String key, int[] value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value10 = value;
        intentData.valueType = TYPE_ARR_INT;
        return intentData;
    }

    public static IntentData put(String key, long[] value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value11 = value;
        intentData.valueType = TYPE_ARR_LONG;
        return intentData;
    }

    public static IntentData put(String key, float[] value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value12 = value;
        intentData.valueType = TYPE_ARR_FLOAT;
        return intentData;
    }

    public static IntentData put(String key, double[] value) {
        IntentData intentData = new IntentData();
        intentData.key = key;
        intentData.value13 = value;
        intentData.valueType = TYPE_ARR_DOUBLE;
        return intentData;
    }
}
