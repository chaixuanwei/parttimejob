package com.example.myapplication.model;

import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.frame.ICommonModel;
import com.example.myapplication.frame.ICommonView;
import com.example.myapplication.frame.NetManager;

import java.io.File;
import java.util.ArrayList;

public class MeModel implements ICommonModel {

    private int mLoadMode;

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.UPLOAD_PICTURES:
                mLoadMode = (int) t[0];
                File mFile = (File) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getUploadTop(mFile), view, whichApi, mLoadMode);
                break;
            case ApiConfig.SET_PREJECT:
                mLoadMode = (int) t[0];
                String mName = (String) t[1];
                String mNickname = (String) t[2];
                String mAddress = (String) t[3];
                int mSix = (int) t[4];
                int mAge = (int) t[5];
                ArrayList<String> mList = (ArrayList<String>) t[6];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setPerfect(mName,mNickname,mAddress,mSix,mAge,mList), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_PREJECT:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getPerfect(), view, whichApi, mLoadMode);
                break;
        }
    }
}
