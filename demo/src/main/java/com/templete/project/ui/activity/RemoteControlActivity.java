package com.templete.project.ui.activity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.greendao.db.helper.CityBeanDao;
import com.greendao.db.util.LocalRepository;
import com.lib.base.aroute.ArouteConfig;
import com.lib.base.aroute.ModuleAService;
import com.lib.base.rxjava.RxUtils;
import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.RemoteControlActivityBinding;
import com.templete.project.ui.draw.OvalRemoteControlMenu;

import io.reactivex.rxjava3.core.Single;

/**
 * 解决复杂控件点击事件思路就是自定义控件,然后使用Region.contains(x,y)来判断点击范围
 * PackageName  com.templete.project.ui.activity
 * ProjectName  TempleteProject-java
 * Date         2022/2/26.
 *
 * @author chenxiaowu
 */

public class RemoteControlActivity extends BaseActivity<RemoteControlActivityBinding> {
    @Override
    public void inits() {
        setTitleStr("复杂控件点击事件处理");
    }

    @Override
    public void initView() {
        mViewBinding.remoteControlMenu.setListener(new OvalRemoteControlMenu.MenuListener() {
            @Override
            public void onCenterCliched() {
                toast("点击中间按钮");
            }

            @Override
            public void onUpCliched() {
                toast("点击上面按钮");
            }

            @Override
            public void onRightCliched() {
                toast("点击右边按钮");
            }

            @Override
            public void onDownCliched() {
                toast("点击下面按钮");
            }

            @Override
            public void onLeftCliched() {
                toast("点击左边按钮");
            }
        });
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        Single
                .create(emitter -> {
                    CityBeanDao cityBeanDao = LocalRepository.getInstance().getCityBeanDao();
                    logD("cityBeanDao", "cityBeanDao2=" + cityBeanDao);
                })
                .compose(RxUtils::toSimpleSingleIo)
                .doOnSubscribe(this::addDisposable)
                .doOnSuccess(o -> logD("cityBeanDao", "cityBeanDao3=doOnSuccess"))
                .doOnError(throwable -> logD("cityBeanDao", "cityBeanDao4=doOnError"))
                .subscribe();
        ModuleAService moduleAService = (ModuleAService) ARouter.getInstance().build(ArouteConfig.MODULE_SERVICE_A).navigation();
        moduleAService.getA(a -> logD("moduleAService", "moduleAService=" + a));
    }

    @Override
    protected RemoteControlActivityBinding viewBinding() {
        return RemoteControlActivityBinding.inflate(getLayoutInflater());
    }
}
