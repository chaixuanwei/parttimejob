package com.sxxh.linghuo.model;

import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.frame.ICommonModel;
import com.sxxh.linghuo.frame.ICommonView;
import com.sxxh.linghuo.frame.NetManager;

public class MessageModel implements ICommonModel {
    private int mLoadMode;
    private String mType;

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.MESSAGE_LIST:
                mLoadMode = (int) t[0];
                mType = (String) t[1];
                String page = (String) t[2];
                String limit = (String) t[3];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getMessageList(mType, page, limit), view, whichApi, mLoadMode);
                break;
            case ApiConfig.YET_READ:
                mLoadMode = (int) t[0];
                String id = (String) t[1];
                mType = (String) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getYetRead(id, mType), view, whichApi, mLoadMode);
                break;
//            case ApiConfig.NON_READ_A:
//                mLoadMode = (int) t[0];
//                mType = (String) t[1];
//                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getNonRead(mType), view, whichApi, mLoadMode);
//                break;
//            case ApiConfig.NON_READ_B:
//                mLoadMode = (int) t[0];
//                mType = (String) t[1];
//                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getNonRead(mType), view, whichApi, mLoadMode);
//                break;
//            case ApiConfig.NON_READ_C:
//                mLoadMode = (int) t[0];
//                mType = (String) t[1];
//                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getNonRead(mType), view, whichApi, mLoadMode);
//                break;
        }
    }
}
