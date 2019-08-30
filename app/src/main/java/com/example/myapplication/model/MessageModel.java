package com.example.myapplication.model;

import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.frame.ICommonModel;
import com.example.myapplication.frame.ICommonView;
import com.example.myapplication.frame.NetManager;

public class MessageModel implements ICommonModel {
    private int mLoadMode;

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.MESSAGE_LIST:
                mLoadMode = (int) t[0];
                String type = (String) t[1];
                String page = (String) t[2];
                String limit = (String) t[3];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getMessageList(type, page, limit), view, whichApi, mLoadMode);
                break;
        }
    }
}
