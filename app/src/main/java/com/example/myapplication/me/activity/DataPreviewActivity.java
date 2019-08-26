package com.example.myapplication.me.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.MeModel;
import com.example.myapplication.view.BarChartView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataPreviewActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.task_number)
    TextView taskNumber;
    @BindView(R.id.accept)
    TextView accept;
    @BindView(R.id.withdraw_depoist)
    TextView withdrawDepoist;
    @BindView(R.id.consume)
    TextView consume;
    @BindView(R.id.barChartView)
    BarChartView barChartView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_data_preview;
    }

    @Override
    public void initView() {
        List<Integer> datas = new ArrayList<>();
        datas.add(1600);
        datas.add(1900);
        datas.add(2000);
        datas.add(2300);
        datas.add(2700);
        List<String> xList = new ArrayList<>();
        xList.add("一月");
        xList.add("二月");
        xList.add("三月");
        xList.add("四月");
        xList.add("五月");
        List<Integer> yList = new ArrayList<>();
        yList.add(1000);
        yList.add(1500);
        yList.add(2000);
        yList.add(2500);
        yList.add(3000);
        barChartView.updateValueData(datas,xList,yList);
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
    public void onError(int whichApi,Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
