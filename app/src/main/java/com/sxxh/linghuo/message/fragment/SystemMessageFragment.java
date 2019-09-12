package com.sxxh.linghuo.message.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.frame.BaseMvpFragment;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.message.SystemTaskMessageActivity;
import com.sxxh.linghuo.model.MessageModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemMessageFragment extends BaseMvpFragment<CommonPresenter, MessageModel> {
    static SystemMessageFragment fragment;
    @BindView(R.id.system_rl)
    RelativeLayout systemMessage;
    @BindView(R.id.task_rl)
    RelativeLayout taskMessage;
    @BindView(R.id.affiche_rl)
    RelativeLayout afficheMessage;

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

    @OnClick({R.id.system_rl, R.id.task_rl, R.id.affiche_rl})
    public void onClick(View view) {
        Intent mIntent = new Intent(getActivity(), SystemTaskMessageActivity.class);
        switch (view.getId()) {
            case R.id.system_rl:
                mIntent.putExtra("type", "0");
                startActivity(mIntent);
                break;
            case R.id.task_rl:
                mIntent.putExtra("type", "1");
                startActivity(mIntent);
                break;
            case R.id.affiche_rl:
                mIntent.putExtra("type", "2");
                startActivity(mIntent);
                break;
        }
    }
}
