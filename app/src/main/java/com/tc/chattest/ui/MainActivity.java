package com.tc.chattest.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.tc.chattest.R;
import com.tc.chattest.base.BaseActivity;
import com.tc.chattest.customer.CustomerBtn;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.btn1)
    CustomerBtn chat;
    @BindView(R.id.btn2)
    CustomerBtn friendList;
    @BindView(R.id.btn3)
    CustomerBtn setting;

    private ChatListFragment chatListFragment;
    private FriendsFragment friendsFragment;
    private SettingFragment settingFragment;

    private CustomerBtn currentBtn;

    @Override
    protected void init() {

        chatListFragment = new ChatListFragment();
        friendsFragment = new FriendsFragment();
        settingFragment = new SettingFragment();

        chat.setmClass(chatListFragment.getClass());
        friendList.setmClass(friendsFragment.getClass());
        setting.setmClass(settingFragment.getClass());

        chat.setOnClickListener(this);
        friendList.setOnClickListener(this);
        setting.setOnClickListener(this);

        clearBtn();

        chat.setSelected(true);
        selectBtn(chat);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                selectBtn(chat);
                break;
            case R.id.btn2:
                selectBtn(friendList);
                break;
            case R.id.btn3:
                selectBtn(setting);
                break;
        }
    }

    private void selectBtn(CustomerBtn btn){

        btn.setSelected(true);
        changeFragment(currentBtn, btn);
        currentBtn = btn;
    }

    private void changeFragment(CustomerBtn oldBtn, CustomerBtn newBtn){

        if(oldBtn == newBtn){
            return;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if(oldBtn != null){
            oldBtn.setSelected(false);
            ft.detach(oldBtn.getFragment());
        }

//        if(oldBtn.getmClass() == null || newBtn.getmClass() == null){
//            return;
//        }

        if(newBtn.getFragment() == null){
            Fragment fragment = Fragment.instantiate(this, newBtn.getmClass().getName());
            newBtn.setFragment(fragment);
            ft.add(R.id.content, fragment, newBtn.getmClass().getName());
        }else {
            ft.attach(newBtn.getFragment());
        }

        ft.commit();
    }

    private void clearBtn(){
        List<Fragment>fragments = getSupportFragmentManager().getFragments();
        if(fragments != null && fragments.size()>0){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            for(Fragment f : fragments){
                ft.remove(f);
            }
            ft.commitNow();
        }
    }

}
