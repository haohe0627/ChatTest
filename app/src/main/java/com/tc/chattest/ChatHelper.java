package com.tc.chattest;

import android.util.Log;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.EMLog;
import com.tc.chattest.utils.SharedPreferenceUtil;

/**
 * Created by haohe on 2017/7/3 0003.
 */

public class ChatHelper {

    private static ChatHelper instance;

    public synchronized static ChatHelper getInstance(){
        if(instance == null){
            instance = new ChatHelper();
        }
        return instance;
    }

    public void init(){

        setListeners();

    }

    private void setListeners(){

        EMConnectionListener connectionListener = new EMConnectionListener() {
            @Override
            public void onDisconnected(int error) {
                EMLog.d("global listener", "onDisconnect" + error);
                if (error == EMError.USER_REMOVED) {
                    // 显示帐号已经被移除
                } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    // 显示帐号在其他设备登录
                } else if (error == EMError.SERVER_SERVICE_RESTRICTED) {

                }
            }

            @Override
            public void onConnected() {
                // in case group and contact were already synced, we supposed to notify sdk we are ready to receive the events

            }
        };

        //register connection listener
        EMClient.getInstance().addConnectionListener(connectionListener);
        EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
    }

    class MyContactListener implements EMContactListener {

        @Override
        public void onContactAdded(String username) { //好友请求被同意
            Log.i("contact-info","//////////////////////////////////////////好友请求被同意！！//////////////////////////////////////////////"+username);
        }

        @Override
        public void onContactDeleted(String username) { //好友请求被拒绝
            Log.i("contact-info","//////////////////////////////////////////好友请求被拒绝！！//////////////////////////////////////////////"+username);
        }

        @Override
        public void onContactInvited(String username, String reason) { //收到好友邀请
            Log.i("contact-info","//////////////////////////////////////////收到好友邀请！！//////////////////////////////////////////////"+username);
            String names = (String) SharedPreferenceUtil.get("invate_names", "");
            names += username+";";
            SharedPreferenceUtil.set("invate_names", names);
        }

        @Override
        public void onFriendRequestAccepted(String username) { //被删除时回调此方法

        }

        @Override
        public void onFriendRequestDeclined(String username) { //增加了联系人时回调此方法

        }

    }
}
