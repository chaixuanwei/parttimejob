package com.sxxh.linghuo.model;

import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.frame.ICommonModel;
import com.sxxh.linghuo.frame.ICommonView;
import com.sxxh.linghuo.frame.NetManager;

import java.io.File;
import java.util.ArrayList;

public class MeModel implements ICommonModel {

    private int mLoadMode;
    private int mPage;
    private int mTaskid;
    private int mUserid;
    private int mT_id;

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
                String email = (String) t[5];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setPeople(card, name, classify, hand, email), view, whichApi, mLoadMode);
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
            case ApiConfig.GET_WORKING_LIST:
                mLoadMode = (int) t[0];
                mPage = (int) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getWorking(mPage), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_SALARY:
                mLoadMode = (int) t[0];
                mPage = (int) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getSalary(mPage), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_WAIT:
                mLoadMode = (int) t[0];
                mPage = (int) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getWait(mPage), view, whichApi, mLoadMode);
                break;
            case ApiConfig.SET_SUBMIT:
                mLoadMode = (int) t[0];
                mT_id = (int) t[1];
                String task_images = (String) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setSubmit(mT_id, task_images), view, whichApi, mLoadMode);
                break;
            case ApiConfig.ID_BIND:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getIdBind(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_ZFB_LOGIN:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getZFBLogin(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.ZFB_LOGIN:
                mLoadMode = (int) t[0];
                String code = (String) t[1];
                String zfbact = (String) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getZFBToken(code, zfbact), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_WX_LOGIN:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getWXLogin(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.WX_LOGIN:
                mLoadMode = (int) t[0];
                String nickname = (String) t[1];
                String openid = (String) t[2];
                String headimgurl = (String) t[3];
                String province = (String) t[4];
                String city = (String) t[5];
                String wxact = (String) t[6];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getWXToken(nickname, openid, headimgurl, province, city, wxact), view, whichApi, mLoadMode);
                break;
            case ApiConfig.HEAD_BALANCE:
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getHeadBalance(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.FINANCIAL_DETAIL:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getFinancialDetails(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.WAIT_APPRAISE:
                mLoadMode = (int) t[0];
                mPage = (int) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getWaitAppraise(mPage), view, whichApi, mLoadMode);
                break;
            case ApiConfig.SET_ATONCE:
                mLoadMode = (int) t[0];
                int u_id = (int) t[1];
                int paycomment = (int) t[2];
                int servicecomment = (int) t[3];
                int stationcomment = (int) t[4];
                String tagscomment = (String) t[5];
                String concomment = (String) t[5];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setAtonce(u_id, paycomment, servicecomment, stationcomment, tagscomment, concomment), view, whichApi, mLoadMode);
                break;
            case ApiConfig.SET_WITHDRAW:
                mLoadMode = (int) t[0];
                int type = (int) t[1];
                float price = (float) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setWithDraw(type, price), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_WITHDRAW:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getWithDraw(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.COMPAINT_FIRM:
                mLoadMode = (int) t[0];
                mUserid = (int) t[1];
                String user_type = (String) t[2];
                String user_content = (String) t[3];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setComplaintFirm(mUserid, user_type, user_content), view, whichApi, mLoadMode);
                break;
            case ApiConfig.COMPAINT_PEOPLE:
                mLoadMode = (int) t[0];
                int firm_id = (int) t[1];
                String firm_type = (String) t[2];
                String firm_content = (String) t[3];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setComplaintPeople(firm_id, firm_type, firm_content), view, whichApi, mLoadMode);
                break;
            case ApiConfig.BASIC_INFORMATION:
                mLoadMode = (int) t[0];
                mUserid = (int) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getBasicInformation(mUserid + ""), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_NATURE:
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getNature(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_ISSUE:
                mLoadMode = (int) t[0];
                mUserid = (int) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getIssue(mUserid + ""), view, whichApi, mLoadMode);
                break;
            case ApiConfig.UPDATA_ISSUS:
                mLoadMode = (int) t[0];
                String comname = (String) t[1];
                String des = (String) t[2];
                String pay = (String) t[3];
                String naparent_id = (String) t[4];
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
                mT_id = (int) t[18];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService()
                        .updataIssus(comname, des, pay, naparent_id, property, work_location, start_time, end_time, zp_num
                                , contact, phone, is_interview, height_require, other_require, is_muster, muster_time, muster_address, mT_id), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_TASK_DETAILS:
                mLoadMode = (int) t[0];
                mT_id = (int) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getTaskPhoto(mT_id), view, whichApi, mLoadMode);
                break;
            case ApiConfig.GET_MYMESSAGE:
                mLoadMode = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().getMyMessage(), view, whichApi, mLoadMode);
                break;
            case ApiConfig.PROJECT_REVIEW:
                mLoadMode = (int) t[0];
                mTaskid = (int) t[1];
                mUserid = (int) t[2];
                int mEnroll = (int) t[3];
                NetManager.getNetManager().netMethod(NetManager.getNetManager().getNetService().setProjectApply(mTaskid, mUserid, mEnroll), view, whichApi, mLoadMode);
                break;
        }
    }
}
