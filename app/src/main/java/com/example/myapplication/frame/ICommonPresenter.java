package com.example.myapplication.frame;

/**
 * Created by 任小龙 on 2019/3/29.
 */
public interface ICommonPresenter<T> {
    void getData(int whichApi, T... t);
}
