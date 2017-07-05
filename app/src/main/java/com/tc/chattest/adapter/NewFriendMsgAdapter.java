package com.tc.chattest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.tc.chattest.R;
import com.tc.chattest.base.BaseActivity;
import com.tc.chattest.utils.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haohe on 2017/7/5 0005.
 */

public class NewFriendMsgAdapter extends BaseAdapter {

    private Context context;
    private List<String> list = new ArrayList<>();

    public NewFriendMsgAdapter(Context context){
        this.context =context;
    }

    public void setList(List<String> list){

        if(list != null && list.size()>0){
            this.list = list;
            notifyDataSetChanged();
        }else{
            this.list.clear();
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder;
        if(convertView == null){
            convertView = View.inflate(context, R.layout.em_row_invite_msg, null);

            holder = new Holder();
            convertView.setTag(holder);

            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.agree = (Button) convertView.findViewById(R.id.agree);
            holder.user_state = (Button) convertView.findViewById(R.id.user_state);
        }else{
            holder = (Holder) convertView.getTag();
        }

        holder.name.setText(list.get(position));
        holder.agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 同意
                positiveBtn(position);
            }
        });
        holder.user_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 拒绝
                negativeBtn(position);
            }
        });
        return convertView;
    }

    private void positiveBtn(final int position){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().contactManager().acceptInvitation(list.get(position));
                    deleteFriendMsg(position);
                } catch (final Exception e) {
                    ((BaseActivity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    private void negativeBtn(final int position){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().contactManager().declineInvitation(list.get(position));
                    deleteFriendMsg(position);
                } catch (final Exception e) {
                    ((BaseActivity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    private void deleteFriendMsg(int position){
        list.remove(position);
        setList(list);
        notifyDataSetChanged();
        String xx = "";
        if( list.size()>0 ){
            for(String s : list){
                xx +=s +";";
            }
        }else{
            list.clear();
        }
        Log.i("list", "/////////////////////////////////////////"+xx+"////////////////////////////////////////");
        SharedPreferenceUtil.set("invate_names", xx);
    }

    class Holder{
        TextView name;
        Button agree, user_state;
    }
}
