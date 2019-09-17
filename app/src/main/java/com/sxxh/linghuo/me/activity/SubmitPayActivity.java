package com.sxxh.linghuo.me.activity;

import android.util.Log;
import android.widget.ImageView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.me.bean.BalanceDrawBean;
import com.sxxh.linghuo.model.MeModel;

import butterknife.BindView;
import butterknife.OnClick;

public class SubmitPayActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;

    @Override
    public int getLayoutId() {
        return R.layout.activity_submit_pay;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_WITHDRAW, LoadConfig.NORMAL);
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
        Log.e("提现", "onError:获取提现" + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_WITHDRAW:
                BalanceDrawBean mBalanceDrawBeans = (BalanceDrawBean) t[0];

                break;
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
