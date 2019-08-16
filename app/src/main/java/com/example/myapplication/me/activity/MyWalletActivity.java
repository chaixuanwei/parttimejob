package com.example.myapplication.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.balancedrawal_ll)
    LinearLayout balancedrawalLl;
    @BindView(R.id.financialdetails_ll)
    LinearLayout financialdetailsLl;
    @BindView(R.id.creditline_txt)
    TextView creditlineTxt;
    @BindView(R.id.datastatistics_ll)
    LinearLayout datastatisticsLl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    public void initView() {

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

    @OnClick({R.id.balancedrawal_ll, R.id.financialdetails_ll, R.id.datastatistics_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.balancedrawal_ll:
                startActivity(new Intent(this,BalanceDrawalActivity.class));
                break;
            case R.id.financialdetails_ll:
                startActivity(new Intent(this,FinancialDetailsActivity.class));
                break;
            case R.id.datastatistics_ll:
                startActivity(new Intent(this,DataPreviewActivity.class));
                break;
        }
    }
}
