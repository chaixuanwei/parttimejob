package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.me.adapter.ApplyPeopleAdapter;
import com.sxxh.linghuo.me.bean.ApplyPeopleBean;
import com.sxxh.linghuo.model.MeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ApplyPeopleActivity extends BaseMvpActivity<CommonPresenter, MeModel> implements ApplyPeopleAdapter.ToUser {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.apply_people_rv)
    RecyclerView applyPeopleRv;
    @BindView(R.id.apply_people_srl)
    SmartRefreshLayout applyPeopleSrl;
    private String mTask_id;
    ArrayList<ApplyPeopleBean.DataBean> mList = new ArrayList<>();
    private ApplyPeopleAdapter mAdapter;
    int mPage = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_apply_people;
    }

    @Override
    public void initView() {
        initRecycleView(applyPeopleRv, applyPeopleSrl);
        Intent mIntent = getIntent();
        mTask_id = mIntent.getStringExtra("task_id");
        mAdapter = new ApplyPeopleAdapter(this, mList);
        applyPeopleRv.setLayoutManager(new LinearLayoutManager(this));
        applyPeopleRv.setAdapter(mAdapter);
        applyPeopleRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter.setJump(this);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_APPLYPEOPLE, LoadConfig.NORMAL, Integer.parseInt(mTask_id), mPage);
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
        Log.e("报名列表", "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_APPLYPEOPLE:
                ApplyPeopleBean mApplyPeopleBeans = (ApplyPeopleBean) t[0];
                int upordown = (int) t[1];
                if (upordown == LoadConfig.REFRESH) {
                    mList.clear();
                    applyPeopleSrl.finishRefresh();
                } else if (upordown == LoadConfig.LOADMORE) {
                    applyPeopleSrl.finishLoadMore();
                }
                List<ApplyPeopleBean.DataBean> mData = mApplyPeopleBeans.getData();
                mList.addAll(mData);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void refresh() {
        mPage = 1;
        mPresenter.getData(ApiConfig.GET_APPLYPEOPLE, LoadConfig.REFRESH, Integer.parseInt(mTask_id), mPage);
    }

    @Override
    public void loadMore() {
        mPage += mPage;
        mPresenter.getData(ApiConfig.GET_APPLYPEOPLE, LoadConfig.LOADMORE, Integer.parseInt(mTask_id), mPage);
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    public void people(String type,int id) {
        if (type.equals("look")) {
            Intent mInsightsUserIntent = new Intent(ApplyPeopleActivity.this, InsightsUserActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putInt("task_id", Integer.parseInt(mTask_id));
            mBundle.putInt("user_id", id);
            mInsightsUserIntent.putExtra("bundle", mBundle);
            startActivity(mInsightsUserIntent);
        } else if (type.equals("compaint")) {
            Intent compaintIntent = new Intent(this, CompaintActivity.class);
            compaintIntent.putExtra(Config.COMPLAINT,"2");
            compaintIntent.putExtra(Config.USER_ID, id);
            startActivity(compaintIntent);
        }
    }
}
