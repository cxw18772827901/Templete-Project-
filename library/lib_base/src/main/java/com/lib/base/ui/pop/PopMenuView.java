package com.lib.base.ui.pop;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.lib.base.R;

/**
 * 占满屏幕宽度的下拉菜单PopupWindow
 * <p>
 * PackageName  com.spot.ispot.view.widget
 * ProjectName  Spot1-26-yoga
 * Author       chenxiaowu
 * Date         6/27/21.
 */
public class PopMenuView extends PopupWindow {
    private final View locationView;

    public PopMenuView(View contentView, View locationView) {
        this(contentView, locationView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public PopMenuView(View contentView, View locationView, int width, int height) {
        super(contentView, width, height);
        this.locationView = locationView;
        setFocusable(false);
        setOutsideTouchable(false);
        setAnimationStyle(R.style.draw_down_pw_style);
        //setBackgroundDrawable(new BitmapDrawable())；已过时
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //height 占满屏幕中locationView以下的剩余高度
        Rect rect = new Rect();
        locationView.getGlobalVisibleRect(rect);
        int h = locationView.getResources().getDisplayMetrics().heightPixels - rect.bottom;//屏幕高度获取可能会有异常,直接根据当前view整体布局来测量实际高度即可.
        setHeight(h);
    }

    public void show() {
        showAsDropDown(locationView);
    }
}