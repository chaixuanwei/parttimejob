package com.example.myapplication.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.issus.activity.PayCenterActivity;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IssusActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.issus_job_name)
    EditText issusJobName;
    @BindView(R.id.issus_describe)
    EditText issusDescribe;
    @BindView(R.id.issus_money)
    EditText issusMoney;
    @BindView(R.id.issus_spinner)
    Spinner issusSpinner;
    @BindView(R.id.issus_interview_yes)
    RadioButton issusInterviewYes;
    @BindView(R.id.issus_interview_no)
    RadioButton issusInterviewNo;
    @BindView(R.id.ishight)
    TextView ishight;
    @BindView(R.id.isfive)
    TextView isfive;
    @BindView(R.id.issus_gather_yes)
    RadioButton issusGatherYes;
    @BindView(R.id.issus_gather_no)
    RadioButton issusGatherNo;
    @BindView(R.id.isgather)
    LinearLayout isgather;
    @BindView(R.id.gather_time)
    TextView gatherTime;
    @BindView(R.id.gather_place)
    TextView gatherPlace;
    @BindView(R.id.show_hidden)
    RelativeLayout showHidden;
    @BindView(R.id.issus_place)
    EditText issusPlace;
    @BindView(R.id.issus_time)
    TextView issusTime;
    @BindView(R.id.issus_number)
    EditText issusNumber;
    @BindView(R.id.issus_name)
    EditText issusName;
    @BindView(R.id.issus_phone)
    EditText issusPhone;
    @BindView(R.id.bt_issus_login)
    TextView btIssusLogin;
    private static final String[] m_arr = {"网络","线下"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_issus;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        ArrayAdapter<String> ada = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m_arr);
        ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        issusSpinner.setAdapter(ada);
        issusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (m_arr[position].equals("网络")) {
                    showHidden.setVisibility(View.GONE);
                } else {
                    showHidden.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.back, R.id.issus_interview_yes, R.id.issus_interview_no, R.id.bt_issus_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.issus_interview_yes:
                break;
            case R.id.issus_interview_no:
                break;
            case R.id.bt_issus_login:
                break;
        }
    }
}
