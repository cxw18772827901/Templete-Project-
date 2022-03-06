package com.templete.project.ui.activity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.ui.dialog.base.BottomSheetDialog;
import com.templete.project.R;
import com.templete.project.databinding.BottomSheetActivityBinding;

import androidx.core.widget.NestedScrollView;

/**
 * BottomSheetDialog不好用....,使用BottomSheet就够了...
 * <p>
 * PackageName  com.templete.project.ui
 * ProjectName  TempleteProject-java
 * Date         2022/1/23.
 *
 * @author xwchen
 */

public class SheetActivity extends BaseActivity<BottomSheetActivityBinding> {

    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected BottomSheetActivityBinding viewBinding() {
        return BottomSheetActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void inits() {
        //setTitleBGResource(R.color.cl_1692DB);

        setTitleStr("BottomSheet");
        /*setRightClickViews(new TitleBar.OnRightViewsClickListener() {
            @Override
            public void clickPosition(int position, View view) {
                mViewBinding.btnBottomSheet.setonclick {

                }


            }
        }, false, new BtnBean("sheet"));*/
    }

    /*STATE_COLLAPSED： 折叠状态
      STATE_EXPANDED： 展开状态
      STATE_DRAGGING ： 过渡状态
      STATE_SETTLING： 视图从脱离手指自由滑动到最终停下的这一小段时间
      STATE_HIDDEN ： 默认无此状态（可通过app:behavior_hideable 启用此状态），启用后用户将能通过向下滑动完全隐藏 bottom sheet*/
    @Override
    public void initView() {
        BottomSheetBehavior<NestedScrollView> behavior = BottomSheetBehavior.from(mViewBinding.llBottomSheet);
        mViewBinding.btnBottomSheet.setOnClickListener(v -> {
            if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                //如果是展开状态，则关闭，反之亦然
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        mViewBinding.btnBottomSheetDialog.setOnClickListener(v -> {
            if (bottomSheetDialog == null) {
                bottomSheetDialog = new BottomSheetDialog(this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
                bottomSheetDialog.show();
                return;
            }
            if (bottomSheetDialog.isShowing()) {
                bottomSheetDialog.dismiss();
            } else {
                bottomSheetDialog.show();
            }
        });
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }
}
