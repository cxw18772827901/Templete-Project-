package com.templete.project.mvvm;

import android.app.Application;
import android.os.Build;

import com.lib.base.config.AppConfig;
import com.lib.base.mvvm.BaseViewModel;
import com.lib.base.rxjava.RxUtils;
import com.lib.base.util.AppUtil;
import com.lib.base.util.DebugUtil;
import com.lib.base.util.OUtil;
import com.lib.base.util.SPUtil;
import com.templete.project.util.OaidHelp;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;

/**
 * PackageName  com.templete.project.mvvm
 * ProjectName  TempleteProject
 * Author       chenxiaowu
 * Date         10/10/21.
 */

public class MainViewModel extends BaseViewModel {

    /**
     * 注意:ViewModel构造函数不要写任务业务逻辑,否则有报错的可能
     *
     * @param application
     */
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 注意需要在获取READ_PHONE_STATE权限后调用
     */
    public void initOnlyId() {
        String id = SPUtil.getString(AppConfig.DEVICE_ID);
        if (OUtil.isNotNull(id)) {
            DebugUtil.logD("onlyId", "id=" + id);
            setId(id);
        } else {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {//小于等于安卓9
                getImei();
            } else {
                getOaId();
            }
        }
    }

    /**
     * oaid要在子线程获取,不然可能会出异常
     */
    private void getOaId() {
        Single
                .create(this::oaHelper)
                .compose(RxUtils::toSimpleSingle)
                .doOnSubscribe(this::addDisposable)
                .doOnSuccess(ids -> {
                    DebugUtil.logD("onlyId", "oaid=" + ids);
                    setId(ids);
                    SPUtil.put(AppConfig.DEVICE_ID, ids);
                    //百度统计
                    //StatService.setOaid(getApplication(), onlyId);
                })
                .doOnError(throwable -> DebugUtil.logD("onlyId", "oaid error,msg=" + throwable.getMessage()))
                .subscribe();
    }

    private void oaHelper(SingleEmitter<String> emitter) {
        try {
            OaidHelp.newInstance(ids -> {
                if (OUtil.isNotNull(ids)) {
                    emitter.onSuccess(ids);
                } else {
                    DebugUtil.logD("onlyId", "oaid=null");
                    emitter.onSuccess(String.valueOf(System.currentTimeMillis()));
                }
            }).getDeviceIds(getApplication());
        } catch (Exception e) {
            DebugUtil.logD("onlyId", "oaid error,msg=" + e.getMessage());
            emitter.onSuccess(String.valueOf(System.currentTimeMillis()));
        }
    }

    private void getImei() {
        String imei = AppUtil.getImei();
        if (OUtil.isNotNull(imei)) {
            DebugUtil.logD("onlyId", "imei=" + imei);
            setId(imei);
            SPUtil.put(AppConfig.DEVICE_ID, imei);
            //百度统计
            //StatService.setOaid(getApplication(), onlyId);
        } else {
            DebugUtil.logD("onlyId", "imei=null");
            getOaId();
        }
    }

    private void setId(String oaid) {
        getGlobalViewModel().setOaid(oaid);
    }

}
