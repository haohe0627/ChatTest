package com.tc.chattest.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tc.chattest.MyApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by haohe on 2017/6/30 0030.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        unbinder = ButterKnife.bind(this);

        init();
    }

    protected abstract void init();
    protected abstract int setLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected MyApplication getMyApp(){
        return (MyApplication) getApplication();
    }

    protected void redictTo(Class<? extends BaseActivity> activity){
        startActivity(new Intent(BaseActivity.this, activity));
    }
}
