package com.sxxh.linghuo.model;

import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.frame.ICommonModel;
import com.sxxh.linghuo.frame.ICommonView;
import com.sxxh.linghuo.frame.NetManager;

public class HomeModel implements ICommonModel {

    private int mLoadMode;
    private int mTId;

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.HOME_BANNER:
                mLoadMode = (int) t[0];
                String id = (String) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getBanner(id), view, whichApi, mLoadMode);
                break;
            case ApiConfig.HOME_MENU:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getMenu(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.ISSUS_MESSAGE:
                mLoadMode = (int) t[0];
                mTId = (int) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getIssusMessage(mTId), view, whichApi, mLoadMode);
                break;
            case ApiConfig.TASKDETAIL:
                mLoadMode = (int) t[0];
                int Id = (int) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getTaskdetail(Id), view, whichApi, mLoadMode);
                break;
            case ApiConfig.SEARCH_DATA:
                mLoadMode = (int) t[0];
                String mData = (String) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getSearch(mData), view, whichApi, mLoadMode);
                break;
            case ApiConfig.HOME_DATA:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getHomeData(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.ISSUER_GENERAL_EVALUATION:
                mLoadMode = (int) t[0];
                int gsId = (int) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService()
                        .getIssuerGeneralEvaluation(gsId), view, whichApi, mLoadMode);
                break;
            case ApiConfig.ATONCE_APPLY:
                mLoadMode = (int) t[0];
                mTId = (int) t[1];
                int enroll = (int) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setApply(mTId, enroll), view, whichApi, mLoadMode);
                break;
        }
    }
}
