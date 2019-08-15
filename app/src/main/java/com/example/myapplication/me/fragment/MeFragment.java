package com.example.myapplication.me.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.MeModel;
import com.example.myapplication.me.activity.AmendActivity;
import com.example.myapplication.me.activity.WaitEnrollActivity;
import com.example.myapplication.view.RoundImage;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseMvpFragment<CommonPresenter, MeModel> {
    static MeFragment fragment;
    @BindView(R.id.head)
    RoundImage head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.signature)
    EditText signature;
    @BindView(R.id.me_amend)
    TextView meAmend;
    @BindView(R.id.grade)
    TextView grade;
    @BindView(R.id.grade_name)
    TextView gradeName;
    @BindView(R.id.credte_line)
    TextView credteLine;
    @BindView(R.id.credte_line_name)
    TextView credteLineName;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.comment_name)
    TextView commentName;
    @BindView(R.id.waitlist)
    LinearLayout waitlist;
    @BindView(R.id.work)
    LinearLayout work;
    @BindView(R.id.salary)
    LinearLayout salary;
    @BindView(R.id.waitappraise)
    LinearLayout waitappraise;
    @BindView(R.id.wallet)
    TextView wallet;
    @BindView(R.id.approve)
    TextView approve;
    @BindView(R.id.me_issus)
    TextView meIssus;
    @BindView(R.id.bind)
    TextView bind;
    @BindView(R.id.feedback)
    TextView feedback;

    public static MeFragment newInstance() {
        if (fragment == null) fragment = new MeFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
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
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.head, R.id.name, R.id.signature, R.id.me_amend, R.id.grade, R.id.grade_name, R.id.credte_line, R.id.credte_line_name, R.id.comment, R.id.comment_name, R.id.waitlist, R.id.work, R.id.salary, R.id.waitappraise, R.id.wallet, R.id.approve, R.id.me_issus, R.id.bind, R.id.feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head:
                break;
            case R.id.name:
                break;
            case R.id.signature:
                break;
            case R.id.me_amend:
                startActivity(new Intent(getActivity(), AmendActivity.class));
                break;
            case R.id.grade:
                break;
            case R.id.grade_name:
                break;
            case R.id.credte_line:
                break;
            case R.id.credte_line_name:
                break;
            case R.id.comment:
                break;
            case R.id.comment_name:
                break;
            case R.id.waitlist:
                startActivity(new Intent(getActivity(), WaitEnrollActivity.class));
                break;
            case R.id.work:
                break;
            case R.id.salary:
                break;
            case R.id.waitappraise:
                break;
            case R.id.wallet:
                break;
            case R.id.approve:
                break;
            case R.id.me_issus:
                break;
            case R.id.bind:
                break;
            case R.id.feedback:
                break;
        }
    }
}
