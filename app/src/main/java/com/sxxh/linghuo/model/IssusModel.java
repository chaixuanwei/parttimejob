package com.sxxh.linghuo.model;

import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.frame.ICommonModel;
import com.sxxh.linghuo.frame.ICommonView;
import com.sxxh.linghuo.frame.NetManager;

public class IssusModel implements ICommonModel {

    private int mLoadMode;
    private String mTask_id;
    private String mPrice;
    private String mPay_type;
    private String mId;

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.TO_ISSUS:
                mLoadMode = (int) t[0];
                String name = (String) t[1];
                String des = (String) t[2];
                String pay = (String) t[3];
                int naparent_id = (int) t[4];
                int property = (int) t[5];
                String work_location = (String) t[6];
                int start_time = (int) t[7];
                int end_time = (int) t[8];
                int zp_num = (int) t[9];
                String contact = (String) t[10];
                String phone = (String) t[11];
                int is_interview = (int) t[12];
                String height_require = (String) t[13];
                String other_require = (String) t[14];
                int is_muster = (int) t[15];
                int muster_time = (int) t[16];
                String muster_address = (String) t[17];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService()
                        .ToIssus(name, des, pay, naparent_id, property, work_location, start_time, end_time, zp_num
                                , contact, phone, is_interview, height_require, other_require, is_muster, muster_time, muster_address), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_NATURE:
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getNature(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.PAY:
                mLoadMode = (int) t[0];
                mTask_id = (String) t[1];
                mPrice = (String) t[2];
                mPay_type = (String) t[3];
                mId = (String) t[4];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getZFBPay(mTask_id, mPrice, mPay_type, mId), view, whichApi, mLoadMode);
                break;
            case ApiConfig.WXPAY:
                mLoadMode = (int) t[0];
                mTask_id = (String) t[1];
                mPrice = (String) t[2];
                mPay_type = (String) t[3];
                mId = (String) t[4];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getWXPay(mTask_id, mPrice, mPay_type, mId), view, whichApi, mLoadMode);
                break;
        }
    }
}
