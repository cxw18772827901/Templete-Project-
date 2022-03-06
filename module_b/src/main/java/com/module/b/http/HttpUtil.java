package com.module.b.http;

import com.lib.base.http.RetrofitHelper;

import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * PackageName  com.hycg.company.http
 * ProjectName  HYCGCompanyProject
 *@author      xwchen
 * Date         2019/4/16.
 */
public class HttpUtil {
    private final DatasApi mDatasApi;
    private volatile static HttpUtil sInstance;

    private HttpUtil() {
        Retrofit mRetrofit = RetrofitHelper.getInstance()
                .getRetrofit();

        mDatasApi = mRetrofit.create(DatasApi.class);
    }

    public static HttpUtil getInstance() {
        if (sInstance == null) {
            synchronized (HttpUtil.class) {
                if (sInstance == null) {
                    sInstance = new HttpUtil();
                }
            }
        }
        return sInstance;
    }

    /**
     * 验证用户状态
     *
     * @param loginName
     * @param password
     * @return
     */
    public Single<ResponseBody> verifyUserState(String loginName, String password) {
        return mDatasApi.login(loginName, password);
    }

}
