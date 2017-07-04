package com.tc.chattest.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.easeui.widget.EaseConversationList;
import com.tc.chattest.R;
import com.tc.chattest.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by haohe on 2017/6/30 0030.
 */

public class ChatListFragment extends BaseFragment {

    @BindView(R.id.conversationList)
    EaseConversationList conversationList;

    @Override
    protected void init() {
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_chat_list;
    }
}
