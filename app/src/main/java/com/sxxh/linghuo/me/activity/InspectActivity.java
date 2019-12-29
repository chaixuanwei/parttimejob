package com.sxxh.linghuo.me.activity;

import android.content.Intent;
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
import com.sxxh.linghuo.me.adapter.InspectAdapter;
import com.sxxh.linghuo.me.bean.ProjectProgressBean;
import com.sxxh.linghuo.model.MeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class InspectActivity extends BaseMvpActivity<CommonPresenter, MeModel> implements InspectAdapter.taskfinish {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.inspect_rv)
    RecyclerView inspectRv;
    @BindView(R.id.inspect_srl)
    SmartRefreshLayout inspectSrl;
    private int mTaskId;
    int mPage = 1;
    ArrayList<ProjectProgressBean.DataBean> mList = new ArrayList<>();
    private InspectAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        String taskid = mIntent.getStringExtra("task_id");
        mTaskId = Integer.parseInt(taskid);
        initRecycleView(inspectRv, inspectSrl);
        mAdapter = new InspectAdapter(this,mList);
        inspectRv.setLayoutManager(new LinearLayoutManager(this));
        inspectRv.setAdapter(mAdapter);
        mAdapter.setTaskfinish(this);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_PROJECT_RATE, LoadConfig.NORMAL, mTaskId, mPage);
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
        Log.e("项目进度", "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_PROJECT_RATE:
                ProjectProgressBean mProjectProgressBeans = (ProjectProgressBean) t[0];
                int upordown = (int) t[1];
                if (upordown == LoadConfig.REFRESH) {
                    mList.clear();
                    inspectSrl.finishRefresh();
                } else if (upordown == LoadConfig.LOADMORE) {
                    inspectSrl.finishLoadMore();
                }
                mList.addAll(mProjectProgressBeans.getData());
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    public void refresh() {
        mPage = 1;
        mPresenter.getData(ApiConfig.GET_PROJECT_RATE, LoadConfig.REFRESH, mTaskId, mPage);
    }

    @Override
    public void loadMore() {
        mPage += mPage;
        mPresenter.getData(ApiConfig.GET_PROJECT_RATE, LoadConfig.LOADMORE, mTaskId, mPage);
    }

    @Override
    public void taskId(String u_id) {
        Intent mProjectReviewIntent = new Intent(this, ProjectReviewActivity.class);
        mProjectReviewIntent.putExtra(Config.GET_TASK_ID, mTaskId);
        mProjectReviewIntent.putExtra(Config.GET_USER_ID, u_id);
        this.startActivity(mProjectReviewIntent);
    }
}
