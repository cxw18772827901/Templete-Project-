package com.templete.project.ui.activity;

import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.util.txt.label.TxtUtil;
import com.lib.base.util.txt.superSoan.SpanData;
import com.templete.project.R;
import com.templete.project.databinding.TxtActivityBinding;

/**
 * PackageName  com.templete.project.ui.activity
 * ProjectName  TempleteProject-java
 * Date         2022/2/15.
 *
 * @author xwchen
 */

public class TxtActivity extends BaseActivity<TxtActivityBinding> {
    @Override
    public void inits() {
        setTitleStr("繁琐的文本标签");
    }

    @Override
    public void initView() {
        /**
         * 多标签上色
         */
        TxtUtil.setMultipleLabelColor(mViewBinding.tv3, "#FF0000", mViewBinding.tv3.getText().toString(), "abc", "标签");
        /**
         * 行首增加单标签
         */
        TxtUtil.addStartLabel(this, mViewBinding.tv4, mViewBinding.tv4.getText().toString(), (int) getDimen(R.dimen.x50), "标签1");
        /**
         * 行首增加双标签
         */
        TxtUtil.addDoubleStartLabel(this, mViewBinding.tv5, mViewBinding.tv5.getText().toString(), (int) getDimen(R.dimen.x50), "标签1", "标签2");
        /**
         * 修改字体粗细,默认值0.3,注意不要跟粗体一起使用
         */
        TxtUtil.setTextBold(mViewBinding.tv6, "默认0.1粗细的字体");
        /**
         * 修改字体粗细,修改为2.0,注意不要跟粗体一起使用
         */
        TxtUtil.setTextBold(mViewBinding.tv7, "2.0粗细的字体", (float) 2.0);
        /**
         * 超级富文本,可以修改多个标签,包括颜色,字号,粗体,可点击
         */
        TxtUtil.setSuperLabel(mViewBinding.tv8, "#FF0000", (int) getDimen(R.dimen.x75), true, true,
                index -> {
                    //do
                    toast("点击了第" + index + "个标签");
                },
                new SpanData("这是一", false),
                new SpanData("段超级文本", true),
                new SpanData(",有颜色的", false),
                new SpanData("+粗体+", true),
                new SpanData("大字号", false),
                new SpanData("的都可以点击", true),
                new SpanData(",可以对多个", false),
                new SpanData("标签", true),
                new SpanData("进行修改,就问你溜不溜", false));
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
    }

    @Override
    protected TxtActivityBinding viewBinding() {
        return TxtActivityBinding.inflate(getLayoutInflater());
    }
}
