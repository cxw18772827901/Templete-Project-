package com.templete.project.ui.activity;

import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.RemoteControlActivityBinding;
import com.templete.project.ui.draw.OvalRemoteControlMenu;

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

    }

    @Override
    protected RemoteControlActivityBinding viewBinding() {
        return RemoteControlActivityBinding.inflate(getLayoutInflater());
    }
}
