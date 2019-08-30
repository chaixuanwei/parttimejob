package com.example.myapplication.model;

import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.frame.ICommonModel;
import com.example.myapplication.frame.ICommonView;
import com.example.myapplication.frame.NetManager;

public class IssusModel implements ICommonModel {

    private int mLoadMode;

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
        }
    }
}
