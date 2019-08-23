package com.example.myapplication.frame;

public interface ICommonView<T> {
    void onError(Throwable e);
    void onResponse(int whichApi, T... t);
}
