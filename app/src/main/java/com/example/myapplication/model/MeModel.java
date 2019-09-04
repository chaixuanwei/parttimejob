package com.example.myapplication.model;

import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.frame.ICommonModel;
import com.example.myapplication.frame.ICommonView;
import com.example.myapplication.frame.NetManager;

import java.io.File;
import java.util.ArrayList;

public class MeModel implements ICommonModel {

    private int mLoadMode;
    private int mPage;
    private int mTaskid;
    private int mUserid;

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.ALTER_TOP:
                mLoadMode = (int) t[0];
                String url = (String) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().alterTop(url), view, whichApi, mLoadMode);
                break;
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
                String ids = "";
                if (mList.size() != 0) {
                    for (int i = 0; i < mList.size(); i++) {
                        if (i != mList.size() - 1) {
                            ids += mList.get(i) + ";";
                        } else {
                            ids += mList.get(i);
                        }
                    }
                }

                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setPerfect(mName, mNickname, mAddress, mSix, mAge, ids), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_PREJECT:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getPerfect(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.SET_PEOPLE:
                mLoadMode = (int) t[0];
                String card = (String) t[1];
                String name = (String) t[2];
                String classify = (String) t[3];
                String hand = (String) t[4];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setPeople(card, name, classify, hand), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_PEOPLE:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getPeopleResult(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.SET_FIRM:
                mLoadMode = (int) t[0];
                String company_name = (String) t[1];
                String scale = (String) t[2];
                String industry = (String) t[3];
                String contacts = (String) t[4];
                String contacts_tel = (String) t[5];
                String img_1 = (String) t[6];
                String img_2 = (String) t[7];
                String img_3 = (String) t[8];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setFirm(company_name, scale, industry, contacts, contacts_tel, img_1, img_2, img_3), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_FIRM:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getFirmResult(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_INDUSTRY:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getIndustry(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_SCALE:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getScale(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.ORDER_RECEIVING:
                mLoadMode = (int) t[0];
                int is_order = (int) t[1];
                mPage = (int) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getMyIssus(is_order, mPage), view, whichApi, mLoadMode);
                break;
            case ApiConfig.FEEDBACK:
                mLoadMode = (int) t[0];
                String title = (String) t[1];
                String content = (String) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setFeedback(title, content), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_FEEDBACK:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getFeedback(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_APPLYPEOPLE:
                mLoadMode = (int) t[0];
                mTaskid = (int) t[1];
                mPage = (int) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getApplyPeople(mTaskid, mPage), view, whichApi, mLoadMode);
                break;
            case ApiConfig.APPLY_AUDIT:
                mLoadMode = (int) t[0];
                mTaskid = (int) t[1];
                mUserid = (int) t[2];
                int status = (int) t[3];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setApplyAudit(mTaskid, mUserid, status), view, whichApi, mLoadMode);
                break;
            case ApiConfig.PROJECT_SUFFER:
                mLoadMode = (int) t[0];
                mUserid = (int) t[1];
                mPage = (int) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getProject(mUserid, mPage), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_PROJECT_RATE:
                mLoadMode = (int) t[0];
                mTaskid = (int) t[1];
                mPage = (int) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getProjectrate(mTaskid, mPage), view, whichApi, mLoadMode);
                break;
        }
    }
}
