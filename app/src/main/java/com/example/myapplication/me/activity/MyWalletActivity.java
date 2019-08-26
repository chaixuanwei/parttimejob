package com.example.myapplication.me.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.config.Config;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.local_utils.SharedPrefrenceUtils;
import com.example.myapplication.model.MeModel;
import com.example.myapplication.view.RoundImage;

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
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.my_photo)
    RoundImage myPhoto;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    public void initView() {
        Bitmap mBitmap = SharedPrefrenceUtils.getBitmap(this, Config.BITMAP, null);
        if (null != mBitmap) {
            myPhoto.setImageBitmap(mBitmap);
        }
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
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.balancedrawal_ll, R.id.financialdetails_ll, R.id.datastatistics_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.balancedrawal_ll:
                startActivity(new Intent(this, BalanceDrawalActivity.class));
                break;
            case R.id.financialdetails_ll:
                startActivity(new Intent(this, FinancialDetailsActivity.class));
                break;
            case R.id.datastatistics_ll:
                startActivity(new Intent(this, DataPreviewActivity.class));
                break;
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
