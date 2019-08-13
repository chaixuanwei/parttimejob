package com.example.myapplication.message;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatRecordFragment extends BaseMvpFragment<CommonPresenter, MessageModel> {
    static ChatRecordFragment fragment;

    public static ChatRecordFragment newInstance() {
        if (fragment == null) fragment = new ChatRecordFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chat_record;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public CommonPresenter getPresenter() {
        return null;
    }

    @Override
    public MessageModel getModel() {
        return null;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }
}
