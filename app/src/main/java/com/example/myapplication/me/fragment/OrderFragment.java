package com.example.myapplication.me.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.config.LoadConfig;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.me.adapter.NoOrderAdapter;
import com.example.myapplication.me.bean.MyIssusBean;
import com.example.myapplication.model.MeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseMvpFragment<CommonPresenter, MeModel> {
    static OrderFragment fragment;
    @BindView(R.id.order_rv)
    RecyclerView orderRv;
    @BindView(R.id.order_srl)
    SmartRefreshLayout orderSrl;
    private int mPage = 1;
    private NoOrderAdapter mNoOrderAdapter;
    ArrayList<MyIssusBean.DataBean> mList = new ArrayList<>();

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
        initRecycleView(orderRv, orderSrl);
        mNoOrderAdapter = new NoOrderAdapter(getActivity(), mList);
        orderRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderRv.setAdapter(mNoOrderAdapter);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.ORDER_RECEIVING, LoadConfig.NORMAL, 0, mPage);
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
    public void onError(int whichApi, Throwable e) {
        Log.e("未接单", "onError: " + "未接单");
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.ORDER_RECEIVING:
                MyIssusBean mMyIssusBeans = (MyIssusBean) t[0];
                int upordown = (int) t[1];
                if (upordown == LoadConfig.REFRESH) {
                    mList.clear();
                    orderSrl.finishRefresh();
                } else if (upordown == LoadConfig.LOADMORE) {
                    orderSrl.finishLoadMore();
                }
                List<MyIssusBean.DataBean> mData = mMyIssusBeans.getData();
                mList.addAll(mData);
                mNoOrderAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void refresh() {
        mPage = 1;
        mPresenter.getData(ApiConfig.ORDER_RECEIVING, LoadConfig.REFRESH, 0, mPage);
    }

    @Override
    public void loadMore() {
        mPage += mPage;
        mPresenter.getData(ApiConfig.ORDER_RECEIVING, LoadConfig.LOADMORE, 0, mPage);
    }
}
