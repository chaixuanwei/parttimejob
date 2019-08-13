package com.example.myapplication.frame;

/**
 * Created by 任小龙 on 2019/3/29.
 */
public interface ICommonView<T> {
    void onError(Throwable e);
    void onResponse(int whichApi, T... t);
}
