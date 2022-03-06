package com.lib.base.ui.dialog.base;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.lib.base.R;

import androidx.annotation.StringRes;

/**
 * @author: Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/12/2
 * desc   : 等待加载对话框
 */
public final class WaitDialog {

    public static final class Builder
            extends BaseDialog.Builder<Builder> implements Runnable, BaseDialog.OnShowListener {

        private final TextView mMessageView;
        private int mDuration = BaseDialog.SHOW_TIME;
        private boolean mAutoDismiss;

        public Builder(Context context) {
            super(context);
            setContentView(R.layout.wait_dialog);
            setAnimStyle(BaseDialog.ANIM_TOAST);
            setBackgroundDimEnabled(false);
            setCancelable(false);

            mMessageView = findViewById(R.id.tv_wait_message);
            addOnShowListener(this);
        }

        public Builder setDuration(int duration) {
            mDuration = duration;
            return this;
        }

        public Builder autoDismiss(boolean autoDismiss) {
            mAutoDismiss = autoDismiss;
            return this;
        }

        public Builder setMessage(@StringRes int id) {
            return setMessage(getString(id));
        }

        public Builder setMessage(CharSequence text) {
            mMessageView.setText(text);
            mMessageView.setVisibility(text == null ? View.GONE : View.VISIBLE);
            return this;
        }

        @Override
        public void onShow(BaseDialog dialog) {
            // 延迟自动关闭
            if (mAutoDismiss) {
                postDelayed(this, mDuration);
            }
        }

        @Override
        public void run() {
            if (!isShowing()) {
                return;
            }
            dismiss();
        }
    }
}