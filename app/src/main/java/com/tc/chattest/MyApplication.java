package com.tc.chattest;
import com.hyphenate.easeui.BaseApplication;

/**
 * Created by haohe on 2017/6/30 0030.
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initHelper();
    }

    private void initHelper(){
        ChatHelper.getInstance().init();
    }
}
