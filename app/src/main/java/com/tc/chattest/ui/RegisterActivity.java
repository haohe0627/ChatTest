package com.tc.chattest.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.tc.chattest.R;
import com.tc.chattest.base.BaseActivity;
import com.tc.chattest.customer.ClearEditText;

import butterknife.BindView;

/**
 * Created by haohe on 2017/6/30 0030.
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.accountTxt)
    ClearEditText accountTxt;
    @BindView(R.id.accountPwd)
    ClearEditText accountPwd;
    @BindView(R.id.registBtn)
    Button registBtn;

    @Override
    protected void init() {

        registBtn.setOnClickListener(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registBtn:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            EMClient.getInstance().createAccount(accountTxt.getText().toString(), accountPwd.getText().toString());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                            redictTo(MainActivity.class);
                            finish();

                        } catch (HyphenateException e) {

                            int errorCode=e.getErrorCode();
                            if(errorCode==EMError.NETWORK_ERROR){
//                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_anomalies), Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_ALREADY_EXIST){
//                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.User_already_exists), Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_AUTHENTICATION_FAILED){
//                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.registration_failed_without_permission), Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_ILLEGAL_ARGUMENT){
//                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.illegal_user_name),Toast.LENGTH_SHORT).show();
                            }else{
//                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registration_failed), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }).start();

                break;
        }
    }
}
