package com.sxxh.linghuo.me.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpFragment;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.me.adapter.YetOrderAdapter;
import com.sxxh.linghuo.me.bean.MyIssusBean;
import com.sxxh.linghuo.model.MeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class YetOrderFragment extends BaseMvpFragment<CommonPresenter, MeModel> {
    static YetOrderFragment fragment;
    @BindView(R.id.order_rv)
    RecyclerView orderRv;
    @BindView(R.id.order_srl)
    SmartRefreshLayout orderSrl;
    private int mPage = 1;
    private YetOrderAdapter mYetOrderAdapter;
    ArrayList<MyIssusBean.DataBean> mList = new ArrayList<>();

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
        mList.clear();
        initRecycleView(orderRv,orderSrl);
        mYetOrderAdapter = new YetOrderAdapter(getActivity(),mList);
        orderRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderRv.setAdapter(mYetOrderAdapter);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.ORDER_RECEIVING, LoadConfig.NORMAL, 1, mPage);
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
        Log.e("已接单", "onError: "+"已接单" + e.getMessage());
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
                mYetOrderAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void refresh() {
        mPage = 1;
        mPresenter.getData(ApiConfig.ORDER_RECEIVING, LoadConfig.REFRESH, 1, mPage);
    }

    @Override
    public void loadMore() {
        mPage += mPage;
        mPresenter.getData(ApiConfig.ORDER_RECEIVING, LoadConfig.LOADMORE, 1, mPage);
    }
}
