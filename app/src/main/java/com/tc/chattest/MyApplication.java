package com.tc.chattest;
import android.util.Log;

import com.hyphenate.easeui.BaseApplication;

/**
 * Created by haohe on 2017/6/30 0030.
 */

public class MyApplication extends BaseApplication {

    public static MyApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Log.i("info","---------------------------------初始化helper--------------------------------");
        initHelper();
    }

    private void initHelper(){
        ChatHelper.getInstance().init();
    }

    public static MyApplication getContext(){
        return context;
    }

}
