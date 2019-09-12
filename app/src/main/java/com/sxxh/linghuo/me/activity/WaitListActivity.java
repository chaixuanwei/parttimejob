package com.sxxh.linghuo.me.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.me.adapter.WaitListAdapter;
import com.sxxh.linghuo.me.bean.SalaryBean;
import com.sxxh.linghuo.model.MeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WaitListActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.wait_list_rv)
    RecyclerView waitListRv;
    @BindView(R.id.wait_list_srl)
    SmartRefreshLayout waitListSrl;
    private WaitListAdapter mAdapter;
    private int mPage = 1;
    ArrayList<SalaryBean.DataBean> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_wait_list;
    }

    @Override
    public void initView() {
        initRecycleView(waitListRv, waitListSrl);
        mAdapter = new WaitListAdapter(this,mList);
        waitListRv.setLayoutManager(new LinearLayoutManager(this));
        waitListRv.setAdapter(mAdapter);
        waitListRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_WAIT, LoadConfig.NORMAL,mPage);
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
        Log.e("待录取列表", "onError: " + "待录取列表");
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_WAIT:
                SalaryBean mSalaryBeans = (SalaryBean) t[0];
                int upordown = (int) t[1];
                if (upordown == LoadConfig.REFRESH) {
                    mList.clear();
                    waitListSrl.finishRefresh();
                } else if (upordown == LoadConfig.LOADMORE) {
                    waitListSrl.finishLoadMore();
                }
                List<SalaryBean.DataBean> mData = mSalaryBeans.getData();
                mList.addAll(mData);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    public void refresh() {
        mPage = 1;
        mPresenter.getData(ApiConfig.GET_WAIT, LoadConfig.REFRESH, mPage);
    }

    @Override
    public void loadMore() {
        mPage += mPage;
        mPresenter.getData(ApiConfig.GET_WAIT, LoadConfig.LOADMORE, mPage);
    }
}
