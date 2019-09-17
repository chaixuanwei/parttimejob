package com.sxxh.linghuo.me.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.me.adapter.FinancialDetailsAdapter;
import com.sxxh.linghuo.me.bean.FinancialDetailsBean;
import com.sxxh.linghuo.me.bean.SalaryBean;
import com.sxxh.linghuo.model.MeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FinancialDetailsActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.financial_details_rv)
    RecyclerView financialDetailsRv;
    @BindView(R.id.financial_details_srl)
    SmartRefreshLayout financialDetailsSrl;
    private FinancialDetailsAdapter mAdapter;
    ArrayList<FinancialDetailsBean.DataBean> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_financial_details;
    }

    @Override
    public void initView() {
        initRecycleView(financialDetailsRv, financialDetailsSrl);
        mAdapter = new FinancialDetailsAdapter(this,mList);
        financialDetailsRv.setLayoutManager(new LinearLayoutManager(this));
        financialDetailsRv.setAdapter(mAdapter);
        financialDetailsRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.FINANCIAL_DETAIL, LoadConfig.NORMAL);
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
        Log.e("资金明细", "onError: 资金明细" + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.FINANCIAL_DETAIL:
                FinancialDetailsBean mFinancialDetailsBeans = (FinancialDetailsBean) t[0];
                int upordown = (int) t[1];
                if (upordown == LoadConfig.REFRESH) {
                    mList.clear();
                    financialDetailsSrl.finishRefresh();
                } else if (upordown == LoadConfig.LOADMORE) {
                    financialDetailsSrl.finishLoadMore();
                }
                mList.addAll(mFinancialDetailsBeans.getData());
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
        mPresenter.getData(ApiConfig.FINANCIAL_DETAIL, LoadConfig.REFRESH);
    }

    @Override
    public void loadMore() {
        mPresenter.getData(ApiConfig.FINANCIAL_DETAIL, LoadConfig.LOADMORE);
    }
}
