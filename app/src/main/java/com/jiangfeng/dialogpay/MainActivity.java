package com.jiangfeng.dialogpay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button payBtn = findViewById(R.id.pay_btn);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPay.createPayDialog(MainActivity.this, new CallbackPay() {
                    @Override
                    public void close() {
                        DialogPay.dismissDialog();
                    }

                    @Override
                    public void forgetPassword() {
                        Toast.makeText(getBaseContext(), "忘记密码", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void passwordFull(String password) {
                        Toast.makeText(getBaseContext(), password, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
