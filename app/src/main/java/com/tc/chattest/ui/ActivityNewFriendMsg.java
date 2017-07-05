package com.tc.chattest.ui;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.tc.chattest.R;
import com.tc.chattest.adapter.NewFriendMsgAdapter;
import com.tc.chattest.base.BaseActivity;
import com.tc.chattest.utils.SharedPreferenceUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by haohe on 2017/7/5 0005.
 */

public class ActivityNewFriendMsg extends BaseActivity {

    private NewFriendMsgAdapter adapter;

    @Override
    protected void init() {

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter = new NewFriendMsgAdapter(this) );
        String x = (String) SharedPreferenceUtil.get("invate_names","");
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
        if(!"".equals(x)){

            String []xx = x.split(";");
            List<String >xxx = Arrays.asList(xx);
            adapter.setList(xxx);
        }
    }

    @Override
    protected int setLayoutId() {
        return com.hyphenate.easeui.R.layout.ease_activity_new_friends_msg;
    }

    @Override
    public void onClick(View v) {

    }

    public void back(View view) {
        finish();
    }
}
