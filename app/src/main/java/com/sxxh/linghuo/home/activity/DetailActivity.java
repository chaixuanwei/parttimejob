package com.sxxh.linghuo.home.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.home.bean.TaskdetailBean;
import com.sxxh.linghuo.local_utils.DateUtil;
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
    //    @BindView(R.id.stop)
//    TextView stop;
//    @BindView(R.id.stop_time)
//    TextView stopTime;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.gather_time)
    TextView gatherTime;
    @BindView(R.id.gather_place)
    TextView gatherPlace;
    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.other)
    TextView other;
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
    @BindView(R.id.under)
    LinearLayout under;
    @BindView(R.id.height_ll)
    LinearLayout heightLl;
    @BindView(R.id.other_ll)
    LinearLayout otherLl;
    private int tId = 1;
    private TaskdetailBean mBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        tId = mIntent.getIntExtra("uid", 1);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.TASKDETAIL, LoadConfig.NORMAL, tId);
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
        Log.e("兼职详情", "onError: 兼职详情");
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.TASKDETAIL:
                TaskdetailBean mBean = (TaskdetailBean) t[0];
                TaskdetailBean.DataBean mTaskdetailBeans = mBean.getData();
                jobName.setText(mTaskdetailBeans.getName());
                jobPlace.setText(mTaskdetailBeans.getWork_location());
                person.setText(mTaskdetailBeans.getZp_num() + "");
                DateUtil mDateUtil = new DateUtil();
                money.setText(mTaskdetailBeans.getPay());
                describeContent.setText(mTaskdetailBeans.getDes());
                companyName.setText(mTaskdetailBeans.getCompany_name());
                workPlace.setText(mTaskdetailBeans.getWork_location());
                int mStart_time = mTaskdetailBeans.getStart_time();
                int mEnd_time = mTaskdetailBeans.getEnd_time();
                if (mStart_time != 0 && mEnd_time != 0) {
                    String mStartToString = mDateUtil.getDateToString(mStart_time, "yyyy-MM-dd");
                    String mEndToString = mDateUtil.getDateToString(mEnd_time, "yyyy-MM-dd");
                    workTime.setText(mStartToString + "至" + mEndToString);
                }
                if (mTaskdetailBeans.getProperty() == 1) {
                    under.setVisibility(View.GONE);
                } else {
                    under.setVisibility(View.VISIBLE);
                    int mMuster_time = mTaskdetailBeans.getMuster_time();
                    if (mMuster_time != 0) {
                        gatherTime.setVisibility(View.VISIBLE);
                        String mDateToString = mDateUtil.getDateToString(mMuster_time * 1000, "yy-MM-dd  hh:mm");
                        gatherTime.setText(mDateToString);
                    } else {
                        gatherTime.setVisibility(View.GONE);
                    }
                    gatherPlace.setText(mTaskdetailBeans.getMuster_address());
                    if (!mTaskdetailBeans.getHeight_require().equals("")) {
                        heightLl.setVisibility(View.VISIBLE);
                        height.setText(mTaskdetailBeans.getHeight_require());
                    } else {
                        heightLl.setVisibility(View.GONE);
                    }
                    if (!mTaskdetailBeans.getOther_require().equals("")) {
                        otherLl.setVisibility(View.VISIBLE);
                        other.setText(mTaskdetailBeans.getOther_require());
                    } else {
                        otherLl.setVisibility(View.GONE);
                    }
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.company_content, R.id.complain, R.id.detail_call, R.id.detail_apply})
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
                compaintIntent.putExtra(Config.COMPLAINT, "1");
                compaintIntent.putExtra(Config.USER_ID, tId);
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
