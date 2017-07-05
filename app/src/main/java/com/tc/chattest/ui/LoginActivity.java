package com.tc.chattest.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.tc.chattest.R;
import com.tc.chattest.base.BaseActivity;
import com.tc.chattest.customer.ClearEditText;
import com.tc.chattest.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by haohe on 2017/6/30 0030.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.re_cb_txt)
    TextView reCbTxt;
    @BindView(R.id.re_cb)
    CheckBox reCb;
    @BindView(R.id.accountTxt)
    ClearEditText accountTxt;
    @BindView(R.id.accountPwd)
    ClearEditText accountPwd;
    @BindView(R.id.register)
    TextView register;

    @Override
    protected void init() {

        setListener();
    }

    private void setListener() {
        loginBtn.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        EMClient.getInstance().login(accountTxt.getText().toString(),accountPwd.getText().toString(),new EMCallBack() {//回调
                            @Override
                            public void onSuccess() {
                                EMClient.getInstance().groupManager().loadAllGroups();
                                EMClient.getInstance().chatManager().loadAllConversations();
                                Log.d("main", accountTxt.getText().toString()+"登录聊天服务器成功！");

                                SharedPreferenceUtil.SP_NAME = accountTxt.getText().toString();
                                redictTo(MainActivity.class);
                                finish();
                            }

                            @Override
                            public void onProgress(int progress, String status) {

                            }

                            @Override
                            public void onError(int code, String message) {
                                Log.d("main", "登录聊天服务器失败！");
                            }
                        });
                    }
                }).start();

                break;
            case R.id.register:
                redictTo(RegisterActivity.class);
                break;
        }
    }
}
