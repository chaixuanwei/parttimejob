package com.example.myapplication.frame;

public interface ICommonView<T> {
    void onError(int whichApi, Throwable e);
    void onResponse(int whichApi, T... t);
}
