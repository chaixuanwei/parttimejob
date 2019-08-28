package com.example.myapplication.frame;

import com.example.myapplication.login.bean.AuthCodeBean;
import com.example.myapplication.login.bean.LoginBean;
import com.example.myapplication.me.bean.PerfectBean;
import com.example.myapplication.me.bean.UploadTopBean;

import java.io.File;
import java.util.ArrayList;

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
                                        @Field("project_images") ArrayList<String> project);
    //获取个人资料
    @GET("api/user/profile/userInfo")
    Observable<PerfectBean> getPerfect();
}
