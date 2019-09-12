package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.login.bean.AuthCodeBean;
import com.sxxh.linghuo.me.adapter.FeedbackAdapter;
import com.sxxh.linghuo.me.bean.FeedBackBean;
import com.sxxh.linghuo.model.MeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedbackActivity extends BaseMvpActivity<CommonPresenter, MeModel> implements FeedbackAdapter.getFeedbackTitle {
    private static final String TAG = "FeedbackActivity";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.feedback_ed_content)
    EditText feedbackEdContent;
    @BindView(R.id.feedback_num)
    TextView feedbackNum;
    @BindView(R.id.feedback_bt_login)
    TextView feedbackBtLogin;
    @BindView(R.id.feedback_rv)
    RecyclerView feedbackRv;
    ArrayList<String> mStrings = new ArrayList<>();
    String title = "";
    private FeedbackAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView() {
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        feedbackRv.setLayoutManager(mManager);
        mAdapter = new FeedbackAdapter(this, mStrings);
        feedbackRv.setAdapter(mAdapter);
        mAdapter.setFeedbackTitle(this);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_FEEDBACK,LoadConfig.NORMAL);
        feedbackEdContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                feedbackNum.setText(s.length() + "/200");
            }
        });
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
        Log.d(TAG, "onError() returned: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_FEEDBACK:
                FeedBackBean mFeedBackBeans = (FeedBackBean) t[0];
                List<String> mData = mFeedBackBeans.getData();
                mStrings.addAll(mData);
                mAdapter.notifyDataSetChanged();
                break;
            case ApiConfig.FEEDBACK:
                AuthCodeBean mAuthCodeBeans = (AuthCodeBean) t[0];
                if (mAuthCodeBeans.getMsg().equals("提交成功！")){
                    Intent mFeedbackSuccessIntent = new Intent(this, FeedbackSuccessActivity.class);
                    mFeedbackSuccessIntent.putExtra(Config.SUCCESS, Config.FEEDBACK);
                    startActivity(mFeedbackSuccessIntent);
                } else {
                    ToastUtils.showShort(mAuthCodeBeans.getMsg());
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.feedback_bt_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.feedback_bt_login:
                String content = feedbackEdContent.getText().toString();
                if (!content.equals("") && !title.equals("")) {
                    mPresenter.getData(ApiConfig.FEEDBACK, LoadConfig.NORMAL, title, content);
                } else {
                    ToastUtils.showShort("请填写完整信息！");
                }
                break;
        }
    }

    @Override
    public void title(String title) {
        this.title = title;
        mAdapter.notifyDataSetChanged();
    }
}
