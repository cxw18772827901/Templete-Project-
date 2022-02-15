package com.templete.project.util;

import android.content.Context;

import com.bun.miitmdid.core.ErrorCode;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;
import com.lib.base.util.DebugUtil;
import com.lib.base.util.OUtil;


/**
 * OaidHelp helper = new OaidHelp(new OaidHelp.AppIdsUpdater() {
 *
 * @Override public void OnIdsAvalid(String ids) {
 * if (TextUtils.isEmpty(ids)) {
 * return;
 * }
 * Log.i("login", "oaid" + ids);
 * SharedPreferencesUtil.putData("oaia", ids);
 * }
 * });
 * helper.getDeviceIds(this);
 * Created by caict on 2020/6/8.
 */

public class OaidHelp implements IIdentifierListener {

    private final AppIdsUpdater appIdsUpdater;

    public OaidHelp(AppIdsUpdater _listener) {
        this.appIdsUpdater = _listener;
    }

    public static OaidHelp newInstance(AppIdsUpdater callback) {
        return new OaidHelp(callback);
    }

    public void getDeviceIds(Context cxt) {
        // 方法调用
        int nres = CallFromReflect(cxt);
        DebugUtil.logD("onlyId", "nres=" + nres);
        if (nres == ErrorCode.INIT_ERROR_DEVICE_NOSUPPORT) {//不支持的设备
            callBackError(null);
        } else if (nres == ErrorCode.INIT_ERROR_LOAD_CONFIGFILE) {//加载配置文件出错
            callBackError(null);
        } else if (nres == ErrorCode.INIT_ERROR_MANUFACTURER_NOSUPPORT) {//不支持的设备厂商
            callBackError(null);
        } else if (nres == ErrorCode.INIT_ERROR_RESULT_DELAY) {//获取接口是异步的，结果会在回调中返回，回调执行的回调可能在工作线程
//            callBackError(null);
        } else if (nres == ErrorCode.INIT_HELPER_CALL_ERROR) {//反射调用出错
            callBackError(null);
        } else if (nres != ErrorCode.INIT_ERROR_BEGIN) {
//            callBackError(null);
        }
    }

    /*
     * 方法调用
     *
     * */
    private int CallFromReflect(Context cxt) {
        try {
            return MdidSdkHelper.InitSdk(cxt, true, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ErrorCode.INIT_ERROR_DEVICE_NOSUPPORT;
    }

    /*
     * 获取相应id
     *
     * */
    @Override
    public void OnSupport(boolean isSupport, IdSupplier _supplier) {
        if (_supplier == null) {
            callBackError(null);
            return;
        }
        String oaid = _supplier.getOAID();
        if (appIdsUpdater != null && OUtil.isNotNull(oaid)) {
            callBackError(oaid);
        } else {
            callBackError(null);
        }
    }

    private void callBackError(String oaid) {
        appIdsUpdater.OnIdsAvalid(oaid);
    }

    public interface AppIdsUpdater {
        void OnIdsAvalid(String ids);
    }

}
