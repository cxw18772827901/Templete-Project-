package com.lib.base.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.greendao.db.util.GsonUtil;
import com.lib.base.config.App;
import com.lib.base.config.AppConfig;


/**
 * 首选项存储工具类
 * Created by Dave on 15/10/25.
 */
public class SPUtil {

    private static SharedPreferences sharedPreferences;

    private static SharedPreferences getSharedPreference() {
        if (sharedPreferences == null) {
            sharedPreferences = App.getContext().getSharedPreferences(AppConfig.SP_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static String getString(String key) {
        return getSharedPreference().getString(key, "");
    }

    public static String getString(String key, String def) {
        return getSharedPreference().getString(key, def);
    }

    public static int getInt(String key) {
        return getSharedPreference().getInt(key, 0);
    }

    public static int getInt(String key, int def) {
        return getSharedPreference().getInt(key, def);
    }

    public static boolean getBoolean(String key) {
        return getSharedPreference().getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean def) {
        return getSharedPreference().getBoolean(key, def);
    }

    public static long getLong(String key) {
        return getSharedPreference().getLong(key, 0);
    }

    public static long getLong(String key, long def) {
        return getSharedPreference().getLong(key, def);
    }

    public static void put(String key, Object value) {
        if (value == null) {
            throw new RuntimeException("cannot put a null object into sp!");
        }
        Editor editor = getSharedPreference().edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (int) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (boolean) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (long) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (float) value);
        } else {
            //editor.putString(key, value.toString());
            editor.putString(key, GsonUtil.getInstance().toJson(value));
        }
        editor.apply();
    }

    public static void clear() {
        Editor edit = getSharedPreference().edit();
        edit.clear();
        edit.apply();
    }
}
