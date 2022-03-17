package com.templete.project.ui.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.hjq.shape.view.FlowLayout;
import com.lib.base.adapter.AppAdapter;
import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.BoxItemBinding;
import com.templete.project.databinding.FlexBoxActivityBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;

/**
 * 谷歌官方流式布局,替代{@link FlowLayout}
 * PackageName  com.templete.project.ui
 * ProjectName  TempleteProject-java
 * Date         2022/1/23.
 *
 * @author xwchen
 */

public class FlexBoxActivity extends BaseActivity<FlexBoxActivityBinding> {

    private MyAdapter myAdapter;

    @Override
    public FlexBoxActivityBinding viewBinding() {
        return FlexBoxActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initS() {
        setTitleStr("官方流式布局解决方案");
    }

    @Override
    public void initView() {
        //设置LayoutManager
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        //主轴为水平方向，起点在左端
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        //按正常方向换行
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        //定义项目在副轴轴上如何对齐
        flexboxLayoutManager.setAlignItems(AlignItems.FLEX_START);
        //多个轴对齐方式
        //SPACE_EVENLY 两边和中间间隙均分
        //SPACE_BETWEEN 两边靠边,中间间隙均分
        //SPACE_AROUND 看起来和SPACE_EVENLY一样
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);

        mViewBinding.getRoot().setLayoutManager(flexboxLayoutManager);
        myAdapter = new MyAdapter(this);
        mViewBinding.getRoot().setAdapter(myAdapter);

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        String name = "我我我我我我我我我";
        List<String> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int num = random.nextInt(10);
            num = num == 0 ? 1 : num;
            list.add(name.substring(0, num));
        }
        myAdapter.setData(list);
    }


    static class MyAdapter extends AppAdapter<String> {

        public MyAdapter(@NonNull Context context) {
            super(context);
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(BoxItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }


        class VH extends AppAdapter<?>.ViewHolder {

            private final BoxItemBinding binding;

            public VH(BoxItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            @Override
            public void onBindView(int position) {
                binding.tv.setText(getItem(position));
            }
        }
    }
}
