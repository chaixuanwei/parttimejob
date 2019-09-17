package com.sxxh.linghuo.me.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.me.adapter.WaitAppraiseAdapter;
import com.sxxh.linghuo.me.bean.SalaryBean;
import com.sxxh.linghuo.me.bean.WaitAppraiseBean;
import com.sxxh.linghuo.model.MeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WaitAppraiseActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.wait_appraise_rv)
    RecyclerView waitAppraiseRv;
    @BindView(R.id.wait_appraise_srl)
    SmartRefreshLayout waitAppraiseSrl;
    private int mPage = 1;
    private WaitAppraiseAdapter mAdapter;
    ArrayList<WaitAppraiseBean.DataBean> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_wait_appraise;
    }

    @Override
    public void initView() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initRecycleView(waitAppraiseRv, waitAppraiseSrl);
        mAdapter = new WaitAppraiseAdapter(this,mList);
        waitAppraiseRv.setLayoutManager(new LinearLayoutManager(this));
        waitAppraiseRv.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.WAIT_APPRAISE, LoadConfig.NORMAL, mPage);
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
        Log.e("待评价", "onError: " + "待评价" + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.WAIT_APPRAISE:
                WaitAppraiseBean mWaitAppraiseBean = (WaitAppraiseBean) t[0];
                int upordown = (int) t[1];
                if (upordown == LoadConfig.REFRESH) {
                    mList.clear();
                    waitAppraiseSrl.finishRefresh();
                } else if (upordown == LoadConfig.LOADMORE) {
                    waitAppraiseSrl.finishLoadMore();
                }
                List<WaitAppraiseBean.DataBean> mData = mWaitAppraiseBean.getData();
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
        mPresenter.getData(ApiConfig.WAIT_APPRAISE, LoadConfig.REFRESH, mPage);
    }

    @Override
    public void loadMore() {
        mPage += mPage;
        mPresenter.getData(ApiConfig.WAIT_APPRAISE, LoadConfig.LOADMORE, mPage);
    }
}
