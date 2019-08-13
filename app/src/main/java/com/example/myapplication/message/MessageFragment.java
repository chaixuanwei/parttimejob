package com.example.myapplication.message;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseMvpFragment<CommonPresenter, MessageModel> {
    static MessageFragment fragment;
    @BindView(R.id.message_table)
    TabLayout messageTable;
    @BindView(R.id.message_vp)
    ViewPager messageVp;
    @BindView(R.id.message_tbr)
    Toolbar messageTbr;
    List<String> mTitleList = new ArrayList<>();
    List<Fragment> mFragmentList = new ArrayList<>();
    private MessageVpAdapter mAdapter;

    public static MessageFragment newInstance() {
        if (fragment == null) fragment = new MessageFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView() {
        mAdapter = new MessageVpAdapter(getChildFragmentManager(), mFragmentList, mTitleList);
        messageVp.setAdapter(mAdapter);
        messageTable.setupWithViewPager(messageVp);
    }

    @Override
    public void initData() {
        if (mTitleList.size() == 0 && mFragmentList.size() == 0) {
            mTitleList.add("系统消息");
            mFragmentList.add(SystemMessageFragment.newInstance());
            mTitleList.add("聊天记录");
            mFragmentList.add(ChatRecordFragment.newInstance());
            mAdapter.notifyDataSetChanged();
        }
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
