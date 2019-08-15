package com.example.myapplication.me.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.me.adapter.NoOrderAdapter;
import com.example.myapplication.me.adapter.YetOrderAdapter;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseMvpFragment<CommonPresenter, MeModel> {
    static OrderFragment fragment;
    @BindView(R.id.order_rv)
    RecyclerView orderRv;
    @BindView(R.id.order_srl)
    SwipeRefreshLayout orderSrl;
    private int mParam;
    private NoOrderAdapter mNoOrderAdapter;
    private YetOrderAdapter mYetOrderAdapter;

    public static OrderFragment newInstance() {
        if (fragment == null) fragment = new OrderFragment();
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
        mNoOrderAdapter = new NoOrderAdapter(getActivity());
        orderRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderRv.setAdapter(mNoOrderAdapter);
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
