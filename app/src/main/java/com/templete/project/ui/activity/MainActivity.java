package com.templete.project.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lib.base.aroute.ArouteConfig;
import com.lib.base.ui.activity.AppTheme;
import com.lib.base.ui.activity.BaseMvvmActivity;
import com.lib.base.util.LoginUtil;
import com.templete.project.R;
import com.templete.project.databinding.ActivityMainBinding;
import com.templete.project.mvvm.MainViewModel;
import com.templete.project.ui.fragment.FirstFragment;
import com.templete.project.ui.fragment.ForthFragment;
import com.templete.project.ui.fragment.SecondFragment;
import com.templete.project.ui.fragment.ThirdFragment;
import com.templete.project.ui.widget.TabHost;

/**
 * @author chenxiaowu
 */
@Route(path = ArouteConfig.ACTIVITY_MAIN)
public class MainActivity extends BaseMvvmActivity<ActivityMainBinding, MainViewModel> {
    public static final String TAG = "MainActivity";

    @Override
    protected Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    public int getActivityTheme() {
        return AppTheme.THEME_NONE;
    }

    @Override
    public boolean darkStatusBarFont() {
        return true;
    }

    @Override
    protected ActivityMainBinding viewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public int finishAnimType() {
        return ANIM_NO;
    }

    @Override
    public void inits() {
        //使用正常主题覆盖闪屏主题
        setTheme(R.style.AppTheme);
        setImmersionBar();
    }

    @Override
    public void initView() {
        TabHost
                .Builder
                .newBuilder()
                .setManager(getSupportFragmentManager())
                .setContainerId(R.id.fl_container)
                .setFragments(FirstFragment.class, SecondFragment.class, ThirdFragment.class, ForthFragment.class)
                .build(mViewBinding.tabHost)
                .setOnLoginListener(LoginUtil::toLogin)
                .setOnTabChangedListener(tabIndex -> logD(TAG, "tabIndex=" + tabIndex))
                .over();
    }

    @Override
    public void initEvent() {
    }

    @Override
    public void initData() {
//        LocalRepository.getInstance().getDaoSession().getDemoBeanDao().insert(new DemoBean(null,"",1));
    }

    @Override
    protected void onResume() {
        super.onResume();
        logD("startTime", "startTime2=" + System.currentTimeMillis());
    }
}