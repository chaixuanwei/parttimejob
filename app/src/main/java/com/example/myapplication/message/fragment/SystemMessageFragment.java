package com.example.myapplication.message.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.message.adapter.SystemMessageAdapter;
import com.example.myapplication.model.MessageModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemMessageFragment extends BaseMvpFragment<CommonPresenter, MessageModel> {
    static SystemMessageFragment fragment;
    @BindView(R.id.system_message_rv)
    RecyclerView systemMessageRv;
    Unbinder unbinder;

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
        SystemMessageAdapter mAdapter = new SystemMessageAdapter(getActivity());
        systemMessageRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        systemMessageRv.setAdapter(mAdapter);
        systemMessageRv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
