package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;
import com.sxxh.linghuo.me.bean.HeadBalanceBean;
import com.sxxh.linghuo.model.MeModel;
import com.sxxh.linghuo.view.RoundImage;

import butterknife.BindView;
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
    @BindView(R.id.balance_num)
    TextView balanceNum;
    private String mBalance;

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
        mPresenter.getData(ApiConfig.HEAD_BALANCE);
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
        switch (whichApi) {
            case ApiConfig.HEAD_BALANCE:
                HeadBalanceBean mHeadBalances = (HeadBalanceBean) t[0];
                Glide.with(this).load(mHeadBalances.getData().getAvatar()).into(myPhoto);
                mBalance = mHeadBalances.getData().getBalance();
                balanceNum.setText(mBalance);
                break;
        }
    }

    @OnClick({R.id.balancedrawal_ll, R.id.financialdetails_ll, R.id.datastatistics_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.balancedrawal_ll:
                Intent mBalanceDrawalIntent = new Intent(this, BalanceDrawalActivity.class);
                mBalanceDrawalIntent.putExtra("balance", mBalance);
                startActivity(mBalanceDrawalIntent);
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
}
