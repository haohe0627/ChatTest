package com.tc.chattest.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.widget.EaseAlertDialog;
import com.hyphenate.exceptions.HyphenateException;
import com.tc.chattest.R;
import com.tc.chattest.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by haohe on 2017/7/5 0005.
 */

public class ActivityAddContact extends BaseActivity {

    @BindView(R.id.add_list_friends)
    TextView addListFriends;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.edit_note)
    EditText editNote;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.name)
    TextView nameText;
    @BindView(R.id.indicator)
    Button indicator;
    @BindView(R.id.ll_user)
    RelativeLayout llUser;

    private String toAddUsername;

    @Override
    protected void init() {

        search.setOnClickListener(this);
        indicator.setOnClickListener(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_contact;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search:
                searchContact();
                break;
            case R.id.indicator:
                addFriend();
                break;
        }
    }

    private void addFriend(){

        final ProgressDialog progressDialog = new ProgressDialog(ActivityAddContact.this);
        Log.i("info","---------------------------start dialog------------------------------");
        String stri = getResources().getString(R.string.Is_sending_a_request);
        progressDialog.setMessage(stri);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Log.i("info","---------------------------start adding------------------------------");
                    EMClient.getInstance().contactManager().addContact(editNote.getText().toString(), "加你咋的");

                    progressDialog.dismiss();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String s1 = getResources().getString(R.string.send_successful);
                            Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
                            Log.i("info","--------------------------- adding successful------------------------------");
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            String s2 = getResources().getString(R.string.Request_add_buddy_failure);
                            Toast.makeText(getApplicationContext(), s2 + e.getMessage(), Toast.LENGTH_LONG).show();
                            Log.i("info","---------------------------error!!!!------------------------------");
                        }
                    });
                }
            }
        }).start();
    }

    private void searchContact() {
        final String name = editNote.getText().toString();
        String saveText = search.getText().toString();

        if (getString(R.string.button_search).equals(saveText)) {
            toAddUsername = name;
            if(TextUtils.isEmpty(name)) {
                new EaseAlertDialog(this, R.string.Please_enter_a_username).show();
                return;
            }

            // TODO you can search the user from your app server here.

            //show the userame and add button if user exist
            llUser.setVisibility(View.VISIBLE);
            nameText.setText(toAddUsername);

        }
    }

    public void back(View v) {
        finish();
    }

}
