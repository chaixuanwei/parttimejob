package com.sxxh.linghuo.frame;

public interface ICommonPresenter<T> {
    void getData(int whichApi, T... t);
}
