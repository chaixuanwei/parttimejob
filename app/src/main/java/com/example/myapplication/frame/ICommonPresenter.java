package com.example.myapplication.frame;

public interface ICommonPresenter<T> {
    void getData(int whichApi, T... t);
}
