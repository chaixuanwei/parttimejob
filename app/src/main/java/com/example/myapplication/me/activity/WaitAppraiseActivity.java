package com.example.myapplication.me.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.me.adapter.WaitAppraiseAdapter;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WaitAppraiseActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.wait_appraise_rv)
    RecyclerView waitAppraiseRv;
    @BindView(R.id.wait_appraise_srl)
    SwipeRefreshLayout waitAppraiseSrl;
    private WaitAppraiseAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wait_appraise;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAdapter = new WaitAppraiseAdapter(this);
        waitAppraiseRv.setLayoutManager(new LinearLayoutManager(this));
        waitAppraiseRv.setAdapter(mAdapter);
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
