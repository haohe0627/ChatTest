package com.tc.chattest.customer;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tc.chattest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by haohe on 2017/7/4 0004.
 */

public class CustomerBtn extends FrameLayout {

    @BindView(R.id.cus_img)
    ImageView cusImg;
    @BindView(R.id.cus_txt)
    TextView cusTxt;
    private Context context;
    private Class mClass;
    private Fragment fragment;

    public CustomerBtn(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CustomerBtn(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
        initXml(attrs);
    }

    public CustomerBtn(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
        initXml(attrs);
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.customer_btn, this, true);
        ButterKnife.bind(this);
    }

    private void initXml(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomerBtn);

        if(typedArray.hasValue(R.styleable.CustomerBtn_txt)){
            cusTxt.setText(typedArray.getString(R.styleable.CustomerBtn_txt));
        }
        if(typedArray.hasValue(R.styleable.CustomerBtn_img)){
            cusImg.setImageResource(typedArray.getResourceId(R.styleable.CustomerBtn_img, 0));
        }

        typedArray.recycle();
    }

    public Class getmClass() {
        return mClass;
    }

    public void setmClass(Class mClass) {
        this.mClass = mClass;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
