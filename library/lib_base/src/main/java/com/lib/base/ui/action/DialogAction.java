package com.lib.base.ui.action;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.lib.base.R;
import com.lib.base.ui.dialog.base.BaseDialog;
import com.lib.base.ui.dialog.base.ConfirmDialog;
import com.lib.base.ui.dialog.base.LoadingDialog;
import com.lib.base.ui.dialog.base.TipsDialog;
import com.lib.base.ui.dialog.base.WaitDialog;

import androidx.appcompat.app.AppCompatActivity;

/**
 * dialog工具类
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.ui.action
 * Author       Administrator
 * Date         2022/1/27.
 */

public class DialogAction extends AppCompatActivity {
    //private static final int DIALOG_DURATION = 1000;
    public static final int DIALOG_OK = 0;
    public static final int DIALOG_ERROR = 1;
    public static final int DIALOG_LOADING = 2;
    public static final int DIALOG_WARNING = 3;
    public static final int DIALOG_NORMAL = 4;
    public static final int DIALOG_CONFIRM = 5;
    //dialog
    private int dialogType = -1;
    private Dialog dialog;
    //七牛取消上传开关,activity关闭时设置为true即可取消上传
    public boolean cancelUpload;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelUpload = true;
    }

    /**
     * 默认使用上次创建的dialog
     *
     * @param info
     */
    public void showLoadingDialog(String info) {
        showLoadingDialog(info, false);
    }

    /**
     * 可选是否使用上次创建的dialog
     *
     * @param info
     * @param update
     */
    public void showLoadingDialog(String info, boolean update) {
        if (update || dialogType != DIALOG_NORMAL || dialog == null) {
            dialog = new LoadingDialog(this, -1, info);
            ((LoadingDialog) dialog).setOnKeyBackListener(this::finish);
            dialogType = DIALOG_NORMAL;
        }
        dialog.show();
    }

    /**
     * 默认使用上次创建的dialog
     *
     * @param info
     */
    public void showConfirmDialog(String info) {
        showConfirmDialog(info, false);
    }

    /**
     * 可选是否使用上次创建的dialog
     *
     * @param info
     * @param update
     */
    public void showConfirmDialog(String info, boolean update) {
        if (update || dialogType != DIALOG_CONFIRM || dialog == null) {
            dialog = new ConfirmDialog(this, info);
            ((ConfirmDialog) dialog).setOnKeyBackListener(() -> {
                finish();
            });
            dialogType = DIALOG_CONFIRM;
        }
        dialog.show();
    }

    public void showDialogView(int dialogType, String info) {
        showDialogView(dialogType, info, false);
    }

    public void showDialogView(int dialogType, String info, boolean update) {
        showDialogView(dialogType, info, update, false);
    }

    public void showDialogView(int dialogType, String info, boolean update, boolean autoDismiss) {
        switch (dialogType) {
            case DIALOG_LOADING:
                showDialogLoading(info, update, autoDismiss);
                break;
            case DIALOG_OK:
                showDialogOk(info, update, autoDismiss);
                break;
            case DIALOG_ERROR:
                showDialogError(info, update, autoDismiss);
                break;
            default:
                showDialogWarning(info, update, autoDismiss);
                break;
        }
    }

    /**
     * 可选是否使用上次创建的dialog
     *
     * @param info
     * @param update
     */
    private void showDialogLoading(String info, boolean update, boolean autoDismiss) {
        if (update || dialogType != DIALOG_LOADING || dialog == null) {
            dialog = new WaitDialog.Builder(this)
                    // 消息文本可以不用填写
                    .setMessage(TextUtils.isEmpty(info) ? getString(R.string.common_loading) : info)
                    .setDuration(BaseDialog.SHOW_TIME)
                    .autoDismiss(autoDismiss)
                    .setOnKeyListener((dialog, event) -> {
                        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                            finish();
                        }
                        return true;
                    })
                    .create();
            dialogType = DIALOG_LOADING;
        }
        dialog.show();
    }


    /**
     * 可选是否使用上次创建的dialog
     *
     * @param info
     * @param update
     */
    private void showDialogOk(String info, boolean update, boolean autoDismiss) {
        if (update || dialogType != DIALOG_OK || dialog == null) {
            dialog = new TipsDialog.Builder(this)
                    .setIcon(TipsDialog.ICON_FINISH)
                    .setMessage(!TextUtils.isEmpty(info) ? info : "完成")
                    .setDuration(BaseDialog.SHOW_TIME)
                    .autoDismiss(autoDismiss)
                    .setOnKeyListener((dialog, event) -> {
                        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                            finish();
                        }
                        return true;
                    })
                    .create();
            dialogType = DIALOG_OK;
        }
        dialog.show();
    }

    /**
     * 可选是否使用上次创建的dialog
     *
     * @param info
     * @param update
     */
    private void showDialogError(String info, boolean update, boolean autoDismiss) {
        if (update || dialogType != DIALOG_ERROR || dialog == null) {
            dialog = new TipsDialog.Builder(this)
                    .setIcon(TipsDialog.ICON_ERROR)
                    .setMessage(!TextUtils.isEmpty(info) ? info : "错误")
                    .setDuration(BaseDialog.SHOW_TIME)
                    .autoDismiss(autoDismiss)
                    .setOnKeyListener((dialog, event) -> {
                        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                            finish();
                        }
                        return true;
                    })
                    .create();
            dialogType = DIALOG_ERROR;
        }
        dialog.show();
    }

    /**
     * 可选是否使用上次创建的dialog
     *
     * @param info
     * @param update
     */
    private void showDialogWarning(String info, boolean update, boolean autoDismiss) {
        if (update || dialogType != DIALOG_WARNING || dialog == null) {
            dialog = new TipsDialog.Builder(this)
                    .setIcon(TipsDialog.ICON_WARNING)
                    .setMessage(!TextUtils.isEmpty(info) ? info : "警告")
                    .setDuration(BaseDialog.SHOW_TIME)
                    .autoDismiss(autoDismiss)
                    .setOnKeyListener((dialog, event) -> {
                        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                            finish();
                        }
                        return true;
                    })
                    .create();
            dialogType = DIALOG_WARNING;
        }
        dialog.show();
    }

    /**
     * 手动close dialog
     */
    public void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
