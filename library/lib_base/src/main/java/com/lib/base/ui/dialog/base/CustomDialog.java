package com.lib.base.ui.dialog.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.lib.base.R;
import com.lib.base.databinding.CommonDialogLayoutBinding;
import com.lib.base.util.inject.MyException;

import androidx.annotation.NonNull;

@SuppressLint("NonConstantResourceId")
public class CustomDialog extends Dialog implements View.OnClickListener {

    private final CommonDialogLayoutBinding binding;
    private Context context;
    private int styleId;
    private String title;
    private String desc;
    private String okMsg;
    private String cancelMsg;
    private OnCommitListener commitListener;
    private OnOKCancelListener okCancelListener;

    @SuppressLint("InflateParams")
    private CustomDialog(@NonNull Context context, int styleId) {
        super(context, styleId);
        binding = CommonDialogLayoutBinding.inflate(LayoutInflater.from(context));
        setContentView(binding.getRoot());
        setCanceledOnTouchOutside(true);
        Window window = getWindow();//设置窗体位置以及动画
        if (null != window) {
            //window.setGravity(Gravity.BOTTOM);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
    }

    private void init() {
        binding.tvTitle.setText(title);
        binding.tvDesc.setText(desc);
        if (!TextUtils.isEmpty(okMsg)) binding.tvOk.setText(okMsg);
        if (!TextUtils.isEmpty(cancelMsg)) binding.tvCancel.setText(cancelMsg);
    }

    public interface OnCommitListener {
        void onCommitClick();
    }

    public interface OnOKCancelListener {
        void ok();

        void cancel();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_ok) {
            dismiss();
            if (commitListener != null)
                commitListener.onCommitClick();
            if (okCancelListener != null)
                okCancelListener.ok();
        } else if (id == R.id.tv_cancel) {
            dismiss();
            if (okCancelListener != null)
                okCancelListener.cancel();
        }
    }

    public static class Builder {
        Context context;
        int styleId;
        String title;
        String desc;
        String okMsg;
        String cancelMsg;
        OnCommitListener commitListener;
        OnOKCancelListener okCancelListener;

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public void setStyleId(int styleId) {
            this.styleId = styleId;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder setOkMsg(String okMsg) {
            this.okMsg = okMsg;
            return this;
        }

        public Builder setCancelMsg(String cancelMsg) {
            this.cancelMsg = cancelMsg;
            return this;
        }

        public Builder setCommitListener(OnCommitListener commitListener) {
            this.commitListener = commitListener;
            return this;
        }

        public Builder setOkCancelListener(OnOKCancelListener okCancelListener) {
            this.okCancelListener = okCancelListener;
            return this;
        }

        public CustomDialog build() {
            if (styleId == 0) styleId = R.style.dialog;
            if (TextUtils.isEmpty(title)) throw new MyException("title 为空");
            if (TextUtils.isEmpty(desc)) throw new MyException("desc 为空");
            if (TextUtils.isEmpty(okMsg)) okMsg = "确认";
            if (TextUtils.isEmpty(cancelMsg)) cancelMsg = "取消";
            if (commitListener == null && okCancelListener == null) throw new MyException("call back 为空");
            CustomDialog dialog = new CustomDialog(context, styleId);
            dialog.context = context;
            dialog.styleId = styleId;
            dialog.title = title;
            dialog.desc = desc;
            dialog.okMsg = okMsg;
            dialog.cancelMsg = cancelMsg;
            if (commitListener != null) dialog.commitListener = commitListener;
            if (okCancelListener != null) dialog.okCancelListener = okCancelListener;
            dialog.init();
            return dialog;
        }
    }
}
