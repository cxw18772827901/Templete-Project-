package com.lib.base.http;

import com.lib.base.config.AppConfig;
import com.lib.base.util.DebugUtil;
import com.lib.base.util.OUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * 处理cookie
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.http
 *@author      xwchen
 * Date         2022/2/22.
 */

public class OkCookieJar implements CookieJar {
    public static final String TAG = "OkCookieJar";
    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    /**
     * 1.只保存指定path的cookie{@link com.lib.base.config.AppConfig#COOKIE_URL_PATH},防止被其他coookie覆盖;
     * 2.使用host关联,后续该host都是用该cookie.
     *
     * @param httpUrl
     * @param cookies
     */
    @Override
    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> cookies) {
        String host = httpUrl.url().getHost();
        String cookiePath = AppConfig.COOKIE_URL_PATH;
        String currPath = httpUrl.url().getPath();
        if (OUtil.isNotNull(host) && OUtil.isNotNull(cookiePath) && OUtil.isNotNull(currPath) && cookiePath.equals(currPath)) {
            cookieStore.put(host, cookies);
        }
        DebugUtil.logD(TAG, "cookies =" + cookies);
    }

    /**
     * 取出域名关联的cookie
     *
     * @param httpUrl
     * @return
     */
    @NonNull
    @Override
    public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
        String host = httpUrl.url().getHost();
        List<Cookie> cookies = null;
        if (OUtil.isNotNull(host) && cookieStore.containsKey(host)) {
            cookies = cookieStore.get(host);
        }
        return cookies != null ? cookies : new ArrayList<>();
    }
}
