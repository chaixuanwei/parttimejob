package com.example.myapplication.me.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.me.adapter.SalaryAdapter;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SalaryActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.salary_rv)
    RecyclerView salaryRv;
    @BindView(R.id.back)
    ImageView back;

    @Override
    public int getLayoutId() {
        return R.layout.activity_salary;
    }

    @Override
    public void initView() {
        SalaryAdapter mAdapter = new SalaryAdapter(this);
        salaryRv.setLayoutManager(new LinearLayoutManager(this));
        salaryRv.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

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
