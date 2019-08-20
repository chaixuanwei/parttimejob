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
import com.example.myapplication.me.activity.FeedbackActivity;
import com.example.myapplication.me.activity.IdBindActivity;
import com.example.myapplication.me.activity.MyApproveActivity;
import com.example.myapplication.me.activity.MyIssusActivity;
import com.example.myapplication.me.activity.MyWalletActivity;
import com.example.myapplication.me.activity.SalaryActivity;
import com.example.myapplication.me.activity.WaitListActivity;
import com.example.myapplication.me.activity.WorkingActivity;
import com.example.myapplication.model.MeModel;
import com.example.myapplication.me.activity.AmendActivity;
import com.example.myapplication.me.activity.WaitAppraiseActivity;
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

    @OnClick({R.id.head, R.id.me_amend, R.id.waitlist, R.id.work, R.id.salary, R.id.waitappraise, R.id.wallet, R.id.approve, R.id.me_issus, R.id.bind, R.id.feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head:
                break;
            case R.id.me_amend:
                Intent mAmendIntent = new Intent(getActivity(), AmendActivity.class);
                startActivity(mAmendIntent);
                break;
            case R.id.waitlist:
                Intent mWaitListIntent = new Intent(getActivity(), WaitListActivity.class);
                startActivity(mWaitListIntent);
                break;
            case R.id.work:
                Intent mWorkingIntent = new Intent(getActivity(), WorkingActivity.class);
                startActivity(mWorkingIntent);
                break;
            case R.id.salary:
                Intent mSalaryIntent = new Intent(getActivity(), SalaryActivity.class);
                startActivity(mSalaryIntent);
                break;
            case R.id.waitappraise:
                Intent mWaitAppraiseIntent = new Intent(getActivity(), WaitAppraiseActivity.class);
                startActivity(mWaitAppraiseIntent);
                break;
            case R.id.wallet:
                Intent mMyWalletIntent = new Intent(getActivity(), MyWalletActivity.class);
                startActivity(mMyWalletIntent);
                break;
            case R.id.approve:
                Intent mMyApproveIntent = new Intent(getActivity(), MyApproveActivity.class);
                startActivity(mMyApproveIntent);
                break;
            case R.id.me_issus:
                Intent mMyIssusIntent = new Intent(getActivity(), MyIssusActivity.class);
                startActivity(mMyIssusIntent);
                break;
            case R.id.bind:
                Intent mIdBindIntent = new Intent(getActivity(), IdBindActivity.class);
                startActivity(mIdBindIntent);
                break;
            case R.id.feedback:
                Intent mFeedbackIntent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(mFeedbackIntent);
                break;
        }
    }
}
