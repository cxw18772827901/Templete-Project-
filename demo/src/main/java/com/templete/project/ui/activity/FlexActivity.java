package com.templete.project.ui.activity;

import android.view.LayoutInflater;

import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.BoxItemBinding;
import com.templete.project.databinding.FlexActivityBinding;

import java.util.Random;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project.ui.activity
 * @author      xwchen
 * Date         2022/2/25.
 */

public class FlexActivity extends BaseActivity<FlexActivityBinding> {
    @Override
    public void initS() {
        setTitleStr("流式布局解决方案");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        String name = "我我我我我我我我我";
        //List<String> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int num = random.nextInt(10);
            num = num == 0 ? 1 : num;
            String tag = name.substring(0, num);
            //list.add(name.substring(0, num));
            BoxItemBinding binding = BoxItemBinding.inflate(LayoutInflater.from(this), mViewBinding.flowLayout, false);
            binding.tv.setText(tag);
            mViewBinding.flowLayout.addView(binding.getRoot());
        }
    }

    @Override
    protected FlexActivityBinding viewBinding() {
        return FlexActivityBinding.inflate(getLayoutInflater());
    }
}
