package com.lib.base.http;

import com.hjq.gson.GsonFactory;
import com.lib.base.config.AppConfig;
import com.lib.base.util.DebugUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenxiaowu on 19-4-20.
 */

public class RetrofitHelper {
    private static final String TAG = "RetrofitHelper";
    private volatile static RetrofitHelper sInstance;
    private final Retrofit mRetrofit;
    private final OkHttpClient mOkHttpClient;

    /**
     * 应用拦截器：关注的是发起请求，不能拦截发起请求到请求成功后返回数据的中间的这段时期;
     * 网络拦截器：关注的是发起请求和请求后获取的数据中间的这一过程。
     */
    private RetrofitHelper() {
        //OkHttpClient
        mOkHttpClient = new OkHttpClient
                .Builder()
                //处理cookie
                /*.cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                        cookieStore.put(HttpUrl.parse("http://192.168.31.231:8080/shiro-2"), cookies);
                        for(Cookie cookie:cookies){
                            System.out.println("cookie Name:"+cookie.name());
                            System.out.println("cookie Path:"+cookie.path());
                        }
                    }

                    @NonNull
                    @Override
                    public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse("http://192.168.31.231:8080/shiro-2"));
                        if(cookies==null){
                            System.out.println("没加载到cookie");
                        }
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })*/
                //.retryOnConnectionFailure(true)//默认重试一次,使用RetryInterceptor可以自定义重试次数
                .followRedirects(true)//允许重定向
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new UrlManagerInterceptor())//---------------------多域名切换拦截器
                .addInterceptor(new NetworkInterceptor())//------------------------网络拦截器,处理header,登录态换检等
                .addInterceptor(getLogInterceptor())//-----------------------------日志拦截器
                .addInterceptor(new RetryInterceptor(2))//-------------------------重试拦截器,总次数为2+1
                .build();
        //修改单域名最大并发,默认为5,总最大并发为64
        /*mOkHttpClient
                .dispatcher()
                .setMaxRequestsPerHost(10);*/
        //retrofit
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create())//--------------------------常规gson,异常解析会直接回调error
                .addConverterFactory(GsonConverterFactory.create(GsonFactory.getSingletonGson()))//解析容错gson,异常解析自动忽略
                .baseUrl(AppConfig.API_BASE_URL_FINAL1)
                .build();
    }

    /**
     * log Interceptor
     *
     * @return
     */
    private Interceptor getLogInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> DebugUtil.logD("OK_LOG", "data = " + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    public static RetrofitHelper getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitHelper.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitHelper();
                }
            }
        }
        return sInstance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
}
