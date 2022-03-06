package com.lib.base.http;


import com.lib.base.config.AppConfig;
import com.lib.base.util.DebugUtil;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ok拦截器:项目中多域名切换,一个host对应一个url
 * PackageName  com.spot.jtt.util
 * ProjectName  Spot1-4
 *@author      xwchen
 * Date         1/30/21.
 */

public class UrlManagerInterceptor implements Interceptor {
    public static final String TAG = "UrlManagerInterceptor";

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl oldHttpUrl = request.url();
        Request.Builder builder = request.newBuilder();
        List<String> headerValues = request.headers(AppConfig.HOST);
        if (headerValues.size() > 0) {
            builder.removeHeader(AppConfig.HOST);
            String headerValue = headerValues.get(0);
            DebugUtil.logD(TAG, "headerValue=" + headerValue);
            HttpUrl newBaseUrl;
            if (AppConfig.HOST1.contains(headerValue)) {
                newBaseUrl = HttpUrl.parse(AppConfig.API_BASE_URL_FINAL1);
            } else if (AppConfig.HOST2.contains(headerValue)) {
                newBaseUrl = HttpUrl.parse(AppConfig.API_BASE_URL_FINAL2);
            } else {
                newBaseUrl = oldHttpUrl;
            }
            if (newBaseUrl == null) {
                return chain.proceed(request);
            }
            HttpUrl newFullUrl = oldHttpUrl.newBuilder()
                    .scheme(newBaseUrl.scheme())//更换网络协议
                    .host(newBaseUrl.host())//更换主机名
                    .port(newBaseUrl.port())//更换端口
                    .build();
            DebugUtil.logD(TAG, "intercept: " + newFullUrl);
            return chain.proceed(builder.url(newFullUrl).build());
        }
        return chain.proceed(request);
    }
}
