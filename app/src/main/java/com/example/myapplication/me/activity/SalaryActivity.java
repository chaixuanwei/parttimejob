package com.example.myapplication.me.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.config.LoadConfig;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.me.adapter.SalaryAdapter;
import com.example.myapplication.me.bean.MyIssusBean;
import com.example.myapplication.me.bean.SalaryBean;
import com.example.myapplication.model.MeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SalaryActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.salary_rv)
    RecyclerView salaryRv;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.salary_srl)
    SmartRefreshLayout salarySrl;
    private int mPage = 1;
    ArrayList<SalaryBean.DataBean> mList = new ArrayList<>();
    private SalaryAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_salary;
    }

    @Override
    public void initView() {
        initRecycleView(salaryRv, salarySrl);
        mAdapter = new SalaryAdapter(this,mList);
        salaryRv.setLayoutManager(new LinearLayoutManager(this));
        salaryRv.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_SALARY, LoadConfig.NORMAL,mPage);
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
        Log.e("工资未结列表", "onError: " + "工资未结列表");
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_SALARY:
                SalaryBean mSalaryBeans = (SalaryBean) t[0];
                int upordown = (int) t[1];
                if (upordown == LoadConfig.REFRESH) {
                    mList.clear();
                    salarySrl.finishRefresh();
                } else if (upordown == LoadConfig.LOADMORE) {
                    salarySrl.finishLoadMore();
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
        mPresenter.getData(ApiConfig.GET_SALARY, LoadConfig.REFRESH, mPage);
    }

    @Override
    public void loadMore() {
        mPage += mPage;
        mPresenter.getData(ApiConfig.GET_SALARY, LoadConfig.LOADMORE, mPage);
    }
}
