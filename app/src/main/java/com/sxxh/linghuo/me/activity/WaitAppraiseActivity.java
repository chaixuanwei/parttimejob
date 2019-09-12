package com.sxxh.linghuo.me.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.me.adapter.WaitAppraiseAdapter;
import com.sxxh.linghuo.model.MeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class WaitAppraiseActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.wait_appraise_rv)
    RecyclerView waitAppraiseRv;
    @BindView(R.id.wait_appraise_srl)
    SmartRefreshLayout waitAppraiseSrl;
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
        initRecycleView(waitAppraiseRv, waitAppraiseSrl);
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
    public void onError(int whichApi, Throwable e) {

    }
    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    public void refresh() {
    }

    @Override
    public void loadMore() {
    }
}
