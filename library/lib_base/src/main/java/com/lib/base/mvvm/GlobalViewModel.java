package com.lib.base.mvvm;

import android.app.Application;

import com.lib.base.config.AppConfig;
import com.lib.base.util.OUtil;
import com.lib.base.util.SPUtil;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * PackageName  com.lib.base.config
 * ProjectName  TempleteProject
 * Author       chenxiaowu
 * Date         10/10/21.
 */

public class GlobalViewModel extends AndroidViewModel {
    private String oaid;
    /*public boolean init;
    //带粘性
    public MutableLiveData<Integer> data1 = new MutableLiveData<>();
    //不带粘性
    public LiveData<Integer> data2 = new LiveData<>();*/

    public GlobalViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
    }

    public String getOaid() {
        if (OUtil.isNull(oaid)) {
            oaid = SPUtil.getString(AppConfig.DEVICE_ID);
        }
        if (OUtil.isNull(oaid)) {
            oaid = String.valueOf(System.currentTimeMillis());
        }
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }
}
