package com.tc.chattest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by haohe on 2017/7/4 0004.
 */

public abstract class BaseFragment extends Fragment {

    protected Unbinder unbinder;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(rootView == null){
            rootView = inflater.inflate(setLayoutId(), container, false);
            unbinder = ButterKnife.bind(this, rootView);
        }

        return rootView;
    }

    protected abstract void init();
    protected abstract int setLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
