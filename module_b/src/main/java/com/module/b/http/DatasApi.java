package com.module.b.http;

import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *@author      xwchen
 * Date         2019-11-27.
 */

public interface DatasApi {

//    /**
//     * 验证用户状态
//     *
//     * @param loginName
//     * @param password
//     * @return
//     */
//    @POST("/MobileLogin/logOn")
//    Single<LoginRecord> verifyUserState(@Query("loginName") String loginName,
//                                        @Query("password") String password);

    /**
     * 登录
     *
     * @param loginName
     * @param password
     * @return
     */
    @POST("/MobileLogin/logOn")
    Single<ResponseBody> login(@Query("loginName") String loginName,
                               @Query("password") String password);

//    /**
//     * 修改信息
//     *
//     * @return
//     */
////    @Headers({"Content-Type: application/json", "Accept: application/json"})
////    @POST("/MobileLogin/updatePassword")
////    Single<ChangeInfoRecord> editInfo(@Body LogoutParamsBean paramsBean);
//
//    /**
//     * 获取风险点--扫一扫
//     *
//     * @return
//     */
////    @POST("/RiskInspect/getRiskContent")
////    Single<RisksRecord> getRisks(@QueryMap Map<String, String> keyMaps);
//
//    /**
//     * 获取微信token
//     *
//     * @param appid
//     * @param secret
//     * @param code
//     * @param grant_type
//     * @return
//     */
//    @Headers(AppConfig.HOST1)
//    @GET("sns/oauth2/access_token")
//    Single<WxTokenBean> getWxTokenBean(@Query("appid") String appid,
//                                       @Query("secret") String secret,
//                                       @Query("code") String code,
//                                       @Query("grant_type") String grant_type);
//
//    /**
//     * 验证token和openid是否有效
//     *
//     * @param access_token
//     * @param openid
//     * @return
//     */
//    @Headers(AppConfig.HOST1)
//    @GET("sns/auth")
//    Single<WxTokenVerifyBean> getWxTokenVerifyBean(@Query("access_token") String access_token,
//                                                   @Query("openid") String openid);
//
//    /**
//     * 获取微信用户信息
//     *
//     * @param access_token
//     * @param openid
//     * @return
//     */
//    @Headers(AppConfig.HOST1)
//    @GET("sns/userinfo")
//    Single<WxInfoBean> getWxInfoBean(@Query("access_token") String access_token,
//                                     @Query("openid") String openid);

}
