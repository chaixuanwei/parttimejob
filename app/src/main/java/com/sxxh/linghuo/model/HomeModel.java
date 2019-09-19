package com.sxxh.linghuo.model;

import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.frame.ICommonModel;
import com.sxxh.linghuo.frame.ICommonView;
import com.sxxh.linghuo.frame.NetManager;

public class HomeModel implements ICommonModel {

    private int mLoadMode;

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
        }
    }
}
