package com.example.myapplication.me.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.me.adapter.NoOrderAdapter;
import com.example.myapplication.me.adapter.YetOrderAdapter;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class YetOrderFragment extends BaseMvpFragment<CommonPresenter, MeModel> {
    static YetOrderFragment fragment;
    @BindView(R.id.order_rv)
    RecyclerView orderRv;
    @BindView(R.id.order_srl)
    SwipeRefreshLayout orderSrl;
    private YetOrderAdapter mYetOrderAdapter;

    public static YetOrderFragment newInstance() {
        if (fragment == null) fragment = new YetOrderFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mYetOrderAdapter = new YetOrderAdapter(getActivity());
        orderRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderRv.setAdapter(mYetOrderAdapter);
    }

    @Override
    public CommonPresenter getPresenter() {
        return new CommonPresenter();
    }

    @Override
    public MeModel getModel() {
        return new MeModel();
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

}
