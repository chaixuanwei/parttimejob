package com.sxxh.linghuo.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.home.adapter.SearchAdapter;
import com.sxxh.linghuo.home.bean.SearchDataBean;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;
import com.sxxh.linghuo.model.HomeModel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseMvpActivity<CommonPresenter, HomeModel> {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.txt_search)
    TextView txtSearch;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.hot)
    TagFlowLayout mHot;
    @BindView(R.id.history)
    TagFlowLayout mHistory;
    List<String> mHotList = new ArrayList<>();
    List<String> mHistoryList = new ArrayList<>();
    @BindView(R.id.bucket)
    TextView bucket;
    @BindView(R.id.search_ll)
    LinearLayout searchLl;
    @BindView(R.id.search_rv)
    RecyclerView searchRv;
    @BindView(R.id.search_srl)
    SmartRefreshLayout searchSrl;
    private TagAdapter mHotAdapter;
    private TagAdapter mHistoryAdapter;
    private SearchAdapter mAdapter;
    ArrayList<SearchDataBean.DataBean> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etSearch.getText().toString().equals("")) {
                    searchLl.setVisibility(View.VISIBLE);
                    searchSrl.setVisibility(View.GONE);
                } else {
                    searchLl.setVisibility(View.GONE);
                    searchSrl.setVisibility(View.VISIBLE);
                }
                mPresenter.getData(ApiConfig.SEARCH_DATA, LoadConfig.NORMAL, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        List<String> mStringList = SharedPrefrenceUtils.getStringList(SearchActivity.this, Config.SEARCH_HISTORY);
        mHistoryList.addAll(mStringList);
//        mHotList.add("adf");
//        mHotList.add("sdfasdfsdfas");
//        mHotList.add("adsfasdf");
//        mHotList.add("adfasdfasdfasdfasdfadsf");
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
                if (mStr.equals("")) {

                } else {
                    int mI = Integer.parseInt(mStr);
                    etSearch.setText(mHotList.get(mI));
                    etSearch.setSelection(mHotList.get(mI).length());
                }
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
                if (mStr.equals("")) {

                } else {
                    int mI = Integer.parseInt(mStr);
                    etSearch.setText(mHistoryList.get(mI));
                    etSearch.setSelection(mHistoryList.get(mI).length());
                }
            }
        });
        mAdapter = new SearchAdapter(this, mList);
        searchRv.setAdapter(mAdapter);
        searchRv.setLayoutManager(new LinearLayoutManager(this));
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
        Log.e("搜索", "onError: 搜索" );
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.SEARCH_DATA:
                SearchDataBean mSearchDataBeans = (SearchDataBean) t[0];
                if (mSearchDataBeans.getData().size() > 0 ) {
                    mList.addAll(mSearchDataBeans.getData());
                } else {
                    ToastUtils.showShort("抱歉，无搜索相关信息!");
                }
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @OnClick({R.id.back, R.id.txt_search, R.id.bucket})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.txt_search:
                String mS = etSearch.getText().toString();
                if (mS.equals("")) {
                    ToastUtils.showShort("请填写搜索内容！");
                } else {
                    mPresenter.getData(ApiConfig.SEARCH_DATA, LoadConfig.NORMAL, mS);
                    if (mHistoryList.contains(mS)) {

                    } else {
                        mHistoryList.add(mS);
                    }
                    SharedPrefrenceUtils.putStringList(SearchActivity.this, Config.SEARCH_HISTORY, mHistoryList);
                    mHistoryAdapter.notifyDataChanged();
                    searchLl.setVisibility(View.GONE);
                    searchSrl.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.bucket:
                mHistoryList.clear();
                SharedPrefrenceUtils.putStringList(SearchActivity.this, Config.SEARCH_HISTORY, mHistoryList);
                mHistoryAdapter.notifyDataChanged();
                break;
        }
    }
}
