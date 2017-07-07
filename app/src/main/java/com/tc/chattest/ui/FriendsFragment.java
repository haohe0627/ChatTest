package com.tc.chattest.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.widget.ContactItemView;
import com.tc.chattest.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by haohe on 2017/7/4 0004.
 */

public class FriendsFragment extends EaseContactListFragment {

    private ContactItemView applicationItem;
    private static Map<String, EaseUser> contactList;

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

        setContactsMap(contactList);

    }

    public static void setFriendsMap(Map<String, EaseUser> list){
        contactList = new HashMap<>();
        contactList.putAll(list);
    }

    public static void clearMap(){
        if( contactList != null &&contactList.size()>0){
            contactList.clear();
        }
    }

    @Override
    protected void setUpView() {
        super.setUpView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EaseUser user = (EaseUser)listView.getItemAtPosition(position);
                if (user != null) {
                    String username = user.getUsername();
                    // demo中直接进入聊天页面，实际一般是进入用户详情页
                    startActivity(new Intent(getActivity(), ChatActivity.class).putExtra("userId", username));
                }
            }
        });
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
