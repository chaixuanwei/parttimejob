package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.home.bean.TaskdetailBean;
import com.sxxh.linghuo.me.adapter.TaskImageAdapter;
import com.sxxh.linghuo.me.bean.TaskPhotoBean;
import com.sxxh.linghuo.model.MeModel;
import com.sxxh.linghuo.view.RoundImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ProjectReviewActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
//    @BindView(R.id.submit_project)
//    TextView submitProject;
//    @BindView(R.id.submit_time)
//    TextView submitTime;
//    @BindView(R.id.submit_name)
//    TextView submitName;
//    @BindView(R.id.submit_img)
//    RoundImage submitImg;
    @BindView(R.id.project_rv)
    RecyclerView projectRv;
    @BindView(R.id.review_refuse)
    TextView reviewRefuse;
    @BindView(R.id.review_pass)
    TextView reviewPass;
    private int mTaskid;
    ArrayList<TaskPhotoBean.ProjectImagesBean> mList = new ArrayList<>();
    private TaskImageAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_review;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        mTaskid = mIntent.getIntExtra(Config.GET_TASK_ID, 1);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3);
        projectRv.setLayoutManager(mGridLayoutManager);
        mAdapter = new TaskImageAdapter(this, mList);
        projectRv.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_TASK_DETAILS, LoadConfig.NORMAL, mTaskid);
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
        Log.e("任务进度图片", "onError: 任务进度图片");
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_TASK_DETAILS:
                TaskPhotoBean mTaskPhotoBean = (TaskPhotoBean) t[0];
                mList.addAll(mTaskPhotoBean.getProject_images());
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @OnClick({R.id.back, R.id.review_refuse, R.id.review_pass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.review_refuse:
                break;
            case R.id.review_pass:
                break;
        }
    }
}
