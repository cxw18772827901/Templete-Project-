package com.lib.base.config;


import com.lib.base.util.DebugUtil;

/**
 * PackageName  com.bigheadhorse.xscat.config
 * ProjectName  NumericalCodeProject
 * Author       chenxiaowu
 * Date         2019-11-27.
 */
public interface AppConfig {
    //host ip
    String API_BASE_URL_TEST = "http://192.168.3.6:8888";
    String API_BASE_URL_RELEASE = "http://tinysafety.fxgkpt.com";
    String API_BASE_URL_FINAL = DebugUtil.isDebug ? API_BASE_URL_TEST : API_BASE_URL_RELEASE;//主域名
    String API_BASE_URL_FINAL1 = "http://app.jnprsc.com";
    String API_BASE_URL_FINAL2 = "";//副域名2
    String HOST = "host";
    String HOST1 = "host:host1";
    String HOST2 = "host:host2";
    //user
    String USER_TOKEN = "user_token";
    String USER_PHONE_NUM = "user_phone_num";
    //debug
    String LETTER_26 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //protocol
    String APP_PROTOCOL = "https://www.baidu.com";
    //易盾
    boolean isTest = true;
    String onePassId = "";//一键登录业务id--------------------------生产环境
    String mobileVerifyId = "";//本机校验业务id----------------------生产环境
    String trialOnePassId = "";//试用一键登录业务id------------------测试环境
    String trialMobileVerifyId = "";//试用本机校验业务id-------------测试环境
    String secretKey = "";
    String secretId = "";
    String onePassUrl = "http://ye.dun.163yun.com/v1/oneclick/check";//本机号码一键登录验证操作操作url地址（此操作也可放服务端）
    String verifyUrl = "http://ye.dun.163yun.com/v1/check";//本机号码验证url地址
    //微信
    String WX_APP_ID = "wx5fcdeb6d2825e571";
    String WX_SECRET = "";
    String AUTHORIZATION_CODE = "authorization_code";
    //sp
    String SP_NAME = "sp_xuanshangmao";
    //others
    long BIG_TV_SHOW_TIME = 400;//字母索引显示时间
    long AUTO_DELAY = 3 * 1000;//轮播图展示时间
    String DEVICE_ID = "device_id";
}
