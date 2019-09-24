package com.sxxh.linghuo.home.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.model.HomeModel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseMvpActivity<CommonPresenter, HomeModel> {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.txt_search)
    TextView txtSearch;
    @BindView(R.id.hot)
    TagFlowLayout mHot;
    @BindView(R.id.history)
    TagFlowLayout mHistory;
    List<String> mHotList = new ArrayList<>();
    List<String> mHistoryList = new ArrayList<>();
    @BindView(R.id.bucket)
    TextView bucket;
    private TagAdapter mHotAdapter;
    private TagAdapter mHistoryAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        mHistoryList.add("aaaaaaaaaaaaaaaa");
        mHistoryList.add("aaaaa");
        mHistoryList.add("aaaaaaaaaaaaaaaaaaaaaaabbbbbb");
        mHistoryList.add("bbbd");
        mHotList.add("adf");
        mHotList.add("sdfasdfsdfas");
        mHotList.add("adsfasdf");
        mHotList.add("adfasdfasdfasdfasdfadsf");
        mHotAdapter = new TagAdapter(mHotList) {
            @Override
            public View getView(FlowLayout parent, int position, Object pO) {
                LayoutInflater inflater = LayoutInflater.from(SearchActivity.this);
                TextView mHottv = (TextView) inflater.inflate(R.layout.flow_item, mHot, false);
                String mStr = (String) pO;
                mHottv.setText(mStr);
                return mHottv;
            }
        };
        mHot.setAdapter(mHotAdapter);
        mHot.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                String m1 = selectPosSet.toString();
                String m2 = m1.replace("[", "");
                String mStr = m2.replace("]", "");
                int mI = Integer.parseInt(mStr);
                etSearch.setText(mHotList.get(mI));
            }
        });
        mHistoryAdapter = new TagAdapter(mHistoryList) {
            @Override
            public View getView(FlowLayout parent, int position, Object pO) {
                LayoutInflater inflater = LayoutInflater.from(SearchActivity.this);
                TextView mHistorytv = (TextView) inflater.inflate(R.layout.flow_item, mHot, false);
                String mStr = (String) pO;
                mHistorytv.setText(mStr);
                return mHistorytv;
            }
        };
        mHistory.setAdapter(mHistoryAdapter);
        mHistory.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                String m1 = selectPosSet.toString();
                String m2 = m1.replace("[", "");
                String mStr = m2.replace("]", "");
                int mI = Integer.parseInt(mStr);
                etSearch.setText(mHistoryList.get(mI));
            }
        });
        bucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHistoryList.clear();
            }
        });
    }

    @Override
    public void initData() {

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

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick(R.id.txt_search)
    public void onClick() {
    }
}
