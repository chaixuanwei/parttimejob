package com.sxxh.linghuo.home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpFragment;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.home.adapter.CommpanyAppraiseAdapter;
import com.sxxh.linghuo.home.bean.IssuerGeneralEvaluationBean;
import com.sxxh.linghuo.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyAppraiseFragment extends BaseMvpFragment<CommonPresenter, HomeModel> {
    static CompanyAppraiseFragment fragment;
    @BindView(R.id.progress_appraise)
    ProgressBar progressAppraise;
    @BindView(R.id.num_appraise)
    TextView numAppraise;
    @BindView(R.id.progress_service)
    ProgressBar progressService;
    @BindView(R.id.num_service)
    TextView numService;
    @BindView(R.id.progress_efficiency)
    ProgressBar progressEfficiency;
    @BindView(R.id.num_efficiency)
    TextView numEfficiency;
    @BindView(R.id.company_appraise_rv)
    RecyclerView companyAppraiseRv;
    private int id;
    List<IssuerGeneralEvaluationBean.DataBean> mDataList = new ArrayList<>();
    List<IssuerGeneralEvaluationBean.DatasBean> mDatasList = new ArrayList<>();

    public static CompanyAppraiseFragment newInstance(int uId) {
        if (fragment == null) fragment = new CompanyAppraiseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", uId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_company_appraise;
    }

    @Override
    public void initView() {
        /*numAppraise.setText(progressAppraise.getProgress() * 0.5 + mDataList.get(0).getPaycomment());
        numService.setText(progressService.getProgress() * 0.5 + mDataList.get(0).getServicecomment());
        numEfficiency.setText(progressEfficiency.getProgress() * 0.5 + mDataList.get(0).getStationcomment());*/

    }

    @Override
    public void initData() {
        id = getArguments().getInt("id");
        mPresenter.getData(ApiConfig.ISSUER_GENERAL_EVALUATION, LoadConfig.NORMAL, id);
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
        Log.e("整体评价", "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.ISSUER_GENERAL_EVALUATION:
                final IssuerGeneralEvaluationBean mData = (IssuerGeneralEvaluationBean) t[0];
                mDataList.clear();
                mDatasList.clear();
                if (mData.getCode() == 0) {
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        IssuerGeneralEvaluationBean.DataBean data = mData.getData();
                        if (!data.equals("")) {
                            mDataList.add(data);
                        }

                        for (int i = 0; i < mData.getDatas().size(); i++) {
                            mDatasList.add(mData.getDatas().get(i));
                        }
                    }
                });
                if (mDataList.get(0).getPaycomment() != null) {
                    String mPaycomment = mDataList.get(0).getPaycomment();
                    String Paycomment = mPaycomment.substring(0, mPaycomment.length() - 5);
                    numAppraise.setText(Paycomment);
                } else {
                    numAppraise.setText(5 + "");
                }
                if (mDataList.get(0).getServicecomment() != null) {
                    String mServicecomment = mDataList.get(0).getServicecomment();
                    String Servicecomment = mServicecomment.substring(0, mServicecomment.length() - 5);
                    numService.setText(Servicecomment);
                } else {
                    numService.setText(5 + "");
                }
                if (mDataList.get(0).getStationcomment() != null) {
                    String mStationcomment = mDataList.get(0).getStationcomment();
                    String Stationcomment = mStationcomment.substring(0, mStationcomment.length() - 5);
                    numEfficiency.setText(Stationcomment);
                } else {
                    numEfficiency.setText(5 + "");
                }
                progressAppraise.setProgress(Integer.parseInt(numAppraise.getText().toString())*2);
                progressEfficiency.setProgress(Integer.parseInt(numEfficiency.getText().toString())*2);
                progressService.setProgress(Integer.parseInt(numService.getText().toString())*2);
                companyAppraiseRv.setFocusable(false);
                CommpanyAppraiseAdapter mAdapter = new CommpanyAppraiseAdapter(getActivity(), mDatasList);
                LinearLayoutManager mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                companyAppraiseRv.setLayoutManager(mManager);
                companyAppraiseRv.setAdapter(mAdapter);
                break;
        }
    }
}
