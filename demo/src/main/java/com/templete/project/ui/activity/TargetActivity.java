package com.templete.project.ui.activity;

import com.hjq.shape.view.NavigationBar;
import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.bean.PBean;
import com.templete.project.databinding.TargetActivityBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PackageName  com.templete.project.ui
 * ProjectName  TempleteProject-java
 * Date         2022/2/11.
 *
 * @author xwchen
 */

public class TargetActivity extends BaseActivity<TargetActivityBinding> {
    @Override
    protected TargetActivityBinding viewBinding() {
        return TargetActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initS() {
        setTitleStr("超级Intent");

        int value1 = getIntExtra("key1");
        String value2 = getStringExtra("key2");
        int[] value3 = getIntArrayExtra("key3");
        PBean value4 = getParcelableExtra("key4");
        ArrayList<Integer> value5 = getIntegerArrayListExtra("key5");
        ArrayList<PBean> value6 = getParcelableArrayListExtra("key6");
        logD("超级Intent", "value1=" + value1);
        logD("超级Intent", "value2=" + value2);
        logD("超级Intent", "value3=" + Arrays.toString(value3));
        logD("超级Intent", "value4=" + value4);
        logD("超级Intent", "value5=" + value5);
        logD("超级Intent", "value6=" + value6);
    }

    @Override
    public void initView() {
        List<NavigationBar.Data> list = Arrays.asList(
                new NavigationBar.Data("导航按钮1"), new NavigationBar.Data("导航按钮2"),
                new NavigationBar.Data("导航按钮3"), new NavigationBar.Data("导航按钮4"),
                new NavigationBar.Data("导航按钮5"), new NavigationBar.Data("导航按钮6"),
                new NavigationBar.Data("导航按钮7"), new NavigationBar.Data("导航按钮8"),
                new NavigationBar.Data("导航按钮2"), new NavigationBar.Data("导航按钮3"),
                new NavigationBar.Data("导航按钮4"), new NavigationBar.Data("导航按钮5"),
                new NavigationBar.Data("导航按钮6"), new NavigationBar.Data("导航按钮7"),
                new NavigationBar.Data("导航按钮8"));
        mViewBinding.bar.setData(list, 2, null);
//        mViewBinding.bar.scrollBys(147);
        mViewBinding.bar.scrollTos(147, 2);
//        mViewBinding.bar.smoothScrollBys(147);
//        mViewBinding.bar.smoothScrollTos(147);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

}
