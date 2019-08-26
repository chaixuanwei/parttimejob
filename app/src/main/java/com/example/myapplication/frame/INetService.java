package com.example.myapplication.frame;

import com.example.myapplication.login.bean.AuthCodeBean;
import com.example.myapplication.login.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface INetService {
    @POST("api/job/Sendsms")
    @FormUrlEncoded
    Observable<AuthCodeBean> getAuthCode(@Field("mobile") String mobile);

    @POST("api/user/public/register")
    @FormUrlEncoded
    Observable<AuthCodeBean> getRegister(@Field("username") String name,
                                         @Field("password") String password,
                                         @Field("mobile_code") String code,
                                         @Field("agreement") String cd);

    @POST("api/user/public/login")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@Field("username") String name,
                                   @Field("password") String password);

    @POST("api/user/public/passwordReset")
    @FormUrlEncoded
    Observable<AuthCodeBean> getFindPassWord(@Field("username") String name,
                                             @Field("password") String password,
                                             @Field("mobile_code") String code);
}
