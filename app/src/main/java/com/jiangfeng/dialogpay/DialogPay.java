package com.jiangfeng.dialogpay;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogPay {
    private static Dialog mDialog;

    public static void createPayDialog(Context context, final CallbackPay callbackPay) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        Activity activity = (Activity) context;
        if (activity.isFinishing()) {
            return;
        }
        mDialog = new Dialog(context, R.style.DialogPay);
        //加载布局
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_pay, null);
        //将布局设置给Dialog
        mDialog.setContentView(dialogView);
        ImageView imageView = dialogView.findViewById(R.id.dialogPay_close_iv);
        final PasswordEditText passwordEditText = dialogView.findViewById(R.id.dialogPay_password_et);
        TextView forgetPasswordTv = dialogView.findViewById(R.id.dialogPay_forgetPassword_tv);
        NumberKeyboardView keyboardView = dialogView.findViewById(R.id.dialogPay_keyboard);
        //设置对话框显示的位置
        Window dialogWindow = mDialog.getWindow();
        assert dialogWindow != null;
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        //与底部距离为10
//        lp.y = 10;
//        dialogWindow.setAttributes(lp);
        //显示对话框
        mDialog.show();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ViewUtil.isFastClick()) {
                    return;
                }
                callbackPay.close();
            }
        });
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 6) {
                    callbackPay.passwordFull(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        forgetPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ViewUtil.isFastClick()) {
                    return;
                }
                callbackPay.forgetPassword();
            }
        });
        keyboardView.setIOnKeyboardListener(new NumberKeyboardView.OnKeyboardListener() {
            @Override
            public void onInsertKeyEvent(String text) {
                passwordEditText.append(text);
            }

            @Override
            public void onDeleteKeyEvent() {
                int start = passwordEditText.length() - 1;
                if (start >= 0) {
                    passwordEditText.getText().delete(start, start + 1);
                }
            }
        });
    }

    public static void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
