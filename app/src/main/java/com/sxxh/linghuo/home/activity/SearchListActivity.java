package com.sxxh.linghuo.home.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.model.HomeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchListActivity extends BaseMvpActivity<CommonPresenter, HomeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.first_search)
    LinearLayout firstSearch;
    @BindView(R.id.first_message)
    ImageView firstMessage;
    @BindView(R.id.search_list_toolbar)
    Toolbar searchListToolbar;
    @BindView(R.id.search_list_rlv)
    RecyclerView searchListRlv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_list;
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
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.back, R.id.first_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.first_message:
                break;
        }
    }
}
