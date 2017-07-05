package com.tc.chattest.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.widget.ContactItemView;
import com.tc.chattest.R;

/**
 * Created by haohe on 2017/7/4 0004.
 */

public class FriendsFragment extends EaseContactListFragment {

    private ContactItemView applicationItem;

    @Override
    protected void initView() {
        super.initView();
        View headerView = LayoutInflater.from(getActivity()).inflate(com.hyphenate.easeui.R.layout.em_contractor_header, null);
        HeaderItemClickListener clickListener = new HeaderItemClickListener();
        applicationItem = (ContactItemView) headerView.findViewById(R.id.application_item);
        applicationItem.setOnClickListener(clickListener);
        headerView.findViewById(R.id.group_item).setOnClickListener(clickListener);
        headerView.findViewById(R.id.chat_room_item).setOnClickListener(clickListener);
        headerView.findViewById(R.id.robot_item).setOnClickListener(clickListener);
        listView.addHeaderView(headerView);

        registerForContextMenu(listView);
        titleBar.setRightImageResource(R.drawable.em_add);
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityAddContact.class));
            }
        });
    }

    @Override
    protected void setUpView() {
        super.setUpView();
    }

    protected class HeaderItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.application_item:
                    // 进入申请与通知页面
                    getActivity().startActivity(new Intent(getActivity(), ActivityNewFriendMsg.class));
                    break;
                case R.id.group_item:
                    // 进入群聊列表页面
                    break;
                case R.id.chat_room_item:
                    //进入聊天室列表页面
                    break;
                case R.id.robot_item:
                    //进入Robot列表页面
                    break;

                default:
                    break;
            }
        }

    }
}
