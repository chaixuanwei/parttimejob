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
import com.sxxh.linghuo.me.adapter.SalaryAdapter;
import com.sxxh.linghuo.me.bean.SalaryBean;
import com.sxxh.linghuo.model.MeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
        salaryRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
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
        Log.e("工资未结列表", "onError: " + "工资未结列表" + e.getMessage());
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
