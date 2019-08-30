package com.example.myapplication.me.bean;

import java.util.List;

public class ScaleBean {
    /**
     * code : 1
     * msg : 获取成功!
     * data : ["0～20","20～50","50～100","100～500"]
     */

    private int code;
    private String msg;
    private List<String> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
