package com.sxxh.linghuo.me.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.frame.BaseMvpFragment;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;
import com.sxxh.linghuo.login.LoginActivity;
import com.sxxh.linghuo.me.activity.AmendActivity;
import com.sxxh.linghuo.me.activity.FeedbackActivity;
import com.sxxh.linghuo.me.activity.IdBindActivity;
import com.sxxh.linghuo.me.activity.MyApproveActivity;
import com.sxxh.linghuo.me.activity.MyIssusActivity;
import com.sxxh.linghuo.me.activity.MyWalletActivity;
import com.sxxh.linghuo.me.activity.SalaryActivity;
import com.sxxh.linghuo.me.activity.WaitAppraiseActivity;
import com.sxxh.linghuo.me.activity.WaitListActivity;
import com.sxxh.linghuo.me.activity.WorkingActivity;
import com.sxxh.linghuo.model.MeModel;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseMvpFragment<CommonPresenter, MeModel> {
    static MeFragment fragment;
    @BindView(R.id.head)
    ImageView head;
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
    @BindView(R.id.log_out)
    TextView logOut;
    private InvokeParam invokeParam;
    private TakePhotoImpl mTakePhoto;
    private ArrayList<File> mFiles = new ArrayList<>();
    private ArrayList<String> mList = new ArrayList<String>();

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
        String mSigna = SharedPrefrenceUtils.getString(getActivity(), Config.SIGNA);
        if (!mSigna.equals("")) {
            signature.setText(mSigna);
        }
    }

    @Override
    public void initData() {
        signature.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SharedPrefrenceUtils.saveString(getActivity(), Config.SIGNA, s.toString().trim());
            }
        });
    }

    @Override
    public void onResume() {
        String mPhoto = SharedPrefrenceUtils.getString(getActivity(), Config.TOPPHOTO);
        if (!mPhoto.equals("")) {
            Glide.with(getActivity()).load(mPhoto).into(head);
        }
        super.onResume();
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

    @OnClick({R.id.me_amend, R.id.waitlist, R.id.work, R.id.salary, R.id.waitappraise, R.id.wallet, R.id.approve, R.id.me_issus, R.id.bind, R.id.feedback, R.id.log_out})
    public void onClick(View view) {
        switch (view.getId()) {
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
            case R.id.log_out:
                SharedPrefrenceUtils.saveString(getActivity(),Config.TOKEN,"");
                Intent mLoginIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(mLoginIntent);
                getActivity().finish();
                break;
        }
    }
}
