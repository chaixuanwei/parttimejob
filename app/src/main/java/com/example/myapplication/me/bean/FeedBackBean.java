package com.example.myapplication.me.bean;

import java.util.List;

public class FeedBackBean {
    /**
     * code : 1
     * msg : 获取成功!
     * data : ["账号问题","支付问题","其他问题"]
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
