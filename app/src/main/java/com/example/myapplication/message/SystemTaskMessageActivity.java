package com.example.myapplication.message;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.config.LoadConfig;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.message.adapter.AfficheAdapter;
import com.example.myapplication.message.adapter.SystemMessageAdapter;
import com.example.myapplication.message.bean.AfficheBean;
import com.example.myapplication.message.bean.NullBean;
import com.example.myapplication.message.bean.SystemBean;
import com.example.myapplication.model.MessageModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SystemTaskMessageActivity extends BaseMvpActivity<CommonPresenter, MessageModel> {

    @BindView(R.id.message_rv)
    RecyclerView messageRv;
    @BindView(R.id.message_srl)
    SmartRefreshLayout messageSrl;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tbr_name)
    TextView tbrName;
    private String mType;
    int mPage = 1;
    private SystemMessageAdapter mSystemAdapter;
    private AfficheAdapter mAfficheAdapter;
    ArrayList<SystemBean.DataBean> mSystemList = new ArrayList<>();
    ArrayList<AfficheBean.DataBean> mAddicheList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_task_message;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        mType = mIntent.getStringExtra("type");
        if (mType.equals("0")) {
            tbrName.setText("系统消息 ");
        } else if (mType.equals("1")) {
            tbrName.setText("任务消息 ");
        } else if (mType.equals("2")) {
            tbrName.setText("公告消息 ");
        }
        initRecycleView(messageRv, messageSrl);
        if (!mType.equals("2")) {
            mSystemAdapter = new SystemMessageAdapter(this, mSystemList);
            messageRv.setAdapter(mSystemAdapter);
        } else {
            mAfficheAdapter = new AfficheAdapter(this, mAddicheList);
            messageRv.setAdapter(mAfficheAdapter);
        }
        messageRv.setLayoutManager(new LinearLayoutManager(this));
        messageRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.MESSAGE_LIST, LoadConfig.NORMAL, mType, mPage + "", 8 + "");
    }

    @Override
    public CommonPresenter getPresenter() {
        return new CommonPresenter();
    }

    @Override
    public MessageModel getModel() {
        return new MessageModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.MESSAGE_LIST:
                if (!mType.equals("2")) {
                    SystemBean mSystemBeans = (SystemBean) t[0];
                    int upordown = (int) t[1];
                    if (upordown == LoadConfig.REFRESH) {
                        mSystemList.clear();
                        messageSrl.finishRefresh();
                    } else if (upordown == LoadConfig.LOADMORE) {
                        messageSrl.finishLoadMore();
                    }
                    mSystemList.addAll(mSystemBeans.getData());
                    mSystemAdapter.notifyDataSetChanged();
                } else {
                    AfficheBean mAfficheBeans = (AfficheBean) t[0];
                    int upordown = (int) t[1];
                    if (upordown == LoadConfig.REFRESH) {
                        mAddicheList.clear();
                        messageSrl.finishRefresh();
                    } else if (upordown == LoadConfig.LOADMORE) {
                        messageSrl.finishLoadMore();
                    }
                    mAddicheList.addAll(mAfficheBeans.getData());
                    mAfficheAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void refresh() {
        mPage = 0;
        mPresenter.getData(ApiConfig.MESSAGE_LIST, LoadConfig.REFRESH, mType, mPage + "", 8 + "");
        super.refresh();
    }

    @Override
    public void loadMore() {
        mPage += mPage;
        mPresenter.getData(ApiConfig.MESSAGE_LIST, LoadConfig.LOADMORE, mType, mPage + "", 8 + "");
        super.loadMore();
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
