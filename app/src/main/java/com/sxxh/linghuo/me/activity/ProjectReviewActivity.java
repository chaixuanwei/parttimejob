package com.sxxh.linghuo.me.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.model.MeModel;
import com.sxxh.linghuo.view.RoundImage;

import butterknife.BindView;
import butterknife.OnClick;

public class ProjectReviewActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.submit_project)
    TextView submitProject;
    @BindView(R.id.submit_time)
    TextView submitTime;
    @BindView(R.id.submit_name)
    TextView submitName;
    @BindView(R.id.submit_img)
    RoundImage submitImg;
    @BindView(R.id.project_rv)
    RecyclerView projectRv;
    @BindView(R.id.review_refuse)
    TextView reviewRefuse;
    @BindView(R.id.review_pass)
    TextView reviewPass;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_review;
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
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

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
