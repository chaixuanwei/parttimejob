package com.sxxh.linghuo.model;

import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.frame.ICommonModel;
import com.sxxh.linghuo.frame.ICommonView;
import com.sxxh.linghuo.frame.NetManager;

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
