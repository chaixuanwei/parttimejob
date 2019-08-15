package com.example.myapplication.message.fragment;


import android.support.v4.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.MessageModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemMessageFragment extends BaseMvpFragment<CommonPresenter, MessageModel> {
    static SystemMessageFragment fragment;

    public static SystemMessageFragment newInstance() {
        if (fragment == null) fragment = new SystemMessageFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message_data;
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
