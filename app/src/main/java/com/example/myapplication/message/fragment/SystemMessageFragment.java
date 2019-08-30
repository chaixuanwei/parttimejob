package com.example.myapplication.message.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.message.SystemTaskMessageActivity;
import com.example.myapplication.model.MessageModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemMessageFragment extends BaseMvpFragment<CommonPresenter, MessageModel> {
    static SystemMessageFragment fragment;
    @BindView(R.id.system_message)
    TextView systemMessage;
    @BindView(R.id.task_message)
    TextView taskMessage;
    @BindView(R.id.affiche_message)
    TextView afficheMessage;

    public static SystemMessageFragment newInstance() {
        if (fragment == null) fragment = new SystemMessageFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message_data;
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
    public MessageModel getModel() {
        return new MessageModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.system_message, R.id.task_message, R.id.affiche_message})
    public void onClick(View view) {
        Intent mIntent = new Intent(getActivity(), SystemTaskMessageActivity.class);
        switch (view.getId()) {
            case R.id.system_message:
                mIntent.putExtra("type", "0");
                startActivity(mIntent);
                break;
            case R.id.task_message:
                mIntent.putExtra("type", "1");
                startActivity(mIntent);
                break;
            case R.id.affiche_message:
                mIntent.putExtra("type", "2");
                startActivity(mIntent);
                break;
        }
    }
}
