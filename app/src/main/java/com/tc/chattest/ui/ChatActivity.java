package com.tc.chattest.ui;

import android.util.Log;
import android.view.View;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.tc.chattest.R;
import com.tc.chattest.base.BaseActivity;

import java.util.List;

/**
 * Created by haohe on 2017/7/4 0004.
 */

public class ChatActivity extends BaseActivity {

    @Override
    protected void init() {

        String username = getIntent().getStringExtra("userId");

        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(username);
        //获取此会话的所有消息
        if(conversation == null){
            Log.i("message_size", "\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\--------无消息----------\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        }else{
            List<EMMessage> messages = conversation.getAllMessages();
            Log.i("message_size", "\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"+messages.size()+"\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
