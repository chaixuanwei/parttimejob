package com.example.myapplication.model;

import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.frame.ICommonModel;
import com.example.myapplication.frame.ICommonView;
import com.example.myapplication.frame.NetManager;

public class LoginModel implements ICommonModel {

    private int mLoadMode;

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.REGIST:
                mLoadMode = (int) t[0];
                String mName = (String) t[1];
                String mPassword = (String) t[2];
                String mCode = (String) t[3];
                String mCd = (String) t[4];
                String zfbid = (String) t[5];
                String wxid = (String) t[6];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getRegister(mName, mPassword, mCode, mCd, zfbid, wxid), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_AUTH_CODE:
                mLoadMode = (int) t[0];
                String mobile = (String) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getAuthCode(mobile), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_Login:
                mLoadMode = (int) t[0];
                String mUserphone = (String) t[1];
                String mUserpassword = (String) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getLogin(mUserphone, mUserpassword), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_FindPassword:
                mLoadMode = (int) t[0];
                String mFindphone = (String) t[1];
                String mFindpassword = (String) t[2];
                String mFindCode = (String) t[3];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getFindPassWord(mFindphone, mFindpassword, mFindCode), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_ZFB_LOGIN:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getZFBLogin(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.ZFB_LOGIN:
                mLoadMode = (int) t[0];
                String code = (String) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getZFBToken(code), view, whichApi, mLoadMode);
                break;
        }
    }
}
