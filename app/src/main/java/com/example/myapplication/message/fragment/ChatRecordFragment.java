package com.example.myapplication.message.fragment;


import android.support.v4.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.MessageModel;

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
        return new CommonPresenter();
    }

    @Override
    public MessageModel getModel() {
        return new MessageModel();
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }
}
