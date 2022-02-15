package com.lib.base.ui.dialog.base;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.lib.base.R;

import androidx.annotation.NonNull;

/**
 * PackageName  com.lib.base.ui.dialog
 * ProjectName  TempleteProject-java
 * Date         2022/1/23.
 *
 * @author chenxiaowu
 */

public class BottomSheetDialog extends com.google.android.material.bottomsheet.BottomSheetDialog {
    private View contentView;

    public BottomSheetDialog(@NonNull Context context) {
        this(context, R.style.BottomSheetDialog);
    }

    public BottomSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    protected BottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    @Override
    public void setContentView(int layoutResId) {
        setContentView(LayoutInflater.from(getContext()).inflate(layoutResId, null));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        this.contentView = view;
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            float dimension = getContext().getResources().getDimension(R.dimen.y1200);
            //拿到系统的 bottom_sheet
            FrameLayout view = findViewById(R.id.design_bottom_sheet);
            //设置view高度
            if (view == null) {
                return;
            }
//            view.getLayoutParams().height = (int) dimension;// ViewGroup.LayoutParams.MATCH_PARENT;
            //获取behavior
            BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(view);
            //设置弹出高度
            behavior.setPeekHeight((int) dimension);
            //设置展开状态
            //behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

            Window dialogWindow = getWindow();
            dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
            //重新设置
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//            dialogWindow.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
            //dimAmount在0.0f和1.0f之间，0.0f完全不暗，1.0f全暗
//            lp.dimAmount = 0.0f;
            // 新位置X坐标
            lp.x = 40;
            /**
             * 1.当Window的Attributes改变时系统会调用此函数;
             * 2.窗体的大小和位置通过WindowManager.LayoutParams来设置，在通过x和y值来设置窗体位置时，
             * 需要注意gravity属性，如果gravity没有设置或者是center之类的，那么设置的x和y值就不会起作用.
             */
            dialogWindow.setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 无效
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        /*if (KeyEvent.KEYCODE_BACK == keyCode) {
            Context context = getContext();
            if (context instanceof Activity) {
                dismiss();
                ((Activity) context).finish();
            }
        }*/
        return super.onKeyDown(keyCode, event);
    }
}
