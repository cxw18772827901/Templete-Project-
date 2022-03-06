package com.lib.base.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * PackageName  com.tiyu.zjwt.util
 * ProjectName  Spot1-2
 *@author      xwchen
 * Date         1/24/21.
 */

public class JsonDataUtil {
    public static final String TAG = "JsonDataUtil";

    public static String getJson(Context context, String fileName) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            AssetManager assetManager = context.getAssets();
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(fileName), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while (!TextUtils.isEmpty(line = bufferedReader.readLine())) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            String string = stringBuilder.toString().trim();
            DebugUtil.logD(TAG, "getJson=" + string);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            DebugUtil.logD(TAG, "error msg=" + e.getMessage());
            return null;
        }
    }
}
