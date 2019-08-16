package com.example.myapplication.me.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.me.adapter.WorkingAdapter;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkingActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.working_rv)
    RecyclerView workingRv;
    @BindView(R.id.working_srl)
    SwipeRefreshLayout workingSrl;
    private WorkingAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_working;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mAdapter = new WorkingAdapter(this);
        workingRv.setLayoutManager(new LinearLayoutManager(this));
        workingRv.setAdapter(mAdapter);
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

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
