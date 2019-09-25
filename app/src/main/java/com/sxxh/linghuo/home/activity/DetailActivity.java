package com.sxxh.linghuo.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.me.activity.CompaintActivity;
import com.sxxh.linghuo.me.activity.FeedbackSuccessActivity;
import com.sxxh.linghuo.model.HomeModel;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailActivity extends BaseMvpActivity<CommonPresenter, HomeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.company_team)
    TextView companyTeam;
    @BindView(R.id.collect)
    ImageView collect;
    @BindView(R.id.complain)
    ImageView complain;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.job_name)
    TextView jobName;
    @BindView(R.id.job_place)
    TextView jobPlace;
    @BindView(R.id.person)
    TextView person;
    @BindView(R.id.stop)
    TextView stop;
    @BindView(R.id.stop_time)
    TextView stopTime;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.gather_time)
    TextView gatherTime;
    @BindView(R.id.gather_place)
    TextView gatherPlace;
    @BindView(R.id.work_date)
    TextView workDate;
    @BindView(R.id.work_time)
    TextView workTime;
    @BindView(R.id.work_place)
    TextView workPlace;
    @BindView(R.id.describe_content)
    TextView describeContent;
    @BindView(R.id.describe)
    LinearLayout describe;
    @BindView(R.id.company)
    TextView company;
    @BindView(R.id.company_photo)
    ImageView companyPhoto;
    @BindView(R.id.company_name)
    TextView companyName;
    @BindView(R.id.company_content)
    LinearLayout companyContent;
    @BindView(R.id.detail_call)
    TextView detailCall;
    @BindView(R.id.detail_apply)
    TextView detailApply;
    private int tId = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
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
    public void onError(int whichApi,Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.back, R.id.company_content, R.id.complain,R.id.detail_call, R.id.detail_apply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.company_content:
                Intent mCompanyIntent = new Intent(this, CompanyActivity.class);
                mCompanyIntent.putExtra(Config.TASK_ID, tId);
                startActivity(mCompanyIntent);
                break;
            case R.id.complain:
                Intent compaintIntent = new Intent(this, CompaintActivity.class);
                compaintIntent.putExtra(Config.COMPLAINT,"1");
                startActivity(compaintIntent);
                break;
            case R.id.detail_call:
                break;
            case R.id.detail_apply:
                Intent mSuccessIntent = new Intent(this, FeedbackSuccessActivity.class);
                mSuccessIntent.putExtra(Config.SUCCESS, Config.APPLY);
                startActivity(mSuccessIntent);
                break;
        }
    }
}
