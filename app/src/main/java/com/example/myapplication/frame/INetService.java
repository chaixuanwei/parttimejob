package com.example.myapplication.frame;

import com.example.myapplication.issus.bean.NatureBean;
import com.example.myapplication.login.bean.AuthCodeBean;
import com.example.myapplication.login.bean.LoginBean;
import com.example.myapplication.me.bean.ApplyPeopleBean;
import com.example.myapplication.me.bean.FeedBackBean;
import com.example.myapplication.me.bean.IndustryBean;
import com.example.myapplication.me.bean.MyIssusBean;
import com.example.myapplication.me.bean.PerfectBean;
import com.example.myapplication.me.bean.ProjectProgressBean;
import com.example.myapplication.me.bean.ProjectSufferBean;
import com.example.myapplication.me.bean.ScaleBean;
import com.example.myapplication.me.bean.UploadTopBean;
import com.example.myapplication.message.bean.AfficheBean;

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
                                         @Field("agreement") String cd);

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
                                       @Field("id_back") String id_back);

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
}
