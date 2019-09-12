package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AtonceActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.rb_salary)
    RatingBar rbSalary;
    @BindView(R.id.rb_service)
    RatingBar rbService;
    @BindView(R.id.rb_post)
    RatingBar rbPost;
    @BindView(R.id.check_post)
    CheckBox checkPost;
    @BindView(R.id.check_welfare)
    CheckBox checkWelfare;
    @BindView(R.id.check_salary)
    CheckBox checkSalary;
    @BindView(R.id.check_speed)
    CheckBox checkSpeed;
    @BindView(R.id.check_manner)
    CheckBox checkManner;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.txt_salary)
    TextView txtSalary;
    @BindView(R.id.txt_service)
    TextView txtService;
    @BindView(R.id.txt_post)
    TextView txtPost;

    @Override
    public int getLayoutId() {
        return R.layout.activity_atonce;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        rbPost.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                RbTxt(txtPost,rating);
            }
        });
        rbService.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                RbTxt(txtService,rating);
            }
        });
        rbSalary.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                RbTxt(txtSalary,rating);
            }
        });
    }

    private void RbTxt(TextView pTxtService, float rating) {
        if (rating == 1.0) {
            pTxtService.setText("极差");
        } else if (rating == 2.0) {
            pTxtService.setText("较差");
        } else if (rating == 3.0) {
            pTxtService.setText("不错");
        } else if (rating == 4.0) {
            pTxtService.setText("给力");
        } else if (rating == 5.0) {
            pTxtService.setText("狂赞");
        }
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
    public void onError(int whichApi,Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.back, R.id.check_post, R.id.check_welfare, R.id.check_salary, R.id.check_speed, R.id.check_manner, R.id.submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.check_post:
                if (checkPost.isChecked()) {
                    checkPost.setTextColor(getResources().getColor(R.color.app_theme_color));
                } else {
                    checkPost.setTextColor(getResources().getColor(R.color.grey_second));
                }
                break;
            case R.id.check_welfare:
                if (checkWelfare.isChecked()) {
                    checkWelfare.setTextColor(getResources().getColor(R.color.app_theme_color));
                } else {
                    checkWelfare.setTextColor(getResources().getColor(R.color.grey_second));
                }
                break;
            case R.id.check_salary:
                if (checkSalary.isChecked()) {
                    checkSalary.setTextColor(getResources().getColor(R.color.app_theme_color));
                } else {
                    checkSalary.setTextColor(getResources().getColor(R.color.grey_second));
                }
                break;
            case R.id.check_speed:
                if (checkSpeed.isChecked()) {
                    checkSpeed.setTextColor(getResources().getColor(R.color.app_theme_color));
                } else {
                    checkSpeed.setTextColor(getResources().getColor(R.color.grey_second));
                }
                break;
            case R.id.check_manner:
                if (checkManner.isChecked()) {
                    checkManner.setTextColor(getResources().getColor(R.color.app_theme_color));
                } else {
                    checkManner.setTextColor(getResources().getColor(R.color.grey_second));
                }
                break;
            case R.id.submit:
                Intent mFeedbackSuccessIntent = new Intent(this, FeedbackSuccessActivity.class);
                mFeedbackSuccessIntent.putExtra(Config.SUCCESS, Config.FEEDBACK);
                startActivity(mFeedbackSuccessIntent);
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
