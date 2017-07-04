package com.tc.chattest;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.EMLog;

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
    }
}
