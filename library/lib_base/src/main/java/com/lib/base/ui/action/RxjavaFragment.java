package com.lib.base.ui.action;

import android.os.Bundle;

import com.lib.base.config.App;
import com.lib.base.mvvm.GlobalViewModel;
import com.lib.base.rxjava.RxHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 防止Fragment重叠和Rxjava取消
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.ui.action
 * @author      xwchen
 * Date         2022/1/27.
 */

public class RxjavaFragment extends Fragment {
    private static final String STATE_FOR_FRAGMENT = "state_save_is_hidden";

    protected GlobalViewModel getGlobalViewModel() {
        return ((App) requireActivity().getApplication()).getGlobalViewModel();
    }

    /**
     * 处理长时间挂后台的错乱问题
     *
     * @param bundle bundle
     */
    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        try {
            if (bundle == null) {
                return;
            }
            FragmentManager fragmentManager = getParentFragmentManager();
            if (bundle.getBoolean(STATE_FOR_FRAGMENT)) {
                fragmentManager.beginTransaction().hide(this).commit();
            } else {
                fragmentManager.beginTransaction().show(this).commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(STATE_FOR_FRAGMENT, isHidden());
    }

    /**
     * 自定义CompositeDisposable,结合Lifecycle,自动取消操作
     */
    private RxHelper rxHelper;

    protected void addDisposable(Disposable subscription) {
        getRxHelper().addDisposable(subscription);
    }

    protected RxHelper getRxHelper() {
        if (rxHelper == null) {
            rxHelper = new RxHelper(getLifecycle());
        }
        return rxHelper;
    }
}
