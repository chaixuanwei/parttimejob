package com.sxxh.linghuo.frame;

import com.sxxh.linghuo.home.bean.BannerBean;
import com.sxxh.linghuo.home.bean.IssuerGeneralEvaluation;
import com.sxxh.linghuo.home.bean.IssusMessageBean;
import com.sxxh.linghuo.home.bean.MenuBean;
import com.sxxh.linghuo.home.bean.SearchDataBean;
import com.sxxh.linghuo.home.bean.TaskdetailBean;
import com.sxxh.linghuo.home.bean.HomeData;
import com.sxxh.linghuo.issus.bean.NatureBean;
import com.sxxh.linghuo.login.bean.AuthCodeBean;
import com.sxxh.linghuo.login.bean.LoginBean;
import com.sxxh.linghuo.login.bean.WBTokenBean;
import com.sxxh.linghuo.login.bean.WXLoginBean;
import com.sxxh.linghuo.login.bean.WXTokenBean;
import com.sxxh.linghuo.login.bean.ZFBLoginBean;
import com.sxxh.linghuo.login.bean.ZFBTokenBean;
import com.sxxh.linghuo.me.bean.ApplyPeopleBean;
import com.sxxh.linghuo.me.bean.BalanceDrawBean;
import com.sxxh.linghuo.me.bean.BasicInformationBean;
import com.sxxh.linghuo.me.bean.FeedBackBean;
import com.sxxh.linghuo.me.bean.FinancialDetailsBean;
import com.sxxh.linghuo.me.bean.GetIssueBean;
import com.sxxh.linghuo.me.bean.HeadBalanceBean;
import com.sxxh.linghuo.me.bean.IdBindBean;
import com.sxxh.linghuo.me.bean.IndustryBean;
import com.sxxh.linghuo.me.bean.MyIssusBean;
import com.sxxh.linghuo.me.bean.PerfectBean;
import com.sxxh.linghuo.me.bean.ProjectProgressBean;
import com.sxxh.linghuo.me.bean.ProjectSufferBean;
import com.sxxh.linghuo.me.bean.SalaryBean;
import com.sxxh.linghuo.me.bean.ScaleBean;
import com.sxxh.linghuo.me.bean.UploadTopBean;
import com.sxxh.linghuo.me.bean.WaitAppraiseBean;
import com.sxxh.linghuo.me.bean.WorkingBean;
import com.sxxh.linghuo.message.bean.AfficheBean;
import com.sxxh.linghuo.message.bean.CountBean;

import java.io.File;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface INetService {
    //发送验证码
    @POST("api/job/Sendsms")
    @FormUrlEncoded
    Observable<AuthCodeBean> getAuthCode(@Field("mobile") String mobile);

    //注册
    @POST("api/user/public/register")
    @FormUrlEncoded
    Observable<AuthCodeBean> getRegister(@Field("username") String name,
                                         @Field("password") String password,
                                         @Field("mobile_code") String code,
                                         @Field("agreement") String cd,
                                         @Field("alipay_user_id") String alipay_user_id,
                                         @Field("wx_user_id") String wx_user_id);

    //登录
    @POST("api/user/public/login")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@Field("username") String name,
                                   @Field("password") String password);

    //忘记密码
    @POST("api/user/public/passwordReset")
    @FormUrlEncoded
    Observable<AuthCodeBean> getFindPassWord(@Field("username") String name,
                                             @Field("password") String password,
                                             @Field("mobile_code") String code);

    //上传
    @POST("api/user/upload/one")
    @FormUrlEncoded
    Observable<UploadTopBean> getUploadTop(@Field("file") File file);

    //完善个人资料
    @POST("api/user/profile/userInfo")
    @FormUrlEncoded
    Observable<AuthCodeBean> setPerfect(@Field("real_name") String name,
                                        @Field("user_nickname") String nickname,
                                        @Field("address") String address,
                                        @Field("sex") int sex,
                                        @Field("age") int age,
                                        @Field("project_images") String project
    );

    //获取个人资料
    @GET("api/user/profile/userInfo")
    Observable<PerfectBean> getPerfect();

    //修改头像
    @POST("api/user/profile/userHeadPic")
    @FormUrlEncoded
    Observable<AuthCodeBean> alterTop(@Field("image_url") String top);

    //发布任务
    @POST("api/task/publish/index")
    @FormUrlEncoded
    Observable<AuthCodeBean> ToIssus(@Field("name") String name, @Field("des") String des, @Field("pay") String pay
            , @Field("parent_id") int parent_id, @Field("property") int property, @Field("work_location") String work_location
            , @Field("start_time") int start_time, @Field("end_time") int end_time, @Field("zp_num") int zp_num, @Field("contact") String contact
            , @Field("phone") String phone, @Field("is_interview") int is_interview, @Field("height_require") String height_require
            , @Field("other_require") String other_require, @Field("is_muster") int is_muster, @Field("muster_time") int muster_time
            , @Field("muster_address") String muster_address);

    //获取任务分类
    @POST("api/task/task_cate")
    Observable<NatureBean> getNature();

    //提交个人认证
    @POST("api/user/profile/authInfo")
    @FormUrlEncoded
    Observable<AuthCodeBean> setPeople(@Field("id_number") String id_number,
                                       @Field("real_name") String real_name,
                                       @Field("id_front") String id_front,
                                       @Field("id_back") String id_back,
                                       @Field("user_email") String user_email);

    //获取个人认证结果
    @GET("api/user/profile/authInfo")
    Observable<AuthCodeBean> getPeopleResult();

    //提交企业认证
    @POST("api/user/profile/companyInfo")
    @FormUrlEncoded
    Observable<AuthCodeBean> setFirm(@Field("company_name") String company_name,
                                     @Field("industry") String industry,
                                     @Field("contacts") String contacts,
                                     @Field("scale") String scale,
                                     @Field("contacts_tel") String contacts_tel,
                                     @Field("img_1") String img_1,
                                     @Field("img_2") String img_2,
                                     @Field("img_3") String img_3);

    //获取企业认证结果
    @GET("api/user/profile/companyInfo")
    Observable<AuthCodeBean> getFirmResult();

    //获取行业
    @GET("api/user/public/industryList")
    Observable<IndustryBean> getIndustry();

    //获取规模
    @GET("api/user/public/scale")
    Observable<ScaleBean> getScale();

    //我的发布(已接单,未接单)
    @GET("api/user/taskorder/index")
    Observable<MyIssusBean> getMyIssus(@Query("is_order") int is_order,
                                       @Query("page") int page);

    //消息列表
    @GET("api/user/profile/myNotice")
    Observable<AfficheBean> getMessageList(@Query("type") String type,
                                           @Query("page") String page,
                                           @Query("limit") String limit);

    //意见反馈
    @POST("api/user/profile/feedBack")
    @FormUrlEncoded
    Observable<AuthCodeBean> setFeedback(@Field("title") String title,
                                         @Field("content") String content);

    //获取意见
    @GET("api/user/public/feedBackCategory")
    Observable<FeedBackBean> getFeedback();

    //获取报名列表
    @GET("api/user/taskorder/enroll")
    Observable<ApplyPeopleBean> getApplyPeople(@Query("task_id") int task_id,
                                               @Query("page") int page);

    //报名审核
    @POST("api/user/checkorderuser/check")
    @FormUrlEncoded
    Observable<AuthCodeBean> setApplyAudit(@Field("task_id") int task_id,
                                           @Field("user_id") int user_id,
                                           @Field("status") int status);

    //项目经验
    @GET("api/user/taskorder/projectexp")
    Observable<ProjectSufferBean> getProject(@Query("user_id") int user_id,
                                             @Query("page") int page);

    //获取项目进度
    @GET("api/user/taskorder/projectrate")
    Observable<ProjectProgressBean> getProjectrate(@Query("task_id") int task_id,
                                                   @Query("page") int page);

    //获取工作中列表
    @GET("api/user/taskcommit/index")
    Observable<WorkingBean> getWorking(@Query("page") int page);

    //获取支付宝登录
    @GET("api/user/alipay/login")
    Observable<ZFBLoginBean> getZFBLogin();

    //获取微信登录
    @GET("api/user/wx/login")
    Observable<WXLoginBean> getWXLogin();

    //支付宝登录获取token
    @POST("api/user/alipay/login")
    @FormUrlEncoded
    Observable<ZFBTokenBean> getZFBToken(@Field("auth_code") String auth_code,
                                         @Field("act") String act);

    //微信登录获取token
    @POST("api/user/wx/login")
    @FormUrlEncoded
    Observable<WXTokenBean> getWXToken(@Field("nickname") String nickname,
                                       @Field("openid") String openid,
                                       @Field("headimgurl") String headimgurl,
                                       @Field("province") String province,
                                       @Field("city") String city,
                                       @Field("act") String act);

    //微博登录获取token
    @POST("api/user/sina/login")
    @FormUrlEncoded
    Observable<WBTokenBean> getWBToken(@Field("nickname") String nickname,
                                       @Field("openid") String openid,
                                       @Field("headimgurl") String headimgurl,
                                       @Field("province") String province,
                                       @Field("city") String city,
                                       @Field("act") String act);

//    //支付宝支付
//    @GET("api/user/alipay/order_pay")
//    Observable<> getZFBPay();

    //工资未结列表
    @GET("api/user/taskorder/notpay")
    Observable<SalaryBean> getSalary(@Query("page") int page);

    //待录取列表
    @GET("api/user/taskorder/waitenroll")
    Observable<SalaryBean> getWait(@Query("page") int page);

    //提交任务
    @POST("api/user/taskcommit/commit")
    @FormUrlEncoded
    Observable<AuthCodeBean> setSubmit(@Field("t_id") int t_id,
                                       @Field("task_images") String task_images);

    //账户绑定
    @GET("api/user/profile/bindingList")
    Observable<IdBindBean> getIdBind();

    //获取头像和余额
    @GET("api/user/profile/user_balance")
    Observable<HeadBalanceBean> getHeadBalance();

    //资金明细
    @GET("api/user/profile/capital_details")
    Observable<FinancialDetailsBean> getFinancialDetails();

    //待评价列表
    @GET("api/user/taskorder/waitcomment")
    Observable<WaitAppraiseBean> getWaitAppraise(@Query("page") int page);

    //评价提交
    @POST("api/user/comment/commit")
    @FormUrlEncoded
    Observable<AuthCodeBean> setAtonce(@Field("u_id") int u_id,
                                       @Field("paycomment") int paycomment,
                                       @Field("servicecomment") int servicecomment,
                                       @Field("stationcomment") int stationcomment,
                                       @Field("tagscomment") String tagscomment,
                                       @Field("concomment") String concomment);

    //提交提现
    @POST("api/user/profile/withdrawal")
    @FormUrlEncoded
    Observable<AuthCodeBean> setWithDraw(@Field("type") int type,
                                         @Field("price") float price);

    //获取提现
    @GET("api/user/profile/withdrawal")
    Observable<BalanceDrawBean> getWithDraw();

    //兼职人对发布任务人投诉
    @POST("api/user/complaint/parttimetopublish")
    @FormUrlEncoded
    Observable<AuthCodeBean> setComplaintFirm(@Field("uid") int uid,
                                                @Field("report_type") String report_type,
                                                @Field("content") String content);

    //发布任务人对兼职人投诉
    @POST("api/user/complaint/publishtoparttime")
    @FormUrlEncoded
    Observable<AuthCodeBean> setComplaintPeople(@Field("user_id") int user_id,
                                              @Field("report_type") String report_type,
                                              @Field("content") String content);

    //首页轮播图
    @GET("api/job/public/get_slides")
    Observable<BannerBean> getBanner(@Query("id") String id);

    //首页菜单
    @GET("api/job/public/menu")
    Observable<MenuBean> getMenu();

    //标记消息已读
    @POST("api/user/profile/readNotice")
    @FormUrlEncoded
    Observable<AuthCodeBean> getYetRead(@Field("id") String id,
                                        @Field("type") String type);

    //获取消息未读
    @GET("api/user/profile/noSeecount")
    Observable<CountBean> getNonRead(@Query("type") String type);

    //首页发布人基础信息
    @GET("api/user/companyinfo/job")
    Observable<IssusMessageBean> getIssusMessage(@Query("uid") int uId);

    //兼职详情
    @POST("demo/index/taskdetail")
    @FormUrlEncoded
    Observable<TaskdetailBean> getTaskdetail(@Field("id") int id);

    //首页信息
    @GET("demo/index/firstPage")
    Observable<HomeData> getHomeData();

    //搜索
    @POST("demo/index/searchjobs")
    @FormUrlEncoded
    Observable<SearchDataBean> getSearch(@Field("name") String name);

    //基础信息
    @POST("demo/index/infomsg")
    @FormUrlEncoded
    Observable<BasicInformationBean> getBasicInformation(@Field("id") String id);

    //获取发布信息
    @GET("demo/index/nordermsg")
    Observable<GetIssueBean> getIssue(@Query("id") String id);


    //首页发布人整体评价
    @POST("/demo/index/publicers")
    @FormUrlEncoded
    Observable<IssuerGeneralEvaluation> getIssuerGeneralEvaluation(@Field("id") int id);
}
