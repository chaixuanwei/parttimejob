package com.sxxh.linghuo.me.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.me.adapter.FinancialDetailsAdapter;
import com.sxxh.linghuo.model.MeModel;

import butterknife.BindView;
import butterknife.OnClick;

public class FinancialDetailsActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.financial_details_rv)
    RecyclerView financialDetailsRv;
    @BindView(R.id.financial_details_srl)
    SwipeRefreshLayout financialDetailsSrl;
    private FinancialDetailsAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_financial_details;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mAdapter = new FinancialDetailsAdapter(this);
        financialDetailsRv.setLayoutManager(new LinearLayoutManager(this));
        financialDetailsRv.setAdapter(mAdapter);
        financialDetailsRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
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

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
