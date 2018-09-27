package com.jiangfeng.dialogpay;

public interface CallbackPay {
    void close();

    void forgetPassword();

    void passwordFull(String password);
}
