package com.example.myapplication.me;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.view.RoundImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseMvpFragment<CommonPresenter, MeModel> {
    static MeFragment fragment;
    @BindView(R.id.me_amend)
    TextView meAmend;
    @BindView(R.id.head)
    RoundImage head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.signature)
    EditText signature;
    @BindView(R.id.my_name)
    LinearLayout myName;
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
    @BindView(R.id.grade_ll)
    LinearLayout gradeLl;
    @BindView(R.id.waitlist)
    TextView waitlist;
    @BindView(R.id.work)
    TextView work;
    @BindView(R.id.salary)
    TextView salary;
    @BindView(R.id.waitappraise)
    TextView waitappraise;
    @BindView(R.id.situation_ll)
    LinearLayout situationLl;
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
    @BindView(R.id.list)
    LinearLayout list;
    Unbinder unbinder;

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

    @OnClick({R.id.me_amend})
    void onClickAmend(){
        Intent intent = new Intent(getContext(), AmendActivity.class);
        startActivity(intent);
    }
}
