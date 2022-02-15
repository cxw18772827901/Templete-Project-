package com.lib.base.ui.dialog.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.lib.base.R;

import androidx.annotation.NonNull;


public class PhotoSelBottomDialog extends Dialog {

    @SuppressLint("InflateParams")
    public PhotoSelBottomDialog(@NonNull Context context, SelectListener selectListener) {
        super(context, R.style.dialog_bottom);
        setContentView(LayoutInflater.from(context).inflate(R.layout.choose_photo_layout, null));
        setCanceledOnTouchOutside(true);
        Window window = getWindow();//设置窗体位置以及动画
        if (null != window) {
            window.setWindowAnimations(R.style.main_menu_animstyle);
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        TextView tv_camera = findViewById(R.id.tv_camera);
        TextView tv_photos = findViewById(R.id.tv_photos);
        TextView tv_cancel = findViewById(R.id.tv_cancel);
        tv_camera.setOnClickListener(v -> {
            dismiss();
            if (selectListener != null) {
                selectListener.camera();
            }
        });
        tv_photos.setOnClickListener(v -> {
            dismiss();
            if (selectListener != null) {
                selectListener.photo();
            }
        });
        tv_cancel.setOnClickListener(v -> dismiss());
    }

    public interface SelectListener {
        void camera();

        void photo();
    }
}
