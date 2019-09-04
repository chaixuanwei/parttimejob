package com.example.myapplication.issus.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.example.myapplication.R;
import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.config.LoadConfig;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.issus.activity.PayCenterActivity;
import com.example.myapplication.issus.bean.NatureBean;
import com.example.myapplication.local_utils.DateUtil;
import com.example.myapplication.login.bean.AuthCodeBean;
import com.example.myapplication.model.IssusModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class IssusFragment extends BaseMvpFragment<CommonPresenter, IssusModel> implements View.OnFocusChangeListener, View.OnTouchListener {
    private static final String TAG = "IssusFragment";
    static IssusFragment fragment;
    @BindView(R.id.issus_job_name)
    EditText issusJobName;
    @BindView(R.id.issus_describe)
    EditText issusDescribe;
    @BindView(R.id.issus_money)
    EditText issusMoney;
    @BindView(R.id.issus_spinner)
    Spinner issusSpinner;
    @BindView(R.id.classify_spinner)
    Spinner classifySpinner;
    @BindView(R.id.issus_interview_yes)
    RadioButton issusInterviewYes;
    @BindView(R.id.issus_interview_no)
    RadioButton issusInterviewNo;
    @BindView(R.id.ishight)
    EditText ishight;
    @BindView(R.id.isfive)
    EditText isfive;
    @BindView(R.id.issus_gather_yes)
    RadioButton issusGatherYes;
    @BindView(R.id.issus_gather_no)
    RadioButton issusGatherNo;
    @BindView(R.id.gather_time)
    TextView gatherTime;
    @BindView(R.id.gather_place)
    EditText gatherPlace;
    @BindView(R.id.show_hidden)
    RelativeLayout showHidden;
    @BindView(R.id.issus_place)
    EditText issusPlace;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.issus_number)
    EditText issusNumber;
    @BindView(R.id.issus_name)
    EditText issusName;
    @BindView(R.id.issus_phone)
    EditText issusPhone;
    @BindView(R.id.bt_issus_login)
    TextView btIssusLogin;
    @BindView(R.id.sc)
    ScrollView sc;
    private static final String[] m_arr = {"网络", "线下"};
    ArrayList<String> mStrings = new ArrayList<>();
    private TimePickerView pvTime;
    private int gathertime;
    private int starttime;
    private int endtime;
    private ArrayList<NatureBean.DataBean> mDataBeans = new ArrayList<>();
    String pattern = "yyyy-MM-dd HH:mm:ss";
    int interview = 0;//是否面试
    int gather = 0;//是否集合
    int nature = 0;//任务属性
    int classify = 0;//任务分类
    int click = 0;
    private ArrayAdapter<String> mStringAda;
    private ArrayAdapter<String> mAda;

    public static IssusFragment newInstance() {
        if (fragment == null) fragment = new IssusFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_issus;
    }

    @Override
    public void initView() {
        initTimePicker();
        issusPlace.setOnFocusChangeListener(this);
        issusPlace.setOnTouchListener(this);
        issusNumber.setOnFocusChangeListener(this);
        issusNumber.setOnTouchListener(this);
        issusName.setOnFocusChangeListener(this);
        issusName.setOnTouchListener(this);
        issusPhone.setOnFocusChangeListener(this);
        issusPhone.setOnTouchListener(this);
    }

    private void initlist() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        sc.postDelayed(new Runnable() {
            @Override
            public void run() {
                sc.smoothScrollTo(0, sc.getHeight());
            }
        }, 300);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_NATURE, LoadConfig.NORMAL);
        mAda = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, m_arr);
        mAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        issusSpinner.setAdapter(mAda);
        issusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (m_arr[position].equals("网络")) {
                    nature = 1;
                    showHidden.setVisibility(View.GONE);
                } else {
                    nature = 2;
                    showHidden.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mStringAda = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mStrings);
        classifySpinner.setAdapter(mStringAda);
        classifySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classify = mDataBeans.get(position).getId();
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
    public IssusModel getModel() {
        return new IssusModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Log.e(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.TO_ISSUS:
                AuthCodeBean mAuthCodeBeans = (AuthCodeBean) t[0];
                ToastUtils.showShort(mAuthCodeBeans.getMsg());
                startActivity(new Intent(getActivity(), PayCenterActivity.class));
                break;
            case ApiConfig.GET_NATURE:
                NatureBean mNatureBeans = (NatureBean) t[0];
                mDataBeans.addAll(mNatureBeans.getData());
                for (int i = 0; i < mDataBeans.size(); i++) {
                    mStrings.add(mDataBeans.get(i).getName());
                }
                mStringAda.notifyDataSetChanged();
                break;
        }
    }

    @OnClick({R.id.issus_interview_yes, R.id.issus_interview_no, R.id.issus_gather_yes, R.id.issus_gather_no, R.id.bt_issus_login, R.id.gather_time, R.id.start_time, R.id.end_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.issus_interview_yes:
                interview = 1;
                break;
            case R.id.issus_interview_no:
                interview = 2;
                break;
            case R.id.start_time:
                click = 1;
                pvTime.show(view);
                break;
            case R.id.end_time:
                click = 2;
                pvTime.show(view);
                break;
            case R.id.issus_gather_yes:
                gather = 1;
                break;
            case R.id.issus_gather_no:
                gather = 2;
                break;
            case R.id.gather_time:
                click = 3;
                pvTime.show(view);
                break;
            case R.id.bt_issus_login:
                String mIssusJobName = issusJobName.getText().toString();
                String mIssusDescribe = issusDescribe.getText().toString();
                String mIssusMoney = issusMoney.getText().toString();
                String mIssusPlace = issusPlace.getText().toString();
                String mIssusNumber = issusNumber.getText().toString();
                int mNumber = 0;
                if (!mIssusDescribe.equals("")) {
                    mNumber = Integer.parseInt(mIssusNumber);
                }
                String mIssusName = issusName.getText().toString();
                String mIssusPhone = issusPhone.getText().toString();
                String mHight = ishight.getText().toString();
                String mFive = isfive.getText().toString();
                String mGatherPlace = gatherPlace.getText().toString();
                if (!mIssusJobName.equals("") && !mIssusDescribe.equals("") && !mIssusMoney.equals("")
                        && !mIssusPlace.equals("") && !mIssusNumber.equals("")
                        && !mIssusName.equals("") && !mIssusPhone.equals("")) {
                    mPresenter.getData(ApiConfig.TO_ISSUS, LoadConfig.NORMAL, mIssusJobName, mIssusDescribe
                            , mIssusMoney, classify, nature, mIssusPlace, starttime, endtime, mNumber, mIssusName, mIssusPhone
                            , interview, mHight, mFive, gather, gathertime, mGatherPlace);
                } else {
                    ToastUtils.showShort("请填写必填信息！");
                }
//                Intent mIntent = new Intent(getActivity(), PayCenterActivity.class);
//                startActivity(mIntent);
                break;
        }
    }

    private void initTimePicker() {//Dialog 模式下，在底部弹出

        pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                long mDate = DateUtil.getStringToDate(getTime(date), pattern);
                String data = mDate + "";
                if (click == 3) {
                    gatherTime.setText(getTime(date));
                    gathertime = Integer.parseInt(data.substring(0, 10));
                } else if (click == 2) {
                    endTime.setText(getTime(date));
                    endtime = Integer.parseInt(data.substring(0, 10));
                } else if (click == 1) {
                    startTime.setText(getTime(date));
                    starttime = Integer.parseInt(data.substring(0, 10));
                }
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .addOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("pvTime", "onCancelClickListener");
                    }
                })
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (sc != null)
        initlist();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        initlist();
        return false;
    }
}
