package com.sxxh.linghuo.frame;

public interface ICommonModel<T> {
    void getData(ICommonView view, int whichApi, T... t);
}
