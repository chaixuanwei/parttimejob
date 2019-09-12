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
import com.sxxh.linghuo.me.adapter.WorkingAdapter;
import com.sxxh.linghuo.me.bean.WorkingBean;
import com.sxxh.linghuo.model.MeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class WorkingActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.working_rv)
    RecyclerView workingRv;
    @BindView(R.id.working_srl)
    SmartRefreshLayout workingSrl;
    private WorkingAdapter mAdapter;
    int mPage = 1;
    ArrayList<WorkingBean.DataBean> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_working;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_WORKING_LIST, LoadConfig.NORMAL,mPage);
        initRecycleView(workingRv, workingSrl);
        mAdapter = new WorkingAdapter(this,mList);
        workingRv.setLayoutManager(new LinearLayoutManager(this));
        workingRv.setAdapter(mAdapter);
        workingRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
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
        Log.e("工作中", "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_WORKING_LIST:
                WorkingBean mWorkingBeans = (WorkingBean) t[0];
                int upordown = (int) t[1];
                if (upordown == LoadConfig.REFRESH) {
                    mList.clear();
                    workingSrl.finishRefresh();
                } else if (upordown == LoadConfig.LOADMORE) {
                    workingSrl.finishLoadMore();
                }
                mList.addAll(mWorkingBeans.getData());
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
        mPresenter.getData(ApiConfig.GET_WORKING_LIST, LoadConfig.REFRESH, mPage);
    }

    @Override
    public void loadMore() {
        mPage += mPage;
        mPresenter.getData(ApiConfig.GET_WORKING_LIST, LoadConfig.LOADMORE, mPage);
    }
}
