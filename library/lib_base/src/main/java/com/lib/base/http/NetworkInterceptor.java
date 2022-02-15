package com.lib.base.http;

import android.text.TextUtils;

import com.lib.base.config.App;
import com.lib.base.config.AppConfig;
import com.lib.base.util.AppUtil;
import com.lib.base.util.LoginUtil;
import com.lib.base.util.SPUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
/* 常见的媒体格式类型如下：

    text/html ： HTML格式
    text/plain ：纯文本格式
    text/xml ：  XML格式
    image/gif ：gif图片格式
    image/jpeg ：jpg图片格式
    image/png：png图片格式

   以application开头的媒体格式类型：

   application/xhtml+xml ：XHTML格式
   application/xml     ： XML数据格式
   application/atom+xml  ：Atom XML聚合格式
   application/json    ： JSON数据格式
   application/pdf       ：pdf格式
   application/msword  ： Word文档格式
   application/octet-stream ： 二进制流数据（如常见的文件下载）
   application/x-www-form-urlencoded ： <form encType=””>中默认的encType，form表单数据被编码为key/value格式发送到服务器（表单默认的提交数据的格式）

   另外一种常见的媒体格式是上传文件之时使用的：

    multipart/form-data ： 需要在表单中进行文件上传时，就需要使用该格式*/

/**
 * ok拦截器:
 * 1.统一添加header;
 * 2.过率图片加载;
 * 3.判断登录态.
 * ProjectName  XSMNewProject
 * PackageName  com.datouma.newproject.http
 * Author       Administrator
 * Date         2021/9/14.
 */

public class NetworkInterceptor implements Interceptor {

    //登录态失效
    private static final String LOGIN_STATE_KEY = "code";
    private static final int LOGIN_STATE_FAIL = -1;

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        //①添加Header参数
        Request request = buildRequest(chain);
        //②解析Response
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        /*
         * 注意此处需要过率非http请求,非http请求调用bodey.string()会出异常:
         * 1.glide加载图片时type为:image/xxx;
         * 2.http请求为:application/xxx.
         */
        if (responseBody != null && responseBody.contentType() != null) {
            String type = Objects.requireNonNull(responseBody.contentType()).type();
            if (!TextUtils.isEmpty(type) && !type.startsWith("application")) {
                return response;
            }
        }
        //③重登陆判断
        String str = null;
        try {
            if (responseBody != null) {
                str = responseBody.string();
            }
            if (str == null) {
                str = "";
            }
        } catch (IOException e) {
            return response;
        }
        MediaType type = getMediaType(responseBody);
        if (responseBody != null && !TextUtils.isEmpty(str)) {
            checkLoginCode(str);
        }
        //④返回Response
        return response
                .newBuilder()
                .body(ResponseBody.create(str, type))
                .build();
    }

    private MediaType getMediaType(ResponseBody responseBody) {
        if (responseBody != null && responseBody.contentType() != null) {
            return responseBody.contentType();
        } else {
            return MediaType.Companion.parse("application/json;charset=utf-8");
        }
    }

    @NotNull
    private Request buildRequest(@NotNull Chain chain) {
        return chain
                .request()
                .newBuilder()
                .cacheControl(new CacheControl.Builder().noCache().build())
                .addHeader("token", SPUtil.getString(AppConfig.USER_TOKEN))
                .addHeader("client_type", "android")
                .addHeader("version_code", String.valueOf(AppUtil.getVersionCode(App.getContext())))
                .addHeader("version_name", AppUtil.getVersionName(App.getContext()))
                .build();
    }

    /**
     * 跳转登录
     *
     * @param str
     */
    private void checkLoginCode(String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.contains(LOGIN_STATE_KEY)) {
                JSONObject object = new JSONObject(str);
                if (!object.isNull(LOGIN_STATE_KEY)) {
                    if (LOGIN_STATE_FAIL == object.getInt(LOGIN_STATE_KEY)) {
                        LoginUtil.toLogin();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}