package com.tc.chattest;

import android.util.Log;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMError;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.tc.chattest.ui.FriendsFragment;
import com.tc.chattest.utils.SharedPreferenceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haohe on 2017/7/3 0003.
 */

public class ChatHelper {

    private static ChatHelper instance;

    private boolean isSyncingGroupsWithServer = false;
    private boolean isSyncingContactsWithServer = false;
    private boolean isSyncingBlackListWithServer = false;
    private boolean isGroupsSyncedWithServer = false;
    private boolean isContactsSyncedWithServer = false;
    private boolean isBlackListSyncedWithServer = false;

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

                asyncFetchContactsFromServer(null);
            }
        };

        //register connection listener
        EMClient.getInstance().addConnectionListener(connectionListener);
        EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
    }

    public void asyncFetchContactsFromServer(final EMValueCallBack<List<String>> callback){
//        if(isSyncingContactsWithServer){
//            return;
//        }
//
//        isSyncingContactsWithServer = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Map<String, EaseUser> userlist = new HashMap<>();
                    for (String username : usernames) {
                        EaseUser user = new EaseUser(username);
                        EaseCommonUtils.setUserInitialLetter(user);
                        userlist.put(username, user);
                    }

                    FriendsFragment.clearMap();
                    FriendsFragment.setFriendsMap(userlist);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    if(callback != null){
                        callback.onError(e.getErrorCode(), e.toString());
                    }
                }
            }
        }).start();
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
            Log.i("contact-info","//////////////////////////////////////////被好友删除！！//////////////////////////////////////////////"+username);
        }

        @Override
        public void onFriendRequestDeclined(String username) { //增加了联系人时回调此方法
            Log.i("contact-info","//////////////////////////////////////////增加了联系人！！//////////////////////////////////////////////"+username);
        }

    }
}
